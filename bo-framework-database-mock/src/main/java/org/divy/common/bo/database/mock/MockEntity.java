package org.divy.common.bo.database.mock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import org.divy.common.bo.IBusinessObject;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@JsonAutoDetect(fieldVisibility=Visibility.NONE,getterVisibility=Visibility.NONE)
public class MockEntity implements IBusinessObject<String> {

	public MockEntity() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.IBusinessObject#identity()
	 */
	@Override
	public String identity() {
		return getUuid();
	}

	private String uuid;

	private String name;

	private MockEntity parentEntity;


	// @JsonManagedReference
	private List<MockEntity> childEntities;

	@PrimaryKeyJoinColumn
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, targetEntity = MockEntity.class)
	@JsonProperty
	public List<MockEntity> getChildEntities() {
		
		if(childEntities==null)
			childEntities = new ArrayList<MockEntity>();
		return childEntities;
	}

	public void setChildEntities(List<MockEntity> childEntities) {
		this.childEntities = childEntities;
	}

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@JsonProperty
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the parentEntity
	 */
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = MockEntity.class, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	// @JsonBackReference
	@JsonIdentityReference
	@JsonProperty
	public MockEntity getParentEntity() {
		return parentEntity;
	}

	/**
	 * @param parentEntity
	 *            the parentEntity to set
	 */
	public void setParentEntity(MockEntity parentEntity) {
		this.parentEntity = parentEntity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
