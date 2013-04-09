package org.divy.common.bo.database.mock;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import org.divy.common.bo.IBusinessObject;
import org.hibernate.annotations.GenericGenerator;


@Entity
@XmlRootElement
public class MockEntity implements IBusinessObject<String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.IBusinessObject#getIdentity()
	 */
	public String identity() {
		return getUuid();
	}

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String uuid;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = MockEntity.class)
	@PrimaryKeyJoinColumn
	private MockEntity parentEntity;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.REMOVE }, targetEntity = MockEntity.class)
	@PrimaryKeyJoinColumn
	private List<MockEntity> childEntities;

	public List<MockEntity> getChildEntities() {
		return childEntities;
	}

	public void setChildEntities(List<MockEntity> childEntities) {
		this.childEntities = childEntities;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setIdentity(String identity) {
		setUuid(identity);
	}

	/**
	 * @return the parentEntity
	 */
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

}
