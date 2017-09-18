package assignment2;

import java.io.*;
import java.net.*;

public class EchoClient {
	
	public static void main (String [] args) {
		
		try {
			//make connection to server socket
			Socket conn = new Socket("127.0.0.1", 8998);
			
			InputStream in = conn.getInputStream();
			OutputStream out = conn.getOutputStream();
			

				String message = "";
				
				for (String arg : args) {
				    message += arg + " ";
				}
				
				// Send message as bytes to server
				byte[] messageInBytes = message.getBytes();
				out.write(messageInBytes);
				
				// Get echo back from server
				while (true) {
					int response = in.read();
					
				}
				
				// conn.close();
		
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
