package ${PROVIDER_IMPL_PACKAGE};

import org.apache.dubbo.config.annotation.DubboService;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import ${ENTITY_PACKAGE}.${ENTITY};
import ${PROVIDER_PACKAGE}.${ENTITY}LocalProvider;
import ${MAPPER_PACKAGE}.${ENTITY}eMapper;
import org.springframework.stereotype.Service;

/**
 * @author ${AUTHOR}
 * @since ${DATE}
 */
@DubboService
@Service
public class ${ENTITY}LocalProviderImpl extends EasyrayServiceImpl<${ENTITY}Mapper,${ENTITY}>implements ${ENTITY}LocalProvider{


}
