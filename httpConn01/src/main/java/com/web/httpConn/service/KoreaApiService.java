package com.web.httpConn.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.httpConn.dao.KoreaApiDao;

@Service
public class KoreaApiService {

	@Autowired
	private KoreaApiDao koreaApiDao;
	
	public void insertKoreaApiList() throws Exception {
		
		try {
		String fullUrl = "http://overtherainbow.korea.kr/search/policyJson.do?startDate=20181204&endDate=20181204";
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
				
				if(koreaApiDao.selectKoreaApiIdCnt(map) == 0) {
					koreaApiDao.insertKoreaApiList(map);
				}
				list.add(map);
				
				map=null;
				
/*				String atchfileNmTmp = (String) tmp.get("atchfileNm");
				String atchfileUrl = (String) tmp.get("atchfileUrl");
				atchfileUrl = atchfileUrl.replaceAll("&amp;", "&");
				if(!atchfileNmTmp.equals("") || atchfileNmTmp == null) {
					String[] arrAtchFileNmList = atchfileNmTmp.split("\\|\\|",0);
					String[] arratchfileUrlList = atchfileUrl.split("\\|\\|",0);
					for(int j=0; j<arrAtchFileNmList.length; j++) {
						System.out.println(arrAtchFileNmList[j] + " / " + arratchfileUrlList[j] );
					}
				}*/
				
			}
			
			urlConn.disconnect();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public List<Map<String, Object>> selectKoreaApiList() throws Exception {
		return koreaApiDao.selectKoreaApiList();
	}
}
