package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.AbstractBusinessObject;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractJPABusinessObject extends AbstractBusinessObject {

    public AbstractJPABusinessObject(UUID uuid) {
        super(uuid);
    }

    public AbstractJPABusinessObject() {
    }

    @Id
    @Override
    public UUID getUuid() {
        return uuid;
    }
}
