
public class Host {
	private String name;
	private String ip;
	private int port;
	
	public Host(String name, String ip, int port) {
		this.name = name;
		this.ip = ip;
		this.port = port;
	}
	public Host() {
		this.name = null;
		this.ip = null;
		this.port = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
