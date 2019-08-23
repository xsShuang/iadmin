package xyz.iotcode.iadmin.demo.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.iotcode.iadmin.demo.system.controller.query.SysRoleQuery;
import xyz.iotcode.iadmin.common.validated.Insert;
import xyz.iotcode.iadmin.common.validated.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.iotcode.iadmin.demo.system.service.SysRoleService;
import xyz.iotcode.iadmin.demo.system.entity.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import xyz.iotcode.iadmin.common.vo.IResult;
import lombok.extern.slf4j.Slf4j;
/**
 *  @description : SysRole 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-23
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
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="角色分页数据接口", nickname="SysRole:page")
    @PostMapping("/list")
    public IResult<IPage<SysRole>> getSysRolePage(SysRoleQuery query) {
        return IResult.ok(sysRoleService.ipage(query));
    }

    /**
     * @description : 通过id获取角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="获取角色数据接口", nickname="SysRole:get")
    @GetMapping("/get/{id}")
    public IResult<SysRole> getSysRoleById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysRoleService.igetById(id));
    }

    /**
     * @description : 通过id删除角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="删除角色数据接口", nickname="SysRole:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysRoleById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysRoleService.iremove(list));
    }

    /**
     * @description : 更新角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="更新角色数据接口", nickname="SysRole:update")
    @PostMapping("/update")
    public IResult updateSysRoleById(@Validated({Update.class}) SysRole param) {
        return IResult.auto(sysRoleService.iupdate(param));
    }

    /**
     * @description : 新增角色
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-23
     */
    @ApiOperation(value="新增角色数据接口", nickname="SysRole:add")
    @PostMapping("/add")
    public IResult addSysRole(@Validated({Insert.class}) SysRole param) {
        return IResult.auto(sysRoleService.isave(param));
    }
}