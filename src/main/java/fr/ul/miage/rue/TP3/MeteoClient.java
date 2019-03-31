package fr.ul.miage.rue.TP3;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.ul.miage.meteo.json.Example;

public class MeteoClient {
	
	private static final Logger LOG = Logger.getLogger(MeteoClient.class.getName());
	
	private static String WEBSERVICE="http://api.openweathermap.org/data/2.5/";
	private String apiKey;
	private String city;
	private String country;
	
	public MeteoClient() {
		this("94525285cdb61add63c7d467fe2b3999","Nancy","fr");
	}
	
	public MeteoClient(String city) {
		this("94525285cdb61add63c7d467fe2b3999",city,"fr");
	}
	
	public MeteoClient(String city,String country) {
		this("94525285cdb61add63c7d467fe2b3999",city,country);
	}
	
	public MeteoClient(String apiKey, String city, String country) {
		setApiKey(apiKey);
		setCity(city);
		setCountry(country);
	}
	
	public String buildRequest() {
		String request = WEBSERVICE + "weather?" + "q=" + getCity() + "," + getCountry() + "&APPID=" + getApiKey();
		return request;
	}
	
	public String getJsonWeatherByCityName() {
		String res = null;
		String request = buildRequest();
		try {
			Client client = Client.create();
			WebResource r = client.resource(request);
			r.accept("application/json");
			ClientResponse response = r.get(ClientResponse.class);
			if(response.getStatus() != 200) {
				LOG.severe("Erreur de requête:"+request + "(code:"+response.getStatus() + ")");
				return null;
			}
			res = response.getEntity(String.class);
		} catch(Exception e) {
			LOG.severe("Erreur de webservice:" + request);
			return null;
		}
		return res;
	}
	
	public Example getWeatherByCityName() {
		Example res = null;
		String tmp = getJsonWeatherByCityName();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		res = gson.fromJson(tmp, Example.class);
		return res;
	}
	
	public String getWEBSERVICE() {
		return WEBSERVICE;
	}
	public void setWEBSERVICE(String wEBSERVICE) {
		WEBSERVICE = wEBSERVICE;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
 

}
