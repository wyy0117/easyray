import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_22
 * @Author: wyy
 */
@Aspect
@Component
public class TeamCheckPermissionAspect extends CheckPermissionAspect<TeamCheckPermission> {

    @Autowired
    private TeamCheckPermission checkPermission;

    @Override
    public TeamCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(TeamProviderImpl)")
    @Override
    public void pointcut() {

    }
}
