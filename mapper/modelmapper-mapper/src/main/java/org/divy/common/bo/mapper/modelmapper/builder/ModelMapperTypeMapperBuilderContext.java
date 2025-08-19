package org.divy.common.bo.mapper.modelmapper.builder;

import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.builder.AbstractTypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;
import org.divy.common.bo.mapper.builder.options.field.*;
import org.divy.common.bo.mapper.builder.options.type.ChildTypeMapperOption;
import org.divy.common.bo.mapper.defaults.AdvanceModelMapperBO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.OffsetDateTime;
import java.util.*;

public class ModelMapperTypeMapperBuilderContext<S, T> extends AbstractTypeMapperBuilderContext<S, T> {

    private final ModelMapperBuilder builder;

    ModelMapperTypeMapperBuilderContext(ModelMapperBuilder builder, Class<S> source, Class<T> target) {
        super(builder, source, target);
        this.builder = builder;
    }

    ModelMapperTypeMapperBuilderContext(ModelMapperBuilder builder, Class<S> source, Class<T> target, MapperBuilderOption... options) {
        super(builder, source, target);
        this.builder = builder;
        this.mapperBuilderOptions.addAll(Arrays.asList(options));
    }

    ModelMapperTypeMapperBuilderContext(ModelMapperBuilder builder, Class<S> source, Class<T> target, List<MapperBuilderOption> options) {
        super(builder, source, target);
        this.builder = builder;
        this.mapperBuilderOptions.addAll(options);
    }

    @Override
    public BOMapper<S, T> buildMapper() {
        return new AdvanceModelMapperBO<>(source, target, buildModelMapper());
    }

    public ModelMapper buildModelMapper() {
        ModelMapper mapper = new ModelMapper();
        
        // Configure for better compatibility with Orika behavior
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMethodAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PUBLIC);

        // Configure destination providers for interface types
        configureDestinationProviders(mapper);

        // Add custom converters similar to Orika setup
        addCustomConverters(mapper);
        
        // Configure POJO to Map conversion if target is Map
        configurePOJOToMapConversion(mapper);
        
        // Configure field mappings
        configureFieldMappings(mapper);
        
        // Process type-level options
        processTypeOptions(mapper);
        
        return mapper;
    }

    private void configurePOJOToMapConversion(ModelMapper mapper) {
        // We'll handle POJO to Map conversion in AbstractModelMapperBOMapper
        // to avoid recursion issues with ModelMapper's converter system
    }
    
    // Static utility methods for POJO to Map conversion
    public static Map<String, Object> convertObjectToMap(Object sourceObject) {
        return convertObjectToMap(sourceObject, 0);
    }
    
    private static Map<String, Object> convertObjectToMap(Object sourceObject, int depth) {
        if (sourceObject == null || depth > 10) { // Limit recursion depth to prevent stack overflow
            return new LinkedHashMap<>();
        }
        
        Map<String, Object> resultMap = new LinkedHashMap<>();
        
        try {
            // Use reflection to get all fields from the source object
            Class<?> sourceClass = sourceObject.getClass();
            java.lang.reflect.Field[] fields = sourceClass.getDeclaredFields();
            
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(sourceObject);
                
                if (value != null) {
                    // Recursively convert nested objects to Maps if needed
                    if (isSimpleType(value.getClass())) {
                        resultMap.put(field.getName(), value);
                    } else if (value instanceof Collection) {
                        resultMap.put(field.getName(), convertCollection((Collection<?>) value, depth + 1));
                    } else {
                        // Convert nested objects to Maps as well
                        resultMap.put(field.getName(), convertObjectToMap(value, depth + 1));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to convert POJO to Map", e);
        }
        
        return resultMap;
    }
    
    private static boolean isSimpleType(Class<?> type) {
        return type.isPrimitive() || 
               type == String.class ||
               type == Integer.class ||
               type == Long.class ||
               type == Double.class ||
               type == Float.class ||
               type == Boolean.class ||
               type == java.util.UUID.class ||
               type == java.time.OffsetDateTime.class ||
               type == java.time.LocalDateTime.class ||
               type == java.util.Date.class ||
               Number.class.isAssignableFrom(type);
    }
    
    private static Object convertCollection(Collection<?> collection, int depth) {
        List<Object> convertedList = new ArrayList<>();
        for (Object item : collection) {
            if (item != null) {
                if (isSimpleType(item.getClass())) {
                    convertedList.add(item);
                } else {
                    convertedList.add(convertObjectToMap(item, depth));
                }
            }
        }
        return convertedList;
    }

    private void configureDestinationProviders(ModelMapper mapper) {
        // Create explicit type map from source to Map interface using LinkedHashMap
        if (target == Map.class) {
            mapper.createTypeMap(source, LinkedHashMap.class);
            
            // Configure ModelMapper to use LinkedHashMap for Map interface mappings
            mapper.getConfiguration().setProvider(request -> {
                if (Map.class.isAssignableFrom(request.getRequestedType())) {
                    return new LinkedHashMap<>();
                }
                return null;
            });
        }
    }

    private void addCustomConverters(ModelMapper mapper) {
        // UUID converter
        mapper.addConverter(context -> {
            if (context.getSource() == null) return null;
            return UUID.fromString((String) context.getSource());
        }, String.class, UUID.class);

        mapper.addConverter(context -> {
            if (context.getSource() == null) return null;
            return ((UUID) context.getSource()).toString();
        }, UUID.class, String.class);

        // OffsetDateTime converter
        mapper.addConverter(context -> {
            if (context.getSource() == null) return null;
            return OffsetDateTime.parse((String) context.getSource());
        }, String.class, OffsetDateTime.class);

        mapper.addConverter(context -> {
            if (context.getSource() == null) return null;
            return ((OffsetDateTime) context.getSource()).toString();
        }, OffsetDateTime.class, String.class);
    }

    private void configureFieldMappings(ModelMapper mapper) {
        if (fields.isEmpty()) {
            return;
        }

        // Configure field exclusions using property condition to avoid Java 23 compatibility issues
        Set<String> excludedFields = getExcludedFields();
        if (!excludedFields.isEmpty()) {
            mapper.getConfiguration().setPropertyCondition(context -> {
                String propertyName = context.getMapping().getLastDestinationProperty().getName();
                return !excludedFields.contains(propertyName);
            });
        }
    }

    private Set<String> getExcludedFields() {
        Set<String> excludedFields = new HashSet<>();
        fields.forEach((fieldName, context) -> {
            List<MapperBuilderOption> options = context.getFieldMapperBuilderOptions();
            if (hasOption(options, FieldExclude.class)) {
                excludedFields.add(fieldName);
            }
        });
        return excludedFields;
    }

    private void processTypeOptions(ModelMapper mapper) {
        mapperBuilderOptions.forEach(option -> {
            if (option instanceof ChildTypeMapperOption) {
                // Handle child type mappings - would need recursive registration
                // Similar to Orika's approach but adapted for ModelMapper
            }
        });
    }

    private <T> boolean hasOption(List<MapperBuilderOption> options, Class<T> optionClass) {
        return options.stream().anyMatch(optionClass::isInstance);
    }

    private <T> Optional<T> getOption(List<MapperBuilderOption> options, Class<T> optionClass) {
        return options.stream()
                .filter(optionClass::isInstance)
                .map(optionClass::cast)
                .findFirst();
    }

    public ModelMapper buildMapperFacade() {
        return buildModelMapper();
    }
}
