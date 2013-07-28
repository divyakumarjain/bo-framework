package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.INotEqualToComparison;


public class NotEqualToComparison<ATTRIBUTE> implements INotEqualToComparison<ATTRIBUTE> {
	
	private ATTRIBUTE value;
	
	public NotEqualToComparison() {
		super();
	}
	
	public NotEqualToComparison(ATTRIBUTE value) {
		super();
		this.value = value;
	}

	public void setValue(ATTRIBUTE value) {
		this.value = value;
	}

	@Override
	public ATTRIBUTE getValue() {
		return value;
	}
}
