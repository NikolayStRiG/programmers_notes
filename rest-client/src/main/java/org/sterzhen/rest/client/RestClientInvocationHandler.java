package org.sterzhen.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RestClientInvocationHandler<T> implements InvocationHandler {

    private final Class<T> restInterfaceClass;
    private String rootPath;
    private final Map<String, MethodDefinition> methodDefinitionMap = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient client;
    private final String address;

    public static<T> RestClientInvocationHandler<T> create(Class<T> restInterfaceClass, String address) {
        return create(restInterfaceClass, address, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build());
    }

    public static<T> RestClientInvocationHandler<T> create(Class<T> restInterfaceClass, String address, HttpClient client) {
        if (!restInterfaceClass.isInterface()) {
            throw new IllegalArgumentException("Not interface");
        }
        final RestClientInvocationHandler<T> instant = new RestClientInvocationHandler<>(restInterfaceClass, address, client);
        instant.init();
        return instant;
    }

    private RestClientInvocationHandler(Class<T> restInterfaceClass, String address, HttpClient client) {
        this.restInterfaceClass = restInterfaceClass;
        this.address = address;
        this.client = client;
    }

    private void init() {
        final Path rootPath = restInterfaceClass.getAnnotation(Path.class);
        if (rootPath == null) {
            throw new IllegalArgumentException("Not Path annotation");
        }
        this.rootPath = rootPath.value();

        for (Method m : restInterfaceClass.getMethods()) {
            final String name = m.getName();
            final Path path = m.getAnnotation(Path.class);
            final String getHttpMethod = m.getAnnotation(GET.class) != null ? HttpMethod.GET : null;

            final MethodDefinition definition = new MethodDefinition(name, getHttpMethod, path.value());
            definition.setOutParam(m.getReturnType());
            definition.setInParam(m.getParameterTypes());

            methodDefinitionMap.put(name, definition);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final MethodDefinition definition = methodDefinitionMap.get(method.getName());

        HttpRequest.Builder build = HttpRequest.newBuilder()
                .uri(URI.create(address + "/" + rootPath + getPath(method, args)))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", definition.getConsumesType());
        if (HttpMethod.GET.equals(definition.httpMethod)) {
            build = build.GET();
        }
        HttpRequest request = build.build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().isEmpty() ? null : objectMapper.readValue(response.body(), definition.getOutParam());
    }

    private String getPath(Method method, Object[] args) {
        final StringBuilder result = new StringBuilder();
        var ann = method.getParameterAnnotations();
        final String[] subString = methodDefinitionMap.get(method.getName()).path.split("/");
        for (String s : subString) {
            if (s.startsWith("{")) {
                final String paramName = s.substring(1, s.indexOf("}"));
                result.append("/").append(getParamValueByName(paramName, ann, args));
            } else {
                result.append("/").append(s);
            }
        }

        return result.toString();
    }

    private String getParamValueByName(String paramName, Annotation[][] ann, Object[] args) {

        for (int i = 0; i < ann.length; i++) {
            var paramAnnot = ann[i];
            for (var a : paramAnnot) {
                if (a instanceof PathParam && paramName.equals(((PathParam) a).value())) {
                    return args[i].toString();
                }
            }
        }
        return null;
    }

    private class MethodDefinition {

        private final String name;
        private final String httpMethod;
        private final String path;
        private String producesType = "application/json";
        private String consumesType = "application/json";
        private Class<?>[] inParam;
        private Class<?> outParam;

        private MethodDefinition(String name, String httpMethod, String path) {
            this.name = name;
            this.httpMethod = httpMethod;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public String getHttpMethod() {
            return httpMethod;
        }

        public String getPath() {
            return path;
        }

        public String getProducesType() {
            return producesType;
        }

        public void setProducesType(String producesType) {
            this.producesType = producesType;
        }

        public String getConsumesType() {
            return consumesType;
        }

        public void setConsumesType(String consumesType) {
            this.consumesType = consumesType;
        }

        public Class<?>[] getInParam() {
            return inParam;
        }

        public void setInParam(Class<?>[] inParam) {
            this.inParam = inParam;
        }

        public Class<?> getOutParam() {
            return outParam;
        }

        public void setOutParam(Class<?> outParam) {
            this.outParam = outParam;
        }
    }
}
