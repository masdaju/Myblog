package com.cg.myblog.configration;

import com.cg.myblog.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
//    LoginInterceptor loginInterceptor;
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/logout","/homepage","/user/doLogin","/user/register","/toRegister")
                .excludePathPatterns("/getPostforAll")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/js/*.js")
                .excludePathPatterns("/css/*.css");
    }
}
