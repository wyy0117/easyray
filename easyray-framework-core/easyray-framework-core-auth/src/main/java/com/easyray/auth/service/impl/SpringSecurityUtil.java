package com.easyray.auth.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.easyray.auth.entity.UserDetailsImpl;
import com.easyray.baseapi.constant.FieldNameConstant;
import com.easyray.common.util.ApplicationContextUtil;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.service.UserLocalProvider;
import org.apache.commons.collections4.map.PassiveExpiringMap;
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

    private Map<Long, User> username_user_map = new PassiveExpiringMap<>(30, TimeUnit.MINUTES);
    private Map<Thread, User> thread_user_map = new PassiveExpiringMap<>(30, TimeUnit.MINUTES);

    @Reference
    private UserLocalProvider userLocalProvider;

    public void setUser(User user) {
        username_user_map.put(user.getId(), user);
        thread_user_map.put(Thread.currentThread(), user);
    }

    public User getUser() {
        User user;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                    String username = ((UserDetailsImpl) principal).getUser().getUsername();
                    user = userLocalProvider.fetchByUsername(username);
                    if (user != null) {
                        setUser(user);
                        return user;
                    }
                }
            }

            String username = RpcContext.getContext().getAttachment(FieldNameConstant.username);
            if (username != null && !username.equals("")) {

                //从缓存中可以取到
                user = username_user_map.get(username);
                if (user != null) {
                    setUser(user);
                    return user;
                }
                if (userLocalProvider == null) {
                    userLocalProvider = ApplicationContextUtil.getBean("userLocalProviderImpl", UserLocalProvider.class);
                    //从数据库中读
                    user = userLocalProvider.fetchByUsername(username);
                    if (user != null) {
                        setUser(user);
                        return user;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
