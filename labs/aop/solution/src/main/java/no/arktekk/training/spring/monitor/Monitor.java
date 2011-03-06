package no.arktekk.training.spring.monitor;

import org.joda.time.DateTime;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface Monitor {
    Monitor start();

    Monitor stop();

    Double hits();

    Double totalCallTime();

    DateTime lastAccessTime();

    Double averageCallTime(String methodName);

    Double totalCallTime(String methodName);

    Double minimumCallTime(String methodName);

    Double maximumCallTime(String methodName);
}
