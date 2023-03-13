package cn.lushengcheng.demo.demo.enums;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public enum ErrorCode {


    /**
     * 操作失败
     */
    FAILED(1, "操作失败"),
    /**
     * 参数丢失
     */
    PARAM_MISSING(400, "参数丢失"),
    /**
     * 参数错误
     */
    PARAM_ERROR(401, "参数错误"),
    SYSTEM_ERROR(500, "系统错误"),
    NULL_RESULT(403, "数据不存在");
    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误描述
     */
    private final String msg;

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
