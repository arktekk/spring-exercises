package no.arktekk.training.spring.invocationhandler;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class ConsoleMessageService implements MessageService {
    @Override
    public Boolean say(String message) {
        System.out.println(message);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // nuthin
        }
        return true;
    }
}
