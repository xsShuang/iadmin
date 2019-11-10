package xyz.iotcode.iadmin.core.base.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author xieshuang
 * @date 2019-04-24 17:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("分页DTO")
public class PageDTO extends BaseDto {

    @NotNull(message = "页码不能为空")
    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer page;
    @NotNull(message = "条数不能为空")
    @ApiModelProperty(value = "条数", required = true, example = "10")
    private Integer size;

    public Page createPage(){
        if (page==null){
            page = 0;
        }
        if (size==null){
            size = 10;
        }
        return new Page(this.getPage(), this.getSize());
    }
}
