package org.sterzhen.rest.client;

import java.lang.reflect.Method;

public interface MethodDefinitionFactory {

    MethodDefinition create(Method method);
}
