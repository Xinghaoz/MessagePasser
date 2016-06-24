import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {
	protected ArrayList<Map<String, Object>> getConfig(String file) throws FileNotFoundException {
		Yaml yaml = new Yaml();
		
		Map<String, ArrayList<Map<String, Object>>> values = (Map<String, ArrayList<Map<String, Object>>>)
				yaml.load(new FileInputStream(new File(file)));
		return values.get("configuration");
	}
	
	protected Map<String, Host> getHosts(String file) throws FileNotFoundException {
		Yaml yaml = new Yaml();		
		Map<String, Host> hosts = new HashMap<>();
		Map<String, ArrayList<Map<String, Object>>> values = (Map<String, ArrayList<Map<String, Object>>>)
				yaml.load(new FileInputStream(new File(file)));
		for (Map<String, Object> map : values.get("configuration")) {
			Host host = new Host();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				if (key.equals("name")) {
					String name = (String) entry.getValue();
					host.setName(name);
				} else if (key.equals("ip")) {
					String ip = (String) entry.getValue();
					host.setIp(ip);;
				} else if (key.equals("port")) {
					int port = (int) entry.getValue();
					host.setPort(port);
				}
			}
			hosts.put(host.getName(), host);
		}
		
		return hosts;
	}
	
	public static void main(String args[]) {
		YamlReader reader = new YamlReader();
		try {
			Map<String, Host> hosts = new HashMap<>();
			hosts = reader.getHosts("configuration.yaml");
			for (Map.Entry<String, Host> entry : hosts.entrySet()) {
				String key = entry.getKey();
				System.out.println(key + ": ");
				Host host = entry.getValue();
				System.out.println("name: " + host.getName() + "\tIP: " + host.getIp() + "\tport: " + host.getPort());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
