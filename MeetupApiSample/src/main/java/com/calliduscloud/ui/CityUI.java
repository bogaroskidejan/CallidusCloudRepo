package com.calliduscloud.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.calliduscloud.api.method.HttpRequest;
import com.calliduscloud.model.City;
import com.calliduscloud.model.Event;
import com.calliduscloud.utils.HelperClass;

public class CityUI {
	
	public static List<City> showAllCities() throws IOException {
		List<City> cities = new ArrayList<>();
    	HttpRequest hr = new HttpRequest();
    	for(City c : hr.cities()) {
    		cities.add(c);
    	}
    	
    	System.out.println("-------------------------------");
    	System.out.printf("%10s %20s", "City ID", "Name");
    	System.out.println();
    	System.out.println("-------------------------------");
   	    	for(City city : cities){
   	    		System.out.format("%10s %20s",
   	                city.getId(), city.getCity());
   	    		System.out.println();
   	    }
    	return cities;
	}
	
	public static City findCityById(int id) throws IOException {
		List<City> cities = new ArrayList<>();
    	HttpRequest hr = new HttpRequest();
    	for(City c : hr.cities()) {
    		cities.add(c);
    	}
    	
		City city = null;
		for (int i = 0; i < cities.size(); i++) {
			City c = cities.get(i);
			if (c.getId() == id) {
				city = c;
				break;
			}
		}
		return city;
	}
	
	public static City findCityById() throws IOException {
		City city = null;
		System.out.println("Type ID of city:");
		int id = HelperClass.readInt();
			city = findCityById(id);
		if (city == null)
			System.out.println("There is no city with ID " + id);
		return city;
	}
	
	public static String toStringEvents() throws IOException {
		City city = CityUI.findCityById();
		
		List<Event> events = new ArrayList<>();
    	HttpRequest hr = new HttpRequest();
    	for(Event e : hr.events()) {
    		events.add(e);
    	}
    	
		StringBuilder sb = new StringBuilder(city + " ");
		
				sb.append("have events\n");
				for(int i = 0;i < events.size();i++) {
					if(city.getCity().equals(events.get(i).getCity())){
						sb.append("\t" + events.get(i).toString() + "\n");
					}
				}
				
			return sb.toString();
	}

}
