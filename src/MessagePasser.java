import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class MessagePasser {
	Controller controller;
	String myName;
	
	public MessagePasser(String file, String name) throws FileNotFoundException {
		YamlReader reader = new YamlReader();
		Map<String, Host> hosts = reader.getHosts(file);
		System.out.println("hosts = " + hosts);
		Controller controller = new Controller(hosts, hosts.get(name));		
		this.controller = controller;
		this.myName = name;
		
		Thread listener = new Thread(new Listener(controller));
		listener.start();
	}
	
	public void send(String dest, String msg) throws IOException {
		Message newMsg = new Message(this.myName, dest, msg);
		ObjectOutputStream oos = this.controller.getOStream(dest);
		if (oos == null) {
			Host host = this.controller.getHosts().get(dest);
			Socket socket = new Socket(host.getIp(), host.getPort());
			oos = new ObjectOutputStream(socket.getOutputStream());
			this.controller.setOStream(dest, oos);
			oos.writeObject(newMsg);
		} else {
			oos.writeObject(newMsg);
		}
	}
}
