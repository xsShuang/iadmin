package xyz.iotcode.iadmin.demo.module.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.iotcode.iadmin.common.tree.Tree;

/**
 * @author xieshuang
 */
@Data
@ApiModel(value = "权限树VO")
public class PermissionTreeVO extends Tree {

    @ApiModelProperty("权限名称")
    private String name;
}
