
import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.provider.EasyrayServiceImpl ;
import org.springframework.stereotype.Component;

/**
 *
 * @author ${AUTHOR}
 * @since ${DATE}
 */
@Service
@Component
public class ${ENTITY}LocalProviderImpl extends EasyrayServiceImpl<${ENTITY}Mapper, ${ENTITY}> implements ${ENTITY}LocalProvider {


}
