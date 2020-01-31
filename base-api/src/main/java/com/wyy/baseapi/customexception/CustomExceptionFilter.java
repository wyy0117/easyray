package com.wyy.baseapi.customexception;

import com.wyy.baseapi.exception.EasyCustomException;
import com.wyy.baseapi.exception.EntityNotExistException;
import com.wyy.baseapi.exception.NoPermissionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date: 20-1-31
 * @Author: wyy
 */
@ControllerAdvice
public class CustomExceptionFilter {

    /**
     * handle this type exception,all return 403
     *
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = {NoPermissionException.class})
    @ResponseBody
    protected ErrorMsgDTO handleNoPermissionException(NoPermissionException exception, HttpServletRequest request, HttpServletResponse response) {
        exception.printStackTrace();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return new ErrorMsgDTO().setErrorCode("403")
                .setMessage(exception.getMessage())
                .setReason("you don't have permission!")
                .setCode(exception.getStackTrace()[0].getClassName() + "." + exception.getStackTrace()[0].getMethodName() + "() : " + exception.getStackTrace()[0].getLineNumber());
    }

    /**
     * 包含手动抛出的异常和无法预期的异常
     *
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = {EasyCustomException.class})
    @ResponseBody
    protected ErrorMsgDTO handleEasyCustomException(EasyCustomException exception, HttpServletRequest request, HttpServletResponse response) {
        exception.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ErrorMsgDTO errorMsgDTO = new ErrorMsgDTO();
        Throwable cause = exception.getCause();
        if (cause instanceof CustomThrowable) {
            CustomThrowable customThrowable = (CustomThrowable) cause;
            ICustomErrorCode errorCode = customThrowable.getErrorCode();
            errorMsgDTO.setErrorCode(errorCode.code())
                    .setReason(errorCode.reason())
                    .setMessage(customThrowable.getMessage())
                    .setCode(exception.getStackTrace()[0].getClassName() + "." + exception.getStackTrace()[0].getMethodName() + "() : " + exception.getStackTrace()[0].getLineNumber());
        } else {
            errorMsgDTO.setErrorCode("000")
                    .setCode(exception.getStackTrace()[0].getClassName() + "." + exception.getStackTrace()[0].getMethodName() + "() : " + exception.getStackTrace()[0].getLineNumber())
                    .setMessage(exception.getClass().getName() + " : " + exception.getMessage())
                    .setReason(exception.getClass().getName() + " : " + exception.getMessage());
        }
        return errorMsgDTO;
    }

    @ExceptionHandler(value = {EntityNotExistException.class})
    @ResponseBody
    protected ErrorMsgDTO handleEntityNotExistException(EntityNotExistException exception, HttpServletRequest request, HttpServletResponse response) {
        exception.printStackTrace();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return new ErrorMsgDTO().setErrorCode("500")
                .setMessage(exception.getMessage())
                .setReason("实体不存在")
                .setCode(exception.getStackTrace()[0].getClassName() + "." + exception.getStackTrace()[0].getMethodName() + "() : " + exception.getStackTrace()[0].getLineNumber());
    }

    /**
     * 无法预期的异常
     * ArrayIndexOutOfBoundsException等
     *
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    protected ErrorMsgDTO handleThrowable(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        Throwable cause = exception.getCause();
        if (cause instanceof EasyCustomException) {
            return handleEasyCustomException((EasyCustomException) cause, request, response);
        } else if (cause instanceof EntityNotExistException) {
            return handleEntityNotExistException(((EntityNotExistException) cause), request, response);
        } else if (cause instanceof NoPermissionException) {
            return handleNoPermissionException(((NoPermissionException) cause), request, response);
        } else {
            exception.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ErrorMsgDTO errorMsgDTO = new ErrorMsgDTO().setCode("000")
                    .setCode(exception.getStackTrace()[0].getClassName() + "." + exception.getStackTrace()[0].getMethodName() + "() : " + exception.getStackTrace()[0].getLineNumber())
                    .setMessage(exception.getClass().getName() + " : " + exception.getMessage())
                    .setReason(exception.getClass().getName() + " : " + exception.getMessage());
            return errorMsgDTO;
        }
    }

}
