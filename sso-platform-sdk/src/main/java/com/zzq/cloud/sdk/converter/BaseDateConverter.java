package com.zzq.cloud.sdk.converter;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public abstract class BaseDateConverter<T> {

    public T convert(String source, Function<String, T> function) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        String sourceTrim = source.trim();
        Set<Map.Entry<String, String>> entries = getFormat().entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if (sourceTrim.matches(entry.getValue())) {
                return function.apply(entry.getKey());
            }
        }
        throw new IllegalArgumentException("无效的日期参数格式:'" + sourceTrim + "'");
    }

    protected abstract Map<String, String> getFormat();

}
