package com.wyy.userservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyy.easyry.entity.User;
import com.wyy.easyry.service.UserLocalService;
import com.wyy.userservice.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyy
 * @since 2020-01-26
 */
@Service
@Component("userLocalService")
public class UserLocalServiceImpl extends ServiceImpl<UserMapper, User> implements UserLocalService {

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User fetchByUsername(String username) {
        return null;
    }

}
