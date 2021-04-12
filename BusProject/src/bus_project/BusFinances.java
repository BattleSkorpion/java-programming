package bus_project;

public class BusFinances extends Finances
{
	public static double TICKET_PRICE = 49.99; 
	
	// default constructor will be called
	public BusFinances() 
	{
		nf.setCurrency(USD);
	}
	
	//TODO: method section comment
	public static double addCustomerProfit(Customer cstmr) 
	{
		return addProfit(cstmr.getNumPersons() * TICKET_PRICE);
	}
	
	public static double removeCustomerProfit(Customer cstmr)
	{
		return subtractProfit(cstmr.getNumPersons() * TICKET_PRICE); 
	}
}
