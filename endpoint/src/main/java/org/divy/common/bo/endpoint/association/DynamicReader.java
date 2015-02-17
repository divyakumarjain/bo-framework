package org.divy.common.bo.endpoint.association;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class DynamicReader extends ReaderBuilder {
    private List<Method> methods;

	protected void addMethod(Method method) {
		getMethods().add(method);
	}

    @Override
    public Object read(Object source, Object ... argv) throws InvocationTargetException, IllegalAccessException {

    	Object result = source;
    	for (Method method : methods) {
    		result = method.invoke(source,argv);
		}
        if(mapper != null) {
            return mapper.createFromBO(result);
        } else {
            return result;
        }
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
            enhancer.setSuperclass(method.getReturnType());
            enhancer.setCallback(new MethodRecorder());
            return enhancer.create();
        }
    }
}
