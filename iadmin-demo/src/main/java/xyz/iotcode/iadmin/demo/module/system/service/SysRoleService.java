package xyz.iotcode.iadmin.demo.module.system.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysRoleQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysRoleService extends IService<SysRole> {
    
    @Cacheable(cacheNames = "cache-SysRole-page", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    IPage<SysRole> ipage(SysRoleQuery query);

    @CacheEvict(cacheNames = "cache-SysRole-page", allEntries = true)
    boolean isave(SysRole param);

    @Caching(evict = {
            @CacheEvict(cacheNames = "cache-SysRole-page", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysRole-getByPermission", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysRole-getByUserId", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysRole-byId", key = "#param.id" )
    })
    boolean iupdate(SysRole param);

    boolean iremove(List<Integer> list);

    @Cacheable(cacheNames = "cache-SysRole-byId", unless = "#result==null")
    SysRole igetById(Integer id);

    @Caching(evict = {
        @CacheEvict(cacheNames = "cache-SysRole-page", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysRole-getByPermission", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysRole-getByUserId", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysRole-byId", key = "#id" )
    })
    boolean iremove(Integer id);

    @Cacheable(cacheNames = "cache-SysRole-getByPermission", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    List<SysRole> getByPermissionId(Integer id);

    @Cacheable(cacheNames = "cache-SysRole-getByUserId", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    List<SysRole> getByUserId(Integer id);
}
