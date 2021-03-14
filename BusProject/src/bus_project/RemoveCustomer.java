package bus_project;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.SelectionListener;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

public class RemoveCustomer extends AbstractProgramWindow{

	protected Shell shlRemoveCustomer;
	private ArrayList<Customer> customers = new ArrayList<Customer>(); 
	private ArrayList<Customer> customersRemoved = new ArrayList<Customer>(); 
	private Table customersTable;
	private Table remCustomersTable;

	public RemoveCustomer (ArrayList<Customer> cstmrs) {
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
		shlRemoveCustomer.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlRemoveCustomer.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlRemoveCustomer.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlRemoveCustomer.isDisposed()) 
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
		
		shlRemoveCustomer = new Shell();
		shlRemoveCustomer.setSize(674, 400);
		shlRemoveCustomer.setText("Remove Customer");
		
		Button btnExit = new Button(shlRemoveCustomer, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(573, 326, 75, 25);
		
		Label lblCustomers = new Label(shlRemoveCustomer, SWT.NONE);
		lblCustomers.setText("Customers: ");
		lblCustomers.setBounds(140, 10, 63, 15);
		
		customersTable = new Table(shlRemoveCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("");
		customersTable.setLinesVisible(true);
		customersTable.setBounds(10, 40, 316, 252);
		updateTable(customersTable, customers); 
		
		Label lblRemovedCustomers = new Label(shlRemoveCustomer, SWT.NONE);
		lblRemovedCustomers.setText("Removed Customers:");
		lblRemovedCustomers.setBounds(444, 10, 121, 15);
		
		remCustomersTable = new Table(shlRemoveCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		remCustomersTable.setToolTipText("");
		remCustomersTable.setLinesVisible(true);
		remCustomersTable.setBounds(332, 40, 316, 252);
		
		Button btnDelete = new Button(shlRemoveCustomer, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				customersRemoved.add(customers.get(customersTable.getSelectionIndex())); 
				customers.remove(customersTable.getSelectionIndex()); 
				updateTable(customersTable, customers); 
				updateTable(remCustomersTable, customersRemoved); 
			}
		});
		btnDelete.setBounds(238, 298, 88, 25);
		btnDelete.setText("Delete");
		
		Button btnUndoDelete = new Button(shlRemoveCustomer, SWT.NONE);
		btnUndoDelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				customers.add(customersRemoved.get(remCustomersTable.getSelectionIndex())); 
				customersRemoved.remove(remCustomersTable.getSelectionIndex()); 
				updateTable(customersTable, customers); 
				updateTable(remCustomersTable, customersRemoved); 
			}
		});
		btnUndoDelete.setBounds(332, 298, 88, 25);
		btnUndoDelete.setText("Undo Deletion");
		
		btnExit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				int confirmDelete; 
				if (customersRemoved.size() > 0) {
					confirmDelete = JOptionPane.showConfirmDialog(null, "Confirm", "Are you sure you want to delete these customers?", JOptionPane.YES_NO_OPTION); 
					if (confirmDelete == 1) //1 = NO
					{
						return; 
					}
				}
				rootShell.forceActive(); 
				shlRemoveCustomer.close(); 	
			}
		});
	}
}
