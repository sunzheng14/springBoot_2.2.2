package com.sun.zq.config;

import com.sun.zq.service.CustomUserDetailsService;
import com.sun.zq.util.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author：sunzheng
 * @date 2020/3/23 23:55
 */
@Component
@Slf4j
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 用户输入的用户名
        String userName = authentication.getName();
        // 用户输入的密码
        String password = authentication.getCredentials().toString();
        // 通过CustomWebAuthenticationDetails获取用户输入的验证码信息
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        String verifyCode = details.getVerifyCode();
        if (null == verifyCode || verifyCode.isEmpty()) {
            log.warn("未输入验证码");
            throw new NullPointerException("请输入验证码");
        }
        // 通过自定义的CustomUserDetailsService，以用户输入的用户名查询用户信息
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userName);
        // 校验用户密码
        if (!userDetails.getPassword().equals(password)) {
            log.warn("密码错误");
            throw new BadCredentialsException("密码错误");
        }
        Object principalToReturn = userDetails;
        // 将用户信息塞到SecurityContext中，方便获取当前用户信息
        return this.createSuccessAuthentication(principalToReturn, authentication, userDetails);
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return null;
    }

    /**
     * 验证用户输入的验证码
     *
     * @param inputVerifyCode
     * @return
     */
    public boolean validateVerifyCode(String inputVerifyCode) {
        //获取当前线程绑定的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 这个VerifyCodeFactory.SESSION_KEY是在servlet中存入session的名字
        HttpSession session = request.getSession();
        String verifyCode = (String) session.getAttribute(VerifyCodeUtil.SESSION_KEY);
        if (!StringUtils.hasText(verifyCode)) {
            log.warn("验证码过期请重新验证");
            throw new DisabledException("验证码过期，请重新验证");
        }
        // 不分区大小写
        verifyCode = verifyCode.toLowerCase();
        inputVerifyCode = inputVerifyCode.toLowerCase();

        log.info("验证码：{}, 用户输入：{}", verifyCode, inputVerifyCode);

        return verifyCode.equals(inputVerifyCode);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
