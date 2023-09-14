package org.boluo.mailserver.receive;

import com.boluo.hr.pojo.Employee;
import com.boluo.hr.pojo.MailConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MailReceiver {

    public Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;

    @RabbitListener(queues = MailConstans.MAIL_QUEUE_NAME)
    public void handler(Message message) {
        Employee employee = (Employee) message.getPayload();
//        消息转发
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
            context.setVariable("joblevelName", employee.getJoblevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String emil = templateEngine.process("email", context);
            helper.setText(emil, true);
            javaMailSender.send(helper.getMimeMessage());
            logger.info("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败！");
        }
    }
}
