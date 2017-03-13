package org.divy.common.bo.database.jpa;

import org.divy.common.bo.AbstractBusinessObject;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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
