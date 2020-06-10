package com.easyray.auth.filter.dubbocontext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.easyray.auth.service.impl.SpringSecurityUtil;
import com.easyray.baseapi.constant.FieldNameConstant;
import com.easyray.common.util.ApplicationContextUtil;
import com.easyray.coreapi.entity.User;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Date: 20-5-2
 * @Author: wyy
 */

@Activate(group = Constants.CONSUMER)
public class AuthContextConsumerFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AuthContextConsumerFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        logger.debug("application {} doFilter for {}:{}", invoker.getUrl().getParameter("application"), invoker.getInterface().getSimpleName(), invocation.getMethodName());

        SpringSecurityUtil springSecurityUtil = ApplicationContextUtil.getBean(SpringSecurityUtil.class);
        User user = springSecurityUtil.getOrSetUser();
        if (user != null) {
            RpcContext.getContext().setAttachment(FieldNameConstant.user, new Gson().toJson(user));
        }
        return invoker.invoke(invocation);
    }
}
