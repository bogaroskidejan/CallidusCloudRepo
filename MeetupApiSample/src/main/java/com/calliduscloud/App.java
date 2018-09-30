package com.calliduscloud;

import java.io.IOException;

import com.calliduscloud.ui.CityUI;
import com.calliduscloud.utils.HelperClass;

public class App {
  
	public static void main( String[] args ) throws IOException{
    	
    	    int decision  = -1;
    		while (decision  != 0) {
    			App.menu();
    			System.out.print("Option:");
    			decision  = HelperClass.readInt();
    			switch (decision) {
    			case 0:
    				System.out.println("Exit from program!");
    				break;
    			case 1:
    				CityUI.showAllCities();
    				break;
    			/*
    			* 
    			* Ideja je nakon get svih gradova, pokupiti sve dogadjaje iz rs, nakon toga 
    			* proveriti koji se dogadjaj odnosi za koji grad
    			* 
    			*/    			
    			case 2:
    				CityUI.toStringEvents();
    				break;
    			default:
    				System.out.println("Invalid command!");
    				break;
    			}
    		}
    }
	
	public static void menu() {
		System.out.println("Meetup API:");
		System.out.println("\tOption 1 - Show all cities");
		System.out.println("\tOption 2 - Show events by city");
		System.out.println("\t\t ...");
		System.out.println("\tOption 0 - EXIT");
	}
	
}
