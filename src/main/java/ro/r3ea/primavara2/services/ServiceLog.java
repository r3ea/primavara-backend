package ro.r3ea.primavara2.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class ServiceLog {

	
	public ServiceLog() {
		System.out.println("CREATING NEW SERVICE LOG INSTANCE");
		try {
			// db connection
			Thread.sleep(5000); // wait 5 seconds
		}catch(Exception e) {
			
		}
		System.out.println("NEW SERVICE LOG INSTANCE CREATED");
		
	}
	
	public void logMessageEndpoint() {
		System.out.println("===================================");
		Date now = new Date();
		System.out.println("***ENDPOINT ACCESSED*** " + now);
		System.out.println("===================================");
	}
	
	public void logMessageEndpoint(String endpoint) {
		System.out.println("===================================");
		Date now = new Date();
		System.out.println("***ENDPOINT "+endpoint+" ACCESSED*** " + now);
		System.out.println("===================================");
	}
	
	
}
