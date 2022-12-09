package com.kob.backend.controller.user;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
public class UserController {

    @Autowired //用到数据库里的mapper要加
    UserMapper userMapper;

    @GetMapping("/user/all")
    public List<User> getAll() {
        System.out.println("yes");
        return userMapper.selectList(null);
    }
}
