package no.arktekk.training.spring.form;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface Form<T> {
    final String DATE_PATTERN = "dd.MM.yyyy hh:mm";

    T to();

    AuctionForm apply(T domainObject);
}
