package org.divy.common.bo.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.divy.common.bo.IBusinessObject;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@uuid")
@MappedSuperclass
public abstract class AbstractBusinessObject implements IBusinessObject<UUID> {

    private UUID uuid;
    private LocalDateTime createTimestamp;
    private LocalDateTime lastUpdateTimestamp;

    public AbstractBusinessObject() {
        uuid = UUID.randomUUID();
        createTimestamp = LocalDateTime.now();
        lastUpdateTimestamp = LocalDateTime.now();
    }

    public AbstractBusinessObject(AbstractBusinessObject entity) {
        this.uuid = entity.uuid;
        this.update(entity);
    }

    public AbstractBusinessObject(UUID uuid) {
        this.uuid = uuid;
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public LocalDateTime getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
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
