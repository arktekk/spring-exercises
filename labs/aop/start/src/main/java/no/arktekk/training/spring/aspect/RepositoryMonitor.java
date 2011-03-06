package no.arktekk.training.spring.aspect;

import no.arktekk.training.spring.monitor.Monitor;
import no.arktekk.training.spring.monitor.MonitorFactory;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class RepositoryMonitor {
    private final MonitorFactory monitorFactory;

    public RepositoryMonitor(MonitorFactory monitorFactory) {
        this.monitorFactory = monitorFactory;
    }

    public Object monitor(ProceedingJoinPoint method) throws Throwable {
        Monitor monitor = monitorFactory.monitor(createMonitorName(method));
        try {
            monitor.start();
            return method.proceed();
        } finally {
            monitor.stop();
            System.out.println("monitor = " + monitor);
        }

    }

    private String createMonitorName(ProceedingJoinPoint method) {
        return method.getSignature().getDeclaringTypeName() + "." + method.getSignature().getName();
    }

}
