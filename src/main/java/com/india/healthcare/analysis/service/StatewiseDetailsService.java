package com.india.healthcare.analysis.service;

import com.india.healthcare.analysis.constants.IllnessNames;
import com.india.healthcare.analysis.dto.AllStatesDetailsDTO;
import com.india.healthcare.analysis.dto.TopOverallDTO;

public interface StatewiseDetailsService {
	
	public TopOverallDTO getTopFiveStatesDetails(IllnessNames illness);
	
	AllStatesDetailsDTO getAllStatesDetails();

}
