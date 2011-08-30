package no.arktekk.training.spring.invocationhandler;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class TimerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                System.out.println("Starter metode timing");
                long start = System.currentTimeMillis();
                Object result = methodInvocation.proceed();
                long end = System.currentTimeMillis();
                System.out.println("Metodekall ferdig. Tiden det tok var: " + (end - start) + " ms");
                return result;
            }
        });
        return proxyFactory.getProxy(this.getClass().getClassLoader());
    }
}
