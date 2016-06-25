import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable {
	Controller controller;
	
	public Listener(Controller cont) {
		this.controller = cont;
	}
	
	@Override
	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(this.controller.getMyHost().getPort());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			try {
				Socket socket = server.accept();
				String clientName = this.controller.findName(socket.getLocalAddress().toString(), socket.getPort());
				System.out.println("+++++++ Received Connecction from " + clientName);
				
				// Do these in the Speaker
				//ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				//this.controller.setOStream(clientName, oos);
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				this.controller.setIStream(clientName, ois);
				Thread listen = new Thread(new ListenSession(this.controller, ois, clientName));
				listen.start();			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	} 
	
}
