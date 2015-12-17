package com.music.link.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2015/6/29.
 */
@Configuration
public class ApplicationServlet {

    @Bean
    public ServletRegistrationBean mappingStatViewServlet(){

        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean mappingStatViewServlet = new ServletRegistrationBean(statViewServlet,"/druid/*");

        return  mappingStatViewServlet;
    }



}
