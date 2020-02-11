
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@Service(filter = {"CheckPermissionFilter"})
@Component
public class ${ENTITY}ServiceImpl extends ${ENTITY}LocalServiceImpl implements ${ENTITY}Service {

}
