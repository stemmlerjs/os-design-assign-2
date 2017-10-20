package assignment2;

import java.io.*;
import java.net.*;

public class EchoServer {
 
 private ServerSocket server;
 private Socket client;
 private int port = 8998;
 private boolean connection = true;
 
 public EchoServer () {
  try {
   // Create socket server
   server = new ServerSocket(port);
   
   System.out.println("[Server]: Started. Waiting for connection on port " + port);
   
   while(connection){
     // Accept a single connection
     client = server.accept();
     System.out.println("[Server]: Client connected");
     
     // Get the input and output stream from the socket connection
     DataOutputStream out = new DataOutputStream(client.getOutputStream());           
     
     DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
     
     while(connection){
       //Get the message from the input stream
       String msg = in.readUTF();
       System.out.println("[Server]: Message received: " + msg);
       
       //Output the message to the output stream
       out.writeUTF(msg);
       out.flush();
     }
     
     connection = false;
         
   }
   
   //Server has been terminated by the exit symbol
   client.close();
   System.out.println("[Server]: Server process terminated by client.");
   
  } catch (SocketException e){
    System.out.print("[Server]: Connection Reset");
    System.exit(1);
  } catch (EOFException e){
    System.out.println("[Server]: Client Connection Closed.");
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
   
 public static void main (String [] args) {
  new EchoServer();
 }

}
