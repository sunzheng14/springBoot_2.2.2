package com.sun.zq.service;

import com.sun.zq.config.CustomUserDetails;
import com.sun.zq.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author：sunzheng
 * @date 2020/3/23 23:38
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private TUserService tUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TUser user = tUserService.findByName(userName);
        Assert.notNull(user, "用户名为" + userName + "的用户不存在");

        return new CustomUserDetails(user);

    }
}
