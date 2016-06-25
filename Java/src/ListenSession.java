import java.io.IOException;
import java.io.ObjectInputStream;

public class ListenSession implements Runnable {
	private ObjectInputStream ois;
	private Controller controller;
	private String withWhom;
	
	public ListenSession(Controller controller, ObjectInputStream ois, String name) {
		this.controller = controller;
		this.ois = ois;
		this.withWhom = name;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Message newMsg = (Message) ois.readObject();
				System.out.println("Recieved: " + newMsg.getMessage());
			} catch (ClassNotFoundException | IOException e) {
				// If the stream has gone, remove here.
				this.controller.removeIStream(this.withWhom);
				e.printStackTrace();
			}
		}
		
	}
}
