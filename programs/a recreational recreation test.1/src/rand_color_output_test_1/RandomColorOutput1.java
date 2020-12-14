package rand_color_output_test_1;

import java.awt.*; 					// For adding/modifying certain qualities of JFrame/JPanel
import javax.swing.*;    			// For JFrame and JPanel, and JOptionPane
//import java.util.concurrent.*;

public class RandomColorOutput1 {

	public static void main(String[] args) {
		double random = Math.random() * 255; 
		double random2 = Math.random() * 255;
		double random3 = Math.random() * 255;
		String speedInput = JOptionPane.showInputDialog("Enter speed (ms)", "1000");
		int speed = Integer.parseInt(speedInput); 
		
		JFrame random_window = new JFrame(); 
		random_window.setTitle("COLORS"); 
		random_window.setSize (777,777);
		random_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//random_window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		random_window.setVisible(true);
		
		JPanel panel1 = new JPanel(); 
		
		Container pane1 = random_window.getContentPane(); 
		// pane1.setLayout(new GridLayout (10, 10)); 
		panel1.setBackground(Color.red);
		
		for (int i = 0; i < 1000000; i++ ) { 
			panel1.setBackground(new Color( (int)random, (int)random2, (int)random3));
			pane1.add(panel1);
			random = (Math.random() * 255) ; 
			random2 = (Math.random() * 255); 
			random3 = (Math.random() * 255);
			
			// testing 
			//JOptionPane.showMessageDialog(null,random, " ", JOptionPane.PLAIN_MESSAGE);
			
			try {
			    Thread.sleep(speed);
			} 
			catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			//pane1.remove(panel1);
		}	
	}

}
