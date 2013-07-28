package org.divy.common.bo.presentation.navigation.defaults;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.divy.common.bo.presentation.navigation.IApplicationSection;


@Path("/site")
@Produces({ MediaType.APPLICATION_JSON})
public class ApplicationNavigationSectionService {
	
	@Inject
	public ApplicationNavigationSectionService(Set<IApplicationSection> applicationSection){
		this.applicationSection = applicationSection;
	}

	private Collection<IApplicationSection> applicationSection;

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	public Collection<IApplicationSection> getSiteMap() {
		return applicationSection;
	}

	public void setSiteMap(Collection<IApplicationSection> applicationSection) {
		this.applicationSection = applicationSection;
	}
}
