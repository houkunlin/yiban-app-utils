package cn.goour.yiban.app.action;

import org.java_websocket.handshake.ServerHandshake;

public interface IChat {
	void onOpen(ServerHandshake handshakedata);
	void onMessage(String message);
	void send(String text);
	void send5(String text);
	void onClose(int code, String reason, boolean remote);
}
