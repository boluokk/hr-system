package org.boluo.hr.common.mq;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.pojo.MailConstants;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author boluo
 * @date 2023/11/17
 */
@Configuration
@Slf4j
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    private RabbitTemplate rabbitTemplate;

    @Resource
    public RabbitTemplate rabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
        return rabbitTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (!b) {
            log.error("发送失败：{}", s);
        }
    }

    /**
     * 未到达队列
     */
    @SneakyThrows
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.warn("准备重传: {}", returnedMessage);
        ThreadUtil.sleep(1, TimeUnit.SECONDS);
        rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                MailConstants.MAIL_ROUTING_KEY_NAME, returnedMessage.getMessage());
    }
}
