package assignment2;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import assignment2.*;

public class EchoServer {
	
	private ServerSocket server;
	private Socket client;
	private InputStream in;
	private OutputStream out;
	private int port = 8998;
	private final int BUFFER_SIZE = 200;
	private ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
	
	public EchoServer () {
		try {
			// Create socket server
			server = new ServerSocket(port);
			
			System.out.println("[Server]: Started. Waiting for connection on port " + port);
			
			// Accept a single connection
			client = server.accept();
			System.out.println("[Server]: Client connected");
			
			// Get the input and output stream from the socket connection
			in = client.getInputStream();
			out = client.getOutputStream();
			
			while (true) {
				// Read in from client
				int byteIn = in.read();
				
				// If client terminated connection, break loop
				if (byteIn == -1) break;
				
				// Check if null terminated byte
				if (byteIn == 0) {
					
					/*
					 * If there was a null terminated byte, then we need to send 
					 * all the bytes in the buffer back to the client.
					 */
					
					for (byte b : getBytesFromBuffer()) 
						out.write(b);
				}
				
				/*
				 * Continuously add bytes to the buffer.
				 */
				
				else {
					try {
						addToBuffer((byte) byteIn);
					} 
					catch (BufferFullException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.println("[Server]: Client disconnected");
			
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * addToBuffer
	 * 
	 * Adds a byte to the end of the buffer if space permits.
	 * 
	 * @param {byte} byteIn - byte to add to the buffer.
	 * @return void
	 */
	
	void addToBuffer (byte byteIn) throws BufferFullException {
		if (byteBuffer.hasRemaining()) {
			byteBuffer.put(byteIn);
			System.out.println("Added byte to buffer: " + byteIn);
		}
		
		else throw new BufferFullException("Buffer reached max size of " + BUFFER_SIZE);
	}
	
	/*
	 * getBytesFromBuffer
	 * 
	 * TODO: Fix this, it's not getting the bytes in the buffer properly.
	 */
	
	byte [] getBytesFromBuffer() {
		// Set position to 0, limit to the last position;
		byteBuffer.flip();
		
		int bufferSize = byteBuffer.position();
		byte [] retData = new byte[bufferSize];
		
		for(int i = 0; i < bufferSize; i++) {
			retData[i] = byteBuffer.get();
		}
		
		return retData;
	}
	
	public static void main (String [] args) {
		new EchoServer();
	}

}
