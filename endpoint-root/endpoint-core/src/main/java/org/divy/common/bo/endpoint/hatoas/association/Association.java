package org.divy.common.bo.endpoint.hatoas.association;

import org.divy.common.bo.dynamic.clazz.member.method.setter.Setter;
import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.member.method.reader.Reader;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Association<T extends BusinessObject<I>, I, L> {
    protected BOMapper<T, Representation<I, Map<String, Object>, L>> mapper;
    private   String                                                 name;
    private   Cardinality                 cardinality;
    private   List<PropagateSave>         propagateSaves;
    private   Reader                      reader;
    private   boolean                     includeInReadOperation;
    private   BOManager<T, I>             manager;
    private   Setter<T, ?>                setter;

    public Optional<Object> read(Object source, Object... argv) {

        return getReader().read(source, argv).map(value -> {
            if ( mapper != null) {
                if (value instanceof Collection) {
                    return mapper.createFromBO((Collection) value);
                } else if(value instanceof BusinessObject) {
                    return mapper.createFromBO( (T) value );
                } else {
                    throw new IllegalArgumentException("Not supported Type");
                }
                
            } else {
                return value;
            }
        });
    }

    public Optional<Object> create(Representation<I, Map<String, Object>,L> target ) {
        final var targetBo = mapper.createBO( target );

        BusinessObject<I> fromStore;

        if(targetBo.identity() == null) {
            fromStore = manager.create(targetBo);
        } else {
            fromStore = manager.get( targetBo.identity() )
                  .orElseGet( () -> manager.create( targetBo ) );
        }

        return Optional.ofNullable( fromStore );
    }

    private Setter<T, ?> getCreator()
    {
        return this.setter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }

    public List<PropagateSave> getPropagateSaves() {
        return propagateSaves;
    }

    public void setPropagateSaves(List<PropagateSave> propagateSaves) {
        this.propagateSaves = propagateSaves;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    protected <V> void setSetter( Setter<T, V> setter ) {
        this.setter = setter;
    }

    public boolean isIncludeInReadOperation() {
        return includeInReadOperation;
    }

    public void setIncludeInReadOperation(boolean includeInReadOperation) {
        this.includeInReadOperation = includeInReadOperation;
    }

    public BOManager<T, I> getManager()
    {
        return manager;
    }

    public void setManager(BOManager<T, I> manager)
    {
        this.manager = manager;
    }
}
