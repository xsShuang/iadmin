package xyz.iotcode.iadmin.demo.module.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.iotcode.iadmin.common.validated.Insert;
import xyz.iotcode.iadmin.common.validated.Update;
import xyz.iotcode.iadmin.common.vo.IResult;
import xyz.iotcode.iadmin.core.common.log.SaveLog;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserRoleQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUserRole;
import xyz.iotcode.iadmin.demo.module.system.service.SysUserRoleService;
import xyz.iotcode.iadmin.permissions.annotation.IPermissions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @description : SysUserRole 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-28
 */

@Api(value="SysUserRole接口", tags="用户角色接口")
@Slf4j
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="用户角色分页数据接口")
    @IPermissions(value="SysUserRole:page")
    @ApiOperation(value="用户角色分页数据接口", nickname="SysUserRole:page")
    @PostMapping("/list")
    public IResult<IPage<SysUserRole>> getSysUserRolePage(SysUserRoleQuery query) {
        return IResult.ok(sysUserRoleService.ipage(query));
    }

    /**
     * @description : 通过id获取用户角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="获取用户角色数据接口")
    @IPermissions(value="SysUserRole:get")
    @ApiOperation(value="获取用户角色数据接口", nickname="SysUserRole:get")
    @GetMapping("/get/{id}")
    public IResult<SysUserRole> getSysUserRoleById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysUserRoleService.igetById(id));
    }

    /**
     * @description : 新增用户角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="新增用户角色数据接口")
    @IPermissions(value="SysUserRole:add")
    @ApiOperation(value="新增用户角色数据接口", nickname="SysUserRole:add")
    @PostMapping("/add")
    public IResult addSysUserRole(@Validated({Insert.class}) SysUserRole param) {
        return IResult.auto(sysUserRoleService.isave(param));
    }

    /**
     * @description : 更新用户角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="更新用户角色数据接口")
    @IPermissions(value="SysUserRole:update")
    @ApiOperation(value="更新用户角色数据接口", nickname="SysUserRole:update")
    @PostMapping("/update")
    public IResult updateSysUserRoleById(@Validated({Update.class}) SysUserRole param) {
        return IResult.auto(sysUserRoleService.iupdate(param));
    }

    /**
     * @description : 通过id删除用户角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="删除用户角色数据接口", level = 3)
    @IPermissions(value="SysUserRole:del")
    @ApiOperation(value="删除用户角色数据接口", nickname="SysUserRole:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysUserRoleById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysUserRoleService.iremove(list));
    }

}