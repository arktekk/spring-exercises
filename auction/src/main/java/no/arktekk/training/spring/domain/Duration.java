package no.arktekk.training.spring.domain;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Duration {
    private final Integer minutes;
    private final Integer seconds;

    public Duration(Integer minutes, Integer seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }


    public Integer getMinutes() {
        return minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }
}
