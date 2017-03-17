package org.divy.common.bo.dynamic.clazz;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DynamicBuilderContext<P extends DynamicBuilderContext> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicBuilderContext.class);
    private static ClassPool pool;
    private P parentContext;

    public DynamicBuilderContext(P parentContext) {
        this.parentContext = parentContext;
    }

    protected Optional<CtClass> getCtClass(Class<?> type) {
        try {
            return Optional.of(getClassPool().getCtClass(type.getName()));
        } catch (NotFoundException e) {
            LOGGER.error("Could not get class for " + type.getName(), e);
            return Optional.empty();
        }
    }

    protected static ClassPool getClassPool() {
        if (pool == null) {
            pool = ClassPool.getDefault();
        }
        return pool;
    }

    public P and() {
        return parentContext;
    }

    public Optional<Class<?>> build() {
        return parentContext.build();
    }
}
