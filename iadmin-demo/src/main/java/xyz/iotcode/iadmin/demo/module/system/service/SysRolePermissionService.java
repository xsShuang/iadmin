package xyz.iotcode.iadmin.demo.module.system.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import xyz.iotcode.iadmin.demo.module.system.entity.SysPermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 根据角色id删除与权限的关联关系
     * @param roleId
     * @return
     */
    @Caching(evict = {
            @CacheEvict(cacheNames = "cache-SysPermission-ByRoleId",  key = "#roleId")
    })
    boolean removeByRoleId(Integer roleId);

    /**
     * 批量保存角色与权限的关联关系
     * @param sysRolePermissionList
     * @return
     */
    boolean saveBatch(List<SysRolePermission> sysRolePermissionList);

    @Cacheable(cacheNames = "cache-SysPermission-ByRoleId",  key = "#roleId", unless = "#result==null")
    Set<SysPermission> getByRoleId(Integer roleId);
}
