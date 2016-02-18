package com.india.healthcare.analysis.constants;

public enum IllnessNames {
	
	OVERALL("Overall"),
	DIARRHOEA("Diarrhoea"),
	MALARIA("Malaria"),
	RESPIRATORY("Respiratory"),
	ENCEPHALITIS("Encephalitis"),
	HEPATITIS("Hepatitis");
	
	private String illnessName;
	
	IllnessNames(String illnessName) {
		this.illnessName = illnessName;
	}
	
	public String getIllnessName() {
		return illnessName;
	}
}
