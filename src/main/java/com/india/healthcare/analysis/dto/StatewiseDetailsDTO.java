package com.india.healthcare.analysis.dto;

/*
 * This is the DTO containing statewise details of case and deaths due to various diseases.
 */
public class StatewiseDetailsDTO {
	
	String stateCode;
	String stateName;
	CasesAndDeathsDTO details;
	
	public String getStateCode() {
		return stateCode;
	}
	
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public CasesAndDeathsDTO getDetails() {
		return details;
	}
	
	public void setDetails(CasesAndDeathsDTO details) {
		this.details = details;
	}
	
	@Override
	public String toString() {
		return "StatewiseDetailsDTO [stateCode=" + stateCode + ", stateName="
				+ stateName + ", details=" + details + "]";
	}
}
