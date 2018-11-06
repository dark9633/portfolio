package com.portfolio.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class Websocket extends TextWebSocketHandler{

	private List<WebSocketSession> connectedUsers;
	
	public Websocket() {
		connectedUsers = new ArrayList<WebSocketSession>();
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		connectedUsers.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		for(WebSocketSession webSocketSession : connectedUsers){
			if(!session.getId().equals(webSocketSession.getId())){
				webSocketSession.sendMessage(new TextMessage(message.getPayload()));
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		connectedUsers.remove(session);
	}

}
