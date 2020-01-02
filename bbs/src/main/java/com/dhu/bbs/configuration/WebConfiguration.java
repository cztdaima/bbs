package com.dhu.bbs.configuration;

import com.dhu.bbs.interceptor.AdminInterceptor;
import com.dhu.bbs.interceptor.AdminRequireInterceprot;
import com.dhu.bbs.interceptor.LoginRequiredInterceptor;
import com.dhu.bbs.interceptor.PassportInterceptor;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


@Component
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    PassportInterceptor PassportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    AdminInterceptor adminInterceptor;

    @Autowired
    AdminRequireInterceprot adminRequireInterceprot;


    @Override
    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(PassportInterceptor);
        registry.addInterceptor(adminInterceptor);
        registry.addInterceptor(adminRequireInterceprot).addPathPatterns("/admin", "/admin_delete", "/informManage", "/adminDeleteInform");
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/post","/profile/*", "/message_comment","/changeHead", "/changePassword", "/title/*");



    }
	
	@Bean
    public LocaleResolver localeResolver(){
        return new NativeLocaleResolver();
    }

    protected static class NativeLocaleResolver implements LocaleResolver{

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getParameter("language");
            Locale locale = Locale.getDefault();
            if(!StringUtils.isEmpty(language)){
                String[] split = language.split("_");
                locale = new Locale(split[0],split[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}
