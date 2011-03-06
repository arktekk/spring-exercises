package no.arktekk.training.spring.aspect;

import no.arktekk.training.spring.monitor.Monitor;
import no.arktekk.training.spring.monitor.MonitorFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Aspect
public class RepositoryMonitor {
    private final MonitorFactory monitorFactory;

    @Autowired
    public RepositoryMonitor(MonitorFactory monitorFactory) {
        this.monitorFactory = monitorFactory;
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void withinRepositoryClass() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethods() {}


    @Around("publicMethods() && withinRepositoryClass()")
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
