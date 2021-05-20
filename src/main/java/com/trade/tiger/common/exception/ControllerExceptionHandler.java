package com.trade.tiger.common.exception;

import com.trade.tiger.common.enums.ResultStatus;
import com.trade.tiger.common.resultbean.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Luo
 * @description:
 * @time: 2020/6/17 20:50
 * Modified By:
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

//        /**
//         * 未认证异常处理
//         *
//         * @return
//         */
//        @ResponseBody
//        @ExceptionHandler(UnauthenticatedException.class)
//        public ResultMsg<String> authenticationException(HttpServletRequest request , Exception e) {
//            return ResultMsg.error(ResultStatus.USER_NOT_EXIST);
//        }
//
//
//        /**
//         * 未授权异常处理
//         *
//         * @return
//         */
//        @ResponseBody
//        @ExceptionHandler(value = UnauthorizedException.class)
//        public ResultMsg<String> authorizationException(HttpServletRequest request , Exception e) {
//            return ResultMsg.error(ResultStatus.USER_NOT_Authorization);
//        }

    /**
     * 参数异常处理
     *
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultMsg<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return ResultMsg.error(ResultStatus.PARAM_ERROR);
    }

    /**
     * 所有异常
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMsg<String> globalException(HttpServletRequest request, Exception e) {
        logger.info("系统异常，{}", e);
        return ResultMsg.error(ResultStatus.SYSTEM_ERROR);
    }
}
