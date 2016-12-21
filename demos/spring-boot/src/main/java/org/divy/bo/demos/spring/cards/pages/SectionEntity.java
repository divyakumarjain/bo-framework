package org.divy.bo.demos.spring.cards.pages;

import org.divy.bo.demos.spring.greetings.GreetingEntity;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractBusinessObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Entity
@XmlRootElement
public class SectionEntity extends AbstractBusinessObject {
    private List<GreetingEntity> greetings;

    @Override
    public void update(IBusinessObject<UUID> entity) {
        if (entity instanceof SectionEntity) {
            this.setGreetings(((SectionEntity) entity).getGreetings());
        } else {
            throw new IllegalArgumentException("Expecting instance of GreetingCardEntity");
        }
    }

    @OneToMany
    public List<GreetingEntity> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<GreetingEntity> greetings) {
        this.greetings = greetings;
    }
}
