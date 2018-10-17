package com.hearing.aiface.controller;

import com.hearing.aiface.bean.Admin;
import com.hearing.aiface.bean.User;
import com.hearing.aiface.dao.UserMapper;
import com.hearing.aiface.service.FaceService;
import com.hearing.aiface.util.Constant;
import com.hearing.aiface.util.Msg;
import com.hearing.aiface.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by hearing on 18-10-17
 */
@Controller
@RequestMapping("/aiface")
public class AIFaceController {
    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private FaceService faceService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("name") String name) {
        System.out.println("name = " + name);
        return "hello";
    }

    @GetMapping("/reLogin")
    @ResponseBody
    public Msg reLogin() {
        System.out.println("reLogin...");
        Msg msg = new Msg();
        msg.setCode(Constant.CODE_RE_LOGIN);
        return msg;
    }

    @PostMapping("/login")
    @ResponseBody
    public Msg login(Admin admin) {
        Msg msg = faceService.login(admin);
        if (msg.getCode() == Constant.CODE_SUCCESS) {
            String token = tokenUtil.generateToken(admin);
            msg.add(Constant.LOGIN_TOKEN, token);
        }
        return msg;
    }

    @PostMapping("/validate")
    @ResponseBody
    public Msg validate(@RequestParam(Constant.LOGIN_TOKEN) String token) {
        Msg msg;
        if (tokenUtil.validateToken(token)) {
            msg = Msg.success();
        } else {
            msg = Msg.fail();
        }
        return msg;
    }

    @PostMapping("/add")
    @ResponseBody
    public Msg add(User user) {
        Msg msg;
        if (user != null) {
            List<User> users = userMapper.selectByExample(null);
            for (User u : users) {
                if (u.getId().equals(user.getId())) {
                    msg = new Msg();
                    msg.setCode(Constant.CODE_REPEAT);
                    msg.setMsg("重复签到");
                    return msg;
                }
            }
            if (userMapper.insert(user) > 0) {
                msg = Msg.success();
            } else {
                msg = Msg.fail();
            }
        } else {
            msg = Msg.fail();
        }
        return msg;
    }

    @GetMapping("/clear")
    @ResponseBody
    public Msg clear() {
        Msg msg;
        if (userMapper.deleteByExample(null) >= 0) {
            msg = Msg.success();
        } else {
            msg = Msg.fail();
        }
        return msg;
    }

    @GetMapping("/all")
    @ResponseBody
    public Msg all() {
        List<User> users = userMapper.selectByExample(null);
        return Msg.success().add(Constant.LIST_USERS, users);
    }
}

