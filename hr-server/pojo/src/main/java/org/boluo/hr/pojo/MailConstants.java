package org.boluo.hr.pojo;

/**
 * 邮件常量
 *
 * @author @1352955539(boluo)
 * @date 2021/2/19 - 19:17
 */
public class MailConstants {
    public static final Integer SENDING = 0;
    public static final Integer SUCCESS = 1;
    public static final Integer FAIL = 3;
    public static final Integer MAX_TRY_COUNT = 3;
    public static final Integer MSG_TIMEOUT = 1;
    public static final String MAIL_QUEUE_NAME = "java.boluo.queue";
    public static final String MAIL_EXCHANGE_NAME = "java.mail.exchange";
    public static final String MAIL_ROUTING_KEY_NAME = "java.mail.routing.key";
}
