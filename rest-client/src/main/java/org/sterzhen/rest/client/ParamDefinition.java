package org.sterzhen.rest.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParamDefinition {

    private final Method method;
    private final String name;
    private final int index;
    private final Class<?> type;
    private final Annotation[] annotations;

    public ParamDefinition(Method method, String name, int index, Class<?> type, Annotation[] annotations) {
        this.method = method;
        this.name = name;
        this.index = index;
        this.type = type;
        this.annotations = annotations;
    }

    public Method getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public Class<?> getType() {
        return type;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }
}
