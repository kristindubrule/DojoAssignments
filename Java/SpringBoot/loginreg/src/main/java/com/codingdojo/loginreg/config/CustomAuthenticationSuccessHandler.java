package com.codingdojo.loginreg.config;


import com.codingdojo.loginreg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.codingdojo.loginreg.models.*;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Component
public final class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String userName = authentication.getName();
        User authUser = userService.findByUsername(userName);

        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);



        userService.updateLastLoginDateForUserByName(authUser.getUsername());

        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        if (userService.isAdmin(authUser)) {
            handle(httpServletRequest, httpServletResponse, authentication, "/dashboard");
        } else {
            handle(httpServletRequest, httpServletResponse, authentication, "/home");
        }
    }


    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication, String targetUrl) throws IOException {
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
