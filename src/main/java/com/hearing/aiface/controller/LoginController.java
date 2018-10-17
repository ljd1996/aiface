package com.hearing.aiface.controller;

import com.hearing.aiface.bean.Admin;
import com.hearing.aiface.service.FaceService;
import com.hearing.aiface.util.Constant;
import com.hearing.aiface.util.Msg;
import com.hearing.aiface.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by hearing on 18-10-17
 */
@Controller
public class LoginController {
    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private FaceService faceService;


    @GetMapping("/admin")
    public String admin() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        if (session.getAttribute(Constant.LOGIN_TOKEN) != null) {
            session.removeAttribute(Constant.LOGIN_TOKEN);
        }
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Msg login(HttpServletRequest request, HttpServletResponse response, Admin admin) {
        Msg msg = faceService.login(admin);
        if (msg.getCode() == Constant.CODE_FAILED) {
            try {
                response.sendRedirect("/admin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String token = tokenUtil.generateToken(admin);
                msg.add(Constant.LOGIN_TOKEN, token);

                HttpSession session = request.getSession(false);
                if (session == null) {
                    session = request.getSession(true);
                }
                session.setAttribute(Constant.LOGIN_TOKEN, token);

                response.sendRedirect("/face/list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
