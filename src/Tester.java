/*
 * Name: Quoc Anh Nguyen
 * Class: CSE 271 - Section A
 * Instructor: Dr. Matthew Stephan
 * Teaching Assistant: Gage Laufenberg
 * Date: 03/06/2017
 * Description: Create an Appointment application
 */

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

public class Tester {

	public static void main(String[] args) throws Exception {
		Scanner reader = new Scanner(System.in);
		while (true) {
			//Prompt the user to select an option
			System.out.println("What would you like to do?");
			System.out.println("1.Save an appointment\n2.Check appointments on a specific dates\n3.Quit");
			int ans = reader.nextInt();
			//Check if the user have the right input option
			while (ans < 1 || ans > 3) {
				System.out.println("Wrong option. Please try again!");
				ans = reader.nextInt();
			}
			//If the user choose to save an appointment
			if (ans == 1) {
				String type;
				//Keep asking for a type of the appointment until the user enters correctly
				do{
				System.out.print("Please specify the type (Daily, Onetime or Monthly): ");
				type = reader.next();
				reader.nextLine();
				}while(!type.equals("Daily")&&!type.equals("Onetime")&&!type.equals("Monthly"));
				
				//Prompt the user to enter a description for the appointment and 
				//keep asking if the user leaves it blank
				String description;
				do{
				System.out.print("Please enter the description for the appointment: ");
				description = reader.nextLine();
				} while (description.isEmpty());
				
				//Ask for the date that the user want to create for the first occurrence of the appointment
				//Keep asking if the date isn't valid
				int month;
				int day;
				int year;
				do{
				System.out.print("Please enter the date of the appointment according to the format (month/date/year): ");
				month = reader.nextInt();
				day = reader.nextInt();
				year = reader.nextInt();
				} while (!dateVerified(month, day, year));
				System.out.println();
				
				//Create a new appointment according to the type, description and started date
				Appointments app = createAppointment(type, description, year, month, day);
				
				//Save it locally to a file name appointments
				AppointmentBook.save(app, "appointments.txt");
			} 
			//If the user choose to load an appointment
			else if (ans == 2) {
				//Prompt the user for the correct date
				int month;
				int day;
				int year;
				do{
				System.out.print("Please choose the date (mm/dd/yy) to check your appointments: ");
				month = reader.nextInt();
				day = reader.nextInt();
				year = reader.nextInt();
				}while (!dateVerified(month, day, year));
				
				//Create a new AppointmentBook to store the appointments
				AppointmentBook book = new AppointmentBook();
				
				//Load all appointments from the locally saved file to the
				//AppointmentBook object
				book.load("appointments.txt");
				
				//Print the corresponding appointments that happen on that date
				printAppointment(book, year, month, day);
			} 
			//If the user choose to quit, terminate the program immediately
			else break;
		}
	}

	// Print the appointments that happen on a specified date
	public static void printAppointment(AppointmentBook a, int year, int month, int date) {
		ArrayList<Appointments> app = a.getList();
		//Set a counter for appointments that doesn't happen on that date
		int count = 0;
			//Iterate through the AppointmentBook and print out the corresponding appointments
			for (Appointments appointments : app) {
				if (appointments.occursOn(year, month, date)) {
					GregorianCalendar cal = appointments.getDate();
					String type = appointments.getClass().toString().substring(6);
					System.out.println(type + ": " + appointments.getDescription() + " - " + cal.get(Calendar.MONTH)
							+ "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR));
				}
				else count++;
			}
			//If there are no appointments on that day, print the following message
			if (count == app.size()) System.out.println("You don't have any appointments on that date!");
			System.out.println();
		}

	// Create a new appointment based on the user's input
	public static Appointments createAppointment(String type, String description, int year, int month, int date) {
		GregorianCalendar cal = new GregorianCalendar(year, month, date);
		if (type.equals("Onetime"))
			return new Onetime(description, cal);
		else if (type.equals("Daily"))
			return new Daily(description, cal);
		return new Monthly(description, cal);
	}
	
	//Check to see if the input date is a correct date
	public static boolean dateVerified(int month, int date, int year){
		//Since the Calendar class's month starts from 0, we have to
		//extract 1 unit from the month field
		GregorianCalendar a = new GregorianCalendar(year, month-1, date);
		//Check to see if the Calendar object's month is the same as we initiated
		//Example: Create a date 02/29/2017
		//It is invalid cause 2017 isn't a leap year so it doesn't have the 29th day of February
		//Because of that, Calendar object returns the date as March 1st 2017.
		//We later on check to see if the following month is the same as when we created
		//If so return true, else return a false date input
		if (a.get(Calendar.MONTH)!= (month-1)) return false;
		return true;
	}

}