package com.portfolio.utils;

public class DeviceCheck {

	public static String deviceCheck(String userAgent) {
		String device = "";
		String browser = "";
		String[] mobile = { "iPhone", "iPod", "Android", "BlackBerry",
				"Windows CE", "Nokia", "Webos", "Opera Mini", "SonyEricsson",
				"Opera Mobi", "IEMobile" };

		int j = -1;
		if (userAgent != null && !userAgent.equals("")) {
			for (int i = 0; i < mobile.length; i++) {
				j = userAgent.indexOf(mobile[i]);
				if (j > -1) {
					device = mobile[i] + " mobile";
				}
			}

			if (device == "") {
				if (userAgent.indexOf("Trident") > 0
						|| userAgent.indexOf("MSIE") > 0) {
					if (userAgent.indexOf("Trident") > 0) {
						if (userAgent.indexOf("Trident/7.0") > 0) {
							browser = "IE 11";
						} else if (userAgent.indexOf("Trident/6.0") > 0) {
							browser = "IE 10";
						} else if (userAgent.indexOf("Trident/5.0") > 0) {
							browser = "IE 9";
						} else if (userAgent.indexOf("Trident/4.0") > 0) {
							browser = "IE 8";
						}
					} else {
						browser = "IE 6, 7";
					}
				} else if (userAgent.indexOf("Opera") > 0) {
					browser = "Opera";
				} else if (userAgent.indexOf("Firefox") > 0) {
					browser = "Firefox";
				} else if (userAgent.indexOf("Safari") > 0) {
					if (userAgent.indexOf("Chrome") > 0) {
						browser = "Chrome";
					} else {
						browser = "Safari";
					}
				} else if (userAgent.indexOf("BingPreview") > 0) {
					browser = "Bing";
				} else if(userAgent.indexOf("facebookexternalhit/1.1") > 0){
					browser = "facebookexternalhit/1.1";
				} else if(userAgent.indexOf("compatible; Daum/4.1") > 0){
					browser = "compatible; Daum/4.1";
				} else if(userAgent.indexOf("compatible; proximic") > 0){
					browser = "compatible; proximic";
				} else if(userAgent.indexOf("BrokenLinkCheck.com/1.1") > 0){
					browser = "BrokenLinkCheck.com/1.1";
				}  else if(userAgent.indexOf("compatible; Yeti/1.1; +http://naver.me/spd") > 0){
					browser = "compatible; Yeti/1.1; +http://naver.me/spd";
				} 
				
				else {
					if (userAgent.indexOf("bot.html") != -1) {
						browser = "bot.html";
					} else if (userAgent.indexOf("bingbot.htm") != -1) {
						browser = "bingbot.htm";
					} else {
						browser = userAgent;
					}
				}
				device = browser + " web browser";
			}
		}
		return device;
	}

}
