package it.polimi.awt.mpcs.service.impl;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import it.polimi.awt.mpcs.domain.SeedQuery;
import it.polimi.awt.mpcs.service.FreeBaseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

/**
 * @author Claudio
 *Service Class with the duty of implement freebase search algorithm
 */
@Service
public class FreeBaseSearch implements FreeBaseService {
	public static Properties properties = new Properties();
	private String queryValue;
	private List<String> freeBaseResult = new ArrayList<String>();
	private List<String> mountainRange = new ArrayList<String>();
	private List<String> oldMountainList = new ArrayList<String>();
	private List<SeedQuery> seedList = new ArrayList<SeedQuery>();

	

	/* (non-Javadoc)
	 * @see it.polimi.awt.mpcs.service.FreeBaseService#mountainRangeSearch(it.polimi.awt.mpcs.domain.SeedQuery)
	 */
	public List<SeedQuery> mountainRangeSearch(List<SeedQuery> oldSeedList) {

		properties.setProperty("API_KEY","AIzaSyAsLI5aIbTd8xwKAiVYwLyklO-lQZLmSD0");
        oldMountainList = oldSeedList.stream().map(SeedQuery::getName).collect(Collectors.toList());
        int count = 0;
		for (String item : oldMountainList) {
			//queryValue = seed.getName();
			count++;
			queryValue = item;
			try {
				System.out.println(count + "   " + oldMountainList.size()  );
				HttpTransport httpTransport = new NetHttpTransport();
				HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
				JSONParser parser = new JSONParser();
				GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
				url.put("query", queryValue);
				url.put("filter", "(any type:/geography/mountain)");
				url.put("limit", "1");
				url.put("indent", "true");
				url.put("output", "(/geography/mountain/mountain_range)");
				//url.put("lang" ,"d/en
				url.put("key", properties.get("API_KEY"));
				
				HttpRequest request = requestFactory.buildGetRequest(url);
				HttpResponse httpResponse = request.execute();
				JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
				JSONArray results = (JSONArray) response.get("result");

				if (results.size() > 0) {
					JSONObject j1 = (JSONObject) results.get(0);
					JSONObject j2 = (JSONObject) j1.get("output");
					JSONObject j3 = (JSONObject) j2.get("/geography/mountain/mountain_range");
					JSONArray j4 = (JSONArray) j3.get("/geography/mountain/mountain_range");
					if (j4 != null) {
						for (Object range : j4) {
							//if(!oldMountainRange.contains(JsonPath.read(range, "$.name").toString())){
							mountainRange.add(JsonPath.read(range, "$.name").toString());
							//oldMountainRange.add(JsonPath.read(range, "$.name").toString());
							//}
						}
					}
				}
			}
			 catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		mountainNameSearch();
		return seedList;
	}
	
	/**
	 *  this method gets all mountainrange contained in mountainRangeList and finds all the mountains name inside it. Then it saves the mountain names in FreebaseResult list
	 */
	public void mountainNameSearch() {

		// rimuovo i duplicati
		Set<String> hs = new HashSet<>();
		hs.addAll(mountainRange);
		mountainRange.clear();
		mountainRange.addAll(hs);
int count = 0;
		for (String item : mountainRange) {
			queryValue = item;
			count++;
			try {
				System.out.println(count + "   " + mountainRange.size()  );
				HttpTransport httpTransport = new NetHttpTransport();
				HttpRequestFactory requestFactory = httpTransport
						.createRequestFactory();
				JSONParser parser = new JSONParser();
				GenericUrl url = new GenericUrl(
						"https://www.googleapis.com/freebase/v1/search");
				url.put("query", queryValue);
				url.put("filter", "(any type:/geography/mountain_range )");
				url.put("limit", "1");
				url.put("output", "(/geography/mountain_range/mountains)");
				url.put("indent", "true");
				url.put("key", properties.get("API_KEY"));
				HttpRequest request = requestFactory.buildGetRequest(url);
				HttpResponse httpResponse = request.execute();
				JSONObject response = (JSONObject) parser.parse(httpResponse
						.parseAsString());
				JSONArray results = (JSONArray) response.get("result");

				if (results.size() > 0) {
					JSONObject j1 = (JSONObject) results.get(0);
					JSONObject j2 = (JSONObject) j1.get("output");
					JSONObject j3 = (JSONObject) j2.get("/geography/mountain_range/mountains");
					JSONArray j4 = (JSONArray) j3.get("/geography/mountain_range/mountains");
					if (j4 != null) {
						for (Object range : j4) {
							freeBaseResult.add(JsonPath.read(range, "$.name").toString());
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		mountainSeedSearch();
	}


	/**
	 * This method gets form FReeBaseresult List the name of all the mountains founded before and finds additional information like longitude latitude...
	 */
	public void mountainSeedSearch() {

		// rimuovo i duplicati
		Set<String> hs = new HashSet<>();
		hs.addAll(freeBaseResult);
		freeBaseResult.clear();
		freeBaseResult.addAll(hs);
int count =0;
		for (String item : freeBaseResult) {
			queryValue = item;
			count++;
			System.out.println(count + "   " + freeBaseResult.size()  );
			try {
				HttpTransport httpTransport = new NetHttpTransport();
				HttpRequestFactory requestFactory = httpTransport
						.createRequestFactory();
				JSONParser parser = new JSONParser();
				GenericUrl url = new GenericUrl(
						"https://www.googleapis.com/freebase/v1/search");
				url.put("query", queryValue);
				url.put("filter", "(all type:/geography/mountain)");
				url.put("limit", "5");
				url.put("indent", "true");
				url.put("output", "(/location/location/geolocation)");
				url.put("key", properties.get("API_KEY"));
				HttpRequest request = requestFactory.buildGetRequest(url);
				HttpResponse httpResponse = request.execute();
				JSONObject response = (JSONObject) parser.parse(httpResponse
						.parseAsString());
				JSONArray results = (JSONArray) response.get("result");
				SeedQuery mySeed;
				List<String> seedListString = seedList.stream().map(SeedQuery::getName).collect(Collectors.toList());

				for (Object result : results) {
					mySeed = new SeedQuery();
					mySeed.setName(JsonPath.read(result, "$.name").toString());
					if (!oldMountainList.contains(mySeed.getName()) && !seedListString.contains(mySeed.getName())) {
						JSONObject j2 = (JSONObject) ((JSONObject) result)
								.get("output");
						JSONObject j3 = (JSONObject) j2
								.get("/location/location/geolocation");
						JSONArray j4 = (JSONArray) j3
								.get("/location/location/geolocation");

						if (j4 != null) {
							if (j4.size() > 0) {
								JSONObject j5 = (JSONObject) j4.get(0);
								mySeed.setLat(JsonPath.read(j5, "$.latitude").toString());
								mySeed.setLng(JsonPath.read(j5, "$.longitude").toString());
								seedList.add(mySeed);
								System.out.println("seedtrovato  "+ mySeed.getName());
							}
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}
