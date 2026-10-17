package com.sci.bpm.command.stores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * One additional info search filter on the view stores page: an attribute from
 * SCI_MATERIAL_ADDINFO_MASTER that has drop values, and the value picked for it.
 */
public class AddInfoFilter implements Serializable {

	private String label;
	private String value;
	private List<String> dropValues = new ArrayList<String>();

	public AddInfoFilter() {
	}

	public AddInfoFilter(String label, List<String> dropValues) {
		this.label = label;
		this.dropValues = dropValues;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getDropValues() {
		return dropValues;
	}

	public void setDropValues(List<String> dropValues) {
		this.dropValues = dropValues;
	}
}
