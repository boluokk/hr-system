package org.boluo.hr.auto;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author 🍍
 * @date 2023/10/27
 */
@Component
public class CustomHttpSessionListener extends HttpSessionEventPublisher {
    private final static LongAdder HR_COUNT = new LongAdder();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HR_COUNT.increment();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HR_COUNT.decrement();
    }

    public int getOnlineCount() {
        return (int) HR_COUNT.sum();
    }
}
