package xyz.iotcode.iadmin.demo.module.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.log.controller.query.SysLoginLogQuery;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-09-12
 */
public interface SysLoginLogService extends IService<SysLoginLog> {
    
    IPage<SysLoginLog> ipage(SysLoginLogQuery query);

    boolean isave(SysLoginLog param);

    boolean iupdate(SysLoginLog param);

    boolean iremove(List<Integer> list);

    SysLoginLog igetById(Integer id);

    boolean iremove(Integer id);
}
