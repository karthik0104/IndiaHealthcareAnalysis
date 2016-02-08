package com.india.healthcare.analysis.service;

import java.util.List;

import com.india.healthcare.analysis.constants.DatasetFieldConstants;

/*
 * This class contains all the formula for making calculations. All data processing is
 * a part of this class. 
 */
public class DataProcessorUtil {
	
	private static final String REPLACEMENT_STRING = "0";
	
	/*
	 * This method is to preprocess the line of data to check whether it contains 
	 * invalid values. If it does, then replace it by the Replacement string.
	 */
	public static String[] preprocessData(String[] data) {
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
	
}
