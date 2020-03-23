package com.sun.zq.config;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：自定义AuthenticationDetailsSource，将HttpServletRequest注入到CustomWebAuthenticationDetails，
 * 使其能获取到请求中的验证码等其他信息
 *
 * @author：sunzheng
 * @date 2020/3/23 23:49
 */
@Component
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomWebAuthenticationDetails(request);
    }


}
