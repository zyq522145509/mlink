package com.music.link.service;

import com.music.link.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Lipengfei on 2015/6/26.
 */
public interface UserService {

    User getUser(Long id);

    Page<User> getAll(Pageable pageable);
    @Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")
    List<User> getUserList();

    Page<User> getUserAll(Pageable pageable);

    void save();

    void saveUser();

}
