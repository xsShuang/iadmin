package xyz.iotcode.iadmin.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 谢霜
 * @Date: 2018/09/12 下午 16:32
 * @Description:
 */
@Data
@ApiModel("全局返回体")
public class IResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer status;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 结果对象
     */
    private T data;

    public static <T> IResult<T> auto(boolean success){
        if (success){
            return ok();
        }else {
            return fail();
        }
    }

    public static <T> IResult<T> ok(String message, T data){
        IResult result = new IResult();
        result.setStatus(200);
        result.setMessage(message);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> IResult<T> ok(String message){
        return ok(message,null);
    }

    public static <T> IResult<T> ok(T data){
        return ok("操作成功", data);
    }

    public static <T> IResult<T> ok(){
        return ok("操作成功");
    }


    public static <T> IResult<T> fail(Integer code, String message, T data){
        IResult result = new IResult();
        result.setStatus(code);
        result.setMessage(message);
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    public static <T> IResult<T> fail(String message){
        return fail(message,null);
    }

    public static <T> IResult<T> fail(Integer code, String message){
        return fail(code, message,null);
    }

    public static <T> IResult<T> fail(String message, T data){
        return fail(500, message, data);
    }

    public static <T> IResult<T> fail(T data){
        return fail("操作失败", data);
    }

    public static <T> IResult<T> fail(){
        return fail("操作失败");
    }
}
