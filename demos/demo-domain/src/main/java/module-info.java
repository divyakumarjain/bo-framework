module bo.framework.demo.domain {
    requires bo.framework.database.jpa;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires bo.framework.repository;
    requires javax.persistence;
    opens org.divy.bo.demos.domain.greetings;
    opens org.divy.bo.demos.domain.cards.pages;

    exports org.divy.bo.demos.domain.cards.pages;
    exports org.divy.bo.demos.domain.cards;
    exports org.divy.bo.demos.domain.greetings;
}
