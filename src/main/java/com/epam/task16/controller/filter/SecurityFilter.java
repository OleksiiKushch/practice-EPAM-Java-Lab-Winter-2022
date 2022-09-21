package com.epam.task16.controller.filter;

import com.epam.task11.constant.ShopLiterals;
import com.epam.task11.entity.user.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SecurityFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext context =  servletRequest.getServletContext();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Map<String, String> securedPages = (Map<String, String>) context.getAttribute(ShopLiterals.SECURED_PAGES);
        LOG.debug("Secured pages: " + securedPages);
        String servletPath = request.getServletPath();
        LOG.debug("Current servlet path: " + servletPath);

        if (securedPages.containsKey(servletPath)) {
            User user = (User) session.getAttribute(ShopLiterals.LOGGED_USER);
            LOG.debug("Current user: " + user);
            if (Objects.isNull(user)) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            } else {
                if (!securedPages.get(servletPath).equals(user.getRole().getName())) {
                    List<String> errors = new ArrayList<>();
                    errors.add("Sorry, access denied!");
                    session.setAttribute(ShopLiterals.ERRORS, errors);
                    response.sendRedirect(request.getContextPath() + "/error");
                    return;
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
