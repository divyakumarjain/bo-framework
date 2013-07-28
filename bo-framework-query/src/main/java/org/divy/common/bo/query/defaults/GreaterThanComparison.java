package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.IGreaterThanComparison;


public class GreaterThanComparison<ATTRIBUTE> implements IGreaterThanComparison<ATTRIBUTE> {

	private ATTRIBUTE value;

	public GreaterThanComparison() {
		super();
	}

	public GreaterThanComparison(ATTRIBUTE value) {
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
