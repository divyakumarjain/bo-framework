import java.lang.Override;
import java.util.Collection;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.annotation.FromEntity;
import org.divy.common.bo.mapper.annotation.ToEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class SourceBOEntityMapStructMapper implements BOMapper<SourceBOEntity, SourceBOEntity> {
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

  @FromEntity
  @Mapping(
      target = ".",
      source = "source"
  )
  protected abstract SourceBOEntity doMapFromBO(SourceBOEntity source,
      @MappingTarget SourceBOEntity target);

  @ToEntity
  @Mapping(
      target = ".",
      source = "source"
  )
  protected abstract SourceBOEntity doMapToBO(SourceBOEntity source,
      @MappingTarget SourceBOEntity target);
}
