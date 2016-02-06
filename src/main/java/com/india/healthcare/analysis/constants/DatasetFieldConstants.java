package com.india.healthcare.analysis.constants;

import java.util.Arrays;
import java.util.List;

/*
 * This class contains all the constants which are relevant in the context of
 * the datasets.
 */
public class DatasetFieldConstants {
	
	public static final int STATE_NAME_INDEX = 1;
	public static final int DIARRHOEA_CASES_INDEX = 2;
	public static final int DIARRHOEA_DEATHS_INDEX = 3;
	public static final int MALARIA_CASES_INDEX = 4;
	public static final int MALARIA_DEATHS_INDEX = 5;
	public static final int RESPIRATORY_CASES_INDEX = 6;
	public static final int RESPIRATORY_DEATHS_INDEX = 7;
	public static final int ENCEPHALITITS_CASES_INDEX = 8;
	public static final int ENCEPHALITITS_DEATHS_INDEX = 9;
	public static final int HEPATITIS_CASES_INDEX = 10;
	public static final int HEPATITIS_DEATHS_INDEX = 11;
	public static final int STATE_CODE_INDEX = 12;
	public static final int LOWER_DATA_INDEX = 2;
	public static final int HIGHER_DATA_INDEX = 11;
	public static final List<String> INVALID_VALUES = Arrays.asList("NULL", "NA", ""); 
}
