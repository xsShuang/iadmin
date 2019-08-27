package xyz.iotcode.iadmin.demo.module.system.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUser;
import xyz.iotcode.iadmin.demo.module.system.mapper.SysUserMapper;
import xyz.iotcode.iadmin.demo.module.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUser> ipage(SysUserQuery query) {
        return this.page(query.createPage(), new WrapperFactory<SysUser>().create(query));
    }

    @Override
    public boolean isave(SysUser param) {
        return this.save(param);
    }

    @Override
    public boolean iupdate(SysUser param) {
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
    public SysUser igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }
}
