package no.arktekk.training.spring.invocationhandler;

import org.junit.Test;

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



}
