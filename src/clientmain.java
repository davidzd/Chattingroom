import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class clientmain {

	public static void main(String[] args) {
		Arg arg = new Arg();
		CmdLineParser parser = new CmdLineParser(arg);

		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
		}
		Socket socket = null;
		try {
			socket = new Socket(arg.remoteServer, arg.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client Connected...");
		//Initialize the two threads
		Client client = new Client(socket);
		clientIn clientIn = new clientIn(socket);
		//Run the two threads
		clientIn.start();
		client.start();

	}

}
