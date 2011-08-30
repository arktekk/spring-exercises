package no.arktekk.training.spring.invocationhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Timer implements InvocationHandler {
    private final Object target;

    public Timer(Object target) {
        this.target = target;
    }

    public static Object newInstance(Object target) {
        return java.lang.reflect.Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new Timer(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            System.out.println("Starter metode timing");
            result = method.invoke(target, args);
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("Metodekall ferdig. Tiden det tok var: " + (end - start) + " ms");
        }
        return result;
    }
}
