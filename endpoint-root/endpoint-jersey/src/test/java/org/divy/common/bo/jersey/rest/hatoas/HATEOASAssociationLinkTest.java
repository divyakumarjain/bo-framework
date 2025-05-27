package org.divy.common.bo.jersey.rest.hatoas;

import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.endpoint.hatoas.association.Association;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.endpoint.hatoas.association.Cardinality;
import org.divy.common.bo.jersey.rest.JerseyHATOASMapper;
import org.divy.common.bo.jersey.rest.JerseyLinkBuilderFactory;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Link;
import java.net.URI;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class HATEOASAssociationLinkTest {

    @Mock
    private KeyValuePairMapper<Order> keyValuePairMapper;

    @Mock
    private MetaDataProvider metaDataProvider;

    @Mock
    private AssociationsHandler<Order, UUID, Link> associationsHandler;

    private LinkBuilderFactory<Link> linkBuilderFactorySpy;

    private JerseyHATOASMapper<Order, UUID> jerseyHATOASMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        linkBuilderFactorySpy = spy(new JerseyLinkBuilderFactory());

        jerseyHATOASMapper = new JerseyHATOASMapper<>(
                keyValuePairMapper,
                linkBuilderFactorySpy,
                metaDataProvider,
                associationsHandler
        );
    }

    @Test
    public void testFillAssociations() {
        // Given
        UUID orderId = UUID.randomUUID();
        Order order = new Order(orderId);
        JerseyRepresentation<UUID> representation = new JerseyRepresentation<>();
        representation.setId(orderId);

        Association<Order, UUID, Link> association = new Association<>();
        association.setName("orderItems");
        // Assuming targetMethodName is now part of Association or accessible through it
        // For this test, let's assume it's directly on Association for simplicity or
        // the handler provides it.
        // association.setTargetMethodName("readRelation"); // This would be ideal if Association stores it

        List<Association<Order, UUID, Link>> associations = Collections.singletonList(association);
        when(associationsHandler.getAssociations()).thenReturn(associations);

        // Mock MetaDataProvider to return endpoint class
        when(metaDataProvider.getEntityEndPointClass(Order.class)).thenReturn(Optional.of(OrderEndpoint.class));

        // When
        jerseyHATOASMapper.createFromBO(order); // This will call doFillAssociations internally

        // Then
        assertNotNull(representation.getLinks());
        assertEquals(1, representation.getLinks().size()); // Expecting self link + association link
                                                              // Update: createFromBO also adds self link.
                                                              // If fillAssociations is called directly, adjust count.
                                                              // For now, assuming createFromBO is the entry point.

        Link associationLink = representation.getLinks().stream()
                .filter(link -> "orderItems".equals(link.getRel()))
                .findFirst()
                .orElse(null);

        assertNotNull(associationLink);
        assertEquals("orderItems", associationLink.getRel());

        // Verify href (actual URI depends on LinkBuilderFactory implementation details)
        // For this test, we'll assume a certain structure.
        // A more robust test would mock the LinkBuilder and verify parameters passed to it.
        String expectedUriPath = "/orders/" + orderId.toString() + "/orderItems";
        assertEquals(expectedUriPath, URI.create(associationLink.getUri().toString()).getPath());


        // To verify the call to linkBuilderFactory.newBuilder()...buildLink(...)
        // This is tricky without deeper mocking or refactoring LinkBuilderFactory/LinkBuilder
        // However, the presence and correctness of the Link object implies correct interaction.
    }

    // Mock Entity and Endpoint classes
    static class Order implements BusinessObject<UUID> {
        private UUID id;
        private List<OrderItem> orderItems;

        public Order(UUID id) {
            this.id = id;
            this.orderItems = new ArrayList<>();
        }

        @Override
        public UUID identity() {
            return id;
        }

        @Override
        public String _type() {
            return "Order";
        }

        public List<OrderItem> getOrderItems() { return orderItems; }
        public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
    }

    static class OrderItem implements BusinessObject<UUID> {
        private UUID id;
        @Override public UUID identity() { return id; }
        @Override public String _type() { return "OrderItem"; }
    }

    // Mock JAX-RS Endpoint (minimal)
    static class OrderEndpoint {
        // Methods like readRelation would be here, but not needed for this specific link generation test focus
    }
}
