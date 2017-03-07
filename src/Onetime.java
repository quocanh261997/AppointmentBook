/*
 * Name: Quoc Anh Nguyen
 * Class: CSE 271 - Section A
 * Instructor: Dr. Matthew Stephan
 * Teaching Assistant: Gage Laufenberg
 * Date: 03/06/2017
 * Description: Create an Appointment application
 */

import java.util.GregorianCalendar;


public class Onetime extends Appointments {
	
	//Default constructor
	public Onetime(String description, GregorianCalendar date){
		super(description, date);
		
	}
	
	//Check to see if that appointment happens on that date
	@Override
	public boolean occursOn(int year, int month, int date){
		GregorianCalendar setDate = this.getDate();
		GregorianCalendar testDate = new GregorianCalendar(year, month, date);
		//Check whether the created date and the test date is the same
		//cause this is a onetime appointment
		return setDate.equals(testDate);
	}
	
	
}
