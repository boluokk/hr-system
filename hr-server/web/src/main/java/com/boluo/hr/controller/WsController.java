package com.boluo.hr.controller;

import com.boluo.hr.pojo.ChatMsg;
import com.boluo.hr.pojo.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/22 - 20:59
 */
@RestController
public class WsController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handel(Authentication authentication, ChatMsg chatMsg) {
        Hr hr = (Hr) authentication.getPrincipal();
        chatMsg.setData(new Date());
        chatMsg.setNickName(hr.getName());
        chatMsg.setFrom(hr.getUsername());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/channel",chatMsg);
    }
}
