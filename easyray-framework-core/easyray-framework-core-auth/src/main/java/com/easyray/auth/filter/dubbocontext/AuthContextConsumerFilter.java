package com.easyray.auth.filter.dubbocontext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.baseapi.constant.FieldNameConstant;
import com.easyray.common.util.ApplicationContextUtil;
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

        SpringSecurityThreadLocal springSecurityThreadLocal = ApplicationContextUtil.getBean(SpringSecurityThreadLocal.class);
        if (springSecurityThreadLocal != null
                && springSecurityThreadLocal.getUser() != null) {
            RpcContext.getContext().setAttachment(FieldNameConstant.userId, springSecurityThreadLocal.getUser().getId().toString());
        }
        return invoker.invoke(invocation);
    }
}
