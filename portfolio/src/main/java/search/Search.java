package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Search
{
  private static DocumentBuilder df = null;
  private static XPath xPath = null;

  static
  {
    XPathFactory xpf = XPathFactory.newInstance();
    xPath = xpf.newXPath();

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
      df = dbf.newDocumentBuilder();
    }
    catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public static SearchResult searchAddress(String address, String key)
  {
    return searchAddress(address, -1, -1, key);
  }

  public static SearchResult searchAddress(String address, int startNum, int endNum, String key)
  {
	  SearchResult result = searchAddressUseNaver2(address, startNum, endNum, key);
	    if ((result == null) || (result.getTotal() == 0))
	    {
	      result = searchAddressUseNaver4(address, startNum, endNum, key);
	    }
	    return result;
  }

  public static SearchResult searchAddressUseYahoo(String keyword, int startNum, int endNum)
  {
    try {
      System.out.println("searchAddressUseYahoo");
      URL url = new URL("http://kr.open.gugi.yahoo.com/service/poi.php?appid=YahooDemo&encoding=utf-8&output=xml&results=150&q=" + keyword.replaceAll(" ", "%20"));

      Document doc = df.parse(url.openStream());

      int total = Integer.parseInt((String)xPath.compile("/ResultSet/head/Found/text()").evaluate(doc, XPathConstants.STRING));

      LinkedList list = new LinkedList();

      if ((startNum < 0) || (endNum < 0))
      {
        startNum = 1;
        endNum = total;
      }
      for (int i = startNum; i <= endNum; ++i)
      {
        HashMap map = new HashMap();
        if ("".equals(xPath.compile("/ResultSet/locations/item[" + i + "]/longitude/text()").evaluate(doc, XPathConstants.STRING))) {
          break;
        }

        map.put("x", xPath.compile("/ResultSet/locations/item[" + i + "]/longitude/text()").evaluate(doc, XPathConstants.STRING));
        map.put("y", xPath.compile("/ResultSet/locations/item[" + i + "]/latitude/text()").evaluate(doc, XPathConstants.STRING));
        map.put("name", xPath.compile("/ResultSet/locations/item[" + i + "]/name/text()").evaluate(doc, XPathConstants.STRING));

        String address = "";
        address = address + xPath.compile(new StringBuilder("/ResultSet/locations/item[").append(i).append("]/state/text()").toString()).evaluate(doc, XPathConstants.STRING);
        address = address + " ";
        address = address + xPath.compile(new StringBuilder("/ResultSet/locations/item[").append(i).append("]/county/text()").toString()).evaluate(doc, XPathConstants.STRING);

        map.put("address", address);

        list.add(map);
      }

      SearchResult sr = new SearchResult();
      sr.setTotal(total);
      sr.setResultList(list);

      return sr;
    }
    catch (Exception localException) {
    }
    return null;
  }

  public static String DocumentToString( Document doc )
  {
   try
   {
    StringWriter clsOutput = new StringWriter( );
    Transformer clsTrans = TransformerFactory.newInstance( ).newTransformer( );
 
    clsTrans.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "no" );
    clsTrans.setOutputProperty( OutputKeys.METHOD, "xml" );
    clsTrans.setOutputProperty( OutputKeys.INDENT, "yes" );
    clsTrans.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
 
    clsTrans.transform( new DOMSource( doc ), new StreamResult( clsOutput ) );

    return clsOutput.toString( );
   }
   catch( Exception ex )
   {
    return "";
   }
 }
  
  public static SearchResult searchAddressUseNaver4(String address, int startNum, int endNum, String key)
  {
    System.out.println("searchAddressUseNaver4==================");
    try
    {
    	
      address = URLEncoder.encode(address, "UTF-8");
      
      String kakaoApiKey = "529a0e6a54df04e779905a3d1ac0d4a4";
      String localSearchUrl = "http://dapi.kakao.com/v2/local/search/address.xml?query="+address;
      String result = getHtml(localSearchUrl, "KakaoAK "+kakaoApiKey);
      
      Document doc = null;
      try
      {
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       DocumentBuilder parser = dbf.newDocumentBuilder();
         doc = parser.parse(new InputSource(new StringReader(result)));
       }
       catch( Exception e )
       {
    	   e.printStackTrace();
       }
      
      int total = Integer.parseInt((String)xPath.compile("/result/meta/total_count/text()").evaluate(doc, XPathConstants.STRING));
      LinkedList list = new LinkedList();
      
      if ((startNum < 0) || (endNum < 0))
      {
        startNum = 1;
        endNum = total;
      }
      
      for (int i = startNum; i <= endNum; ++i)
      {
    	  HashMap map = new HashMap();
          if ("".equals(xPath.compile("/result/documents/address["+i+"]/x/text()").evaluate(doc, XPathConstants.STRING))) {
            break;
          }
    	  map.put("x", xPath.compile("/result/documents/address["+i+"]/x/text()").evaluate(doc, XPathConstants.STRING));
    	  map.put("y", xPath.compile("/result/documents/address["+i+"]/y/text()").evaluate(doc, XPathConstants.STRING));
    	  map.put("address", xPath.compile("/result/documents/address["+i+"]/address_name/text()").evaluate(doc, XPathConstants.STRING));
    	  
    	  String name = (String)xPath.compile("/result/documents/address["+i+"]/region_1depth_name/text()").evaluate(doc, XPathConstants.STRING);
          name = name + ((String)xPath.compile(new StringBuilder("/result/documents/address[").append(i).append("]/region_2depth_name/text()").toString()).evaluate(doc, XPathConstants.STRING));
          name = name + ((String)xPath.compile(new StringBuilder("/result/documents/address[").append(i).append("]/region_3depth_name/text()").toString()).evaluate(doc, XPathConstants.STRING));
          map.put("name", name);
          list.add(map);
          
      }
      
      SearchResult sr = new SearchResult();
      sr.setTotal(total);
      sr.setResultList(list);

      System.out.println(sr.toString());
      
      return sr;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static SearchResult searchAddressUseNaver2(String address, int startNum, int endNum, String key)
  {
    try
    {
    	
      address = URLEncoder.encode(address, "UTF-8");
      String display = (endNum == -1) ? "30" : "5";
      if(startNum == -1){ startNum = 1; }
      
      String result = getHtmlNaver("https://openapi.naver.com/v1/search/local.xml?query=" + address + "&display=" + display + "&start=" + startNum);

      Document doc = null;
      try
      {
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       DocumentBuilder parser = dbf.newDocumentBuilder();
         doc = parser.parse(new InputSource(new StringReader(result)));
       }
       catch( Exception e )
       {
    	   e.printStackTrace();
       }
      
      
      int total = Integer.parseInt((String)xPath.compile("/rss/channel/total/text()").evaluate(doc, XPathConstants.STRING));
      GeoPoint katec_pt = null;

      LinkedList list = new LinkedList();
      int oldEndNum = endNum;

      if ((startNum < 0) || (endNum < 0))      {
        startNum = 1;
        endNum = total;
      }

      if ((total >= 5) && (oldEndNum != -1))
        endNum = 5;
      else {
        endNum = total;
      }
      	
      System.out.println("endNum:" + endNum);
      
      for (int i = 1; i <= endNum; ++i)
      {
        HashMap map = new HashMap();
        if ("".equals(xPath.compile("/rss/channel/item[" + i + "]/mapx/text()").evaluate(doc, XPathConstants.STRING)))
        {
          break;
        }

        double x = Double.valueOf(xPath.compile("/rss/channel/item[" + i + "]/mapx/text()").evaluate(doc, XPathConstants.STRING).toString()).doubleValue();
        double y = Double.valueOf(xPath.compile("/rss/channel/item[" + i + "]/mapy/text()").evaluate(doc, XPathConstants.STRING).toString()).doubleValue();

        katec_pt = new GeoPoint(x, y);

        System.out.println("convert:" + katec_pt);
        katec_pt = GeoTrans.convert(1, 0, katec_pt);

        map.put("x", Double.valueOf(katec_pt.getX()));
        map.put("y", Double.valueOf(katec_pt.getY()));
        map.put("address", xPath.compile("/rss/channel/item[" + i + "]/address/text()").evaluate(doc, XPathConstants.STRING));
        map.put("name", xPath.compile("/rss/channel/item[" + i + "]/title/text()").evaluate(doc, XPathConstants.STRING));

        list.add(map);
      }

      SearchResult sr = new SearchResult();
      sr.setTotal(total);
      sr.setResultList(list);
      return sr;
    }
    catch (Exception e)
    {
      e.printStackTrace(); }
    return null;
  }
  
  public static String getHtml(String localSearchUrl, String authorization) {
		HttpURLConnection httpRequest = null;
		String resultValue = null;

		try {
			URL u = new URL(localSearchUrl);
			httpRequest = (HttpURLConnection) u.openConnection();
			httpRequest.setRequestProperty("Content-type", "text/xml; charset=UTF-8");
			httpRequest.setRequestProperty("Authorization", authorization);
			httpRequest.connect();
			
			int code = httpRequest.getResponseCode();
			if (code == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(httpRequest.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = in.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
				resultValue = sb.toString();
			} else {
				resultValue = "responseCodeError code : " + code;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultValue;
	}
  
  public static String getHtmlNaver(String localSearchUrl) {
		HttpURLConnection httpRequest = null;
		String resultValue = null;

		try {
			URL u = new URL(localSearchUrl);
			httpRequest = (HttpURLConnection) u.openConnection();
			httpRequest.setRequestMethod("GET");
			httpRequest.setRequestProperty("User-Agent", "curl/7.49.1");
					
			httpRequest.setRequestProperty("Content-type", "application/xml; charset=UTF-8");
			httpRequest.setRequestProperty("X-Naver-Client-Id", "hnhOmevJPrU_BdfnVxyP");
			httpRequest.setRequestProperty("X-Naver-Client-Secret", "LESdxB5em7");
			
			httpRequest.connect();
			
			int code = httpRequest.getResponseCode();
			if (code == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(httpRequest.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = in.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
				resultValue = sb.toString();
			} else {
				resultValue = "responseCodeError code : " + code;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultValue;
	}
		
}