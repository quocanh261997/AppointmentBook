/*
 * Name: Quoc Anh Nguyen
 * Class: CSE 271 - Section A
 * Instructor: Dr. Matthew Stephan
 * Teaching Assistant: Gage Laufenberg
 * Date: 03/06/2017
 * Description: Create an Appointment application
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class AppointmentBook {
	//Declare an array list to hold the appointments
	private ArrayList<Appointments> a;
	
	//Use constant to represent different types of appointments
	public final int ONETIME = 0;
	public final int DAILY = 1;
	public final int MONTHLY = 2;
	
	//Default constructor
	public AppointmentBook(){
		//Initialize the ArrayList
		a = new ArrayList<Appointments>();
	}
	
	//Add a new appointment to the appointment book
	public void add(int a, String description, GregorianCalendar date){
		Appointments app;
		//Check to see what type of appointment the user want to create
		// and create a new object of that appointment type
		if (a==this.ONETIME){
			app =  new Onetime(description, date);
		}else if (a==this.DAILY){
			app = new Daily(description, date);
		}
		else {
			app = new Monthly(description, date);
		}
		//Finally add this appointment to the appointment book
		this.a.add(app);
	}
	
	//A static method to save an appointment to a file 
	public static void save(Appointments app, String file) throws IOException{
		//Declare a new PrintWriter, allowing to append the file later on
		//without erasing all the existing data
		PrintWriter out = new PrintWriter(new FileWriter(file,true));
		//Extract the name of the type of the appointment
		String type = app.getClass().toString().substring(6);
		//Get the date of the appointment in GregorianCalendar type
		GregorianCalendar a = app.getDate();
		//Write the information of the type, description and started date of the appointment according to a format
		out.println(type + ": " + app.getDescription() + " - " + a.get(Calendar.MONTH)+"/"+a.get(Calendar.DATE)+"/"+a.get(Calendar.YEAR));
		//Close the PrintWriter
		out.close();
	}
	
	//Load all the appointments in a file to an AppointmentBook object
	public void load(String file) throws IOException{
		Scanner reader = new Scanner(new File(file));
		//Loop through the file for every appointment there is
		while(reader.hasNextLine()){
			String a = reader.nextLine();
			//Get the type of the appointment
			String type = a.substring(0,a.indexOf(':'));
			//Get the description of the appointment
			String description = a.substring(a.indexOf(':')+2,a.indexOf('-')-1);
			//Get the created date of the appointment
			int month = Integer.parseInt(a.substring(a.indexOf('-')+2,a.indexOf('/')));
			int day = Integer.parseInt(a.substring(a.indexOf('/')+1,a.lastIndexOf('/')));
			int year = Integer.parseInt(a.substring(a.lastIndexOf('/')+1));
			GregorianCalendar cal = new GregorianCalendar(year, month, day);
			//Check to see which kind of appointment it is and then add it to the 
			//AppointmentBook object
			if (type.equals("Onetime")) this.add(0, description, cal);
			else if (type.equals("Daily")) this.add(1, description, cal);
			else this.add(2, description, cal);
		}
		reader.close();
	}
	
	//Get the ArrayList of appointments
	public ArrayList<Appointments> getList(){
		return this.a;
	}
		
}
