package no.arktekk.training.spring.invocationhandler;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class InvocationHandlerTest {


    @Test
    public void withoutProxy() {
        MessageService service = new ConsoleMessageService();
        Boolean result = service.say("Hi");
        assertTrue(result);
    }

    @Test
    public void withProxy() {
        MessageService service = (MessageService) Timer.newInstance(new ConsoleMessageService());
        Boolean result = service.say("Hi from proxy");
        assertTrue(result);
    }

    @Test
    public void withoutProxyFromSpring() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Boolean result = ctx.getBean(MessageService.class).say("hi from spring");
        assertTrue(result);
    }

    @Test
    public void withProxyFromSpring() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, ProxyConfig.class);
        Boolean result = ctx.getBean(MessageService.class).say("hi from spring with proxy");
        assertTrue(result);
    }

    @Configuration
    public static class AppConfig {
        @Bean
        public MessageService messageService() {
            return new ConsoleMessageService();
        }
    }

    @Configuration
    public static class ProxyConfig {
        @Bean
        public BeanPostProcessor timer() {
            return new TimerBeanPostProcessor();
        }
    }

}
