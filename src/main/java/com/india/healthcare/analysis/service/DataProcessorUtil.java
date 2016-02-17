package com.india.healthcare.analysis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.india.healthcare.analysis.constants.DatasetFieldConstants;
import com.india.healthcare.analysis.dto.TopOverallDTO;

/*
 * This class contains all the formula for making calculations. All data processing is
 * a part of this class. 
 */
public class DataProcessorUtil {
	
	private static final String REPLACEMENT_STRING = "0";
	private static final Boolean ASCENDING_ORDER = true;
	private static final Boolean DESCENDING_ORDER = false;
	
	/*
	 * This method is to preprocess the data to check whether it contains 
	 * invalid values. If it does, then replace it by the Replacement string.
	 */
	public static List<String[]> preprocessData(List<String[]> data) {
		List<String[]> modifiedData = new ArrayList<String[]>(data.size());
		
		for (String[] dataRow : data) {
			modifiedData.add(preprocessDataRow(dataRow));
		}
		
		return modifiedData;
	}
	
	/*
	 * This method is to preprocess the line of data to check whether it contains 
	 * invalid values. If it does, then replace it by the Replacement string.
	 */
	public static String[] preprocessDataRow(String[] data) {
		for(int counter = DatasetFieldConstants.LOWER_DATA_INDEX; counter <= DatasetFieldConstants.HIGHER_DATA_INDEX; counter++) {
			if(DatasetFieldConstants.INVALID_VALUES.contains(data[counter])) {
				data[counter] = REPLACEMENT_STRING;
			}
		}
		
		return data;
	}
	
	/*
	 * This method calculates the sum of the values of the columns specified in the List
	 * and calculates the percentage over all the columns.
	 */
	public static Double getImpactPercentage(String[] data, List<Integer> concernedColumnsList) {
		Long sumOfConcernedColumnValues = 0L;
		Long sumOfAllColumnValues = 0L;
		
		for (int columnCounter = DatasetFieldConstants.LOWER_DATA_INDEX; columnCounter <= 
			DatasetFieldConstants.HIGHER_DATA_INDEX; columnCounter++) {
			
			if (concernedColumnsList.contains(columnCounter)) {
				sumOfConcernedColumnValues += Long.valueOf(data[columnCounter]);
			}
			
			sumOfAllColumnValues += Long.valueOf(data[columnCounter]);
		}
		
		return (sumOfConcernedColumnValues.doubleValue()/
				sumOfAllColumnValues.doubleValue()) * 100;
	}
	
	/*
	 * Get the details of Top "n" states based on some particular data.
	 */
	public static TopOverallDTO getTopStateDetails(List<String[]> data, Integer stateNameIndex, Integer lowerDataIndex, 
			Integer higherDataIndex, int numberOfResults) {
		
		TopOverallDTO topOverallStateDetails = new TopOverallDTO();
		List<String> stateNames = new ArrayList<String>(data.size());
		List<String> values = new ArrayList<String>(data.size());
		
		List<Integer> valueTotalList = getValueTotalList(data, stateNameIndex, lowerDataIndex, higherDataIndex);
		Map<String, Integer> stateValueMap = new HashMap<String, Integer>();
		int listIndex = 0;
		
		// Add the state name as key and total value as value to the map.
		for(Integer valueTotal: valueTotalList) {
			stateValueMap.put(data.get(listIndex)[stateNameIndex], valueTotal);
			listIndex++;
		}
		
		Map<String, Integer> sortedStateValueMap = sortByTotalValue(stateValueMap, DESCENDING_ORDER);
		
		// Get the top "n" results from the map.
		for (Map.Entry<String, Integer> entry : sortedStateValueMap.entrySet()) {
			
			if ((numberOfResults--) != 0) {
				String stateName = entry.getKey();
				String totalValue = Integer.toString(entry.getValue());
				stateNames.add(stateName);
				values.add(totalValue);
			}
			else
				break;
		}
		
		topOverallStateDetails.setStateNames(stateNames);
		topOverallStateDetails.setValues(values);
		
		return topOverallStateDetails;
	}
	
	private static Map<String, Integer> sortByTotalValue(Map<String, Integer> map, Boolean isAscendingOrder) {
		
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String,Integer>>(map.entrySet());
		
		if(isAscendingOrder) {
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

				@Override
				public int compare(Entry<String, Integer> e1,
						Entry<String, Integer> e2) {
						return (e1.getValue()).compareTo(e2.getValue());
				}
			
			});
		}
		else {
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

				@Override
				public int compare(Entry<String, Integer> e1,
						Entry<String, Integer> e2) {
						return (e2.getValue()).compareTo(e1.getValue());
				}
			
			});			
		}
		
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedMap;
	}
	
	/*
	 * Get the total of all values in a row between lowerDataIndex and higherDataIndex and add it 
	 * in valueTotalList.  
	 */
	public static List<Integer> getValueTotalList(List<String[]> data, Integer stateNameIndex, Integer lowerDataIndex, 
			Integer higherDataIndex) {
		
		List<Integer> valueTotalList = new ArrayList<Integer>();
		
		for(String[] row : data) {
			int total = 0;
			
			for(int columnIndex = lowerDataIndex; columnIndex <= higherDataIndex; columnIndex++) {
				total += Integer.parseInt(row[columnIndex]);
			}
			
			valueTotalList.add(total);
		}
		
		return valueTotalList;
	}
	
}
