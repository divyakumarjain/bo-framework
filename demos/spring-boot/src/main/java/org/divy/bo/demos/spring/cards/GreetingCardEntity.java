package org.divy.bo.demos.spring.cards;

import org.divy.bo.demos.spring.cards.pages.PagesEntity;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractBusinessObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Entity
@XmlRootElement
public class GreetingCardEntity extends AbstractBusinessObject {

    List<PagesEntity> pages;

    @OneToMany
    public List<PagesEntity> getPages() {
        return pages;
    }

    public void setPages(List<PagesEntity> pages) {
        this.pages = pages;
    }

    @Override
    public void update(IBusinessObject<UUID> entity) {
        if(entity instanceof GreetingCardEntity) {
            this.setPages(((GreetingCardEntity) entity).getPages());
        } else {
            throw new IllegalArgumentException("Expecting instance of GreetingCardEntity");
        }
    }


}
