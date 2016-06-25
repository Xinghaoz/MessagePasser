import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket server = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			server = new ServerSocket(22222);
			while (true) {
				Socket socket = server.accept();
				
				System.out.println("Receieved from " + socket.getLocalAddress() + ":" + socket.getPort());
				Message msg = new Message("Accepted!");
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(msg);
				ois = new ObjectInputStream(socket.getInputStream());
				//Thread listen = new Thread(new ListenSession(ois));
				//listen.start();				
			}
		} catch (Exception e) {
			
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
