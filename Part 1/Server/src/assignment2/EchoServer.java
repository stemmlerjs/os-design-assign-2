package assignment2;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class EchoServer {
	
	private ServerSocket server;
	private Socket client;
	private InputStream in;
	private OutputStream out;
	private int port = 8998;
	private final int BUFFER_SIZE = 200;
	private ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
	
	public EchoServer () {
		System.out.println("Byte buffer position:" + byteBuffer.position());
		byteBuffer.put((byte) 84);
		System.out.println("Byte buffer position:" + byteBuffer.position());
		System.out.println("Byte buffer remaining:" + byteBuffer.remaining());
//		try {
//			// Create socket server
//			server = new ServerSocket(port);
//			
//			System.out.println("[Server]: Started. Waiting for connection on port " + port);
//			
//			// Accept a single connection
//			client = server.accept();
//			System.out.println("Client connected");
//			
//			// Get the input and output stream from the socket connection
//			in = client.getInputStream();
//			out = client.getOutputStream();
//			
//			while (true) {
//				// Read in from client
//				int byteIn = in.read();
//				
//				// If client terminated connection, break loop
//				if (byteIn == -1) break;
//				
//				// Check if null terminated byte
//					// if yes, then send the message back to the client from the buffer
//					// then, clear the buffer
//				
//				// If not null terminated byte, more message coming in- add to buffer
//				byteBuffer.put((byte) byteIn);
//				
//				
//				
//				System.out.println(byteIn);
//			}
//			
//			System.out.println("Client disconnected");
//		} 
//		
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	void writeToBuffer(byte byteIn) {
		// Confirm that the byte buffer is not
	}
	
	public static void main (String [] args) {
		new EchoServer();
	}

}
