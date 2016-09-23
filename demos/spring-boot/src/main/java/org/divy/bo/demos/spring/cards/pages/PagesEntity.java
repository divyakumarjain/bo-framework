package org.divy.bo.demos.spring.cards.pages;

import org.divy.common.bo.AbstractBusinessObject;
import org.divy.common.bo.IBusinessObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Entity
@XmlRootElement
public class PagesEntity extends AbstractBusinessObject {

    List<SectionEntity> sections;

    @Override
    public void update(IBusinessObject<UUID> entity) {
        if (entity instanceof PagesEntity) {
            this.setSections(((PagesEntity) entity).getSections());
        } else {
            throw new IllegalArgumentException("Expecting instance of GreetingCardEntity");
        }
    }

    @OneToMany
    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }
}
