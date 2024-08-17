package com.novalabs.taskmanagement.interceptor;

import com.novalabs.taskmanagement.customException.TokenNotAvailableException;
import com.novalabs.taskmanagement.customException.TokenUnAuthorized;
import com.novalabs.taskmanagement.model.MetaResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    RestTemplate restTemplate;
    @Value("${auth.url}")
    String url;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader =request.getHeader("Authorization");
        if(authorizationHeader!=null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", authorizationHeader);
            HttpEntity<String> entity = new HttpEntity<String>("", headers);

            ResponseEntity<Map> authResponse=  restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
          if(authResponse.getStatusCode()==HttpStatus.OK){
            return true;}
          throw new TokenUnAuthorized("Token UnAuthorized");
        }
        throw new TokenNotAvailableException("token not available");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
