package org.divy.common.bo.query.defaults;

import org.divy.common.bo.query.ILessThanComparison;


public class LessThanComparison<ATTRIBUTE> implements ILessThanComparison<ATTRIBUTE>{

	private ATTRIBUTE value;
	
	public LessThanComparison() {
		super();
	}
	
	public LessThanComparison(ATTRIBUTE value) {
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
