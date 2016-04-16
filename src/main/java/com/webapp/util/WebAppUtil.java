package com.webapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.joda.time.DateTime;

public class WebAppUtil {

	public static Integer generateIdValue(Integer staticValue){
		DateTime dt = new DateTime();
		Integer year = dt.getYear();
		Integer month = dt.getMonthOfYear();
		Integer day = dt.getDayOfYear();
		Integer hour = dt.getHourOfDay();
		Integer milli = dt.getMillisOfDay();
		Integer id = staticValue + year + month + day + hour + milli;
		return id;		
	}
	
	public static String[] covertObjectArrayToStringArray(ArrayList<String> input){
		String output[] = new String[input.size()];
		if(input != null){
			for(int i = 0; i < input.size(); i++){
				output[i] = input.get(i);
			}
		}
		return output;
	}
	
	public static Map<String,String> getCookiesValue(Cookie[] cookies){
		Map<String,String> coockieMap = new HashMap<String, String>();
		if(cookies != null){
			for(Cookie cookie : cookies){
				coockieMap.put(cookie.getName(),cookie.getValue());
			}
		}
		return coockieMap;
	}
	
	public static String passwordEncrypter(String password){
		String generatedDigest = null;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(password.getBytes());

			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			generatedDigest = sb.toString();
		}catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return generatedDigest;
	}
	
	public static List<String> arraySplitter(String actors){
		if(actors == null) {return null;}
		List<String> arrayList = new ArrayList<String>();
		arrayList = Arrays.asList(actors.split("\\s*,\\s*"));			
		return arrayList; 
	}
	
}
