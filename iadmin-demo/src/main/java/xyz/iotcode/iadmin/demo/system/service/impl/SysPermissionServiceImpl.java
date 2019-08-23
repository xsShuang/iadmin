package xyz.iotcode.iadmin.demo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.common.exception.MyRuntimeException;
import xyz.iotcode.iadmin.demo.system.controller.query.SysPermissionQuery;
import xyz.iotcode.iadmin.demo.system.entity.SysPermission;
import xyz.iotcode.iadmin.demo.system.mapper.SysPermissionMapper;
import xyz.iotcode.iadmin.demo.system.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-23
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public IPage<SysPermission> ipage(SysPermissionQuery query) {
        return this.page(query.createPage(), new WrapperFactory<SysPermission>().create(query));
    }

    @Override
    public boolean isave(SysPermission param) {
        SysPermission byCode = this.getByCode(param.getPermissionCode());
        if (byCode!=null){
            throw new MyRuntimeException("权限编码已存在");
        }
        SysPermission byUrl = this.getByUrl(param.getUrl(), param.getRequestWay());
        if (byUrl!=null){
            throw new MyRuntimeException("访问链接以及请求方式都相同的权限已存在");
        }
        return this.save(param);
    }

    @Override
    public boolean iupdate(SysPermission param) {
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
    public SysPermission igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }

    @Override
    public SysPermission getByUrl(String url, String requestWay) {
        return this.getOne(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getUrl, url)
                .eq(SysPermission::getRequestWay, requestWay));
    }

    @Override
    public SysPermission getByCode(String code) {
        return this.getOne(new LambdaQueryWrapper<SysPermission>()
                .eq(SysPermission::getPermissionCode, code));
    }
}
