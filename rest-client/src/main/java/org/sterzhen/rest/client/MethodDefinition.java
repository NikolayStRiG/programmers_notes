package org.sterzhen.rest.client;

import java.lang.annotation.Annotation;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class MethodDefinition {

    protected final String name;
    protected final String path;

    private final Map<String, ParamDefinition> paramDefinitionMap = new HashMap<>();

    protected String producesType = "application/json";
    protected String consumesType = "application/json";
    protected Class<?> outParam;
    protected Annotation[][] paramAnnotation;

    protected MethodDefinition(String name, String path) {
        this.name = name;
        this.path = path;
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
        String result = path.isEmpty() || path.startsWith("/") ? path : "/" + path;
        for (ParamDefinition d : paramDefinitionMap.values()) {
            result = result.replaceFirst("\\{" + d.getName() + "}", args[d.getIndex()].toString());
        }
        return result;
    }

    public void addParamDefinitions(List<ParamDefinition> paramDefinitions) {
        paramDefinitions.forEach(d -> paramDefinitionMap.put(d.getName(), d));
    }
}
