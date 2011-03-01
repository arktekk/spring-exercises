package no.arktekk.training.spring.config;

import no.arktekk.training.spring.util.AnnotationModelAndViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Config file that sets up Spring MVC the way I would like for the auction application.
 * <p/>
 * Uses jspx as view, and also incorporates a little tweeks so that we can use "/" as
 * servlet mapping, and our very own way of determining view names.
 * <p/>
 * Intentionally not a @Configuration class so that it is not picked up
 * by the main application context component scanning.
 * Needs to be added to the mvc context manually.
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class MvcConfig {

    /**
     * Set it up, so that Spring looks for views as jspx files in the /WEB-INF/views folder
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jspx");
        return viewResolver;
    }

    /**
     * Registeres the file labels.properties, labels_nb_NO.properties ...
     * as a source for labels to be used in the jspx files.
     * Uses standards java resource bundle stragegies
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("labels");
        return messageSource;
    }

    /**
     * Selects the strategy for resolving locale in the webapp. This particular strategy
     * tries to find the accept-language header from the browser.
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    /**
     * Since we are using "/" as servlet mapping, we need to force the spring controller
     * servlet to use annotations as the first strategy for handling request mapping and
     * model attribute handling. If we do not enforce order=0 for the mapping, then
     * the webserver default servlet will try to handle the reuest, and tries to find
     * static resources instead.
     */
    @Autowired
    public void handlerMapping(DefaultAnnotationHandlerMapping handlerMapping) {
        handlerMapping.setOrder(0);
    }

    /**
     * I really did not like the way Spring do not allow us to specify a concrete view name even when
     * using annotations. The default rule is that the view name returned is the same as the one entered.
     * I would prefer if I had an annotation on the method specifying both the view name, and optionally
     * the model attribute name. So I created a custom view resolver to support this, and an annotation
     * to specify it.
     */
    @Autowired
    public void handlerAdapter(AnnotationMethodHandlerAdapter adapter) {
        adapter.setCustomModelAndViewResolver(new AnnotationModelAndViewResolver());
    }
}
