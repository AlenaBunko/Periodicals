package org.study.periodicals.filters;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.study.periodicals.configuration.SecurityConfig;
import org.study.periodicals.service.UserAuthorizationService;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

public class AuthorizationFilter extends HttpFilter {

    private UserAuthorizationService userAuthorizationService;

    private FilterConfig filterConfig;

    private SecurityConfig securityConfig;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpSession session = req.getSession(false);

        boolean userAuthorized = userAuthorizationService.isUserAuthorized(session.getId());

        String contextPath = filterConfig.getServletContext().getContextPath();

        if (!userAuthorized) {
            res.sendRedirect(contextPath + "/loginPage");
            return;
        }
//        else {
//            String currentUri = req.getRequestURI();
//            for (Pattern it : securityConfig.getRolesToUrls().get(1)) {
//                boolean matches = it.pattern().matches(currentUri);
//                if (!matches) {
//                    try {
//                        res.sendRedirect(contextPath + "/errorPage");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return;
//
//        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

        this.filterConfig = config;

        this.userAuthorizationService =
                (UserAuthorizationService) WebApplicationContextUtils.
                        getRequiredWebApplicationContext(config.getServletContext()).
                        getBean("userAuthorizationService");
        this.securityConfig = (SecurityConfig) WebApplicationContextUtils.
                getRequiredWebApplicationContext(config.getServletContext()).
                getBean("securityConfig");

        super.init(config);

    }
}
