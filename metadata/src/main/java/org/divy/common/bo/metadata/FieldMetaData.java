package org.divy.common.bo.metadata;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Objects;

public class FieldMetaData {
    Type type;
    boolean isCollection;
    String fieldName;

    public FieldMetaData(PropertyDescriptor pd) {
        this.fieldName = pd.getName();
        Type genericReturnType = pd.getReadMethod().getGenericReturnType();
        if(genericReturnType instanceof ParameterizedType && isCollection((ParameterizedType)genericReturnType)) {
            this.isCollection = true;
            type = ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];
        } else {
            type = genericReturnType;
            this.isCollection = false;
        }
    }

    public FieldMetaData(String fieldName, Class type, boolean isCollection) {
        this.fieldName = fieldName;
        this.type = type;
        this.isCollection = isCollection;
    }

    private boolean isCollection(ParameterizedType genericReturnType) {
        return Collection.class.isAssignableFrom((Class) genericReturnType.getRawType());
    }

    public boolean isCollection() {
        return isCollection;
    }

    public Type getType() {
        return type;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toString() {
        return "FieldMetaData{" +
                "type=" + type +
                ", isCollection=" + isCollection +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldMetaData that = (FieldMetaData) o;
        return isCollection == that.isCollection &&
                Objects.equals(type, that.type) &&
                Objects.equals(fieldName, that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, isCollection, fieldName);
    }
}
