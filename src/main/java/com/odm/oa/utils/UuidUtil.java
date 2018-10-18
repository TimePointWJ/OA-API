package com.odm.oa.utils;

import java.util.UUID;

public class UuidUtil {
	
	 private UuidUtil() {
		    throw new IllegalStateException("TravelskyConstants class");
	 }
	 
	public static String get32UUID() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
	
}

