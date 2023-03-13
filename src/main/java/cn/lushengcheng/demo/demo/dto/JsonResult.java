package cn.lushengcheng.demo.demo.dto;

import cn.lushengcheng.demo.demo.enums.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Data
public class JsonResult implements Serializable {

    /**
     * 状态
     */
    private Boolean success = true;

    /**
     * 错误码
     */
    private Integer code = 0;

    /**
     * 提示语
     */
    private String msg = "操作成功";

    /**
     * 返回对象
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object response;

    /**
     * 数据总数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long count;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public Integer getCode() {
        return this.code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public Object getResponse() {
        return this.response;
    }

    public void setResponse(final Object response) {
        this.response = response;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(final Long count) {
        this.count = count;
    }


    /**
     * 构造函数
     */
    public JsonResult() {
    }

    public JsonResult(String msg) {
        this.msg = msg;
    }

    public JsonResult(Object response) {
        this.response = response;
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public JsonResult(Boolean success,Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(Integer code, String msg, Object response) {
        this.code = code;
        this.msg = msg;
        this.response = response;
    }


    public static JsonResult success() {
        return new JsonResult();
    }

    public JsonResult(Integer code, String msg, Object response, long count) {
        this.code = code;
        this.msg = msg;
        this.response = response;
        this.count = count;
    }
    public JsonResult(Boolean success,Integer code, String msg, Object response) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.response = response;
    }

    public static JsonResult success(String msg) {
        return new JsonResult(msg);
    }

    public static JsonResult success(Object response) {
        return new JsonResult(response);
    }

    public static JsonResult success(String msg, Object response) {
        return new JsonResult(200, msg, response);
    }

    public static JsonResult success(String msg, Object response, long count) {
        return new JsonResult(200, msg, response, count);
    }


    public static JsonResult error() {
        return new JsonResult(false,-1, "操作失败");
    }

    public static JsonResult error(String msg) {
        return new JsonResult(false,1, msg);
    }

    public static JsonResult error(Integer code, String msg) {
        return new JsonResult(false,code, msg);
    }

    public static JsonResult error(Integer code, String msg, Object response) {
        return new JsonResult(code, msg, response);
    }

    public static JsonResult error(ErrorCode errorCode) {
        return new JsonResult(false,errorCode.getCode(), errorCode.getMsg());
    }

    public static JsonResult error(HttpStatus httpStatus, String msg, Object response) {
        return new JsonResult(false,httpStatus.value(), msg, response);
    }

    public Object error(HttpStatus httpStatus, String msg) {
        this.success = false;
        this.code = httpStatus.value();
        this.msg = msg;
        return this;
    }
}
