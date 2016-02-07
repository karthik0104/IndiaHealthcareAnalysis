package com.india.healthcare.analysis.dto;

/*
 * This is the DTO containing statewise details of case and deaths due to various diseases.
 */
public class StatewiseDetailsDTO {
	CasesAndDeathsDTO details;
	
	public CasesAndDeathsDTO getDetails() {
		return details;
	}
	
	public void setDetails(CasesAndDeathsDTO details) {
		this.details = details;
	}
}
