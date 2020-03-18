package org.sterzhen.rest.client;

import javax.ws.rs.PathParam;
import java.lang.annotation.Annotation;
import java.net.http.HttpRequest;

abstract class MethodDefinition {

    protected final String name;
    protected final String path;

    protected String producesType = "application/json";
    protected String consumesType = "application/json";
    protected Class<?>[] inParam;
    protected Class<?> outParam;
    protected Annotation[][] paramAnnotation;

    protected MethodDefinition(String name, String path) {
        this.name = name;
        this.path = path;
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

    public void setParamAnnotation(Annotation[][] paramAnnotation) {
        this.paramAnnotation = paramAnnotation;
    }

    public abstract HttpRequest buildHttpRequest(final String address, final String rootPath, final Object[] args);

    protected String getPath(Object[] args) {
        final StringBuilder result = new StringBuilder();

        final String[] subString = path.split("/");
        for (String s : subString) {
            if (s.startsWith("{")) {
                final String paramName = s.substring(1, s.indexOf("}"));
                result.append("/").append(getParamValueByName(paramName, args));
            } else {
                result.append("/").append(s);
            }
        }

        return result.toString();
    }

    private String getParamValueByName(String paramName, Object[] args) {

        for (int i = 0; i < paramAnnotation.length; i++) {
            var paramAnnot = paramAnnotation[i];
            for (var a : paramAnnot) {
                if (a instanceof PathParam && paramName.equals(((PathParam) a).value())) {
                    return args[i].toString();
                }
            }
        }
        return null;
    }
}
