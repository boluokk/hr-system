package org.boluo.mailserver.receive;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.boluo.hr.pojo.Employee;
import org.boluo.hr.pojo.MailConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author @1352955539(boluo)
 * @date 2021/2/19 - 19:31
 */
@Component
@Slf4j
public class MailReceiver {

    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;
    private final TemplateEngine templateEngine;

    @Autowired
    public MailReceiver(JavaMailSender javaMailSender,
                        MailProperties mailProperties,
                        TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
        this.templateEngine = templateEngine;
    }

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message) {
        Employee employee = JSONUtil.toBean((String) message.getPayload(), Employee.class);
        // 消息转发
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);

        try {
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
            log.info("邮件发送成功！{}", employee);
        } catch (MessagingException e) {
            log.error("邮件发送失败！");
        }
    }
}
