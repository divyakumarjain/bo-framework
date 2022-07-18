package org.divy.common.bo.dynamic;

@ExistingAnnotation("ExistingValue")
public class BaseClass
{
    protected String attribute1;
    protected String attribute2;

    public BaseClass(String attribute1, String attribute2) {
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    protected void changeAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }

    protected void changeAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
}
