package org.divy.bo.demos.spring.mvc.cards.pages;

import org.divy.bo.demos.spring.mvc.greetings.Greeting;
import org.divy.common.bo.database.jpa.AbstractJPABusinessObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Section extends AbstractJPABusinessObject {
    private List<Greeting> greetings;

    public Section() {
        //noop
    }

    public Section(UUID uuid) {
        super(uuid);
    }

    @OneToMany
    public List<Greeting> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Section section = (Section) o;
        return Objects.equals(getGreetings(), section.getGreetings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGreetings());
    }
}
