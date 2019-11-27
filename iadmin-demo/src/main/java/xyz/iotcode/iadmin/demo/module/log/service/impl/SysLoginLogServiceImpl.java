package xyz.iotcode.iadmin.demo.module.log.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.common.util.HttpContextUtil;
import xyz.iotcode.iadmin.common.util.IPUtil;
import xyz.iotcode.iadmin.core.base.dto.TimeDTO;
import xyz.iotcode.iadmin.demo.common.util.AddressUtil;
import xyz.iotcode.iadmin.demo.module.log.controller.query.SysLoginLogQuery;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLoginLog;
import xyz.iotcode.iadmin.demo.module.log.mapper.SysLoginLogMapper;
import xyz.iotcode.iadmin.demo.module.log.service.SysLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-09-12
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Override
    public IPage<SysLoginLog> ipage(SysLoginLogQuery query) {
        TimeDTO.setBeginAndEndTime(query);
        return this.page(query.createPage(), new WrapperFactory<SysLoginLog>().create(query));
    }

    @Override
    public boolean isave(SysLoginLog param) {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        param.setLoginTime(new Date());
        String ip = IPUtil.getIpAddr(request);
        param.setIp(ip);
        param.setAddress(AddressUtil.getCityInfo(ip));
        param.setSystemBrowserInfo(request.getHeader("user-agent"));
        return this.save(param);
    }

    @Override
    public boolean iupdate(SysLoginLog param) {
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
    public SysLoginLog igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }
}
