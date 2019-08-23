package xyz.iotcode.iadmin.demo.system.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.system.controller.query.SysUserRoleQuery;
import xyz.iotcode.iadmin.demo.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
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
    
    @Cacheable(cacheNames = "cache-SysUserRole-page", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    IPage<SysUserRole> ipage(SysUserRoleQuery query);

    @CacheEvict(cacheNames = "cache-SysUserRole-page", allEntries = true)
    boolean isave(SysUserRole param);

    @Caching(evict = {
            @CacheEvict(cacheNames = "cache-SysUserRole-page", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysUserRole-byId", key = "#param.id" )
    })
    boolean iupdate(SysUserRole param);

    boolean iremove(List<Integer> list);

    @Cacheable(cacheNames = "cache-SysUserRole-byId", unless = "#result==null")
    SysUserRole igetById(Integer id);

    @Caching(evict = {
        @CacheEvict(cacheNames = "cache-SysUserRole-page", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysUserRole-byId", key = "#id" )
    })
    boolean iremove(Integer id);
}
