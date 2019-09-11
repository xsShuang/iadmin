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
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUser;
import xyz.iotcode.iadmin.demo.module.system.service.SysUserService;
import xyz.iotcode.iadmin.permissions.annotation.IPermissions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @description : SysUser 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-28
 */

@Api(value="SysUser接口", tags="用户接口")
@Slf4j
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="用户分页数据接口")
    @IPermissions(value="SysUser:page")
    @ApiOperation(value="用户分页数据接口", nickname="SysUser:page")
    @PostMapping("/list")
    public IResult<IPage<SysUser>> getSysUserPage(SysUserQuery query) {
        return IResult.ok(sysUserService.ipage(query));
    }

    /**
     * @description : 通过id获取用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="获取用户数据接口")
    @IPermissions(value="SysUser:get")
    @ApiOperation(value="获取用户数据接口", nickname="SysUser:get")
    @GetMapping("/get/{id}")
    public IResult<SysUser> getSysUserById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysUserService.igetById(id));
    }

    /**
     * @description : 新增用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="新增用户数据接口")
    @IPermissions(value="SysUser:add")
    @ApiOperation(value="新增用户数据接口", nickname="SysUser:add")
    @PostMapping("/add")
    public IResult addSysUser(@Validated({Insert.class}) SysUser param) {
        param.setUserId(null);
        return IResult.auto(sysUserService.isaveOrUpdate(param));
    }

    /**
     * @description : 更新用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="更新用户数据接口")
    @IPermissions(value="SysUser:update")
    @ApiOperation(value="更新用户数据接口", nickname="SysUser:update")
    @PostMapping("/update")
    public IResult updateSysUserById(@Validated({Update.class}) SysUser param) {
        return IResult.auto(sysUserService.isaveOrUpdate(param));
    }

    /**
     * @description : 通过id删除用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="删除用户数据接口", level = 3)
    @IPermissions(value="SysUser:del")
    @ApiOperation(value="删除用户数据接口", nickname="SysUser:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysUserById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysUserService.iremove(list));
    }

}