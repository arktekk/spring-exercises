package no.arktekk.training.spring.util;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AnnotationModelAndViewResolver implements ModelAndViewResolver {
    public org.springframework.web.servlet.ModelAndView resolveModelAndView(Method handlerMethod, Class handlerType, Object returnValue, ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
        final org.springframework.web.servlet.ModelAndView modelAndView = new org.springframework.web.servlet.ModelAndView();
        if (handlerMethod.isAnnotationPresent(View.class)) {
            View viewAnnotation = handlerMethod.getAnnotation(View.class);
            modelAndView.setViewName(viewAnnotation.value());
            transferImplicitModel(modelAndView, implicitModel);
            updatModel(handlerMethod, returnValue, modelAndView, viewAnnotation);
            return modelAndView;

        } else {
            return UNRESOLVED;
        }
    }

    private void updatModel(Method handlerMethod, Object returnValue, org.springframework.web.servlet.ModelAndView modelAndView, View viewAnnotation) {
        if (viewAnnotation.modelAttribute().isEmpty()) {
            if (handlerMethod.isAnnotationPresent(ModelAttribute.class)) {
                ModelAttribute modelAttribute = handlerMethod.getAnnotation(ModelAttribute.class);
                addObjectToModel(modelAndView, modelAttribute.value(), returnValue);
            } else {
                addObjectToModel(modelAndView, returnValue);
            }
        } else {
            addObjectToModel(modelAndView, viewAnnotation.modelAttribute(), returnValue);
        }
    }

    private void addObjectToModel(org.springframework.web.servlet.ModelAndView modelAndView, Object value) {
        addObjectToModel(modelAndView, null, value);
    }

    private void addObjectToModel(org.springframework.web.servlet.ModelAndView modelAndView, String name, Object value) {
        if (value != null) {
            if (name == null) {
                modelAndView.addObject(value);
            } else {
                modelAndView.addObject(name, value);
            }
        }
    }

    private void transferImplicitModel(org.springframework.web.servlet.ModelAndView modelAndView, ExtendedModelMap implicitModel) {
        for (String key : implicitModel.keySet()) {
            modelAndView.addObject(key, implicitModel.get(key));
        }
    }
}
