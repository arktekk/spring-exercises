package no.arktekk.training.spring.domain;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Duration {
    private final int minutes;
    private final int seconds;

    public Duration(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
