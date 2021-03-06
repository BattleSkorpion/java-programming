package trayprogmulti;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.function.*; 

public class TrayProgClient {
	
	private static Socket socket; 
			
	public static void main(String args[]) {
		
		try {
			String ip = "10.10.25.151"; 
			
			socket = new Socket(ip, 5555); 
			
			InputStream is = socket.getInputStream(); 
			
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(is)); 
			
			//String time = serverInput.readLine(); 
			//System.out.println("Time: " + time); 
			String command = serverInput.readLine();
			System.out.println("command received: " + command); 
			switch (command.toLowerCase()) 
			{
			case "open":
				CDUtils.open("D:\\"); 
				break; 
			case "close":
				CDUtils.close("D:\\"); 
				break;
			default: 
				break; 
			}
			
			serverInput.close(); 
			is.close(); 
			socket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
	}
	
	public void finalize () {
		try {
			super.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}


