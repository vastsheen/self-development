package com.xh.config;

import com.xh.annotations.NeedDoc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Set;

@Component
@Slf4j
public class SwaggerExtension extends AbstractHandlerMethodMapping {
    @Override
    protected boolean isHandler(Class beanType) {
        final boolean b = AnnotatedElementUtils.hasAnnotation(beanType, NeedDoc.class);
        if (b){
            log.info("check success");
        }
        return b;
    }

    @Override
    protected Object getMappingForMethod(Method method, Class handlerType) {
        RequestMappingInfo.Builder builder = RequestMappingInfo
                .paths("/needDock/demo")
                .methods(RequestMethod.GET);
        return builder.build();
    }

    @Override
    protected Set<String> getMappingPathPatterns(Object mapping) {
        final RequestMappingInfo info = (RequestMappingInfo) mapping;
        return info.getPatternsCondition().getPatterns();
    }

    @Override
    protected Object getMatchingMapping(Object mapping, HttpServletRequest request) {
        return null;
    }

    @Override
    protected Comparator getMappingComparator(HttpServletRequest request) {
        return null;
    }
}
