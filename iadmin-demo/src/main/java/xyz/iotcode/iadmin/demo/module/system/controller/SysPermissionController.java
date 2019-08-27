package xyz.iotcode.iadmin.demo.module.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysPermissionQuery;
import xyz.iotcode.iadmin.common.validated.Insert;
import xyz.iotcode.iadmin.common.validated.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.iotcode.iadmin.demo.module.system.service.SysPermissionService;
import xyz.iotcode.iadmin.demo.module.system.entity.SysPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import xyz.iotcode.iadmin.common.vo.IResult;
import lombok.extern.slf4j.Slf4j;
/**
 *  @description : SysPermission 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-23
 */

@Api(value="SysPermission接口", tags="权限接口")
@Slf4j
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="权限分页数据接口", nickname="SysPermission:page")
    @PostMapping("/list")
    public IResult<IPage<SysPermission>> getSysPermissionPage(SysPermissionQuery query) {
        return IResult.ok(sysPermissionService.ipage(query));
    }

    /**
     * @description : 通过id获取权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="获取权限数据接口", nickname="SysPermission:get")
    @GetMapping("/get/{id}")
    public IResult<SysPermission> getSysPermissionById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysPermissionService.igetById(id));
    }

    /**
     * @description : 通过id删除权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="删除权限数据接口", nickname="SysPermission:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysPermissionById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysPermissionService.iremove(list));
    }

    /**
     * @description : 更新权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="更新权限数据接口", nickname="SysPermission:update")
    @PostMapping("/update")
    public IResult updateSysPermissionById(@Validated({Update.class}) SysPermission param) {
        return IResult.auto(sysPermissionService.iupdate(param));
    }

    /**
     * @description : 新增权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="新增权限数据接口", nickname="SysPermission:add")
    @PostMapping("/add")
    public IResult addSysPermission(@Validated({Insert.class}) SysPermission param) {
        return IResult.auto(sysPermissionService.isave(param));
    }
}