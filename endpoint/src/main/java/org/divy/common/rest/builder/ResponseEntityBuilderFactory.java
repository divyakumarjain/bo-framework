package org.divy.common.rest.builder;

import java.util.List;

import org.divy.common.bo.IBusinessObject;


public class ResponseEntityBuilderFactory<T> {

        /* Builder static methods*/

    public static <T extends IBusinessObject> CreateEntityResponseBuilder<T> create(T entity){
        return new CreateEntityResponseBuilder(entity);
    }

    public static <T extends IBusinessObject> UpdateEntityResponseBuilder<T> update(){
        return new UpdateEntityResponseBuilder();
    }

    public static <T extends IBusinessObject> DeleteEntityResponseBuilder<T> delete(){
        return new DeleteEntityResponseBuilder();
    }

    public static <T extends IBusinessObject> ReadEntityResponseBuilder<T> read(T entity){
        return new ReadEntityResponseBuilder<T>(entity);
    }



    public static <T extends IBusinessObject> ResponseEntityBuilder<List<T>> list(List<T> list) {
        return new ListEntityResponseBuilder<T>(list);
    }
}
