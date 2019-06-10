package com.devdays.fhir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a quick Spring boot application I put together during the HL7 Dev
 * Days 2019 conference.
 * 
 * @author Ian Meinert
 *
 */
@SpringBootApplication
public class DevdaysApplication {

	/**
	 * The main method which runs the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(DevdaysApplication.class, args);
	}

}
