package com.hearing.aiface.controller;

import com.hearing.aiface.bean.UserExample;
import com.hearing.aiface.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Create by hearing on 18-10-16
 */
@Controller
@RequestMapping("/face")
public class FaceController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/clear")
    @ResponseBody
    public String clear() {
        userMapper.deleteByExample(null);
        return "list";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") String id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(id);
        userMapper.deleteByExample(userExample);
        return "list";
    }

    @GetMapping("/list")
    public String getAllUser(Map<String, Object> map) {
        map.put("list", userMapper.selectByExample(null));
        return "list";
    }
}
