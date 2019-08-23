package xyz.iotcode.iadmin.demo.system.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.system.controller.query.SysPermissionQuery;
import xyz.iotcode.iadmin.demo.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysPermissionService extends IService<SysPermission> {
    
    @Cacheable(cacheNames = "cache-SysPermission-page", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    IPage<SysPermission> ipage(SysPermissionQuery query);

    @CacheEvict(cacheNames = "cache-SysPermission-page", allEntries = true)
    boolean isave(SysPermission param);

    @Caching(evict = {
            @CacheEvict(cacheNames = "cache-SysPermission-page", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysPermission-byUrl", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysPermission-code", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysPermission-byId", key = "#param.id" )
    })
    boolean iupdate(SysPermission param);

    boolean iremove(List<Integer> list);

    @Cacheable(cacheNames = "cache-SysPermission-byId", unless = "#result==null")
    SysPermission igetById(Integer id);

    @Caching(evict = {
        @CacheEvict(cacheNames = "cache-SysPermission-page", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysPermission-byUrl", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysPermission-code", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysPermission-byId", key = "#id" )
    })
    boolean iremove(Integer id);

    @Cacheable(cacheNames = "cache-SysPermission-byUrl", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    SysPermission getByUrl(String url, String requestWay);

    @Cacheable(cacheNames = "cache-SysPermission-code", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    SysPermission getByCode(String code);
}
