package bus_project;

import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														//INTERFACE.
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.widgets.Display;					// RESPONSIBLE FOR MANAGING THE CONNECTION 
														// BETWEEN SWT AND THE UNDERLYING OPERATING
														// SYSTEM.
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 	
import org.eclipse.swt.widgets.Button;					// REPRESENTS A SELECTABLE USER INTERFACE 
														// OBJECT THAT ISSUES NOTIFICATION WHEN 
														// PRESSED AND RELEASED. 
import org.eclipse.swt.widgets.Label;					// REPRESENTS A NON-SELECTABLE USER INTERFACE 
														//OBJECT THAT DISPLAYS A STRING OR IMAGE 
														//(OR HORIZONTAL/VERTICAL LINE).
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.widgets.Table;

import org.eclipse.swt.widgets.TableColumn;

public class ListCustomers extends AbstractProgramWindow 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	//TODO: 
	protected Shell shlListCustomers;
	private ArrayList<Customer> customers; 					// LIST OF CUSTOMERS
	private ArrayList<Customer> customersSorted; 			// SORTED LIST OF CUSTOMERS 
															// (by designated filter)
	private Table customersTable;							
	private int sortBy; 									// sort by filter		
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/ 
	public ListCustomers (ArrayList<Customer> cstmrs) 
	{
		customers = cstmrs;  
	}
	
	/****************************
	 * Open the window.			*
	 * @wbp.parser.entryPoint	*
	 ****************************/
	//TODO: label method more
	public void open(Shell rootShell)
	/****************************************************************/
	/* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	/* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	/****************************************************************/
	{	
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Display display = Display.getDefault();				// MANAGES THE CONNECTION BETWEEN SWT 
															// AND THE UNDERLYING OPERATING SYSTEM 

		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlListCustomers.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlListCustomers.forceActive();						// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlListCustomers.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shlListCustomers.isDisposed()) 
		{
			/*******************************************/
			/* SLEEP DISPLAY IF THERE IS NOTHING TO DO */
			/*******************************************/
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	protected void createContents(Shell rootShell)
	{
		//TODO: label method calls/shell
		shlListCustomers = new Shell();
		shlListCustomers.setSize(550, 360);
		shlListCustomers.setText(Messages.getString("ListCustomers.shlListCustomers.text")); //$NON-NLS-1$
		
		//TODO: label button
		Button btnExit = new Button(shlListCustomers, SWT.NONE);
		//TODO: label method calls
		btnExit.setText(Messages.getString("btnExit.text")); //$NON-NLS-1$
		btnExit.setBounds(10, 289, 516, 25);
		
		// update table with sorted list, sort list depending on sort method specified
		
		//TODO: label method calls
		sortCustomers();
		
		Label lblCustomers = new Label(shlListCustomers, SWT.CENTER);
		lblCustomers.setText(Messages.getString("lblCustomers.text")); //$NON-NLS-1$
		lblCustomers.setBounds(10, 10, 516, 15);
		
		customersTable = new Table(shlListCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("!AddCustomer.customersTable.toolTipText!");
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		customersTable.setBounds(10, 31, 516, 252);
		
		TableColumn tableColumn = new TableColumn(customersTable, SWT.NONE);
		tableColumn.setWidth(60);
		tableColumn.setText("ID");
		
		TableColumn tableColumn_1 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_1.setWidth(160);
		tableColumn_1.setText("Name");
		
		TableColumn tableColumn_2 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Date");
		
		TableColumn tableColumn_3 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Group Size");
		
		TableColumn tableColumn_4 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("Refunds");
		
		//TODO: logic section 
		sortCustomers(); 
		updateTable(customersTable, customersSorted); 
		
		//TODO: label listener
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			//TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				//TODO: label method calls
				rootShell.forceActive(); 
				shlListCustomers.close(); 
			}
		});
	}
	
	//TODO: label method
	public void setSortByName() 
	{
		sortBy = 0;  	// set sort by filter to name
	}
	
	//TODO: label method
	public void setSortBySize() 
	{
		sortBy = 1; 	// set sort by filter to size
	}
	
	//TODO: label method
	@SuppressWarnings("unchecked")		// TO SUPRESS WARNING ABOUT "TYPE SAFETY: UNCHECKED CAST...." with arrayList cast 
	private void sortCustomers() 
	{
		//TODO: label call
		customersSorted =  (ArrayList<Customer>) customers.clone(); 
		
		//TODO: label switch
		switch (sortBy) 
		{
			case 0: 
				customersSorted.sort(new Customer.CompareName());
				break; 
			case 1: 
				customersSorted.sort(new Customer.CompareSize());
				break; 
			default: 
				break; 
		}
	}
	
}
