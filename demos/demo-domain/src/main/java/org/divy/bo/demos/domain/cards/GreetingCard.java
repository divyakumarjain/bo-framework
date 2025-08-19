package org.divy.bo.demos.domain.cards;

import org.divy.bo.demos.domain.cards.pages.Page;
import org.divy.common.bo.database.jpa.AbstractJPABusinessObject;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class GreetingCard extends AbstractJPABusinessObject {

    private List<Page> pages;

    public GreetingCard() {
        //noop
    }

    public GreetingCard(UUID uuid) {
        super(uuid);
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        GreetingCard that = (GreetingCard) o;
        return Objects.equals(getPages(), that.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPages());
    }
}
