import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {
		String name = args[1];
		String file = args[0];
		
		MessagePasser mp = new MessagePasser(file, name);
		
		while (true) {
			System.out.print(">>");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			String[] s = str.split(" ");
			//Message newMsg = new Message(name, s[0], s[1]);
			mp.send(s[0], s[1]);
		}
	}

}
