package com.music.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by liangbicong
 * Created Date 16/12/2015
 * Created Time 20:09
 * Created Version 1.1
 * 功能描述：
 */
@SpringBootApplication
@EnableScheduling
public class MusicServer {
    public static void main(String[] args) {
        SpringApplication.run(MusicServer.class, args);
    }
}
