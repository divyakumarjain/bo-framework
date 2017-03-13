package org.divy.bo.demos.spring.greetings;

import org.divy.common.bo.database.jpa.AbstractJPABusinessObject;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Entity
@XmlRootElement
public class Greeting extends AbstractJPABusinessObject {

    private String greetingMessage;


    public Greeting() {
        //noop
    }

    public Greeting(UUID uuid) {
        super(uuid);
    }

    /**
     * @return returns the greeting
     */
    public String getGreetingMessage() {
        return greetingMessage;
    }

    /**
     * @param greetingMessage sets the greeting
     */
    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Greeting)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Greeting that = (Greeting) o;

        if (!getUuid().equals(that.getUuid())) {
            return false;
        }
        return getGreetingMessage() != null ? getGreetingMessage().equals(that.getGreetingMessage()) : that.getGreetingMessage() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUuid().hashCode();
        result = 31 * result + (getGreetingMessage() != null ? getGreetingMessage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "greeting='" + greetingMessage + '\'' +
                '}';
    }
}
