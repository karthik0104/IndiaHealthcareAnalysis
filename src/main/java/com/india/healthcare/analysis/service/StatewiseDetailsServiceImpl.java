package com.india.healthcare.analysis.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.india.healthcare.analysis.constants.DatasetFieldConstants;
import com.india.healthcare.analysis.constants.IllnessNames;
import com.india.healthcare.analysis.dto.AllStatesDetailsDTO;
import com.india.healthcare.analysis.dto.CasesAndDeathsDTO;
import com.india.healthcare.analysis.dto.PercentImpactDTO;
import com.india.healthcare.analysis.dto.StatewiseDetailsDTO;
import com.india.healthcare.analysis.dto.TopOverallDTO;

@Service
@Validated
public class StatewiseDetailsServiceImpl implements StatewiseDetailsService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatewiseDetailsServiceImpl.class);
	private static Map<String, StatewiseDetailsDTO> statewiseDetailsMap = new HashMap<String, StatewiseDetailsDTO>();
	private static final String CASES_DEATHS_CSV_FILE = "Cases_Deaths.csv";
	private static final String DELIMITER = ",";
	private static final Integer TOP_STATE_RESULTS_NUMBER = 5;
	private static final String OVERALL = "Overall";

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
	 * This method extracts data from our datasets and populates it in our DTOs.
	 */
	private static void populateAdditionalStateInfo() {
		BufferedReader reader = getDatasetStreamReader();
		createStateProfileFromDataset(reader);

	}

	private static BufferedReader getDatasetStreamReader() {
		URL fileUrl = Thread.currentThread().getContextClassLoader()
				.getResource(CASES_DEATHS_CSV_FILE);
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
			while ((line = reader.readLine()) != null) {
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
		
		data = DataProcessorUtil.preprocessDataRow(data);
		
		stateDetails.setName(data[DatasetFieldConstants.STATE_NAME_INDEX]);
		
		CasesAndDeathsDTO info = new CasesAndDeathsDTO();
		info.setCasesDueToDiarrhoea(Long.valueOf(data[DatasetFieldConstants.DIARRHOEA_CASES_INDEX]));
		info.setDeathsDueToDiarrhoea(Long.valueOf(data[DatasetFieldConstants.DIARRHOEA_DEATHS_INDEX]));
		info.setCasesDueToMalaria(Long.valueOf(data[DatasetFieldConstants.MALARIA_CASES_INDEX]));
		info.setDeathsDueToMalaria(Long.valueOf(data[DatasetFieldConstants.MALARIA_DEATHS_INDEX]));
		info.setCasesDueToRespInfection(Long.valueOf(data[DatasetFieldConstants.RESPIRATORY_CASES_INDEX]));
		info.setDeathsDueToRespInfection(Long.valueOf(data[DatasetFieldConstants.RESPIRATORY_DEATHS_INDEX]));
		info.setCasesDueToEncephalitis(Long.valueOf(data[DatasetFieldConstants.ENCEPHALITITS_CASES_INDEX]));
		info.setDeathsDueToEncephalitis(Long.valueOf(data[DatasetFieldConstants.ENCEPHALITITS_DEATHS_INDEX]));
		info.setCasesDueToHepatitis(Long.valueOf(data[DatasetFieldConstants.HEPATITIS_CASES_INDEX]));
		info.setDeathsDueToHepatitis(Long.valueOf(data[DatasetFieldConstants.HEPATITIS_DEATHS_INDEX]));
		
		PercentImpactDTO percentImpacts = new PercentImpactDTO();
		percentImpacts.setDiarrhoeaImpact(Double.toString(DataProcessorUtil.getImpactPercentage(data, Arrays.asList(DatasetFieldConstants.DIARRHOEA_CASES_INDEX,
				DatasetFieldConstants.DIARRHOEA_DEATHS_INDEX))));
		percentImpacts.setMalariaImpact(Double.toString(DataProcessorUtil.getImpactPercentage(data, Arrays.asList(DatasetFieldConstants.MALARIA_CASES_INDEX,
				DatasetFieldConstants.MALARIA_DEATHS_INDEX))));
		percentImpacts.setRespInfectionImpact(Double.toString(DataProcessorUtil.getImpactPercentage(data, Arrays.asList(DatasetFieldConstants.RESPIRATORY_CASES_INDEX,
				DatasetFieldConstants.RESPIRATORY_DEATHS_INDEX))));
		percentImpacts.setEncephalitisImpact(Double.toString(DataProcessorUtil.getImpactPercentage(data, Arrays.asList(DatasetFieldConstants.ENCEPHALITITS_CASES_INDEX,
				DatasetFieldConstants.ENCEPHALITITS_DEATHS_INDEX))));
		percentImpacts.setHepatitisImpact(Double.toString(DataProcessorUtil.getImpactPercentage(data, Arrays.asList(DatasetFieldConstants.HEPATITIS_CASES_INDEX,
				DatasetFieldConstants.HEPATITIS_DEATHS_INDEX))));
		
		stateDetails.setDetails(info);
		stateDetails.setPercentImpact(percentImpacts);
		
		//Put this DTO into the map
		statewiseDetailsMap.put(stateCode, stateDetails);
	}
	
	@Override
	public TopOverallDTO getTopFiveStatesDetails(IllnessNames illness) {
		
		BufferedReader reader = getDatasetStreamReader();
		Integer[] columnIndices = getColumnIndices(illness);
		TopOverallDTO topFiveOverall = getTopFiveStatesDetailsFromDataset(reader, illness, columnIndices[0], columnIndices[1]);
		
		return topFiveOverall;
	}
	
	private Integer[] getColumnIndices(IllnessNames illness) {
		
		Integer[] columnIndices = new Integer[2];
		
		switch(illness) {
		
			case DIARRHOEA:
				columnIndices[0] = DatasetFieldConstants.DIARRHOEA_CASES_INDEX;
				columnIndices[1] = DatasetFieldConstants.DIARRHOEA_DEATHS_INDEX;
				break;
				
			case ENCEPHALITIS:
				columnIndices[0] = DatasetFieldConstants.ENCEPHALITITS_CASES_INDEX;
				columnIndices[1] = DatasetFieldConstants.ENCEPHALITITS_DEATHS_INDEX;
				break;
				
			case HEPATITIS:
				columnIndices[0] = DatasetFieldConstants.HEPATITIS_CASES_INDEX;
				columnIndices[1] = DatasetFieldConstants.HEPATITIS_DEATHS_INDEX;
				break;
				
			case MALARIA:
				columnIndices[0] = DatasetFieldConstants.DIARRHOEA_CASES_INDEX;
				columnIndices[1] = DatasetFieldConstants.DIARRHOEA_DEATHS_INDEX;
				break;
				
			case OVERALL:
				columnIndices[0] = DatasetFieldConstants.LOWER_DATA_INDEX;
				columnIndices[1] = DatasetFieldConstants.HIGHER_DATA_INDEX;
				break;
				
			case RESPIRATORY:
				columnIndices[0] = DatasetFieldConstants.RESPIRATORY_CASES_INDEX;
				columnIndices[1] = DatasetFieldConstants.RESPIRATORY_DEATHS_INDEX;
				break;
				
			default:
				break;
		}
		
		return columnIndices;
	}
	
	private TopOverallDTO getTopFiveStatesDetailsFromDataset(BufferedReader reader, IllnessNames illness, int lowerColumnIndex, 
			int higherColumnIndex) {
		List<String[]> overallStateDetails = getOverallDetailsFromDataset(reader);
		overallStateDetails = DataProcessorUtil.preprocessData(overallStateDetails);
		TopOverallDTO topFiveOverall = DataProcessorUtil.getTopStateDetails(overallStateDetails,
				DatasetFieldConstants.STATE_NAME_INDEX, lowerColumnIndex, higherColumnIndex, TOP_STATE_RESULTS_NUMBER);
		topFiveOverall.setCategory(illness.getIllnessName());
		return topFiveOverall;
	}
	
	private List<String[]> getOverallDetailsFromDataset(BufferedReader reader) {
		List<String[]> stateDetailsList = new ArrayList<String[]>();
		String line;
		try {
			line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(DELIMITER);
				stateDetailsList.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stateDetailsList;
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
