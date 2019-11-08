package xyz.iotcode.iadmin.demo.module.system.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
public interface SysUserService extends IService<SysUser> {
    
    @Cacheable(cacheNames = "cache-SysUser-page", keyGenerator = "cacheKeyGenerator", unless = "#result==null")
    IPage<SysUser> ipage(SysUserQuery query);

    @Caching(evict = {
            @CacheEvict(cacheNames = "cache-SysUser-page", allEntries = true),
            @CacheEvict(cacheNames = "cache-SysUser-byId", key = "#param.id" )
    })
    boolean isaveOrUpdate(SysUser param);

    @Cacheable(cacheNames = "cache-SysUser-byId", unless = "#result==null")
    SysUser igetById(Integer id);

    @Caching(evict = {
        @CacheEvict(cacheNames = "cache-SysUser-page", allEntries = true),
        @CacheEvict(cacheNames = "cache-SysUser-byId", key = "#id" )
    })
    boolean iremove(Integer id);
}
