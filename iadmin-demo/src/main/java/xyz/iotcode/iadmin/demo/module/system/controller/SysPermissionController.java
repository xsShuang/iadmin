package xyz.iotcode.iadmin.demo.module.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.iotcode.iadmin.common.tree.TreeFactory;
import xyz.iotcode.iadmin.common.validated.Insert;
import xyz.iotcode.iadmin.common.validated.Update;
import xyz.iotcode.iadmin.common.vo.IResult;
import xyz.iotcode.iadmin.core.common.log.SaveLog;
import xyz.iotcode.iadmin.demo.module.system.controller.query.SysPermissionQuery;
import xyz.iotcode.iadmin.demo.module.system.entity.SysPermission;
import xyz.iotcode.iadmin.demo.module.system.service.SysPermissionService;
import xyz.iotcode.iadmin.demo.module.system.vo.PermissionListTreeVO;
import xyz.iotcode.iadmin.demo.module.system.vo.PermissionTreeVO;
import xyz.iotcode.iadmin.permissions.annotation.IPermissions;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  @description : SysPermission 控制器
 *  ---------------------------------
 *   @author 谢霜
 *  @since 2019-08-28
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
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="权限分页数据接口")
    @IPermissions(value="SysPermission:page")
    @ApiOperation(value="权限分页数据接口", nickname="SysPermission:page")
    @PostMapping("/list")
    public IResult<IPage<SysPermission>> getSysPermissionPage(SysPermissionQuery query) {
        return IResult.ok(sysPermissionService.ipage(query));
    }

    /**
     * @description : 通过id获取权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="获取权限数据接口")
    @IPermissions(value="SysPermission:get")
    @ApiOperation(value="获取权限数据接口", nickname="SysPermission:get")
    @GetMapping("/get/{id}")
    public IResult<SysPermission> getSysPermissionById(@PathVariable(value = "id") Integer id) {
        return IResult.ok(sysPermissionService.igetById(id));
    }

    /**
     * @description : 新增权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="新增权限数据接口")
    @IPermissions(value="SysPermission:add")
    @ApiOperation(value="新增权限数据接口", nickname="SysPermission:add")
    @PostMapping("/add")
    public IResult addSysPermission(@Validated({Insert.class}) SysPermission param) {
        return IResult.auto(sysPermissionService.isave(param));
    }

    /**
     * @description : 更新权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="更新权限数据接口")
    @IPermissions(value="SysPermission:update")
    @ApiOperation(value="更新权限数据接口", nickname="SysPermission:update")
    @PostMapping("/update")
    public IResult updateSysPermissionById(@Validated({Update.class}) SysPermission param) {
        return IResult.auto(sysPermissionService.iupdate(param));
    }

    /**
     * @description : 通过id删除权限
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @SaveLog(value="删除权限数据接口", level = 3)
    @IPermissions(value="SysPermission:del")
    @ApiOperation(value="删除权限数据接口", nickname="SysPermission:del")
    @PostMapping("/remove/{id}")
    public IResult deleteSysPermissionById(@PathVariable(value = "id") String id) {
        List<Integer> list = Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return IResult.auto(sysPermissionService.iremove(list));
    }

    /**
     * @description : 根据用户id获取权限信息
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @ApiOperation(value="根据用户id获取权限信息", nickname="SysPermission:getByUserId")
    @PostMapping("/getByUserId/{id}")
    public IResult<Set<SysPermission>> getByUserId(@NotNull @PathVariable(value = "id") Integer id) {
        return IResult.ok(sysPermissionService.getByUserId(id));
    }

    /**
     * @description : 根据角色id获取权限信息
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @ApiOperation(value="根据角色id获取权限信息", nickname="SysPermission:getByRoleId")
    @PostMapping("/getByRoleId/{id}")
    public IResult<Set<SysPermission>> getByRoleId(@NotNull @PathVariable(value = "id") Integer id) {
        return IResult.ok(sysPermissionService.getByRoleId(id));
    }

    /**
     * @description : 权限VO树接口2
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @ApiOperation(value="权限VO树接口,用于权限列表", nickname="SysPermission:getListTreeVO")
    @GetMapping("/getListTreeVO")
    public IResult<Page<PermissionListTreeVO>> getPermissionListTreeVO(Integer type) {
        SysPermissionQuery query = new SysPermissionQuery();
        query.setPage(1);
        query.setSize(10000);
        if (type!=null){
            query.setType(type);
        }
        IPage<SysPermission> ipage = sysPermissionService.ipage(query);
        if (CollectionUtil.isEmpty(ipage.getRecords())){
            return IResult.ok(new Page<>());
        }
        Set<PermissionListTreeVO> collect = ipage.getRecords().stream().map(sysPermission -> {
            PermissionListTreeVO vo = new PermissionListTreeVO();
            BeanUtils.copyProperties(sysPermission, vo);
            return vo;
        }).collect(Collectors.toSet());
        Page<PermissionListTreeVO> page = new Page<>();
        page.setRecords((List<PermissionListTreeVO>) new TreeFactory<PermissionListTreeVO>().createTree(collect));
        return IResult.ok(page);
    }

    /**
     * @description : 权限VO树接口
     * ---------------------------------
     * @author : 谢霜
     * @since : Create in 2019-08-28
     */
    @ApiOperation(value="权限VO树接口", nickname="SysPermission:getTreeVO")
    @GetMapping("/getTreeVO")
    public IResult<Collection<PermissionTreeVO>> getPermissionTreeVO() {
        SysPermissionQuery query = new SysPermissionQuery();
        query.setPage(1);
        query.setSize(10000);
        IPage<SysPermission> ipage = sysPermissionService.ipage(query);
        if (CollectionUtil.isEmpty(ipage.getRecords())){
            return IResult.ok(Collections.emptySet());
        }
        Set<PermissionTreeVO> collect = ipage.getRecords().stream().map(sysPermission -> {
            PermissionTreeVO vo = new PermissionTreeVO();
            BeanUtils.copyProperties(sysPermission, vo);
            vo.setName(sysPermission.getPermissionName());
            return vo;
        }).collect(Collectors.toSet());
        return IResult.ok(new TreeFactory<PermissionTreeVO>().createTree(collect));
    }

}