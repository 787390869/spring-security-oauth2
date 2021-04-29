package com.zzq.cloud.sdk.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desciption copy
 * @Author ZhangZiQiang
 * @Date 2019/06/08
 **/
public class BeanUtil extends org.apache.commons.beanutils.BeanUtils {

    public BeanUtil() {
    }

    /** 将orig中的非null 属性拷贝给 dest */
    public static void copyNotNullProperties(Object dest, Object orig, String... excludeProperties) {
        copyProperties(dest, orig, false, excludeProperties);
    }

    /** 将orig中的所有属性拷贝给dest */
    public static void copyAllProperties(Object dest, Object orig, String... excludeProperties) {
        copyProperties(dest, orig, true, excludeProperties);
    }

    /** 判断对象中的每一个属性是否为空 */
    public static boolean isBlank(Object object) {
        if (object == null) return true;
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        boolean flag = true;
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = null;
            try {
                fieldValue = field.get(object);
                fieldName = field.getName();
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue != null && !"serialVersionUID".equals(fieldName)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /** 私有拷贝方法 */
    private static void copyProperties(Object dest, Object orig, boolean isCopyNull, String... excludeProperties) {
        Assert.notNull(dest, "No destination bean specified");
        Assert.notNull(orig, "No origin bean specified");
        PropertyDescriptor[] origDescriptors = BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptors(orig);

        try {
            PropertyDescriptor[] var5 = origDescriptors;
            int var6 = origDescriptors.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                PropertyDescriptor propertyDescriptor = var5[var7];
                String name = propertyDescriptor.getName();
                if (!"class".equals(name)) {
                    if (ArrayUtils.isNotEmpty(excludeProperties)) {
                        Arrays.sort(excludeProperties);
                        if (Arrays.binarySearch(excludeProperties, name) >= 0) {
                            continue;
                        }
                    }

                    Object value = BeanUtilsBean.getInstance().getPropertyUtils().getSimpleProperty(orig, name);
                    if (isCopyNull || value != null) {
                        copyProperty(dest, name, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map toMap(Object bean) {
        try {
            return BeanUtils.describe(bean);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    public static <T> T toBean(Map<String, T> map, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.newInstance();
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
