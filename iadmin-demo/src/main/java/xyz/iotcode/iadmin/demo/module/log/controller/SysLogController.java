package xyz.iotcode.iadmin.demo.module.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.iotcode.iadmin.common.vo.IResult;
import xyz.iotcode.iadmin.core.common.log.SaveLog;
import xyz.iotcode.iadmin.demo.module.log.controller.query.SysLogQuery;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLog;
import xyz.iotcode.iadmin.demo.module.log.service.SysLogService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 *  @description : SysLog 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-27
 */

@Api(value="SysLog接口", tags="日志接口")
@Slf4j
@RestController
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-27
     */
    @SaveLog(value = "日志分页数据接口")
    @ApiOperation(value="日志分页数据接口", nickname="SysLog:page")
    @PostMapping("/list")
    public IResult<IPage<SysLog>> getSysLogPage(SysLogQuery query) {
        return IResult.ok(sysLogService.ipage(query));
    }

    /**
     * @description : 通过id删除
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-27
     */
    @SaveLog(value = "日志删除数据接口", level = 3)
    @ApiOperation(value="日志删除数据接口", nickname="SysLog:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysLogById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysLogService.iremove(list));
    }

}