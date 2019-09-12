package xyz.iotcode.iadmin.demo.module.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.log.controller.query.SysLoginLogQuery;
import xyz.iotcode.iadmin.common.validated.Insert;
import xyz.iotcode.iadmin.common.validated.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.iotcode.iadmin.demo.module.log.service.SysLoginLogService;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLoginLog;
import xyz.iotcode.iadmin.permissions.annotation.IPermissions;
import xyz.iotcode.iadmin.core.common.log.SaveLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import xyz.iotcode.iadmin.common.vo.IResult;
import lombok.extern.slf4j.Slf4j;
/**
 *  @description : SysLoginLog 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-09-12
 */

@Api(value="SysLoginLog接口", tags="登录日志接口")
@Slf4j
@RestController
@RequestMapping("/sysLoginLog")
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-09-12
     */
    @SaveLog(value="登录日志分页数据接口")
    @IPermissions(value="SysLoginLog:page")
    @ApiOperation(value="登录日志分页数据接口", nickname="SysLoginLog:page")
    @PostMapping("/list")
    public IResult<IPage<SysLoginLog>> getSysLoginLogPage(SysLoginLogQuery query) {
        return IResult.ok(sysLoginLogService.ipage(query));
    }

    /**
     * @description : 通过id获取
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-09-12
     */
    @SaveLog(value="获取登录日志数据接口")
    @IPermissions(value="SysLoginLog:get")
    @ApiOperation(value="获取登录日志数据接口", nickname="SysLoginLog:get")
    @GetMapping("/get/{id}")
    public IResult<SysLoginLog> getSysLoginLogById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysLoginLogService.igetById(id));
    }

    /**
     * @description : 通过id删除
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-09-12
     */
    @SaveLog(value="删除登录日志数据接口", level = 3)
    @IPermissions(value="SysLoginLog:del")
    @ApiOperation(value="删除登录日志数据接口", nickname="SysLoginLog:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysLoginLogById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysLoginLogService.iremove(list));
    }

}