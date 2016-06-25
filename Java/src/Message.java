import java.io.Serializable;

public class Message implements Serializable {
	String msg;
	String src;
	String dest;
	
	public Message(String msg) {
		this.msg = msg;
		this.src = null;
		this.dest = null;
	}
	
	public Message(String src, String dest, String msg) {
		this.msg = msg;
		this.src = src;
		this.dest = dest;
	}
	
	public String getMessage() {
		return this.msg;
	}
}
