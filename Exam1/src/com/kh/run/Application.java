package com.kh.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	
	// serviceKey 값
	public static final String serviceKey = "a6DTmTBcXqCCeghCkSZ9%2BcJqbQboL6RFQbbHccmXHvqx2nHA1BtYTfTuOfDsXZcV30LqFRZ62b1W8tO54a3cwQ%3D%3D";

	public static void main(String[] args) throws IOException {
		
		// URL
		String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
		
		url += "?serviceKey=" + serviceKey;
		url += "&numOfRows=5"; //한 페이지 결과 수 -파라미터값 추가하려면
		url += "&_type=json"; // 기본값 : xml -한개더 추가 하고 싶다면
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		StringBuffer responseBuffer = new StringBuffer(); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		while((line = br.readLine())!=null) {
//			System.out.println(line);
			responseBuffer.append(line);
		}
		br.close();
		urlConnection.disconnect();
		
		// JSON 파싱 시작
		String responseData = responseBuffer.toString();
		JSONObject jsonResponse = new JSONObject(responseData);
		
		// 구조 파악 (콘솔에 나오는거 보고 {-객체 [-배열 )
		JSONObject response = jsonResponse.getJSONObject("response");
		JSONObject body = response.getJSONObject("body");
		JSONObject items = body.getJSONObject("items");
		JSONArray item = items.getJSONArray("item");
		
//		System.out.println(response);
//		System.out.println(body);
//		System.out.println(items);
		System.out.println(item);
		
		for(int i=0; i<item.length(); i++) {
			JSONObject result = item.getJSONObject(i);
//			System.out.println(result);
			String careNm = result.getString("careNm");
			String careAddr = result.getString("careAddr");
			System.out.println("동물보호센터명 : " + careNm);
			System.out.println("주소 : " + careAddr);
			System.out.println();
		}
	
	
	}

}
