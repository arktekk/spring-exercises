package no.arktekk.training.spring.domain;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Track {
    private final Double id;
    private final String name;
    private final Duration duration;
    private final int number;
    private final boolean live;
    private final String note;


    public Track(double id, String name, int number, Duration duration, boolean live, String note) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.number = number;
        this.live = live;
        this.note = note;
    }

    public Track(String name, int number, Duration duration, boolean live, String note) {
        this.id = null;
        this.name = name;
        this.duration = duration;
        this.number = number;
        this.live = live;
        this.note = note;
    }


    public String name() {
        return name;
    }

    public Duration duration() {
        return duration;
    }

    public int number() {
        return number;
    }

    public boolean live() {
        return live;
    }

    public String note() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (live != track.live) return false;
        if (number != track.number) return false;
        if (duration != null ? !duration.equals(track.duration) : track.duration != null) return false;
        if (name != null ? !name.equals(track.name) : track.name != null) return false;
        if (note != null ? !note.equals(track.note) : track.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (live ? 1 : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
