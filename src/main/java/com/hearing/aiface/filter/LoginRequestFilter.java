package com.hearing.aiface.filter;

import com.hearing.aiface.util.Constant;
import com.hearing.aiface.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by hearing on 18-10-17
 */
@Component
@WebFilter(filterName = "loginRequestFilter", urlPatterns = "/aiface/*")
public class LoginRequestFilter implements Filter {
    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        for (String p : Constant.PERMISSIONS) {
            if (request.getRequestURI().contains(p)) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        String authToken = request.getHeader(this.tokenHeader);
        if (authToken != null) {
            String username = this.tokenUtil.getUsernameFromToken(authToken);
            String password = this.tokenUtil.getPasswordFromToken(authToken);

            if (username != null && password != null && this.tokenUtil.validateToken(authToken)) {
                System.out.println("validate token!");
                chain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("/aiface/reLogin");
            }
        } else {
            response.sendRedirect("/aiface/reLogin");
        }
    }

    @Override
    public void destroy() {

    }
}
