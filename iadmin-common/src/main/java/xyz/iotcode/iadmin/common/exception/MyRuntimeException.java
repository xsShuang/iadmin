package xyz.iotcode.iadmin.common.exception;

/**
 * @author xieshuang
 * @date 2019-04-07 17:23
 */
public class MyRuntimeException extends RuntimeException {

    private Integer code;

    public MyRuntimeException(String msg){
        super(msg);
        this.code = 500;
    }

    public MyRuntimeException(String msg, Integer code){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
