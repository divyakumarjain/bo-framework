package org.divy.bo.demos.spring.cards.pages;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractBusinessObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Entity
@XmlRootElement
public class Page extends AbstractBusinessObject {

    List<Section> sections;

    @Override
    public void update(IBusinessObject<UUID> entity) {
        if (entity instanceof Page) {
            this.setSections(((Page) entity).getSections());
        } else {
            throw new IllegalArgumentException("Expecting instance of GreetingCard");
        }
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
