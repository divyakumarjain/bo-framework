
import org.divy.common.bo.api.BOEntity;
import org.divy.common.bo.api.Identity;

import java.time.OffsetDateTime;
import java.util.UUID;

@BOEntity
public class SourceBOEntity {

    @Identity
    private int id;
    private String name;
    private int age;

    private OffsetDateTime lastUpdateTimestamp;

    private OffsetDateTime createTimestamp;

    private UUID uuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public OffsetDateTime getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(OffsetDateTime lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public OffsetDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(OffsetDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}