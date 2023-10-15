package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class test {
	
	public static final String serviceKey = "a6DTmTBcXqCCeghCkSZ9%2BcJqbQboL6RFQbbHccmXHvqx2nHA1BtYTfTuOfDsXZcV30LqFRZ62b1W8tO54a3cwQ%3D%3D";

	public static void main(String[] args) throws IOException {
		
		String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
		
		url += "?serviceKey=" + serviceKey;
		url += "&numOfRows=215";
		url += "&_type=json";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		while((line = br.readLine()) != null) {
			
			System.out.println(line);
		}
		br.close();
		urlConnection.disconnect();
		

	}

}
