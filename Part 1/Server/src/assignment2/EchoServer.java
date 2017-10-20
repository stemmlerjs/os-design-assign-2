package assignment2;

import java.io.*;
import java.net.*;

public class EchoServer {
 
 private ServerSocket server;
 private Socket client;
 private int port = 8998;
 private boolean connection;
 
 public EchoServer () {
  try {
   // Create socket server
   server = new ServerSocket(port);
   connection = true;
   
   System.out.println("[Server]: Started. Waiting for connection on port " + port);
   
   while(connection == true){
     // Accept a single connection
     client = server.accept();
     System.out.println("[Server]: Client connected");
     
     // Get the input and output stream from the socket connection
     DataOutputStream out = new DataOutputStream(client.getOutputStream());           
     
     DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
     
     while(connection == true){
       //Get the message from the input stream
       String msg = in.readUTF();
       System.out.println("[Server]: Message received: " + msg);
       
       if(msg.equals(".")){
         connection = false; 
       }
       
       //Output the message to the output stream
       out.writeUTF(msg);
       out.flush();
     }
         
   }
   
   //Server has been terminated by the exit symbol
   client.close();
   System.out.println("[Server]: Server process terminated by client.");
   
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
   
 public static void main (String [] args) {
  new EchoServer();
 }

}
