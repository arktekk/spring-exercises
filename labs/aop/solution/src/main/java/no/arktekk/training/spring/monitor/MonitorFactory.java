package no.arktekk.training.spring.monitor;

import no.arktekk.training.spring.monitor.impl.JamonMonitor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Component
public class MonitorFactory {
    private Map<String, Monitor> monitors = new HashMap<String, Monitor>();

    public Monitor monitor(String monitorName) {
        if (!monitors.containsKey(monitorName)) {
            monitors.put(monitorName, new JamonMonitor(com.jamonapi.MonitorFactory.start(monitorName)));
        }
        return monitors.get(monitorName);
    }
}
