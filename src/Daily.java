/*
 * Name: Quoc Anh Nguyen
 * Class: CSE 271 - Section A
 * Instructor: Dr. Matthew Stephan
 * Teaching Assistant: Gage Laufenberg
 * Date: 03/06/2017
 * Description: Create an Appointment application
 */

import java.util.GregorianCalendar;

public class Daily extends Appointments {
	
	//Default constructor
	public Daily(String description, GregorianCalendar date){
		//Call the Appointment constructor
		super(description, date);
	}
	
	//Override the method in Appointment class to check whether the appointment
	//happens on that date
	@Override
	public boolean occursOn(int year, int month, int date){
		GregorianCalendar setDate = this.getDate();
		GregorianCalendar testDate = new GregorianCalendar(year, month, date);
		//If the test date is before the created date of the appointment, return false
		if (setDate.compareTo(testDate)>0) return false;
		//Else always return true as this appointment happens daily
		return true;
	}
	
}
