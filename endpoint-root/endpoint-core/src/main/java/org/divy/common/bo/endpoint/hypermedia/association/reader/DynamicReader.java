package org.divy.common.bo.endpoint.hypermedia.association.reader;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.divy.common.bo.endpoint.hypermedia.association.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class DynamicReader implements Reader {
    private List<Method> methods;

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicReader.class);

    protected void addMethod(Method method) {
        getMethods().add(method);
    }

    @Override
    public Optional<Object> read(Object source, Object ... argv) {

        Object result = source;
        try {
            for (Method method : methods) {
                result = method.invoke(result,argv);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOGGER.error("Could not read value using reader", ex);
            return Optional.empty();
        }

        return Optional.ofNullable(result);
    }

    public <S> S withMethodOn(Class<S> sourceClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(sourceClass);
        enhancer.setCallback(new MethodRecorder());

        return (S) enhancer.create();
    }

    public List<Method> getMethods() {
        if(methods == null) {
            methods = new ArrayList<>();
        }
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    class MethodRecorder implements InvocationHandler {

        @Override
        public Object invoke(Object source, Method method, Object[] args)
                throws Throwable {

            DynamicReader.this.addMethod(method);

            Enhancer enhancer = new Enhancer();
            if(!Void.TYPE.equals(method.getReturnType()) ) {
                enhancer.setSuperclass(method.getReturnType());
            }
            enhancer.setCallback(new MethodRecorder());
            return enhancer.create();
        }
    }
}
