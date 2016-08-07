package org.divy.bo.demos.spring.greetings;

import org.divy.common.bo.AbstractBusinessObject;
import org.divy.common.bo.IBusinessObject;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Entity
@XmlRootElement
public class GreetingEntity extends AbstractBusinessObject {

    /**
     * update object with the copy
     *
     * @param entity
     */
    @Override
    public void update(IBusinessObject<UUID> entity) {
        if(entity instanceof GreetingEntity) {
            this.setGreeting(((GreetingEntity) entity).getGreeting());
        } else {
            throw new IllegalArgumentException("Expecting instance of Mock");
        }
    }

    private String greeting;

    /**
     * @return returns the greeting
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * @param greeting sets the greeting
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GreetingEntity)) return false;
        if (!super.equals(o)) return false;

        GreetingEntity that = (GreetingEntity) o;

        if (!getUuid().equals(that.getUuid())) return false;
        return getGreeting() != null ? getGreeting().equals(that.getGreeting()) : that.getGreeting() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUuid().hashCode();
        result = 31 * result + (getGreeting() != null ? getGreeting().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GreetingEntity{" +
                "greeting='" + greeting + '\'' +
                '}';
    }
}
