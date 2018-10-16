package com.portfolio.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

/*
 * 이미지의 미디어 타입을 확인하고 리턴한다.
 * */
public class MimeMediaUtil {

	private static Map<String, MediaType> mediaMap;
	
	static{
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type){
		
		return mediaMap.get(type.toUpperCase());
	}
}
