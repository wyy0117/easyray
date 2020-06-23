package ${PROVIDER_IMPL_PACKAGE};

import org.apache.dubbo.config.annotation.DubboService;
import ${PROVIDER_PACKAGE}.${ENTITY}Provider;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@DubboService
@Service
public class ${ENTITY}ProviderImpl extends ${ENTITY}LocalProviderImpl implements ${ENTITY}Provider{

}
