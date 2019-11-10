package xyz.iotcode.iadmin.demo.module.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.iotcode.iadmin.common.validated.Insert;
import xyz.iotcode.iadmin.common.validated.Update;
import xyz.iotcode.iadmin.common.vo.IResult;
import xyz.iotcode.iadmin.core.common.log.SaveLog;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysRoleQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysRole;
import xyz.iotcode.iadmin.demo.module.system.service.SysRoleService;
import xyz.iotcode.iadmin.permissions.annotation.IPermissions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @description : SysRole 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-28
 */

@Api(value="SysRole接口", tags="角色接口")
@Slf4j
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="角色分页数据接口")
    @IPermissions(value="SysRole:page")
    @ApiOperation(value="角色分页数据接口", nickname="SysRole:page")
    @PostMapping("/list")
    public IResult<IPage<SysRole>> getSysRolePage(SysRoleQuery query) {
        return IResult.ok(sysRoleService.ipage(query));
    }

    /**
     * @description : 通过id获取角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="获取角色数据接口")
    @IPermissions(value="SysRole:get")
    @ApiOperation(value="获取角色数据接口", nickname="SysRole:get")
    @GetMapping("/get/{id}")
    public IResult<SysRole> getSysRoleById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysRoleService.igetById(id));
    }

    /**
     * @description : 新增角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="新增角色数据接口")
    @IPermissions(value="SysRole:add")
    @ApiOperation(value="新增角色数据接口", nickname="SysRole:add")
    @PostMapping("/add")
    public IResult addSysRole(@Validated({Insert.class}) SysRole param) {
        param.setId(null);
        return IResult.auto(sysRoleService.isaveOrUpdate(param));
    }

    /**
     * @description : 更新角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="更新角色数据接口")
    @IPermissions(value="SysRole:update")
    @ApiOperation(value="更新角色数据接口", nickname="SysRole:update")
    @PostMapping("/update")
    public IResult updateSysRoleById(@Validated({Update.class}) SysRole param) {
        return IResult.auto(sysRoleService.isaveOrUpdate(param));
    }

    /**
     * @description : 通过id删除角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="删除角色数据接口", level = 3)
    @IPermissions(value="SysRole:del")
    @ApiOperation(value="删除角色数据接口", nickname="SysRole:del")
    @PostMapping("/remove/{id}")
    @Transactional(rollbackFor = Exception.class)
    public IResult deleteSysRoleById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.stream(id.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        for (Integer integer : list) {
            sysRoleService.iremove(integer);
        }
        return IResult.ok();
    }

    @SaveLog(value="获取用户的角色列表接口")
    @IPermissions(value="SysRole:getByUser")
    @ApiOperation(value="获取用户的角色列表接口", nickname="SysRole:getByUser")
    @GetMapping("/getByUser/{id}")
    public IResult<List<SysRole>> getRoleByUser(@PathVariable Integer id){
        return IResult.ok(sysRoleService.getByUserId(id));
    }
}