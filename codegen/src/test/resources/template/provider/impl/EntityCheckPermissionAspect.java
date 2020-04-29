import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@Aspect
@Component
public class $ {
    ENTITY
}CheckPermissionAspect extends CheckPermissionAspect<${ENTITY}CheckPermission>{

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
