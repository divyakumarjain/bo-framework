package org.divy.common.bo.spring.repository;

import org.divy.common.bo.database.jpa.EntityManagerCommandContext;
import org.divy.common.bo.database.jpa.context.DatabaseContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    EntityManagerCommandContext entityManagerCommandContext(@Value("${persistentUnitName}") String persistentUnitName) {
        return new DatabaseContext(persistentUnitName);
    }
}
