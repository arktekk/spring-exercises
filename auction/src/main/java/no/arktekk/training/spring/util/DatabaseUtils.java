package no.arktekk.training.spring.util;

import org.springframework.format.datetime.DateFormatter;

import java.util.Locale;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class DatabaseUtils {
    public final static DateFormatter timeStampFormatter = new DateFormatter("yyyy-MM-dd hh:mm:ss");
    public final static Locale no_NO = new Locale("no_NO");
}
