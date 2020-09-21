package org.divy.common.bo.microprofile.repository.extensions;

import org.divy.common.bo.database.command.CommandProvider;
import org.divy.common.bo.database.jpa.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.database.jpa.defaults.DefaultDatabaseRepository;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.repository.BORepository;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;
import java.util.Optional;

public class RepositoryBeansExtension implements Extension {

    BeanNamingStrategy beanNamingStrategy;

    public void afterBean(final @Observes AfterBeanDiscovery afterBeanDiscovery, BeanManager beanManager) {


        resolveBeanRef( "entityMetaDataProvider", MetaDataProvider.class, beanManager )
        .ifPresent( metaDataProvider -> {
            metaDataProvider.getEntityTypes().forEach( entity -> {
                afterBeanDiscovery
                      .addBean()
                      .scope( ApplicationScoped.class )
                      .types( DefaultDBCommandProvider.class )
                      .id( beanNamingStrategy.calculateCommandProviderId( entity ) )
                      .injectionPoints(  )
            } );
        } );




        resolveBeanRef( beanNamingStrategy.cal )

        resolveBeanRef(beanNamingStrategy.calculateCommandProviderId( (Class<? extends BusinessObject>) this.getClass() ), CommandProvider.class, beanManager)
              .ifPresent(commandProvider ->  {
                  afterBeanDiscovery
                        .addBean()
                        .scope(ApplicationScoped.class)
                        .types( BORepository.class)
                        .id("Created by " + RepositoryBeansExtension.class)
                        .types(DefaultDatabaseRepository.class)
                        .createWith( context -> new DefaultDatabaseRepository(commandProvider) );
        });
    }

    private <T> Optional<T> resolveBeanRef( String beanName, Class<T> type, BeanManager beanManager )
    {
        return (Optional<T>) beanManager
              .getBeans( beanName )
              .stream()
              .findAny()
              .map( bean -> beanManager.getReference(bean, type, beanManager.createCreationalContext( bean )) );
    }
}
