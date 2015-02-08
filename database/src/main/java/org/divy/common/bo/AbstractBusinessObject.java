package org.divy.common.bo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@uuid")
@Entity
public abstract class AbstractBusinessObject implements IBusinessObject<UUID>{

    private UUID uuid;
    private Calendar createTimestamp;
    private Calendar lastUpdateTimestamp;

    public AbstractBusinessObject() {
        uuid = UUID.randomUUID();
        createTimestamp = Calendar.getInstance();
        lastUpdateTimestamp = Calendar.getInstance();
    }

    public AbstractBusinessObject(AbstractBusinessObject entity) {
        this.setUuid(entity.getUuid());
        this.update(entity);
    }

    @Override
    public UUID identity() {
        return uuid;
    }

    @Id
    @JsonProperty
    public UUID getUuid() {
        return uuid;
    }

    protected void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Calendar getCreateTimestamp() {
        return createTimestamp;
    }

    public Calendar getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    void setCreateTimestamp(Calendar createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    void setLastUpdateTimestamp(Calendar lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractBusinessObject)) {
            return false;
        }

        AbstractBusinessObject that = (AbstractBusinessObject) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }
}
