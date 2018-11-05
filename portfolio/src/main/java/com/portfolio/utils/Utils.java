package com.portfolio.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	//REST API KEY 
	public static String kakaoApiKey = "{kakaoApiKey}";
	
	public static JSONObject getLocation(String address) throws Exception{
		address = address.replaceAll(" ", "");
		
		String localSearchUrl = "https://dapi.kakao.com/v2/local/search/address.json?query="+URLEncoder.encode(address, "UTF-8");
		JSONObject json = new JSONObject(Utils.getHtml(localSearchUrl, "KakaoAK "+kakaoApiKey));
		
		JSONObject returnJson = new JSONObject();
		try {
			if(json.getJSONArray("documents").length() == 0){
				json = new JSONObject(Utils.getHtml(localSearchUrl, "KakaoAK "+kakaoApiKey));
			}
			returnJson = json.getJSONArray("documents").getJSONObject(0).getJSONObject("address");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnJson;
	}
	
	/* 한글 키워드 분석 사이트에서 쿼리를 날린다. */
	public static JSONObject koreanTextPharase(String text) throws Exception{
		String jsonString = Jsoup.connect("https://open-korean-text.herokuapp.com/extractPhrases?text="+URLEncoder.encode(text, "UTF-8"))
	    		.timeout(10000).header("Content-Type", "application/json")
	    		.ignoreContentType(true)
	    		.get().getElementsByTag("body").text().toString();
	    return new JSONObject(jsonString);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            String str = (String) object;
            return str.length() == 0;
        }

        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            return collection.size() == 0;
        }

        if (object.getClass().isArray()) {
            try {
                if (Array.getLength(object) == 0) {
                    return true;
                }
            } catch (Exception e) {
                //do nothing
            }
        }
        return false;
    }
	
	public static String postUrlConnection(String urls, File file) throws Exception{
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "1234567890*****";
		  
		try {
			FileInputStream mFileInputStream = new FileInputStream(file.getAbsolutePath());
		   
			URL connectUrl = new URL(urls);
			   
			// open connection 
			HttpURLConnection conn = (HttpURLConnection)connectUrl.openConnection();   
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			  
			// write data
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + file.getName()+"\"" + lineEnd);
			dos.writeBytes(lineEnd);
			   
			int bytesAvailable = mFileInputStream.available();
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize);
			   
			byte[] buffer = new byte[bufferSize];
			int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
			   
			// read image
			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = mFileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
			}
			   
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			   
			// close streams
			mFileInputStream.close();
			dos.flush(); // finish upload...   
			   
			int ch;
			InputStream is = conn.getInputStream();
			StringBuffer b =new StringBuffer();
			while( ( ch = is.read() ) != -1 ){
				b.append( (char)ch );
			}
			if(!b.toString().equals("succ")){
				System.out.println("image upload fail : " + file.getName());
			}
			dos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return "";
	}
	
	public static String generateState() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString();
	}
	
	@SuppressWarnings("resource")
	public static byte[] getFileDate(File file) throws IOException {
		int total = 0;
		int length = (int)file.length();
		byte[] ret = new byte[length];
		FileInputStream reader = new FileInputStream(file);
		while(total < length){
			int read = reader.read(ret);
			if(read<0) throw new IOException("fail read file : "+file);
			total += read;
		}
		return ret;
	}
	
	public static String postHTML(String targetUrl, Map<String, Object> params){
		
		String result = "";
		
		try {
			URL url = new URL(targetUrl);
	        
	        StringBuilder postData = new StringBuilder();
	        for(Map.Entry<String,Object> param : params.entrySet()) {
	            if(postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	 
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        
			conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	 
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	 
	        String inputLine;
	        while((inputLine = in.readLine()) != null) {
	        	result += inputLine;
	        }
	        in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (ProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
        
        return result;
	}

	public static String getHtml(String url, String authorization) {
		HttpURLConnection httpRequest = null;
		String resultValue = null;

		try {
			URL u = new URL(url);
			httpRequest = (HttpURLConnection) u.openConnection();
			httpRequest.setRequestProperty("Content-type", "text/xml; charset=UTF-8");
			if (authorization != null) {
				httpRequest.setRequestProperty("Authorization", authorization);
				// System.out.println("authorization : "+authorization);
			}
			httpRequest.connect();
			
			int code = httpRequest.getResponseCode();
			if(code == 200){
				BufferedReader in = new BufferedReader(new InputStreamReader(
						httpRequest.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = in.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
				resultValue = sb.toString();
			}else{
				resultValue = null;
				System.out.println("responseCodeError code : " + code);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("resultValue : "+resultValue);
		return resultValue;
	}// getHtml end
	
	/* MAP 변환 클래스 */
	public static Map<String, String> JSONStringToMap(String str) {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			map = mapper.readValue(str,
					new TypeReference<HashMap<String, String>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}// JSONStringToMap end

}
