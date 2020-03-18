package org.sterzhen.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.Path;
import java.lang.reflect.Method;

public class MethodDefinitionFactoryImpl implements MethodDefinitionFactory {


    @Override
    public MethodDefinition create(Method method) {
        final MethodDefinition definition = createDefinition(method);
        definition.setOutParam(method.getReturnType());
        definition.setInParam(method.getParameterTypes());
        definition.setParamAnnotation(method.getParameterAnnotations());
        return definition;
    }

    private MethodDefinition createDefinition(Method method) {
        final String name = method.getName();
        final Path path = method.getAnnotation(Path.class);
        if (method.getAnnotation(GET.class) != null) {
            return new GetMethodDefinition(name, path.value());
        }
        throw new NotSupportedException();
    }
}
