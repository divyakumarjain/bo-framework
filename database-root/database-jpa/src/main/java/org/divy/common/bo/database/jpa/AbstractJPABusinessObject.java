package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.AbstractBusinessObject;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractJPABusinessObject extends AbstractBusinessObject {

    protected AbstractJPABusinessObject(UUID uuid) {
        super(uuid);
    }

    protected AbstractJPABusinessObject() {
    }

    @Id
    @Override
    public UUID getUuid() {
        return uuid;
    }
}
