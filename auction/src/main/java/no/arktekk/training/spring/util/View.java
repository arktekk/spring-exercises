package no.arktekk.training.spring.util;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface View {
    String value();

    String modelAttribute() default "";

}
