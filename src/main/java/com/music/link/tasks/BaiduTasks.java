package com.music.link.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by liangbicong
 * Created Date 12/12/2015
 * Created Time 17:37
 * Created Version 1.1
 * 功能描述：定时任务
 */
@Component
public class BaiduTasks {
    /**
     * 每隔1秒钟做一次
     *//*
    @Scheduled(initialDelay = 200, fixedRate = 1 * 1 * 1000)
    public void getBaiduMusic() {
    }

    *//**
     * 每天22:02点11:02点做一次
     *//*
    @Scheduled(cron = "0 2 22,11 * * ?")
    public void getBaiduPic() {
    }*/

}
