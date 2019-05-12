package cn.goour.yiban.app.action;

import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.goour.yiban.app.entity.User;
import cn.goour.yiban.app.entity.Msg.GroupMsg;
import cn.goour.yiban.app.entity.Msg.UserMsg;

public class WebSocketChat extends WebSocketClient {
	private YiBanRequest action;
	private String timeStamp = "1464229527";
	private int resetNum = 0;
	private IChat iChat;
	public WebSocketChat(URI serverUri, Draft arg1) {
		this(serverUri, arg1,new IChat() {
			@Override
			public void send5(String text) {
			}
			
			@Override
			public void send(String text) {
			}
			
			@Override
			public void onOpen(ServerHandshake handshakedata) {
			}
			
			@Override
			public void onMessage(String message) {
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public WebSocketChat(URI serverUri, Draft arg1,IChat iChat) {
		super(serverUri, arg1);
		this.iChat = iChat;
	}

	public YiBanRequest getAction() {
		return action;
	}

	public void setAction(YiBanRequest action) {
		this.action = action;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		iChat.onOpen(handshakedata);
	}
	@Override
	public void onMessage(String message) {
		if (message.equals("2::")) {// 保持链接
			send("2::");
		}else if (message.equals("1::")) {// 刚刚建立连接
			resetConnect();
		}else if (message.equals("0::")) {// 链接要关闭的前奏
			
		}else {
			if (message.startsWith("5:::")) {
				JSONObject json = JSONObject.parseObject(message.substring(4));
				JSONArray jsonArr = json.getJSONArray("args");
				List<Object> list = new ArrayList<Object>();
				for (Object object : jsonArr) {
					JSONObject item = (JSONObject)object;
					String order = item.getString("order");
					switch (order.toUpperCase()) {
					case "REG":// 开始链接时需要的一个信息
						send5(getOFLMsg().toJSONString());
						break;
					case "MSG":// 主动发送消息后收到的消息回复，此内容为发送消息的具体信息，
						list.add(item.get("messageId"));
						// 然后要向系统告知我已收到该消息
						break;
					case "OFL":// 系统向用户发送了一条其他用户发送的聊天消息
						// 处理完消息后同样要向系统发送一条消息告知我已收到消息
						break;
					case "SYS":// 系统消息
						// 这个标识的消息可以直接忽略不处理
						break;
					case "DIS":
						if (item.getIntValue("code")==150) {
//							if (item.containsKey("timeStamp")) {
								timeStamp = item.getString("timeStamp");
								if (resetNum < 5) {
									++resetNum;
									resetConnect();
								}
//							}
						}
						
						break;
					default:
						break;
					}
				}
				if (list.size() > 0) {
					// 向系统告知我已收到的消息内容
					send5(buildSYSMsg(list).toJSONString());
				}
			}
		}
		iChat.onMessage(message);
	}
	public void resetConnect(){
		JSONObject start = getRegMsg();
		send5(start.toJSONString());
	}
	@Override
	public void send(String text) throws NotYetConnectedException {
		super.send(text);
		iChat.send(text);
	}
	public void send5(String text) throws NotYetConnectedException {
		String string = "5:::"+text;
		super.send(string);
		iChat.send5(text);
	}
	public void sendUserMsg(String touserid, String text){
		User vo = action.getUser();
		UserMsg msg = new UserMsg(vo, touserid, text);
		send5(buildUserMsg(msg).toJSONString());
	}
	public void sendGroupMsg(JSONObject groupItem, String text){
		User vo = action.getUser();
		GroupMsg msg = new GroupMsg(vo, groupItem, text);
		send5(buildGroupMsg(msg).toJSONString());
	}
	@Override
	public void onClose(int code, String reason, boolean remote) {
		iChat.onClose(code, reason, remote);
	}

	@Override
	public void onError(Exception ex) {
		System.out.println("遇到了错误：" + ex.getMessage());
	}
	public JSONObject getRegMsg() {
		// {"name":"ybmp","args":[{"access_token":"df71ef17b9076c5a446111cdfa4f52f6",
		// "divice":"mobile","diviceinfo":"android","host":"6746755","order":"REG","signature":"8224354c58f86eef4715d3feb2d3a0a7",
		// "timeStamp":"1464229747","version":"4.4.3"}]}
		User user = action.getUser();
		JSONObject json2 = new JSONObject();
		String access_token = user.getAccess_token();
		String userid = user.getUser_id()+"";
		json2.put("access_token", access_token);
		json2.put("divice", "mobile");
		json2.put("diviceinfo", "android");
		json2.put("host", userid);
		json2.put("order", "REG");
		Object signature = YiBanRequest.signature(userid, access_token, timeStamp);
		json2.put("signature", signature);
		json2.put("timeStamp", timeStamp);
		json2.put("version", "4.4.3");
		JSONArray json3 = new JSONArray();
		json3.add(json2);
		JSONObject json1 = new JSONObject();
		json1.put("name", "ybmp");
		json1.put("args", json3);
		return json1;
	}
	public JSONObject getOFLMsg() {
		// {"name":"ybmp","args":[{"order":"OFL","userid":"6746755"}]}
		User user = action.getUser();
		JSONObject json2 = new JSONObject();
		Object userid = user.getUser_id();
		json2.put("userid", userid);
		json2.put("order", "OFL");
		
		JSONArray json3 = new JSONArray();
		json3.add(json2);
		JSONObject json1 = new JSONObject();
		json1.put("name", "ybmp");
		json1.put("args", json3);
		return json1;
	}
	public JSONObject buildSYSMsg(List<Object> messageIds) {
		// {"name":"ybmp","args":[{"order":"SYS","action":"feedback","userid":6746755,
		// "messageIds":["6746755:84934367-da14-40be-a180-c8f9cc5d6e38"]}]}
		User user = action.getUser();
		JSONObject json2 = new JSONObject();
		Object userid = user.getUser_id();
		json2.put("userid", userid);
		json2.put("order", "SYS");
		json2.put("action", "feedback");
		json2.put("messageIds", messageIds);
		
		JSONArray json3 = new JSONArray();
		json3.add(json2);
		JSONObject json1 = new JSONObject();
		json1.put("name", "ybmp");
		json1.put("args", json3);
		return json1;
	}
	public JSONObject buildUserMsg(UserMsg vo){
		// {"name":"ybmp","args":[{"access_token":"df71ef17b9076c5a446111cdfa4f52f6","audio_length":0,
		// "avatar":"http://img02.fs.yiban.cn/6746755/avatar/user/88","id":"ed038578-f56b-49e2-9a97-f4f72e2749b0",
		// "order":"MSG","poster":"6746755","text":"1234567890","touser":"6502935","truename":"","type":"1",
		// "userkind":"4","username":""}]}
		
		JSONArray json3 = new JSONArray();
		json3.add(vo);
		JSONObject json1 = new JSONObject();
		json1.put("name", "ybmp");
		json1.put("args", json3);
		return json1;
	}
	public JSONObject buildGroupMsg(GroupMsg vo){
		// {"name":"ybmp","args":[{"access_token":"df71ef17b9076c5a446111cdfa4f52f6",
		// "avatar":"http://img02.fs.yiban.cn/6746755/avatar/user/88","groupname":"","id":"14a39c7e-db98-48e9-a866-6fff5b3a138d",
		// "order":"MSG","poster":"6746755","text":"0123","togroup":"162061","truename":"","type":"1","userkind":"1","username":""}]}
		JSONArray json3 = new JSONArray();
		json3.add(vo);
		JSONObject json1 = new JSONObject();
		json1.put("name", "ybmp");
		json1.put("args", json3);
		return json1;
	}
}
