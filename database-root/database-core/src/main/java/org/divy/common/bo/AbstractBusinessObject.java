package org.divy.common.bo;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AbstractBusinessObject implements BusinessObject<UUID> {
    protected UUID uuid;
    private OffsetDateTime createTimestamp;
    private OffsetDateTime lastUpdateTimestamp;
    private String type;


    protected AbstractBusinessObject() {
        uuid = UUID.randomUUID();
        createTimestamp = OffsetDateTime.now();
        lastUpdateTimestamp = OffsetDateTime .now();
        type = this.getClass().getSimpleName();
    }

    public AbstractBusinessObject(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID identity() {
        return uuid;
    }

    @Override
    public String _type() {
        return type;
    }

    @Override
    public void _type(String type) {
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public OffsetDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public OffsetDateTime  getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
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

        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    public void refreshLastUpdateTimestamp() {
        this.lastUpdateTimestamp = OffsetDateTime.now();
    }

    public void refreshCreateUpdateTimestamp() {
        this.createTimestamp = OffsetDateTime.now();
    }
}
