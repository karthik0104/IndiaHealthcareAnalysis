package com.india.healthcare.analysis.service;

import com.india.healthcare.analysis.dto.AllStatesDetailsDTO;
import com.india.healthcare.analysis.dto.TopOverallDTO;

public interface StatewiseDetailsService {
	
	TopOverallDTO getTopFiveOverallStatesDetails();
	
	AllStatesDetailsDTO getAllStatesDetails();

}
