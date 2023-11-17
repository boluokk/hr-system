package org.boluo.mailserver.receive;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.MailConstants;
import org.boluo.mailserver.service.ConsumeLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 消费邮件
 *
 * @author @1352955539(boluo)
 * @date 2021/2/19 - 19:31
 */
@Component
@Slf4j
public class MailReceiver {

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private MailProperties mailProperties;
    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private ConsumeLogService consumeLogService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MailConstants.MAIL_QUEUE_NAME, durable = "true"),
            exchange = @Exchange(name = MailConstants.MAIL_EXCHANGE_NAME, type = "topic"),
            arguments = @Argument(name = "x-queue-mode", value = "lazy"),
            key = {MailConstants.MAIL_ROUTING_KEY_NAME}
    ))
    public void consumeEmail(Message message) throws Exception {
        // 消息转发
        String uuid = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
        if (consumeLogService.uploadToConsumed(uuid)) {
            MimeMessage msg = javaMailSender.createMimeMessage();
            ObjectMapper objectMapper = new ObjectMapper();
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            Employee employee = objectMapper.readValue(message.getBody(), Employee.class);
            helper.setTo(employee.getEmail());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("入职欢迎！");
            helper.setSentDate(new Date());
            Context context = new Context();  // thymeleaf的context
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("jobLevelName", employee.getJobLevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String emil = templateEngine.process("email", context);
            helper.setText(emil, true);
            javaMailSender.send(helper.getMimeMessage());
            log.info("邮件发送成功！");
        }
    }
}
