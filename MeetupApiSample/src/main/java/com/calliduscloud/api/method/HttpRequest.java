package com.calliduscloud.api.method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.calliduscloud.model.City;
import com.calliduscloud.model.Event;

public class HttpRequest {
	
	public List<City> cities() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("https://api.meetup.com/2/cities?country=serbia");

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            
            String responseBody = httpclient.execute(httpget, responseHandler);
            List<City> cities = new ArrayList<City>();
            
            try {
                JSONObject myResponse = new JSONObject(responseBody.toString());
                JSONArray city = myResponse.getJSONArray("results");

                int n = city.length();
                for (int i = 0; i < n; i++) {
                    JSONObject obj = city.getJSONObject(i);

                    int id = obj.getInt("id");
                    String citiName = obj.getString("city");

                    City newCity = new City(id, citiName);
                    cities.add(newCity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return cities;
            
        } finally {
            httpclient.close();
        }
    }
	
	public List<Event> events() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("https://api.meetup.com/find/events?photo-host=public&sig_id=264274015&lon=20.457273&lat=44.787197&sig=779cff12059527bd80da26df818024b4f7843e4a");

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            
            String responseBody = httpclient.execute(httpget, responseHandler);
            List<Event> events = new ArrayList<Event>();
            
            try {
                JSONArray array = new JSONArray(responseBody.toString());

                int n = array.length();
                for (int i = 0; i < n; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String name = obj.getString("name");
                    String city = obj.getJSONObject("venue").getString("city");
                    
                    Event eve = new Event(name, city);
                    events.add(eve);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return events;
            
        } finally {
            httpclient.close();
        }
    }

}
