package com.zzq.cloud.sdk.framework;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zzq.cloud.sdk.framework.enums.IBaseEnum;
import com.zzq.cloud.sdk.utils.ServletUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZZQ
 * @date 2020/1/6 15:09
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "统一返回结果")
public class Result<T> implements Serializable {

    public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    public static final String DEFAULT_FAIL_MESSAGE = "操作失败";

    /**
     * 成功状态响应编码
     **/
    public static final Integer SUCCESS_CODE = 200;

    @ApiModelProperty(value = "响应状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应消息", required = true)
    private String msg;

    @ApiModelProperty(value = "响应数据", required = true, dataType = "string")
    private T data;

    @ApiModelProperty(value = "枚举列表", dataType = "JSON", hidden = true)
    private Map<String, T> fields = new HashMap<>();

    @ApiModelProperty(value = "请求路径", dataType = "string", hidden = true)
    private String path;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.path = ServletUtil.getRequest().getRequestURI();
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.path = ServletUtil.getRequest().getRequestURI();
    }

    public static Result success() {
        return success(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result success(String msg) {
        return new Result(200, msg);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static <T> Result<T> success(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static Result fail(String msg) {
        return fail(100, msg);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static Result toResult(Object result) {
        if (result instanceof Integer) return (Integer) result > 0 ? success(DEFAULT_SUCCESS_MESSAGE) : fail(DEFAULT_FAIL_MESSAGE);
        if (result instanceof Boolean) return (Boolean) result ? success(DEFAULT_SUCCESS_MESSAGE) : fail(DEFAULT_FAIL_MESSAGE);
        if (result instanceof Long) return (Long) result > 0L ? success(DEFAULT_SUCCESS_MESSAGE) : fail(DEFAULT_FAIL_MESSAGE);
        return fail(DEFAULT_FAIL_MESSAGE);
    }

    public Result withField(Class<? extends IBaseEnum> e) {
        try {
            Method method = e.getMethod("values");
            String name = e.getSimpleName();
            this.fields.put(name.substring(0,1).toLowerCase() + name.substring(1), (T) method.invoke(null));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public Result withField(Class<? extends IBaseEnum> e, String... fieldMethods) {
        String name = e.getSimpleName();
        List<String> methods = Stream.of(fieldMethods).filter(m -> !m.equals("getCode")).collect(Collectors.toList());
        methods.add("getCode");
        List<JSONObject> all = new ArrayList<>();
        try {
            for (Object ob: e.getEnumConstants()) {
                JSONObject jo = new JSONObject();
                for (String m: methods) {
                    Method method = e.getMethod(m);
                    String fieldName = m.replace("get", "");
                    jo.put(fieldName.substring(0,1).toLowerCase() + fieldName.substring(1), method.invoke(ob));
                }
                all.add(jo);
            }
            this.fields.put(name, (T) all);
        } catch (Exception ex) {
            log.error("不存在方法", ex);
        }
        return this;
    }

    public Result withField(Class<? extends IBaseEnum> e, List<Map<Object, Object>> all) {
        try {
            String name = e.getSimpleName();
            this.fields.put(name.substring(0,1).toLowerCase() + name.substring(1),  (T) all);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this;
    }

}
