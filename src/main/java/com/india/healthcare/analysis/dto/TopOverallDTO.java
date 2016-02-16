package com.india.healthcare.analysis.dto;

import java.util.List;

public class TopOverallDTO {
	
	String category;
	List<String> values;
	List<String> stateNames;
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public List<String> getStateNames() {
		return stateNames;
	}

	public void setStateNames(List<String> stateNames) {
		this.stateNames = stateNames;
	}
	
}
