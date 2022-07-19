package com.zhloong.blog.common.exception;

import com.zhloong.blog.common.component.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 描述：全局统一异常处理
 * @author zhloong

 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

    /**
     * 参数验证异常
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMsg =  e.getBindingResult().getFieldError().getDefaultMessage();
        if (!StringUtils.isEmpty(errorMsg)) {
            return Result.error(errorMsg);
        }
        log.error(e.getMessage(),e);
        return Result.error(e.getMessage());
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(value = {ValidationException.class})
    public Result constraintViolationException(ValidationException e) {
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException err = (ConstraintViolationException) e;
            ConstraintViolation<?> constraintViolation = err.getConstraintViolations().stream().findFirst().get();
            String messageTemplate = constraintViolation.getMessageTemplate();
            if(!StringUtils.isEmpty(messageTemplate)){
                return Result.error(messageTemplate);
            }
        }
        log.error(e.getMessage(),e);
        return Result.error(e.getMessage());
    }

    /**
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("GlobalExceptionHandler.exceptionHandler , 异常信息",e);
        return Result.error(e.getMessage());
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Result bplCommonExceptionHandler(RuntimeException e) {
        return Result.error(e.getMessage());
    }

}
