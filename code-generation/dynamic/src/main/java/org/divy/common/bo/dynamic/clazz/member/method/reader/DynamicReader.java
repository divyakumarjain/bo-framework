package org.divy.common.bo.dynamic.clazz.member.method.reader;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;

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

        return (S) ((new MethodRecorder(sourceClass)).newInstance());
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

    public class MethodRecorder implements InvocationHandler
    {
        private final Class loaded;

        public MethodRecorder(Class sourceClass) {

            DynamicType.Builder subclass;
            if(sourceClass.isInterface()) {
                subclass = new ByteBuddy().subclass( Object.class ).implement( sourceClass )
                .method(any())
                .intercept( InvocationHandlerAdapter.of( this ) );
            } else {
                subclass = new ByteBuddy().subclass( sourceClass );
            }

            loaded = subclass
                  .make()
                  .load( this.getClass()
                        .getClassLoader(), ClassLoadingStrategy.Default.INJECTION )
                  .getLoaded();

        }

        public Object newInstance() {
            try
            {
                return loaded.getDeclaredConstructor()
                      .newInstance();
            }
            catch( InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex )
            {
                throw new RuntimeException("Could not create instance ", ex );
            }
        }

        @Override
        public Object invoke(Object source, Method method, Object[] args) {

            DynamicReader.this.addMethod(method);

            Class<?> returnType = method.getReturnType();

            if(!Void.TYPE.equals( returnType ) ) {
                return new MethodRecorder(returnType).newInstance();
            }

            return null;
        }
    }
}
