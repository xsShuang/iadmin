package xyz.iotcode.iadmin.demo.module.log.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.log.controller.query.SysLogQuery;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLog;
import xyz.iotcode.iadmin.demo.module.log.mapper.SysLogMapper;
import xyz.iotcode.iadmin.demo.module.log.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-27
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public IPage<SysLog> ipage(SysLogQuery query) {
        return this.page(query.createPage(), new WrapperFactory<SysLog>().create(query));
    }

    @Override
    public boolean isave(SysLog param) {
        return this.save(param);
    }

    @Override
    public boolean iupdate(SysLog param) {
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
    public SysLog igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }
}
