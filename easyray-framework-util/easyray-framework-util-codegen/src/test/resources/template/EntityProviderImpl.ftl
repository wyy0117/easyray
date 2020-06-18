package ${PROVIDER_IMPL_PACKAGE};

import com.alibaba.dubbo.config.annotation.Service;
import ${PROVIDER_PACKAGE}.${ENTITY}Provider;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@Service
@Component
public class ${ENTITY}ProviderImpl extends ${ENTITY}LocalProviderImpl implements ${ENTITY}Provider{

}
