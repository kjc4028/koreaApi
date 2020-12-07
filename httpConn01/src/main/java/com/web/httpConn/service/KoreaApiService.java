package com.web.httpConn.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
		int cnt = 0;
		HttpURLConnection urlConn = null;
		BufferedReader br = null;
		int insertCnt = 0;
		try {
			Calendar cal2 = new GregorianCalendar(Locale.KOREA); 
	        cal2.add(10, -1); 
	        SimpleDateFormat df2 = new SimpleDateFormat("HH"); 
	        String targetHour = df2.format(cal2.getTime()); 
	        SimpleDateFormat df3 = new SimpleDateFormat("yyyyMMdd"); 
	        String targetDay = df3.format(cal2.getTime());
	        
	        
		//String fullUrl = "http://www.korea.kr/search/policyJson.do?startDate="+targetDay+"&endDate="+targetDay;
		//String fullUrl = "https://www.kisa.or.kr/business/kisareportjson.jsp?startDate=20200101&endDate=20200301";
		String fullUrl = "https://www.kaeri.re.kr/OpenAPI24?startDate=20200301&endDate=20200331";
		URL url; 

		String json;
		
			url = new URL(fullUrl);
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("GET");
			/*try {
				urlConn.connect();
			} catch (Exception e) {
				urlConn.disconnect();
				return;
			}*/
			urlConn.setConnectTimeout(1000*5);
			urlConn.setReadTimeout(1000*5);
			
			
			br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"UTF-8"));
			System.out.println(">>>>>>>>>>>>>br");
			StringBuffer sbf = new StringBuffer();
			String str = "";
			int cnt2 = 0;
			while((str = br.readLine()) != null){
				cnt2++;
				StringBuffer sbf2 = new StringBuffer();
				sbf.append(str);
				System.out.println(cnt2 + " " + str);
			}
			
			System.out.println(sbf.toString());
			
/*			json = br.readLine();
			
			System.out.println(">>>>>>>>>>>>>json");
			System.out.println(json.toString());
			
			JSONParser parser = new JSONParser();
			
			JSONObject obj = (JSONObject)parser.parse(json.toString());
			System.out.println(">>>>>>>>>>>>>obj");
			System.out.println(obj.toString());
			
			
			JSONObject response = (JSONObject)obj.get("response");
			JSONArray result = (JSONArray)response.get("result");
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>result");
			System.out.println(result.toString());

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();*/
			/*for(int i = 1; i<result.size();i++){
				Map<String, Object> map = new HashMap<String, Object>();
				 cnt++;
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
					insertCnt++;
				}
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
				
			}*/
			

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(br != null){
					System.out.println("br used "+br.read());
					br.close();
				}
				if(urlConn != null){
					urlConn.disconnect();
					System.out.println("urlConn.disconnect()");
				}
			}
		System.out.println("totalCount: " + cnt);
		System.out.println("insertCnt: " + insertCnt);
		
	}
	
	public List<Map<String, Object>> selectKoreaApiList() throws Exception {
		return koreaApiDao.selectKoreaApiList();
	}
}
