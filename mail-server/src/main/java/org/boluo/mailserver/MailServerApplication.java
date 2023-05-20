package org.boluo.mailserver;
import com.boluo.hr.pojo.MailConstans;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MailServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailServerApplication.class, args);
    }

    @Bean
    Queue queue() {
        return new Queue(MailConstans.MAIL_QUEUE_NAME);
    }
}
