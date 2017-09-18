package assignment2;

public class BufferFullException extends Exception {
	
	private String message;

	BufferFullException(String a) {
		message = a;
	}
	
	public String toString() {
		return "BufferFullException[" + message + "]";
	}

}
