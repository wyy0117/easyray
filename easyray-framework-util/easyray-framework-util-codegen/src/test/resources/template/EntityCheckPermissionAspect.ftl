package ${PROVIDER_IMPL_PACKAGE};

import com.easyray.baseapi.provider.CheckPermissionAspect;
import ${PROVIDER_PACKAGE}.${ENTITY}CheckPermission;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@Aspect
@Component
public class ${ENTITY}CheckPermissionAspect extends CheckPermissionAspect<${ENTITY}CheckPermission>{

@Autowired
private ${ENTITY}CheckPermission checkPermission;

@Override
public ${ENTITY}CheckPermission getCheckPermission(){
        return checkPermission;
}

@Pointcut("target(${ENTITY}ProviderImpl)")
@Override
public void pointcut(){

        }
}
