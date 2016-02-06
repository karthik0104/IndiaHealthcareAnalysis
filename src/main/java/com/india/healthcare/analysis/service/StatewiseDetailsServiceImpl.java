package com.india.healthcare.analysis.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.india.healthcare.analysis.constants.DatasetFieldConstants;
import com.india.healthcare.analysis.dto.AllStatesDetailsDTO;
import com.india.healthcare.analysis.dto.CasesAndDeathsDTO;
import com.india.healthcare.analysis.dto.StatewiseDetailsDTO;

@Service
@Validated
public class StatewiseDetailsServiceImpl implements StatewiseDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StatewiseDetailsServiceImpl.class);
	private static Map<String, StatewiseDetailsDTO> statewiseDetailsMap = new HashMap<String, StatewiseDetailsDTO>();
	private static final String CASES_DEATHS_CSV_FILE = "Cases_Deaths.csv";
	private static final String DELIMITER = ",";
	private static final String REPLACEMENT_STRING = "0";
	
	private static AllStatesDetailsDTO allStatesDetailsDTO;
	
	static {
		populateAdditionalStateInfo();
		setAllStatesDetailsDTO();
	}

	@Override
	public AllStatesDetailsDTO getAllStatesDetails() {
		return allStatesDetailsDTO;
	}
	
	/*
	 * This method extracts data from our datasets and populates it in our
	 * DTOs.
	 */
	private static void populateAdditionalStateInfo() {
		BufferedReader reader = getDatasetStreamReader();
		createStateProfileFromDataset(reader);
		
	}
	
	private static BufferedReader getDatasetStreamReader() {
		URL fileUrl = Thread.currentThread().getContextClassLoader().getResource(CASES_DEATHS_CSV_FILE);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return br;
	}
	
	/*
	 * This method reads each line of the dataset and populates the information 
	 * in the State DTOs.
	 */
	private static void createStateProfileFromDataset(BufferedReader reader) {
		String line;
		try {
			line = reader.readLine();
			while((line = reader.readLine()) != null) {
				String[] data = line.split(DELIMITER);
				createSingleStateProfile(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void createSingleStateProfile(String[] data) {
		/*
		 * Parse the State Code from the data and assign the corresponding state information to 
		 * the appropriate DTO by the using the code as the key for the map.
		 */
		String stateCode = data[DatasetFieldConstants.STATE_CODE_INDEX];
		StatewiseDetailsDTO stateDetails = new StatewiseDetailsDTO();
		
		data = preprocessData(data);
		
		CasesAndDeathsDTO info = new CasesAndDeathsDTO();
		info.setCasesDueToDiarrhoea(Long.valueOf(data[DatasetFieldConstants.DIARRHOEA_CASES_INDEX]));
		info.setDeathsDueToDiarrhoea(Long.valueOf(data[DatasetFieldConstants.DIARRHOEA_DEATHS_INDEX]));
		info.setCasesDueToMalaria(Long.valueOf(data[DatasetFieldConstants.MALARIA_CASES_INDEX]));
		info.setDeathsDueToMalaria(Long.valueOf(data[DatasetFieldConstants.MALARIA_DEATHS_INDEX]));
		info.setCasesDueToRespInfection(Long.valueOf(data[DatasetFieldConstants.RESPIRATORY_CASES_INDEX]));
		info.setCasesDueToRespInfection(Long.valueOf(data[DatasetFieldConstants.RESPIRATORY_DEATHS_INDEX]));
		info.setCasesDueToEncephalitis(Long.valueOf(data[DatasetFieldConstants.ENCEPHALITITS_CASES_INDEX]));
		info.setDeathsDueToEncephalitis(Long.valueOf(data[DatasetFieldConstants.ENCEPHALITITS_DEATHS_INDEX]));
		info.setCasesDueToHepatitis(Long.valueOf(data[DatasetFieldConstants.HEPATITIS_CASES_INDEX]));
		info.setDeathsDueToHepatitis(Long.valueOf(data[DatasetFieldConstants.HEPATITIS_DEATHS_INDEX]));
		
		stateDetails.setDetails(info);
		
		//Put this DTO into the map
		statewiseDetailsMap.put(stateCode, stateDetails);
	}
	
	/*
	 * This method is to preprocess the line of data to check whether it contains 
	 * invalid values. If it does, then replace it by the Replacement string.
	 */
	private static String[] preprocessData(String[] data) {
		for(int counter = DatasetFieldConstants.LOWER_DATA_INDEX; counter <= DatasetFieldConstants.HIGHER_DATA_INDEX; counter++) {
			if(DatasetFieldConstants.INVALID_VALUES.contains(data[counter])) {
				data[counter] = REPLACEMENT_STRING;
			}
		}
		
		return data;
	}
	
	private static void setAllStatesDetailsDTO() {
		allStatesDetailsDTO = new AllStatesDetailsDTO();
		allStatesDetailsDTO.setMH(statewiseDetailsMap.get("MH"));
		allStatesDetailsDTO.setUP(statewiseDetailsMap.get("UP"));
		allStatesDetailsDTO.setBR(statewiseDetailsMap.get("BR"));
		allStatesDetailsDTO.setWB(statewiseDetailsMap.get("WB"));
		allStatesDetailsDTO.setAP(statewiseDetailsMap.get("AP"));
		allStatesDetailsDTO.setMP(statewiseDetailsMap.get("MP"));
		allStatesDetailsDTO.setTN(statewiseDetailsMap.get("TN"));
		allStatesDetailsDTO.setRJ(statewiseDetailsMap.get("RJ"));
		allStatesDetailsDTO.setKA(statewiseDetailsMap.get("KA"));
		allStatesDetailsDTO.setGJ(statewiseDetailsMap.get("GJ"));
		allStatesDetailsDTO.setOR(statewiseDetailsMap.get("OR"));
		allStatesDetailsDTO.setKL(statewiseDetailsMap.get("KL"));
		allStatesDetailsDTO.setJH(statewiseDetailsMap.get("JH"));
		allStatesDetailsDTO.setAS(statewiseDetailsMap.get("AS"));
		allStatesDetailsDTO.setPB(statewiseDetailsMap.get("PB"));
		allStatesDetailsDTO.setCT(statewiseDetailsMap.get("CT"));
		allStatesDetailsDTO.setHR(statewiseDetailsMap.get("HR"));
		allStatesDetailsDTO.setDL(statewiseDetailsMap.get("DL"));
		allStatesDetailsDTO.setJK(statewiseDetailsMap.get("JK"));
		allStatesDetailsDTO.setUT(statewiseDetailsMap.get("UT"));
		allStatesDetailsDTO.setHP(statewiseDetailsMap.get("HP"));
		allStatesDetailsDTO.setTR(statewiseDetailsMap.get("TR"));
		allStatesDetailsDTO.setML(statewiseDetailsMap.get("ML"));
		allStatesDetailsDTO.setMN(statewiseDetailsMap.get("MN"));
		allStatesDetailsDTO.setNL(statewiseDetailsMap.get("NL"));
		allStatesDetailsDTO.setGA(statewiseDetailsMap.get("GA"));
		allStatesDetailsDTO.setAR(statewiseDetailsMap.get("AR"));
		allStatesDetailsDTO.setPY(statewiseDetailsMap.get("PY"));
		allStatesDetailsDTO.setMZ(statewiseDetailsMap.get("MZ"));
		allStatesDetailsDTO.setCH(statewiseDetailsMap.get("CH"));
		allStatesDetailsDTO.setSK(statewiseDetailsMap.get("SK"));
		allStatesDetailsDTO.setAN(statewiseDetailsMap.get("AN"));
		allStatesDetailsDTO.setDN(statewiseDetailsMap.get("DN"));
		allStatesDetailsDTO.setDD(statewiseDetailsMap.get("DD"));
		allStatesDetailsDTO.setLD(statewiseDetailsMap.get("LD"));
	}
}
