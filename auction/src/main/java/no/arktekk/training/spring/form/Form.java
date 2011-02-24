package no.arktekk.training.spring.form;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface Form<T> {

    T to();

    AuctionForm apply(T domainObject);
}
