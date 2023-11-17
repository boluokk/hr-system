package org.boluo.mailserver.config;

import org.boluo.hr.pojo.MailConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author boluo
 * @date 2023/11/17
 */
@Configuration
public class RabbitTemplateConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Bean
    public MessageRecoverer messageRecoverer() {
        return new RepublishMessageRecoverer(rabbitTemplate, MailConstants.MAIL_ERROR_EXCHANGE_NAME,
                MailConstants.MAIL_ERROR_ROUTING_KEY_NAME);
    }

    @Bean
    public Queue emailErrorQueue() {
        return new Queue(MailConstants.MAIL_ERROR_NAME, true, false, false);
    }

    @Bean
    public TopicExchange emailErrorExchange() {
        return new TopicExchange(MailConstants.MAIL_ERROR_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding emailErrorbinding() {
        return BindingBuilder.bind(emailErrorQueue()).to(emailErrorExchange()).with(MailConstants.MAIL_ERROR_ROUTING_KEY_NAME);
    }
}
