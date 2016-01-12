package com.india.healthcare.analysis.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.india.healthcare.analysis.dto.AllStatesDetailsDTO;
import com.india.healthcare.analysis.dto.CasesAndDeathsDTO;
import com.india.healthcare.analysis.dto.StatewiseDetailsDTO;

@Service
@Validated
public class StatewiseDetailsServiceImpl implements StatewiseDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StatewiseDetailsServiceImpl.class);
	private static Map<String, StatewiseDetailsDTO> statewiseDetailsMap = new HashMap<String, StatewiseDetailsDTO>();
	private static StatewiseDetailsDTO statewiseDetailsDTO1, statewiseDetailsDTO2;
	private static AllStatesDetailsDTO allStatesDetailsDTO;
	
	static {
		initializeMap();
		setBasicStateProfile();
		populateAdditionalStateInfo();
		setAllStatesDetailsDTO();
	}

	@Override
	public AllStatesDetailsDTO getAllStatesDetails() {
		return allStatesDetailsDTO;
	}
	
	private static void setBasicStateProfile() {
		
		//Add details
		CasesAndDeathsDTO casesAndDeathsDTO = new CasesAndDeathsDTO();
		casesAndDeathsDTO.setCasesDueToSample(100L);
		casesAndDeathsDTO.setDeathsDueToSample(10L);
		
		statewiseDetailsDTO1 = new StatewiseDetailsDTO();
		statewiseDetailsDTO1.setStateName("Maharashtra");
		statewiseDetailsDTO1.setStateCode("MH");
		statewiseDetailsDTO1.setDetails(casesAndDeathsDTO);
		
		statewiseDetailsDTO2 = new StatewiseDetailsDTO();
		statewiseDetailsDTO2.setStateName("Uttar Pradesh");
		statewiseDetailsDTO2.setStateCode("UP");
		statewiseDetailsDTO2.setDetails(casesAndDeathsDTO);
		
	}
	
	private static void initializeMap() {
		statewiseDetailsMap.put("MH", statewiseDetailsDTO1);
		statewiseDetailsMap.put("UP", statewiseDetailsDTO2);
	}
	
	/*
	 * This method extracts data from our datasets and populates it in our
	 * DTOs.
	 */
	private static void populateAdditionalStateInfo() {
		
	}
	
	private static void setAllStatesDetailsDTO() {
		allStatesDetailsDTO = new AllStatesDetailsDTO();
		allStatesDetailsDTO.setMH(statewiseDetailsDTO1);
		allStatesDetailsDTO.setUP(statewiseDetailsDTO2);
	}
}
