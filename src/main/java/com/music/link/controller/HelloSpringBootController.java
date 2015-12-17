package com.music.link.controller;

import com.music.link.entity.User;
import com.music.link.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Lipengfei on 2015/6/24.
 */
@Controller
@RequestMapping("/")
public class HelloSpringBootController {
    private final static Logger logger = LoggerFactory.getLogger(HelloSpringBootController.class);

    @Autowired
    private UserService userService;

    @Transactional
    @RequestMapping("/")
    @ResponseBody
    public String sayHello(Pageable pageable) {


        Page<User> all = userService.getAll(pageable);

        User user = userService.getUser(1L);

        List<User> userList = userService.getUserList();

        Page<User> allList = userService.getUserAll(pageable);

       /* System.out.println(all.getTotalPages());
        System.out.println(user.getId());
        System.out.println(userList.get(0).getId());
        System.out.println(allList.getTotalPages());*/
        userService.save();
        userService.saveUser();
        logger.debug("debug");
        logger.info("info");
        logger.error("info");
        return "hello SpringBoot! -- userId:";
    }

}
