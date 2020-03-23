package com.sun.zq.config;

import com.sun.zq.model.TUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 描述：自定义UserDetails，使UserDetails具有TUser的实体结构
 */
public class CustomUserDetails extends TUser implements UserDetails {

    public CustomUserDetails(TUser tUser) {
        if (null != tUser) {
            this.setId(tUser.getId());
            this.setCode(tUser.getCode());
            this.setCreateTime(tUser.getCreateTime());
            this.setUpdateTime(tUser.getUpdateTime());
            this.setUsername(tUser.getUsername());
            this.setPassword(tUser.getPassword());
            this.setIsDelete(tUser.getIsDelete());
            this.setEmail(tUser.getEmail());
            this.setPhone(tUser.getPhone());
            this.setRole(tUser.getRole());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
