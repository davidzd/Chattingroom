package unimelb.daz1.JsonPackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class clientJson {
	@SuppressWarnings("unchecked")
	public static JSONObject sendJoin(String roomid) {
		JSONObject obj = new JSONObject();
		obj.put("type", "join");
		obj.put("roomid", roomid);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject sendDelete(String roomid) {
		JSONObject obj = new JSONObject();
		obj.put("type", "delete");
		obj.put("roomid", roomid);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject indentityChange(String newid) {
		JSONObject obj = new JSONObject();
		obj.put("type", "identitychange");
		obj.put("identity", newid);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject sendWho(String roomid) {
		JSONObject obj = new JSONObject();
		obj.put("type", "who");
		obj.put("roomid", roomid);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject sendMessage(String content) {
		JSONObject obj = new JSONObject();
		obj.put("type", "message");
		obj.put("content", content);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject sendErrorMessage() {
		JSONObject obj = new JSONObject();
		obj.put("type", "errormessage");
		obj.put("content", "Error Plz try again");
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject sendCreateRoom(String roomid) {
		JSONObject obj = new JSONObject();
		obj.put("type", "createroom");
		obj.put("roomid", roomid);
		return obj;
	}
	@SuppressWarnings("unchecked")
	public static JSONObject sendKick(String indentity, String roomid) {
		JSONObject obj = new JSONObject();
		obj.put("type", "kick");
		obj.put("time", 3600);
		obj.put("identity", indentity);
		obj.put("roomid", roomid);
		return obj;
	}
	@SuppressWarnings("unchecked")
	public static JSONObject Roomlist() {
		JSONObject obj = new JSONObject();
		JSONArray roominfo = new JSONArray();
		obj.put("type", "list");
		return obj;
	}
	public static JSONObject Quit() {
		JSONObject obj = new JSONObject();
		obj.put("type", "quit");
		return obj;
	}

}