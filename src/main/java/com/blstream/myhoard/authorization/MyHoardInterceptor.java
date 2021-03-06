package com.blstream.myhoard.authorization;

import static com.blstream.myhoard.constants.Constants.USER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.blstream.myhoard.biz.model.TokenDTO;
import com.blstream.myhoard.biz.service.TokenService;
import com.blstream.myhoard.constants.Constants;
import com.blstream.myhoard.exception.AuthorizationException;

public class MyHoardInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(MyHoardInterceptor.class.getCanonicalName());

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("MyHoardInterceptor - preHandle");

        if (isAllowed(request)) {
            return true;
        }

        final String AccessToken = request.getHeader("Authorization");

        if (AccessToken == null) {
            logger.error("AccessToken is null");
            throw new AuthorizationException(Constants.ERROR_CODE_AUTH_TOKEN_INVALID);
        }

        TokenDTO tokenDTO = tokenService.getByAccessToken(AccessToken);

        request.setAttribute(USER, tokenDTO.getUser());

        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(tokenDTO.getUser()));

        // TODO RT remove
        logger.info(String.format("\nAccess Token: %s\nToken ID: %s\nUser ID: %s\nUser email: %s",
                AccessToken, tokenDTO.getId(), tokenDTO.getUser().getId(), tokenDTO.getUser().getEmail()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        logger.info("MyHoardInterceptor - postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        logger.info("MyHoardInterceptor - afterCompletion");
    }

    private boolean isAllowed(HttpServletRequest request) {
        logger.info(String.format("URI: %s METHOD: %s", request.getRequestURI().toUpperCase(), request.getMethod().toUpperCase()));

        return (request.getRequestURI().toUpperCase().equals("/USERS")
                || request.getRequestURI().toUpperCase().equals("/USERS/")
                || request.getRequestURI().toUpperCase().equals("//USERS/")
                || request.getRequestURI().toUpperCase().equals("//USERS"))
                && request.getMethod().toUpperCase().equals("POST");
    }

}
