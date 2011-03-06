package no.arktekk.training.spring.monitor.impl;

import com.jamonapi.MonitorFactory;
import no.arktekk.training.spring.monitor.Monitor;
import org.joda.time.DateTime;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class JamonMonitor implements Monitor {
    private final com.jamonapi.Monitor monitor;

    public JamonMonitor(com.jamonapi.Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public Monitor start() {
        monitor.start();
        return this;
    }

    @Override
    public Monitor stop() {
        monitor.stop();
        return this;
    }


    @Override
    public Double hits() {
        return monitor.getHits();
    }

    @Override
    public Double totalCallTime() {
        return monitor.getTotal();
    }

    @Override
    public DateTime lastAccessTime() {
        return new DateTime(monitor.getLastAccess());

    }

    @Override
    public Double averageCallTime(String methodName) {
        return monitor.getAvg();
    }

    @Override
    public Double totalCallTime(String methodName) {
        return monitor.getTotal();
    }

    @Override
    public Double minimumCallTime(String methodName) {
        return monitor.getMin();
    }

    @Override
    public Double maximumCallTime(String methodName) {
        return monitor.getMax();
    }

    @Override
    public String toString() {
        return monitor.toString();
    }
}
