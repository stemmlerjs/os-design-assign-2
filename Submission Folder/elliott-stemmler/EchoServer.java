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
     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
     
     //DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
     InputStream in = client.getInputStream();
     
     while(connection){
       //Get the message from the input stream
       //String msg = in.readUTF();

         int nRead;
         byte[] data = new byte[16384];

         if ((nRead = in.read(data, 0, data.length)) != -1){
             buffer.write(data,0,nRead);
         } else if ((nRead = in.read(data, 0, data.length)) == -1){
             break;
         }

         buffer.flush();

         byte[] byteMsg = buffer.toByteArray();

         String msg = new String(byteMsg, "UTF-8");



         System.out.println("[Server]: Message received: " + msg);
       
       //Output the message to the output stream
       out.write(byteMsg);
       out.flush();
       buffer.reset();
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
