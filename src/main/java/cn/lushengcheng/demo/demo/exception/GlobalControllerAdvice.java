package cn.lushengcheng.demo.demo.exception;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@RestControllerAdvice
@Log4j2
public class GlobalControllerAdvice {

    private static final String BAD_REQUEST_MSG = "客户端请求参数错误";
    private static final String INTERNAL_SERVER_ERROR_MSG = "服务器内部错误";

    @ExceptionHandler(BindException.class)
    public JsonResult bindExceptionHandler(BindException e, HttpServletResponse response) {
        log.error("{},异常信息：\n\t{}，\n堆栈跟踪信息：\n\t{}",BAD_REQUEST_MSG,e.getMessage(),e.getStackTrace());
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return JsonResult.error(HttpStatus.BAD_REQUEST, BAD_REQUEST_MSG, collect);
    }

    @ExceptionHandler(Exception.class)
    public JsonResult exceptionHandler(Exception e) {
        log.error("{},异常信息：{}，\n堆栈跟踪信息：\n\t{}",BAD_REQUEST_MSG,e.getMessage(),e.getStackTrace());
        return JsonResult.error(500, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(BusinessException.class)
    public JsonResult businessExceptionHandler(BusinessException e, HttpServletResponse response) {
        log.error("{},异常信息：{}，\n堆栈跟踪信息：\n\t{}",BAD_REQUEST_MSG,e.getMessage(),e.getStackTrace());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return JsonResult.error(e.getCode(), e.getMessage());
    }
}
