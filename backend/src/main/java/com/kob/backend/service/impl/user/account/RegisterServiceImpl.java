package com.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.config.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if  (username == null) {
            map.put("error message", "usename can't be null");
            return map;
        }
        if (password == null || confirmedPassword == null) {
            map.put("error message", "password can't be null");
            return map;
        }

        username = username.trim();

        if (username.length() == 0) {
            map.put("error message", "usename can't be empty");
            return map;
        }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error message", "password can't be empty");
            return map;
        }

        if (username.length() >= 100) {
            map.put("error message", "username length can't longer than 100");
            return map;
        }

        if (password.length() >= 100) {
            map.put("error message", "password length can't longer than 100");
            return map;
        }

        if (!password.equals(confirmedPassword)) {
            map.put("error message", "password doesn't match");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error message", "username already exist");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/256676_sm_fe8e34246e.jpg";
        User user = new User(null,username, encodedPassword, photo);
        userMapper.insert(user);

        map.put("error message", "create user successfully");


        return map;
    }
}
