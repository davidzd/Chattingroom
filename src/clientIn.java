import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class clientIn extends Thread {
	// guest id for store the id of this client
	private Socket socket = null;

	public clientIn(Socket socket) {
		this.socket = socket;
	}

	// client main end
	public void run() {
		// initialize socket.
		try {
			// connect to a server listening on port 4444 on localhost

			// Preparing sending and receiving streams
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "UTF-8"));
			// Initialize the Parser for transfering the String into JSON
			JSONParser parser = new JSONParser();
			// Initialize the object of injson
			JSONObject injson;
			String response;
			//JSONValue cannot Parse the null String;
			while ((response = in.readLine())!=null) {
				// Transfer the input stream to String.

				// Transfer the String to JSON object.
				injson = (JSONObject) JSONValue.parse(response);
				clientControl.injsonControl(injson);
				// forcing TCP to send data immediately

				if (response.equals("end"))
					break;
			}
			// Thread ct = new Thread(new ClientThread(socket,user));
			// // It starts running the thread by calling run() method
			// ct.start();
			in.close();
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
