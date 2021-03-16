package bus_project;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

public class ModifyCustomer extends AbstractProgramWindow {

	protected Shell shlModifyCustomers;
	private ArrayList<Customer> customers; 
	//private ArrayList<Customer> modifiedCustomers = new ArrayList<Customer>(); 		// LIST OF MODIFIED CUSTOMERS (BUT WILL STORE ORIGINAL CUSTOMER BEFORE MODIFICATION) 
	private Table customersTable;
	private Text nameField;
	private Text sizeField;
	private Text indexField;
	private DateTime dateTime;
	
	public ModifyCustomer (ArrayList<Customer> cstmrs) {
		customers = cstmrs; 
	}
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(Shell rootShell)
	/****************************************************************/
	/* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	/* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	/****************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Display display = Display.getDefault();			// MANAGES THE CONNECTION BETWEEN SWT 
														// AND THE UNDERLYING OPERATING SYSTEM 
		
		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlModifyCustomers.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlModifyCustomers.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlModifyCustomers.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlModifyCustomers.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Shell rootShell) {
		shlModifyCustomers = new Shell();
		shlModifyCustomers.setSize(600, 400);
		shlModifyCustomers.setText("Modify Customers");
		
		Button btnExit = new Button(shlModifyCustomers, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(499, 326, 75, 25);
		
		customersTable = new Table(shlModifyCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		
		customersTable.setToolTipText("");
		customersTable.setLinesVisible(true);
		customersTable.setBounds(10, 40, 316, 252);
		if (customers.size() > 0) {
			updateTable(customersTable, customers); 
		}
		
		Label lblCustomers = new Label(shlModifyCustomers, SWT.NONE);
		lblCustomers.setText("Customers: ");
		lblCustomers.setBounds(140, 10, 63, 15);
		
		Label lblName = new Label(shlModifyCustomers, SWT.NONE);
		lblName.setText("Name:");
		lblName.setBounds(341, 13, 73, 15);
		
		nameField = new Text(shlModifyCustomers, SWT.BORDER);
		nameField.setText("");
		nameField.setBounds(420, 10, 80, 24);
		
		sizeField = new Text(shlModifyCustomers, SWT.BORDER);
		sizeField.setBounds(420, 40, 80, 24);
		
		Label lblSize = new Label(shlModifyCustomers, SWT.NONE);
		lblSize.setText("Group size: ");
		lblSize.setBounds(341, 43, 73, 15);
		
		Label lblNumber = new Label(shlModifyCustomers, SWT.NONE);
		lblNumber.setText("Index: ");
		lblNumber.setBounds(341, 71, 55, 15);
		
		indexField = new Text(shlModifyCustomers, SWT.BORDER);
		indexField.setBounds(420, 70, 80, 24);
		
		Label lblTripDate = new Label(shlModifyCustomers, SWT.NONE);
		lblTripDate.setText("Trip Date: ");
		lblTripDate.setBounds(341, 92, 73, 15);
		
		dateTime = new DateTime(shlModifyCustomers, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(341, 113, 233, 151);

		Button btnModify = new Button(shlModifyCustomers, SWT.NONE);
		btnModify.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int confirmModify; 
				Customer selectedCustomer; 
				Text[] fields = {nameField, sizeField, indexField}; 
				
				selectedCustomer = customers.get(customersTable.getSelectionIndex()); 
				confirmModify = JOptionPane.showConfirmDialog(null, "Confirm", "Are you sure you want to modify these customers?", JOptionPane.YES_NO_OPTION); 
				
				if (confirmModify == 1) 								// no option
				{
					//updateTable(customersTable, customers); 			// reset table
					updateCustomerInfoDisplay(selectedCustomer);
					return; 
				}
				else 													// yes option
				{
					if (legalCustomerModification(customers)) {
						modifyCustomer(selectedCustomer);
						clearInput(fields); 							// customer now modified 
						updateTable(customersTable, customers); 
					}
					else {
						updateCustomerInfoDisplay(selectedCustomer);	// reset modification if invalid modification 
						JOptionPane.showMessageDialog(null, "Invalid modification."); 
					}
				}
			}
		});
		btnModify.setText("Modify");
		btnModify.setBounds(425, 267, 75, 25);
		
		customersTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Customer selectedCustomer = customers.get(customersTable.getSelectionIndex()); 
				
				updateCustomerInfoDisplay(selectedCustomer); 
			}
		});
		
		btnExit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				rootShell.forceActive(); 
				shlModifyCustomers.close(); 	
			}
		});
	}
	
	// modify customer
	// precondition: legal modifications 
	private void modifyCustomer(Customer slctdCstmr) {
		int index = Integer.parseInt(indexField.getText()); 	// POSSIBLY NEW INDEX OF SELECTED CUSTOMER
		int old_index = slctdCstmr.getIndex();  				// PREVIOUS INDEX OF SELECTED CUSTOMER (OR THE SAME) 
		Customer temp; 
		
		if (index != old_index) {		// if new index swap positions 
			temp = customers.get(index); 
			customers.set(index, slctdCstmr); 
			customers.set(old_index, temp); 
			
		}
		
		setCustomerDetails(slctdCstmr, nameField, sizeField, indexField, index, dateTime); 
	}
	
	private int getDateTimeYear(Customer cstmr) {	// returns year in format acceptable for DateTime
		return cstmr.getDate().getYear(); 
	}
	
	private int getDateTimeMonth(Customer cstmr) {
		return cstmr.getDate().getMonthValue() - 1; 
	}
	
	private int getDateTimeDay(Customer cstmr) {
		return cstmr.getDate().getDayOfMonth(); 
	}
	
	private void updateCustomerInfoDisplay(Customer cstmr) {
		nameField.setText(cstmr.getName());
		sizeField.setText(Integer.toString(cstmr.getNumPersons()));
		indexField.setText(Integer.toString(customersTable.getSelectionIndex()));
		dateTime.setDate(getDateTimeYear(cstmr), getDateTimeMonth(cstmr), getDateTimeDay(cstmr));
	}
	
	private boolean legalCustomerModification(ArrayList<Customer> cstmrs) {
		
		if (nameField.getText().equals("") || sizeField.getText().equals("") || Integer.parseInt(indexField.getText()) < 0 || Integer.parseInt(indexField.getText()) >= cstmrs.size() || !vaildDate(dateTime))
		{
			return false; 
		}
		else 
		{
			return true; 
		} 
	}
}
