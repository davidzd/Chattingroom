import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import unimelb.daz1.JsonPackage.*;

public class Client extends Thread {
    // guest id for store the id of this client
    public static String guestid = null;
    public static String roomid = null;
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    // client main end
    public void run() {
        // initialize socket.
        try {
            // connect to a server listening on port 4444 on localhost

            // Preparing sending and receiving streams
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "UTF-8"));
            // Reading from console
            Scanner cmdin = new Scanner(System.in);
            while (!interrupted()) {
                String msg = cmdin.nextLine();
                if(msg.equals("#quit")) {
                    JSONObject obj =  clientJson.Quit();
                    out.println(obj.toJSONString());
                    out.flush();
                    this.interrupt();
                    this.join(5000);
//                    String lastRes = in.readUTF();
//                    JSONParser parser = new JSONParser();
//                    JSONObject jsonObject = (JSONObject) parser.parse(lastRes);
//                    if(jsonObject.get("type").equals("roomchange"))
                    break;
//                    else
//                        listen.start();
                }
                // Transfer the input stream to String.
//                System.out.print("["+Client.roomid+"]"+" "+Client.guestid+">");

                out.println(clientControl.inputControl(msg).toJSONString());
                out.flush();
                // forcing TCP to send data immediately

            }
            // Thread ct = new Thread(new ClientThread(socket,user));
            // // It starts running the thread by calling run() method
            // ct.start();
            cmdin.close();

            out.close();
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print("");
        } catch (InterruptedException e) {
            System.out.print("");
        }
    }

}
