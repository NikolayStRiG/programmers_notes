package org.sterzhen.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

public class MethodDefinitionFactoryImpl implements MethodDefinitionFactory {

    private final ObjectMapper objectMapper;

    public MethodDefinitionFactoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public MethodDefinition create(Method method) {
        final MethodDefinition definition = createDefinition(method);
        definition.setOutParam(method.getReturnType());
        definition.setParamAnnotation(method.getParameterAnnotations());
        definition.addParamDefinitions(createParamDefinitions(method));
        return definition;
    }

    private List<ParamDefinition> createParamDefinitions(Method method) {
        final var defList = new ArrayList<ParamDefinition>();
        final var annotations = method.getParameterAnnotations();
        final var types = method.getParameterTypes();

        for (int i = 0; i < types.length; i++) {
            var annot = annotations[i];
            var type = types[i];

            for (var a : annot) {
                if (a instanceof PathParam) {
                    final var def = new ParamDefinition(method, ((PathParam) a).value(), i, type, annot);
                    defList.add(def);
                }
            }
        }
        return defList;
    }

    private MethodDefinition createDefinition(Method method) {
        final String name = method.getName();
        final Path path = method.getAnnotation(Path.class);
        if (method.getAnnotation(GET.class) != null) {
            return new GetMethodDefinition(name, path.value());
        }
        if (method.getAnnotation(POST.class) != null) {
            return new PostMethodDefinition(name, path.value(), objectMapper);
        }
        if (method.getAnnotation(PUT.class) != null) {
            return new PutMethodDefinition(name, path.value(), objectMapper);
        }
        if (method.getAnnotation(DELETE.class) != null) {
            return new DeleteMethodDefinition(name, path.value());
        }
        return new MethodDefinition(name, path.value()) {
            @Override
            public HttpRequest buildHttpRequest(String address, String rootPath, Object[] args) {
                throw new NotSupportedException();
            }
        };
    }
}
