package com.packtpub.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

	public static <T> T getUserNotAvailableError(){
		Map<String, Object> map = new LinkedHashMap<>();
		
		map.put("result_code", 501);
		map.put("result", "User Not Available");			
		return (T) map;
	}
	
	public static <T> T getSuccessResult(){
		Map<String, Object> map = new LinkedHashMap<>();
		
		map.put("result_code", 0);
		map.put("result", "success");			
		return (T) map;
	}
	
	public static <T> T getSuccessResult(Object obj){
		Map<String, Object> map = new LinkedHashMap<>();
		
		map.put("result_code", 0);
		map.put("result", "success");
		map.put("value", obj);
		return (T) map;
	}
}
