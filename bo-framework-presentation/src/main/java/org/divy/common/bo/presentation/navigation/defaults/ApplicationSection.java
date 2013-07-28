package org.divy.common.bo.presentation.navigation.defaults;

import java.util.List;

import org.divy.common.bo.presentation.navigation.IApplicationSection;
import org.divy.common.bo.presentation.navigation.IOperation;

public class ApplicationSection implements IApplicationSection{

	public ApplicationSection(String sectionLabel) {
		super();
		this.sectionLabel = sectionLabel;
	}
	String sectionLabel;
	List<IApplicationSection> subSection;
	List<IOperation> operations;
	
	public String getSectionLabel() {
		return sectionLabel;
	}
	public void setSectionLabel(String sectionLabel) {
		this.sectionLabel = sectionLabel;
	}
	public List<IApplicationSection> getSubSection() {
		return subSection;
	}
	public void setSubSection(List<IApplicationSection> subSection) {
		this.subSection = subSection;
	}
	public List<IOperation> getOperations() {
		return operations;
	}
	public void setOperations(List<IOperation> operations) {
		this.operations = operations;
	}
	
}
