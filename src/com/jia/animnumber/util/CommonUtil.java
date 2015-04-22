package com.jia.animnumber.util;

import java.math.BigDecimal;

/**
 * @author Mars
 *
 */
public class CommonUtil {
	
	public static boolean compare(BigDecimal val1, BigDecimal val2) {  
		boolean result = false;  
	    if (val1.compareTo(val2) < 0) {  
	    	result = false;
	    }  
	    if (val1.compareTo(val2) == 0) {  
	    	result = true; 
	    }  
	    if (val1.compareTo(val2) > 0) {  
	    	result = true;
	    }  
	    return result;  
	}  
	
	public static int compareInt(double valone, double valTwo) {  
		BigDecimal val1 = new BigDecimal(valone);
		BigDecimal val2 = new BigDecimal(valTwo);
		int result = -1;  
	    if (val1.compareTo(val2) < 0) {  
	    	result = -1;
	    }  
	    if (val1.compareTo(val2) == 0) {  
	    	result = 0; 
	    }  
	    if (val1.compareTo(val2) > 0) {  
	    	result = 1;
	    }  
	    return result;  
	}  
}
