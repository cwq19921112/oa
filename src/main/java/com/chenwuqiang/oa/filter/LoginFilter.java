package com.chenwuqiang.oa.filter;

import com.chenwuqiang.oa.entity.Account;
import com.chenwuqiang.oa.entity.Permission;
import com.chenwuqiang.oa.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
    public static final List<String> whiteList = Arrays.asList("/account/validataAccount", "/account/login", "/account/list",
            "/account/register", "/account/reg-success", "/index", "/css", "/images", "/js", "/druid", "/noAuth");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        LOGGER.info("request uri:" + requestURI);
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
        List<String> permitUris = account.getPermissionList().stream().map(Permission::getUri).collect(Collectors.toList());
        if (!hasAuth(account, permitUris, requestURI)) {
            request.getRequestDispatcher("/noAuth").forward(request, response);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean hasAuth(Account account,List<String> permitUris, String uri) {
        List<Role> roleList = account.getRoleList();
        boolean isAdmin = roleList.stream().anyMatch(role -> "管理员".equals(role.getName()));
        if (isAdmin) {
            return true;
        }
        for (String permitUri : permitUris) {
            if (uri.startsWith(permitUri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("========Filter Init=========");
    }
}
