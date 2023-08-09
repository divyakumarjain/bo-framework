package org.divy.common.bo.mapper.annotation;

import org.divy.common.bo.api.BOEntity;
import org.divy.common.bo.api.Identity;

@BOEntity
public class MockBOEntity {
    @Identity
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setAge(int age) {
        this.age = age;
    }
}
