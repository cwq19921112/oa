package com.chenwuqiang.oa.filter;

import com.chenwuqiang.oa.entity.Account;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    public static final List<String> whiteList = Arrays.asList("/account/validataAccount", "/account/login", "/account/list",
            "/account/register", "/account/reg-success", "/index", "/css", "/images", "/js", "/druid");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        for (String white : whiteList) {
            if (requestURI.contains(white)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("/oa/account/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("========Filter Init=========");
    }
}
