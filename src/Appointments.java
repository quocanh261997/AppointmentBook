/*
 * Name: Quoc Anh Nguyen
 * Class: CSE 271 - Section A
 * Instructor: Dr. Matthew Stephan
 * Teaching Assistant: Gage Laufenberg
 * Date: 03/06/2017
 * Description: Create an Appointment application
 */

import java.util.GregorianCalendar;


public abstract class Appointments {
	//Instant variables holding the description and date
	private String description;
	private GregorianCalendar date;

	//Default constructor
	public Appointments (String description, GregorianCalendar date){
		this.description = description;
		this.date = date;

	}
	
	//Public Getter and Setter
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
	//Abstract method that other subclasses must overwrite
	public abstract boolean occursOn(int year, int month, int date);
	
}
