package no.arktekk.training.spring.converter;

import no.arktekk.training.spring.domain.Duration;
import org.h2.util.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class DurationConverter implements Converter<String, Duration> {

    @Override
    public Duration convert(String value) {
        if (StringUtils.isNullOrEmpty(value)){
            return new Duration(null,null);
        }
        String[] split = value.split(":");
        int hour = Integer.valueOf(split[0]);
        int minute = Integer.valueOf(split[1]);
        return new Duration(hour, minute);
    }
}
