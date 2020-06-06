
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_22
 * @Author: wyy
 */
@Service(filter = {"CheckPermissionFilter"})
@Component
public class TeamProviderImpl extends TeamLocalProviderImpl implements TeamProvider {

}
