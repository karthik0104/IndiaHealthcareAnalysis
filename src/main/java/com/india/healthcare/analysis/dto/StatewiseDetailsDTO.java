package com.india.healthcare.analysis.dto;

/*
 * This is the DTO containing statewise details of case and deaths due to various diseases.
 */
public class StatewiseDetailsDTO {
	String name;
	CasesAndDeathsDTO details;
	PercentImpactDTO percentImpact;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CasesAndDeathsDTO getDetails() {
		return details;
	}
	
	public void setDetails(CasesAndDeathsDTO details) {
		this.details = details;
	}

	public PercentImpactDTO getPercentImpact() {
		return percentImpact;
	}

	public void setPercentImpact(PercentImpactDTO percentImpact) {
		this.percentImpact = percentImpact;
	}
	
}
