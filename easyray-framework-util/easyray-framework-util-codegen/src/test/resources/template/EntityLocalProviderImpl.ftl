package ${PROVIDER_IMPL_PACKAGE};

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import ${ENTITY_PACKAGE}.${ENTITY};
import ${PROVIDER_PACKAGE}.${ENTITY}LocalProvider;
import ${MAPPER_PACKAGE}.${ENTITY}eMapper;
import org.springframework.stereotype.Component;

/**
 * @author ${AUTHOR}
 * @since ${DATE}
 */
@Service
@Component
public class ${ENTITY}LocalProviderImpl extends EasyrayServiceImpl<${ENTITY}Mapper,${ENTITY}>implements ${ENTITY}LocalProvider{


}
