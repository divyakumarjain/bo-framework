package org.divy.common.bo.endpoint.json.test;

import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.rest.LinkBuilderFactory;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.UUID;

/**
 *
 *
 */
@Path("/mock")
public class MockBoEndpoint extends AbstractBOEndpoint<MockEntity, UUID> {
    @Inject
    public MockBoEndpoint(IBOManager<MockEntity, UUID> manager, LinkBuilderFactory linkBuilderFactory) {
        super(manager, linkBuilderFactory);
    }
}
