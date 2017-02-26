package org.divy.bo.demos.spring.cards.pages;

import org.divy.bo.demos.spring.greetings.Greeting;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractBusinessObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Entity
@XmlRootElement
public class Section extends AbstractBusinessObject {
    private List<Greeting> greetings;

    public Section() {
    }

    public Section(UUID uuid) {
        super(uuid);
    }

    @Override
    public void update(IBusinessObject<UUID> entity) {
        if (entity instanceof Section) {
            this.setGreetings(((Section) entity).getGreetings());
        } else {
            throw new IllegalArgumentException("Expecting instance of GreetingCard");
        }
    }

    @OneToMany
    public List<Greeting> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }
}
