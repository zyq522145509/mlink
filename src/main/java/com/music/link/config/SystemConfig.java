package com.music.link.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * Created by liangbicong
 * Created Date 12/12/2015
 * Created Time 18:52
 * Created Version 1.1
 */
@Component
@Configuration
@PropertySources(value = {@PropertySource("classpath:/application.properties")})
public class SystemConfig {
}
