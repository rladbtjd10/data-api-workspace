package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	
	private static final String serviceKey = "a6DTmTBcXqCCeghCkSZ9%2BcJqbQboL6RFQbbHccmXHvqx2nHA1BtYTfTuOfDsXZcV30LqFRZ62b1W8tO54a3cwQ%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="air.do", produces = "application/json; charset=utf-8")
	public String airPollution(String location) throws IOException {
		
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		
		//url에 parameter 정보들 추가하기
		//- serviceKey : 공공데이터포털에서 훈련생 개인이 승인받은 OpenAPI 인증키
		url += "?serviceKey" + serviceKey;
		//- returnType (json으로 출력결과 받음)
		url += "&returnType=json";
		//- sidoName : 서울, 부산, 대전
		String sidoName = "서울";
		url += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8");
		
		
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine())!=null) {
			responseText += line;
		}
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	
	
	}

}

