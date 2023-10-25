package org.boluo.hr.controller;

import org.boluo.hr.annotation.Log;
import org.boluo.hr.pojo.ChatMessage;
import org.boluo.hr.pojo.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 聊天信息
 *
 * @author 🍍
 * @date 2023/10/1
 */
@RestController
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 聊天
     */
    @MessageMapping("/ws/chat")
    @Log("开始聊天连接")
    public void handel(Authentication authentication, ChatMessage chatMessage) {
        Hr hr = (Hr) authentication.getPrincipal();
        chatMessage.setDate(new Date());
        chatMessage.setNickName(hr.getName());
        chatMessage.setFrom(hr.getUsername());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getTo(), "/queue/channel", chatMessage);
    }
}
