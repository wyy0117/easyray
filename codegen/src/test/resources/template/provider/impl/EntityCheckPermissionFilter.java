
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.easyray.baseapi.provider.BaseCheckPermissionFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@Component
public class ${ENTITY}CheckPermissionFilter extends BaseCheckPermissionFilter<${ENTITY}CheckPermission> {

    @Override
    public ${ENTITY}CheckPermission getCheckPermission() {
        ApplicationContext applicationContext = ServiceBean.getSpringContext();
        return applicationContext.getBean(${ENTITY}CheckPermission.class);
    }
}
