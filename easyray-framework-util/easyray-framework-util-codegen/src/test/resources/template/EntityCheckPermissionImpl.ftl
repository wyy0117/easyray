package ${PROVIDER_IMPL_PACKAGE};

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import ${ENTITY_PACKAGE}.${ENTITY};
import ${PROVIDER_PACKAGE}.${ENTITY}CheckPermission;
import ${MAPPER_PACKAGE}.${ENTITY}Mapper;
import org.springframework.stereotype.Component;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
@Component
public class ${ENTITY}CheckPermissionImpl extends EasyrayServiceImpl<${ENTITY}Mapper,${ENTITY}>implements ${ENTITY}CheckPermission{

}
