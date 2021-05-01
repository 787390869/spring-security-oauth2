package com.zzq.cloud.sdk.converter;

import com.zzq.cloud.sdk.utils.DateUtil;
import org.springframework.core.convert.converter.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class String2LocalDateConverter extends BaseDateConverter<LocalDate> implements Converter<String, LocalDate> {

    protected static final Map<String, String> FORMAT = new LinkedHashMap(5);

    static {
        FORMAT.put(DateUtil.fm_yyyy_MM_dd, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
        FORMAT.put(DateUtil.fmx_yyyy_MM_dd, "^\\d{4}/\\d{1,2}/\\d{1,2}$");
        FORMAT.put(DateUtil.cn_yyyyMMdd, "^\\d{4}年\\d{1,2}月\\d{1,2}日$");
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public LocalDate convert(String source) {
        System.out.println("哈哈");
        return super.convert(source, (key) -> LocalDate.parse(source, DateTimeFormatter.ofPattern(key)));
    }

}

