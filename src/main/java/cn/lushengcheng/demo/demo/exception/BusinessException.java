package cn.lushengcheng.demo.demo.exception;

import cn.lushengcheng.demo.demo.enums.BusinessError;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    public BusinessException(BusinessError businessErrorEnum) {
        super(businessErrorEnum.getMessage());
        this.code = businessErrorEnum.getCode();
        this.message = businessErrorEnum.getMessage();
    }

}
