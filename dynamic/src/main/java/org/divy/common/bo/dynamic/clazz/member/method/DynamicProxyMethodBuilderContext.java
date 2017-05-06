package org.divy.common.bo.dynamic.clazz.member.method;

import javassist.*;
import org.divy.common.bo.dynamic.clazz.DynamicSubClassBuilderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicProxyMethodBuilderContext
        extends DynamicMethodBuilderContext<DynamicProxyMethodBuilderContext, DynamicSubClassBuilderContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicProxyMethodBuilderContext.class);
    private CtMethod methodTobeProxied;

    public DynamicProxyMethodBuilderContext(DynamicSubClassBuilderContext builderContext
            , CtMethod methodTobeProxied ) {
        super(builderContext);
        name(methodTobeProxied.getName());
        this.methodTobeProxied = methodTobeProxied;
        copyParameters(methodTobeProxied);
    }

    public DynamicProxyMethodBuilderContext(DynamicSubClassBuilderContext dynamicSubClassBuilderContext) {
        super(dynamicSubClassBuilderContext);
    }

    private void copyParameters(CtMethod methodTobeProxied) {
        name(methodTobeProxied.getName());
        try {
            CtClass[] parameterTypes = methodTobeProxied .getParameterTypes();

            for(int paramIndex =0; paramIndex< parameterTypes.length; paramIndex++) {
                this.parameters.add(paramIndex, new DynamicMethodParamBuilderContext<>(this, parameterTypes[paramIndex], paramIndex));
            }
        } catch (NotFoundException e) {
            LOGGER.error("Could not copy parameters of method {}",methodTobeProxied.getLongName(), e);
        }
    }


    @Override
    public void doBuild(CtClass newClass) {
        if(methodTobeProxied!=null) {
            CtMethod proxyBehaviour = null;
            try {
                proxyBehaviour = CtNewMethod.make(Modifier.PUBLIC
                        , methodTobeProxied.getReturnType()
                        , this.memberName
                        , getParameters()
                        , methodTobeProxied.getExceptionTypes()
                        , prepareProxyBody()
                        , newClass);

                newClass.addMethod(proxyBehaviour);
            } catch (CannotCompileException | NotFoundException e) {
                LOGGER.error("Could not build the method {} in class {} ", methodTobeProxied.getName(), newClass.getName(), e);
            }

            doBuild(proxyBehaviour);
        } else {
            LOGGER.warn("Method to be Proxied is not mentioned seems to be a dynamic API usage error.");
        }
    }

    private String prepareProxyBody() throws NotFoundException {
        return "{ return (("+methodTobeProxied.getDeclaringClass().getName()+") super)."
                + this.methodTobeProxied.getName()
                + "("
                + getParamCode()
                +");"
                + "}";
    }

    private String getParamCode() throws NotFoundException {
        CtClass[] parameterTypes = methodTobeProxied.getParameterTypes();
        if(parameterTypes.length==0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder(parameterTypes.length*3);
            builder.append("(")
                    .append(parameterTypes[0].getName())
                    .append(")$1");
            for (int i = 2; i <=parameterTypes.length; i++) {
                builder.append(",(")
                        .append(parameterTypes[i-1].getName())
                        .append(")$").append(i);
            }
            return builder.toString();
        }
    }

    public DynamicMethodParamBuilderContext<DynamicProxyMethodBuilderContext> param() {
        return super.parameters.get(paramCount++);
    }
}
