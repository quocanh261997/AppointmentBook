/*
 * Name: Quoc Anh Nguyen
 * Class: CSE 271 - Section A
 * Instructor: Dr. Matthew Stephan
 * Teaching Assistant: Gage Laufenberg
 * Date: 03/06/2017
 * Description: Create an Appointment application
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Monthly extends Appointments{
	
	//Default constructor
	public Monthly(String description, GregorianCalendar date){
		super(description, date);
	}
	
	//Check to see if the appointment happens on a date
	@Override
	public boolean occursOn(int year, int month, int date){
		GregorianCalendar setDate = this.getDate();
		GregorianCalendar testDate = new GregorianCalendar(year, month, date);
		//If the test date is before the set date, return false;
		if (setDate.compareTo(testDate)>0) return false;
		//Else check to see if the date of the test date is equal to that of
		//the created date since this happens monthly
		return setDate.get(Calendar.DATE)==testDate.get(Calendar.DATE);
	}
	
}
