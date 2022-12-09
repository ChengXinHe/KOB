package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
public class UserController {

    @Autowired //用到数据库里的mapper要加
    UserMapper userMapper;







    //测试API
    @GetMapping("/user/all")
    public List<User> getAll() {
        System.out.println("yes");
        return userMapper.selectList(null);
    }

    @GetMapping("/user/{userId}/")
    public List<User> getuser(@PathVariable int userId){
        //复杂查询需要封装条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("id", 2).le("id", 3);
        return userMapper.selectList(queryWrapper);
    }

    @GetMapping("/user/add/{userId}/{username}/{password}/{rating}/")
    public String addUser(@PathVariable int userId,
                          @PathVariable String username,
                          @PathVariable String password,
                          @PathVariable int rating){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userId, username, encodedPassword, rating);
        userMapper.insert(user);
        return "Add User Successfully";
    }

    @GetMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable int userId){
        userMapper.deleteById(userId);
        return "Delete User Succussfully";
    }




}
