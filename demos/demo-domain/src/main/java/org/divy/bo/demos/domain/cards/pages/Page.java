package org.divy.bo.demos.domain.cards.pages;

import org.divy.common.bo.database.jpa.AbstractJPABusinessObject;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Page extends AbstractJPABusinessObject {

    private List<Section> sections;

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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        var page = (Page) o;
        return Objects.equals(getSections(), page.getSections());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSections());
    }
}
