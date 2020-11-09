package com.ys.appler.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SockerHandler  extends TextWebSocketHandler {

    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //클라이언트가 서버로 메세지를 보냈을때의 서버 처리 로직

        //session으로부터 메세지를 받음
        log.info("{}로 부터 {}받음 ", session.getId(), message.getPayload());

        for(WebSocketSession sess : sessionList){
            sess.sendMessage(new TextMessage(message.getPayload()));
            //세션을 principal 을 이용하여 불러온뒤 메세지는 전체전달한다.
        }


        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //클라이언특가 만났을때 접속연결처리 로직

        super.afterConnectionEstablished(session);

        //리스트를 이용하여 세션 주입
        sessionList.add(session);

        log.info("{} 연결됨", session.getId());

        log.info("채팅방 입장자 : "+ session.getPrincipal().getName());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //클라이언트와 접속이 끊어졌을시 세션종료밑 퇴장 처리 로직

        sessionList.remove(session);
        //세션을 세션리스트에서 삭제한다.

        log.info("{} 연결 끊김 " + session.getId());

        log.info("채팅방 퇴장자 : "+ session.getPrincipal().getName());
        //Principal 을 이용하여 세션의 이름을 가지고온다.


        super.afterConnectionClosed(session, status);
    }
}
