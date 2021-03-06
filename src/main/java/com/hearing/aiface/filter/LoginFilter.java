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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Create by hearing on 18-10-16
 */
@Component
@WebFilter(filterName = "loginFilter", urlPatterns = "/face/*")
public class LoginFilter implements Filter {

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        if (session.getAttribute(Constant.LOGIN_TOKEN) != null) {
            filterChain.doFilter(request, servletResponse);
            return;
        }

        String authToken = request.getHeader(this.tokenHeader);
        if (authToken != null) {
            String username = this.tokenUtil.getUsernameFromToken(authToken);
            String password = this.tokenUtil.getPasswordFromToken(authToken);

            if (username != null && password != null && this.tokenUtil.validateToken(authToken)) {
                System.out.println("validate token!");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("/admin");
            }
        } else {
            response.sendRedirect("/admin");
        }
    }

    @Override
    public void destroy() {
    }
}