package assignment2;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoClient {
  
  private static int port = 8998;
  private static String hostname;
 
 public static void main (String [] args) {
  
   
   if(args.length != 1){
     System.out.println("Usage: EchoClient hostname");
     System.exit(1);
   } else {
     hostname = args[0];
   }
   
   Scanner sc = new Scanner(System.in);
  
  try {
   //make connection to server socket
   Socket conn = new Socket(hostname, port);
   
   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
   
   DataInputStream in = new DataInputStream(new BufferedInputStream(conn.getInputStream()));
   

    String message = "";
    
    while(true){
      message = sc.nextLine();
      
      if(message.equals(".")){
        conn.close();
        break;
      } else {
        out.writeUTF(message);     
        String res = in.readUTF();
        if(res != null) {
          System.out.println("[Echo]: " + res);
        }
      }

    }
  } 
  catch (IOException e) {
   System.out.println("Server Connection Failed: Server not available.");
   System.exit(1);
  }
  
 }

}
