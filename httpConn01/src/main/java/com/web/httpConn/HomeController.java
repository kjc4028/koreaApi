package com.web.httpConn;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		try {
		String fullUrl = "http://overtherainbow.korea.kr/search/policyJson.do?startDate=20181205&endDate=20181205";
		URL url; 
		HttpURLConnection urlConn;
		BufferedReader br;
		String json;
		
			url = new URL(fullUrl);
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("GET");
			urlConn.connect();
			
			br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8"));
			
			json = br.readLine();
			
			JSONParser parser = new JSONParser();
			
			JSONObject obj = (JSONObject)parser.parse(json);
			
			JSONObject response = (JSONObject)obj.get("response");
			JSONArray result = (JSONArray)response.get("result");
			
			for(int i = 0; i<result.size();i++){
				JSONObject tmp = (JSONObject)result.get(i);
				System.out.println(tmp.get("subject"));
			}
			//System.out.println("123: "+json.length()+"/"+obj.size()+"/");
			
			urlConn.disconnect();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
	        // 응답 헤더의 정보를 모두 출력
/*	        for (Map.Entry<String, List<String>> header : urlConn.getHeaderFields().entrySet()) {
	            for (String value : header.getValue()) {
	                System.out.println(header.getKey() + " : " + value);
	            }
	        }
	        

				try {
		        	SAXBuilder builder = new SAXBuilder();
		        	Document jdomDoc = builder.build(url);;
		        	Element root = jdomDoc.getRootElement().getChild("root");
		        	System.out.println(root.getValue());
				} catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

        	
        	
	        // 응답 내용(BODY) 구하기        
/*	        try (
	        		InputStream in = urlConn.getInputStream();
	                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
         
	            byte[] buf = new byte[1024 * 8];
	            int length = 0;
	            while ((length = in.read(buf)) != -1) {
	                out.write(buf, 0, length);
	            }
	            
	            System.out.println(new String(out.toByteArray(), "UTF-8"));            
	        }*/
	        

			
		return "home";
	}
	
}
