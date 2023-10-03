package org.boluo.hr.controller;

import org.boluo.hr.pojo.ChatMsg;
import org.boluo.hr.pojo.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
public class WsController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WsController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ws/chat")
    public void handel(Authentication authentication, ChatMsg chatMsg) {
        Hr hr = (Hr) authentication.getPrincipal();
        chatMsg.setData(new Date());
        chatMsg.setNickName(hr.getName());
        chatMsg.setFrom(hr.getUsername());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/channel", chatMsg);
    }
}
