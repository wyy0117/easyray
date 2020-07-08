package com.easyray.auth.service.impl;

import com.easyray.auth.entity.UserDetailsImpl;
import com.easyray.baseapi.constant.FieldNameConstant;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.service.UserLocalProvider;
import com.google.gson.Gson;
import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2020/6/10
 * @Author: wyy
 */
@Component
public class SpringSecurityUtil {

    private Map<Long, User> userId_user_map = new PassiveExpiringMap<>(30, TimeUnit.MINUTES);
    private Map<Thread, User> thread_user_map = new PassiveExpiringMap<>(30, TimeUnit.MINUTES);

    @DubboReference
    private UserLocalProvider userLocalProvider;

    /**
     * 方便测试使用
     *
     * @param user
     */
    public void setUser(User user) {
        userId_user_map.put(user.getId(), user);
        thread_user_map.put(Thread.currentThread(), user);
    }

    public User getOrSetUser() {
        User user;
        try {
            Thread thread = Thread.currentThread();
            user = thread_user_map.get(thread);
            //当前线程已有用户
            if (user != null) {
                setUser(user);
                return user;
            }

            //从SecurityContextHolder获取当前用户
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetailsImpl) {
                    user = ((UserDetailsImpl) principal);
                    setUser((user));
                    return user;
                }
            }

            String userStr = RpcContext.getContext().getAttachment(FieldNameConstant.user);
            if (userStr != null) {
                user = new Gson().fromJson(userStr, User.class);
                setUser(user);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
