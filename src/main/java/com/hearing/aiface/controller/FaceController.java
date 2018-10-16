package com.hearing.aiface.controller;

import com.hearing.aiface.bean.User;
import com.hearing.aiface.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Create by hearing on 18-10-16
 */
@Controller
public class FaceController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/add")
    @ResponseBody
    public String add(User user) {
        if (user != null) {
            System.out.println(userMapper.insert(user));
        }
        return "hello";
    }

    @GetMapping("/list")
    public String getAllUser(Map<String, Object> map) {
        map.put("list", userMapper.selectByExample(null));
        return "list";
    }

    
}
