package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;

public class BusCalculation {
	private static ArrayList<LocalDate> dates; 
	private static ArrayList<Integer> busesPerDate;					// number of buses used per each date  
	private static ArrayList<ArrayList<Customer>> customers; 	 	// customers each date 
	
	/* constructors */
	
	public BusCalculation () {
		
	}
	
	/* method section */ 
	
	// schedule customer on date selected
	public static void scheduleTrip (Customer cstmr) {
		LocalDate date = cstmr.getDate(); 
		
		// if another booking from any customer exists on same date
		if (dates.contains(date)) {
			//// if another booking from identical customer exists on same date
			for (int i = 0; i < customers.get(dates.indexOf(date)).size(); i++) {
				if ()
				
				
				
				//// ask to merge customers? 
				//JOptionPane.showMessageDialog(null, "There already exists a customer ")
				//// if not merge
				
			}
			// another booking from another customer(s) exists on same date
			//else {
			//	customers.get(dates.indexOf(date)).add(cstmr); 
			//}
			
		}
		// else (no booking on date)
		else {	
			
		}
	}
}