package org.divy.bo.demos.spring.cards;

import org.divy.bo.demos.spring.cards.pages.Page;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractBusinessObject;
import org.dozer.Mapping;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Entity
@XmlRootElement
public class GreetingCard extends AbstractBusinessObject {

    List<Page> pages;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public void update(IBusinessObject<UUID> entity) {
        if(entity instanceof GreetingCard) {
            this.setPages(((GreetingCard) entity).getPages());
        } else {
            throw new IllegalArgumentException("Expecting instance of GreetingCard");
        }
    }


}
