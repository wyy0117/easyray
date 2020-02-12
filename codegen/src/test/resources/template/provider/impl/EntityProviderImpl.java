
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@Service(filter = {"CheckPermissionFilter"})
@Component
public class ${ENTITY}ProviderImpl extends ${ENTITY}LocalProviderImpl implements ${ENTITY}Provider {

}
