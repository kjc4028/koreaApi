package com.web.httpConn.controller;

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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.httpConn.service.KoreaApiService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private KoreaApiService koreaApiService;
	
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
		
/*		try {
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
			
			List<Map<String, Object>> list = new ArrayList<>();
			for(int i = 1; i<result.size();i++){
				Map<String, Object> map = new HashMap<>();
				 
				JSONObject tmp = (JSONObject)result.get(i);
				
				map.put("subject", tmp.get("subject"));
				map.put("publishOrg", tmp.get("publishOrg"));
				map.put("contentsKor", tmp.get("contentsKor"));
				map.put("atchfileUrl", tmp.get("atchfileUrl"));
				map.put("atchfileNm", tmp.get("atchfileNm"));
				map.put("contentId", tmp.get("contentId"));
				map.put("policyType", tmp.get("policyType"));
				map.put("originUrl", tmp.get("originUrl"));
				map.put("viewCnt", tmp.get("viewCnt"));
				map.put("regDate", tmp.get("regDate"));
				list.add(map);
				map=null;
				
				String atchfileNmTmp = (String) tmp.get("atchfileNm");
				String atchfileUrl = (String) tmp.get("atchfileUrl");
				atchfileUrl = atchfileUrl.replaceAll("&amp;", "&");
				if(!atchfileNmTmp.equals("") || atchfileNmTmp == null) {
					String[] arrAtchFileNmList = atchfileNmTmp.split("\\|\\|",0);
					String[] arratchfileUrlList = atchfileUrl.split("\\|\\|",0);
					for(int j=0; j<arrAtchFileNmList.length; j++) {
						System.out.println(arrAtchFileNmList[j] + " / " + arratchfileUrlList[j] );
					}
				}
			}
			
			urlConn.disconnect();
			model.addAttribute("list", list);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			
		return "home";
	}
	
	
	@RequestMapping(value = "/insertNew")
	public String insertNews(Model model, @RequestParam Map<String, String> param
			,HttpServletRequest req) throws Exception {
		
		String referer = req.getHeader("referer");
		koreaApiService.insertKoreaApiList();
		
		return "redirect:"+referer;
	}	
	
}
