import java.lang.Override;
import java.util.Collection;
import org.divy.common.bo.mapper.BOMapper;

public class SourceBOEntityMapper implements BOMapper<SourceBOEntity, SourceBOEntity> {
  @Override
  public SourceBOEntity createBO(SourceBOEntity source) {
    var target = new SourceBOEntity();
    return mapToBO(source,target);
  }

  @Override
  public SourceBOEntity createFromBO(SourceBOEntity source) {
    var target = new SourceBOEntity();
    return mapFromBO(source,target);
  }

  @Override
  public Collection<SourceBOEntity> createFromBO(Collection<SourceBOEntity> source) {
    return source.stream().map(this::createFromBO).collect(java.util.stream.Collectors.toList());
  }

  @Override
  public Collection<SourceBOEntity> createBO(Collection<SourceBOEntity> source) {
    return source.stream().map(this::createBO).collect(java.util.stream.Collectors.toList());
  }

  @Override
  public SourceBOEntity mapFromBO(SourceBOEntity source, SourceBOEntity target) {
    doMapFromBO(source,target);
    return target;
  }

  @Override
  public SourceBOEntity mapToBO(SourceBOEntity source, SourceBOEntity target) {
    doMapToBO(source,target);
    return target;
  }

  private SourceBOEntity doMapFromBO(SourceBOEntity source, SourceBOEntity target) {
    target.setId(source.getId());
    target.setName(source.getName());
    target.setAge(source.getAge());
    target.setLastUpdateTimestamp(source.getLastUpdateTimestamp());
    target.setCreateTimestamp(source.getCreateTimestamp());
    target.setUuid(source.getUuid());
    return target;
  }

  private SourceBOEntity doMapToBO(SourceBOEntity source, SourceBOEntity target) {
    target.setId(source.getId());
    target.setName(source.getName());
    target.setAge(source.getAge());
    target.setLastUpdateTimestamp(source.getLastUpdateTimestamp());
    target.setCreateTimestamp(source.getCreateTimestamp());
    target.setUuid(source.getUuid());
    return target;
  }
}
