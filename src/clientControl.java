import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import unimelb.daz1.JsonPackage.*;

public class clientControl {
    //Control the Jsonsfrom Server;
    public static void injsonControl(JSONObject injson) {
        String command = injson.get("type").toString();
        if (command != null) {
            //Room Change is used when joinging the first room "MainHall" and also join the others.
            if (command.equals("roomchange")) {
                if (injson.get("identity").equals(Client.guestid)) {
                    if (injson.get("former").toString().isEmpty()) {
                        System.out.println("Welcome to OO chatting");
                        Client.roomid = injson.get("roomid").toString();
                    } if (injson.get("roomid").toString().isEmpty()){
                        System.out.println("Successfully Quits !! C U Later");
                    } else {
                        Client.roomid = injson.get("roomid").toString();
                        System.out.println("You moved to Room: " + Client.roomid);
                    }
                } else {
                    if(injson.get("roomid").toString().isEmpty()){
                        System.out.println(injson.get("identity").toString() +
                                " quits ");
                    }
                    if(!injson.get("roomid").toString().isEmpty()) {
                        System.out.println(injson.get("identity").toString() +
                                " joins in our chatting room " + "[" + injson.get("roomid").toString() + "]");
                    }

                    }
            }
            //new idenditys from the Server will be utilized both changename and broadcast other's change
            if (command.equals("newidentity")) {
                if (Client.guestid != null) {
                    if (Client.guestid.equals(injson.get("former").toString())) {
                        System.out.println("Name changed");
                        Client.guestid = injson.get("identity").toString();
                    } else {
                        System.out
                                .println(injson.get("former").toString()
                                        + " change to name : "
                                        + injson.get("identity"));
                    }
                } else {
                    Client.guestid = injson.get("identity").toString();
                    System.out.println("Welcome " + Client.guestid);
                }
            }
            //Server response of Room List;
            if (command.equals("roomlist")) {
                JSONArray rooms = (JSONArray) injson.get("rooms");
                System.out.println();
                for (Object o : rooms) {
                    JSONObject roomInfo = (JSONObject) o;
                    String roomname = roomInfo.get("roomid").toString();
                    Long count = (Long) roomInfo.get("count");
                    System.out.println(roomname + ": " + count + " guests");
                }
            }
            //Server response of porple in room;
            if (command.equals("roomcontents")) {
                String owner = injson.get("owner").toString();
                String roomId = injson.get("roomid").toString();
                System.out.println();
                System.out.print(roomId + " Users are Listed: ");
                if (injson.get("identities")==null) {
                    System.out.print("Room belongs to " +owner+ " is Empty");
                } else {
                    JSONArray userlist = (JSONArray) injson.get("identities");
                    for (Object o : userlist) {
                        String identity = (String) o;
                        if (!owner.equals("") && identity.equals(owner)) {
                            identity = identity + "*";
                        }
                        System.out.print(" " + identity);
                    }
                }
                System.out.print('\n');
            }
            //Read message from server;
            if (command.equals("message")) {
                System.out.print("[" + Client.roomid + "]" + injson.get("identity") + ": ");
                System.out.println(injson.get("content").toString());
            }

        } else {
            System.out.print("no information");
        }
    }
    //Handle the commandline from the users;
    public static JSONObject inputControl(String msg) {
        if (msg.isEmpty()) {
            return clientJson.sendErrorMessage();
        } else {
            //functions should begin with '#';
            if (msg.charAt(0) == '#') {
                String commandline = msg.replace("#", "");
                String[] command = commandline.split("\\ ");
                //all commands inludes: join indetitychange, createroom, who, kick, delete;;
                if (command.length == 2) {
                    if (command[0].equals("join")) {
                        return clientJson.sendJoin(command[1]);
                    }
                    if (command[0].equals("identitychange")) {
                        return clientJson.indentityChange(command[1]);
                    }
                    if (command[0].equals("createroom")) {
                        return clientJson.sendCreateRoom(command[1]);
                    }
                    if (command[0].equals("who")) {
                        return clientJson.sendWho(command[1]);
                    }
                    if (command[0].equals("kick")) {
                        return clientJson.sendKick(command[1],Client.roomid);
                    }
                    if (command[0].equals("delete")) {
                        return clientJson.sendDelete(command[1]);

                    }
                    if (!(command[0].equals("delete")
                            || (command[0].equals("kick"))
                            || (command[0].equals("who"))
                            || (command[0].equals("identitychange"))
                            || (command[0].equals("join")) || (command[0]
                            .equals("quit")))) {
                        System.out.println("wrong function, plz check");
                    }
                } else if (command.length == 1 && (command[0].equals("quit"))) {
                    System.out.println("quit room");
                } else if (command.length == 1 && (command[0].equals("list"))) {
                    return clientJson.Roomlist();
                } else {
                    System.out.println("Plz check ur input arguments");
                }
            } else {
                return clientJson.sendMessage(msg);
            }
        }
        return clientJson.sendErrorMessage();
    }

}
