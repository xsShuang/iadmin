package xyz.iotcode.iadmin.demo.module.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserRoleQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRolePermission;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.iotcode.iadmin.demo.module.system.mapper.SysRolePermissionMapper;
import xyz.iotcode.iadmin.demo.module.system.mapper.SysUserRoleMapper;

import javax.validation.constraints.NotEmpty;
import java.util.List;
/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户id删除角色信息
     * @version: 1.0
     * @date: 2019/9/11 17:04
     * @author: xieshuang
     * @param userId
     * @return boolean
     */
    @Caching(evict = {
            @CacheEvict(cacheNames = "cache-SysPermission-ByUserId",  key = "#userId")
    })
    boolean removeByUserId(Integer userId);

    /**
     * 批量保存用户与角色的对应关系
     * @param sysUserRoles
     * @return
     */
    boolean saveBatch(@NotEmpty List<SysUserRole> sysUserRoles);
}
