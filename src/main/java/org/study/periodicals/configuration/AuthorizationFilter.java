package org.study.periodicals.configuration;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.study.periodicals.service.UserAuthorizationService;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter extends HttpFilter {

    private UserAuthorizationService userAuthorizationService;

    private FilterConfig filterConfig;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        HttpSession session = req.getSession(false);

        boolean userAuthorized = userAuthorizationService.isUserAuthorized(session.getId());


        if (!userAuthorized) {
            String contextPath = filterConfig.getServletContext().getContextPath();
            res.sendRedirect(contextPath + "/loginPage");
            return;
        }


        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

        this.filterConfig = config;

        this.userAuthorizationService =
                (UserAuthorizationService) WebApplicationContextUtils.
                        getRequiredWebApplicationContext(config.getServletContext()).
                        getBean("userAuthorizationService");
        super.init(config);

    }
}
