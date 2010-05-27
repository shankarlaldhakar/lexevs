package org.lexevs.dao.database.service.event.entity;

import org.LexGrid.concepts.Entity;

public class EntityInsertOrRemoveEvent {
	private String codingSchemeUri;
	private String version;
	private Entity entity;
	
	public EntityInsertOrRemoveEvent() {
		super();
	}
	
	public EntityInsertOrRemoveEvent(String codingSchemeUri, String version, Entity entity) {
		this.codingSchemeUri = codingSchemeUri;
		this.version = version;
		this.entity = entity;
	}

	public String getCodingSchemeUri() {
		return this.codingSchemeUri;
	}
	
	public void setCodingSchemeUri(String codingSchemeUri) {
		this.codingSchemeUri = codingSchemeUri;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}
