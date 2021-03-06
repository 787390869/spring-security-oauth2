package com.zzq.cloud.platform.config;

import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.platform.framework.E;
import com.zzq.cloud.platform.framework.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = BusiException.class)
    public Result exceptionData(BusiException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage()).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.INVALID_PARAMETER, e.getMessage()).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public Result handleBindException(BindException e) {
        log.error("??????????????????", e);
        List<String> defaultMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return Result.fail(E.INVALID_PARAMETER, defaultMsg.get(0)).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("??????????????????", e);
        List<String> defaultMsg = e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return Result.fail(E.INVALID_PARAMETER, defaultMsg.get(0)).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("??????????????????", e);
        return Result.fail(E.INVALID_PARAMETER, e.getMessage()).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handleBindGetException(ConstraintViolationException e) {
        log.error("??????????????????", e);
        List<String> defaultMsg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return Result.fail(E.INVALID_PARAMETER, defaultMsg.get(0)).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageConversionException.class)
    public Result exceptionData(HttpMessageConversionException e) {
        log.error("??????????????????", e);
        return Result.fail(E.INVALID_PARAMETER, "??????????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result exceptionDuplication(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.INVALID_PARAMETER, "???????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result notSupportException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.INVALID_PARAMETER, "??????????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result accessDenied(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.UN_AUTHORIZED, "????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public Result nullPoint(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.SYSTEM_NULL_POINT, "???????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.INVALID_PARAMETER, "????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result httpMediaTypeNotSupportException(HttpMediaTypeNotSupportedException e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.INVALID_PARAMETER, "?????????Content-Type:" + e.getContentType()).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestPartException.class)
    public Result missingServletRequestPartException(MissingServletRequestPartException e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.INVALID_PARAMETER, "????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.INVALID_PARAMETER, "????????????").setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(PersistenceException.class)
    public Result persistenceException(PersistenceException e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.SQL_EXEC_ERROR, e.getMessage()).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public Result SQLException(SQLException e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.SQL_EXEC_ERROR, e.getMessage()).setPath(getPath());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(E.SYSTEM_ERROR, e.getMessage()).setPath(getPath());
    }

    private String getPath() {
        String path = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            path = request.getRequestURI();
        }
        return path;
    }
}