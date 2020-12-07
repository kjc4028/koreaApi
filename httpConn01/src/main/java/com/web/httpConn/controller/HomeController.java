package com.web.httpConn.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		Calendar cal2 = new GregorianCalendar(Locale.KOREA);
		cal2.add(10, -1);
		SimpleDateFormat df2 = new SimpleDateFormat("HH");
		String targetHour = df2.format(cal2.getTime());
		SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
		String targetDay = df3.format(cal2.getTime());
		
		System.out.println(targetHour + " : " + targetDay);
		
		
		
		
		
		return "home";
	}
	
	
	@RequestMapping(value = "/insertNew")
	public String insertNews(Model model, @RequestParam Map<String, String> param
			,HttpServletRequest req) throws Exception {
		
		String referer = req.getHeader("referer");
		try {
			koreaApiService.insertKoreaApiList();
		} catch (Exception e) {
			System.out.println("ex");
			e.printStackTrace();
		}
		
		return "redirect:"+referer;
	}	
	
	@RequestMapping(value = "/xmlLoad", method = RequestMethod.GET)
	public String xmlLoad(Locale locale, Model model) throws IOException {
		
		  String fullUrl = "http://www.mafra.go.kr/gonews/xml/xml2.jsp?startDate=20200416&endDate=20200416";
		  //String fullUrl = "http://www.ktv.go.kr/function/exp.gov.kr?startDate=20200901&endDate=20200901";
	      URL url = new URL(fullUrl);
	      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	      
	      BufferedReader tempBufferedReader = null;
	      BufferedReader bufferedReader = null;
	      InputStream inputStream = null;
	      
	      conn.setDoOutput(true);
	      conn.setRequestMethod("GET");

	      tempBufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "euc-kr"));

	      String tempStr = "";
	      StringBuilder bufferedStr = new StringBuilder();
	      while ((tempStr = tempBufferedReader.readLine()) != null) {
	        bufferedStr.append(tempStr).append("\n");
	      }

	      inputStream = new ByteArrayInputStream(bufferedStr.toString().getBytes());
	      bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

	      System.out.println("===============================");
	      System.out.println(bufferedStr.toString());
	      
	      return "home";
	  
	  
	}
	
	
}
