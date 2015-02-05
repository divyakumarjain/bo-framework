package org.divy.common.bo.endpoint.hypermedia;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.Set;

import javax.persistence.Id;
import javax.ws.rs.core.Link;

public abstract class AbstractRepresentation {

	private UUID uuid;

	private Set<Link> links;
	private Map<String,Link> associations = new HashMap<>();

	public java.util.UUID identity() {
		return uuid;
	}

	@Id
	@JsonProperty
	public java.util.UUID getUuid() {
		return uuid;
	}

	protected void setUuid(java.util.UUID uuid) {
		this.uuid = uuid;
	}

	public void addLink(Link link) {
		if(links==null) {
			links = new HashSet<Link>();
		}

		links.add(link);
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}


	public Map<String, Link> getAssociations() {
		return associations;
	}

	public void setAssociations(Map<String, Link> associations) {
		this.associations = associations;
	}

}
