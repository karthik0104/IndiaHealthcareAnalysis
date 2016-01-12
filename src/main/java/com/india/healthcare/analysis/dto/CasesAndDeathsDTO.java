package com.india.healthcare.analysis.dto;

public class CasesAndDeathsDTO {
	
	Long casesDueToSample;
	Long deathsDueToSample;
	
	public Long getCasesDueToSample() {
		return casesDueToSample;
	}
	public void setCasesDueToSample(Long casesDueToSample) {
		this.casesDueToSample = casesDueToSample;
	}
	public Long getDeathsDueToSample() {
		return deathsDueToSample;
	}
	public void setDeathsDueToSample(Long deathsDueToSample) {
		this.deathsDueToSample = deathsDueToSample;
	}
	
	@Override
	public String toString() {
		return "CasesAndDeathsDTO [casesDueToSample=" + casesDueToSample
				+ ", deathsDueToSample=" + deathsDueToSample + "]";
	}

}
