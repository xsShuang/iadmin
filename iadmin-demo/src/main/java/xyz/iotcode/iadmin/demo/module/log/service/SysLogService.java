package xyz.iotcode.iadmin.demo.module.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.iotcode.iadmin.demo.module.log.controller.query.SysLogQuery;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLog;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-27
 */
public interface SysLogService extends IService<SysLog> {

    IPage<SysLog> ipage(SysLogQuery query);

    boolean isave(SysLog param);

    boolean iupdate(SysLog param);

    boolean iremove(List<Integer> list);

    SysLog igetById(Integer id);

    boolean iremove(Integer id);
}
