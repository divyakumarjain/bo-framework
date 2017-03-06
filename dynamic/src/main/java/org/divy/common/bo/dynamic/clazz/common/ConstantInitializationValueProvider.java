package org.divy.common.bo.dynamic.clazz.common;

public class ConstantInitializationValueProvider implements DynamicInitializationValueProvider{

    private Object value;

    public ConstantInitializationValueProvider(Object value) {
        this.value = value;
    }

    @Override
    public String code() {
        return codeForValue(value);
    }

    private String codeForValue(Object value) {
        if(value instanceof String) {
            return "\"" + value + "\"";
        }
        else if(value instanceof Class){
            return ((Class) value).getName() + ".class";
        } else {
            throw new UnsupportedOperationException("Only String and Class Param are supported");
        }
    }
}
