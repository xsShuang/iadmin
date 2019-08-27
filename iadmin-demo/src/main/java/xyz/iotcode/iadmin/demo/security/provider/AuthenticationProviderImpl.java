package xyz.iotcode.iadmin.demo.security.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.common.exception.MyRuntimeException;
import xyz.iotcode.iadmin.demo.config.redis.RedisUtils;
import xyz.iotcode.iadmin.demo.security.bean.LoginDTO;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRole;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUser;
import xyz.iotcode.iadmin.demo.module.system.service.SysRoleService;
import xyz.iotcode.iadmin.permissions.bean.PermissionUser;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private static final long SEVEN_DAYS = 604800L;

    private static final long TWO_HOURS = 7200;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public String login(LoginDTO dto) {
        long l;
        SysUser sysUser = new SysUser().selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, dto.getUsername())
                .eq(SysUser::getPassword, dto.getPassword()));
        if (sysUser==null){
            throw new MyRuntimeException("用户名或者密码错误");
        }
        if (sysUser.getState()==0){
            throw new MyRuntimeException("账户已被禁用");
        }
        if (dto.getSaveLogin()!=null&&dto.getSaveLogin()){
            l = SEVEN_DAYS;
        }else {
            l = TWO_HOURS;
        }
        PermissionUser user = new PermissionUser();
        BeanUtils.copyProperties(sysUser, user);
        user.setRoles(sysRoleService.getByUserId(sysUser.getUserId()).stream().map(SysRole::getLabel).collect(Collectors.toList()));
        String s = UUID.randomUUID().toString();
        redisUtils.set(s, user, l);
        return s;
    }

    @Override
    public PermissionUser getUserByToken(String token) {
        PermissionUser user = (PermissionUser) redisUtils.get(token);
        if (user==null){
            throw new MyRuntimeException("token 已过期");
        }
        return user;
    }
}
