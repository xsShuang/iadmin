package xyz.iotcode.iadmin.demo.module.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysUserQuery;
import xyz.iotcode.iadmin.common.validated.Insert;
import xyz.iotcode.iadmin.common.validated.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.iotcode.iadmin.demo.module.system.service.SysUserService;
import xyz.iotcode.iadmin.demo.module.system.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import xyz.iotcode.iadmin.common.vo.IResult;
import lombok.extern.slf4j.Slf4j;
/**
 *  @description : SysUser 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-23
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
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="用户分页数据接口", nickname="SysUser:page")
    @PostMapping("/list")
    public IResult<IPage<SysUser>> getSysUserPage(SysUserQuery query) {
        return IResult.ok(sysUserService.ipage(query));
    }

    /**
     * @description : 通过id获取用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="获取用户数据接口", nickname="SysUser:get")
    @GetMapping("/get/{id}")
    public IResult<SysUser> getSysUserById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysUserService.igetById(id));
    }

    /**
     * @description : 通过id删除用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="删除用户数据接口", nickname="SysUser:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysUserById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysUserService.iremove(list));
    }

    /**
     * @description : 更新用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="更新用户数据接口", nickname="SysUser:update")
    @PostMapping("/update")
    public IResult updateSysUserById(@Validated({Update.class}) SysUser param) {
        return IResult.auto(sysUserService.iupdate(param));
    }

    /**
     * @description : 新增用户
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="新增用户数据接口", nickname="SysUser:add")
    @PostMapping("/add")
    public IResult addSysUser(@Validated({Insert.class}) SysUser param) {
        return IResult.auto(sysUserService.isave(param));
    }
}