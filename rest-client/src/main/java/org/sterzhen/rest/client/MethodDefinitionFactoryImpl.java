package org.sterzhen.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodDefinitionFactoryImpl implements MethodDefinitionFactory {


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
        throw new NotSupportedException();
    }
}
