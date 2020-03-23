package com.sun.zq.service;

import com.sun.zq.dao.TUserMapper;
import com.sun.zq.model.TUser;
import com.sun.zq.model.TUserExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public TUser findByName(String name) {
        TUserExample example = new TUserExample();
        example.or().andUsernameEqualTo(name);
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        return tUsers.stream().findFirst().get();
    }
}
