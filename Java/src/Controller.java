import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Controller {
	private Map<String, Host> hosts;
	private Map<String, ObjectInputStream> iStreams;
	private Map<String, ObjectOutputStream> oStreams;
	Host myHost;
	
	public Controller(Map<String, Host> hosts, Host myHost) {
		this.hosts = hosts;
		this.iStreams = new HashMap<>();
		this.oStreams = new HashMap<>();
		this.myHost = myHost;
	}
	
	public Controller() {
		this.hosts = new HashMap<>();
		this.iStreams = new HashMap<>();
		this.oStreams = new HashMap<>();
		myHost = null;
	}
	
	public void setIStream(String name, ObjectInputStream ois) {
		this.iStreams.put(name, ois);
	}
	
	public void setOStream(String name, ObjectOutputStream oos) {
		this.oStreams.put(name, oos);
	}
	
	public void setMyHost(Host host) {
		this.myHost = host;
	}
	
	public void removeIStream(String name) {
		this.iStreams.remove(name);
	}
	
	public void removeOStream(String name) {
		this.oStreams.remove(name);
	}
	
	public ObjectInputStream getIStream(String name) {
		if (!this.oStreams.containsKey(name)) {
			return null;
		}
		return this.iStreams.get(name);
	}
	
	public ObjectOutputStream getOStream(String name) {
		if (!this.oStreams.containsKey(name)) {
			return null;
		}
		return this.oStreams.get(name);
	}
	
	public Host getMyHost() {
		return this.myHost;
	}
	
	public Map<String, Host> getHosts() {
		return this.hosts;
	}
	
	public String findName(String ip, int port) {
		for (Entry<String, Host> entry : this.hosts.entrySet()) {
			Host host = entry.getValue();
			if (host.getIp().equals(ip) && host.getPort() == port) {
				return entry.getKey();
			}
		}
		return null;
	}
}
