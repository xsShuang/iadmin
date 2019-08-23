package xyz.iotcode.iadmin.demo.system.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.system.controller.query.SysUserRoleQuery;
import xyz.iotcode.iadmin.demo.system.entity.SysUserRole;
import xyz.iotcode.iadmin.demo.system.mapper.SysUserRoleMapper;
import xyz.iotcode.iadmin.demo.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;
import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public IPage<SysUserRole> ipage(SysUserRoleQuery query) {
        return this.page(query.createPage(), new WrapperFactory<SysUserRole>().create(query));
    }

    @Override
    public boolean isave(SysUserRole param) {
        return this.save(param);
    }

    @Override
    public boolean iupdate(SysUserRole param) {
        return this.updateById(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean iremove(List<Integer> list) {
        for (Integer integer : list) {
            this.iremove(integer);
        }
        return true;
    }

    @Override
    public SysUserRole igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }
}
