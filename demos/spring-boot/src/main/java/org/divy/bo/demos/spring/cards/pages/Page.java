package org.divy.bo.demos.spring.cards.pages;

import org.divy.common.bo.database.jpa.AbstractJPABusinessObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@XmlRootElement
public class Page extends AbstractJPABusinessObject {

    List<Section> sections;

    public Page() {
        //noop
    }

    public Page(UUID uuid) {
        super(uuid);
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Page page = (Page) o;
        return Objects.equals(getSections(), page.getSections());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSections());
    }
}
