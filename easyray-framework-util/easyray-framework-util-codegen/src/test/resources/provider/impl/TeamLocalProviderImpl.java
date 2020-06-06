
import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_22
 */
@Service
@Component
public class TeamLocalProviderImpl extends EasyrayServiceImpl<TeamMapper, Team> implements TeamLocalProvider {


}
