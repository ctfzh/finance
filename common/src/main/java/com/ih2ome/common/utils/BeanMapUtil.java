package com.ih2ome.common.utils;

import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterReqVO;
import com.ih2ome.common.PageVO.PinganWxPayVO.PinganWxRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Sky
 * create 2018/07/25
 * email sky.li@ixiaoshuidi.com
 * 实体类与map集合的相互转换
 **/
public class BeanMapUtil {

    private static Logger LOG = LoggerFactory.getLogger(BeanMapUtil.class);

    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static TreeMap<String, String> objectToMap(Object obj) {
        TreeMap<String, String> map = new TreeMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), (String) field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("object2Map转换异常---:{}", e.getMessage());
        }
        return map;
    }


    /**
     * Map转实体类
     *
     * @param map    需要初始化的数据，key字段必须与实体类的成员名字一样，否则赋值为空
     * @param entity 需要转化成的实体类
     * @return
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> entity) {
        T t = null;
        try {
            t = entity.newInstance();
            List<Field> fieldList = new ArrayList<>();
            Field[] subFields = entity.getDeclaredFields();
            fieldList.addAll(Arrays.asList(subFields));
            if (entity.getSuperclass() != null) {
                Field[] parentFields = entity.getSuperclass().getDeclaredFields();
                fieldList.addAll(Arrays.asList(parentFields));
            }
            for (Field field : fieldList) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                        field.set(t, object);
                    }
                    field.setAccessible(flag);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            LOG.error("map2Object转换异常---:{}", e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOG.error("map2Object转换异常---:{}", e.getMessage());
        }
        return t;
    }


    public static void mapToBean(Object javabean, Map<String, Object> m) {
        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                Class<?>[] params = method.getParameterTypes();
                String field = method.getName();
                field = field.substring(field.indexOf("set") + 3);
                field = field.toLowerCase().charAt(0) + field.substring(1);
                Object value = m.get(field.toString());
                try {
                    if (value != null && !"".equals(value)) {
                        String pa = params[0].getName().toString();
                        if (pa.equals("java.util.Date")) {
                            value = new Date(
                                    ((Date) value).getTime());
                        } else if (pa.equals("java.lang.String")) {
                            value = new String(value.toString());
                        } else if (pa.equals("java.lang.Integer")
                                || pa.equals("int")) {
                            value = new Integer(value.toString());
                        } else if (pa.equals("java.lang.Long")) {
                            value = new Long(value.toString());
                        } else if (pa.equals("java.lang.Double")) {
                            value = new Double(value.toString());
                        } else if (pa.equals("java.lang.Float")) {
                            value = new Float(value.toString());
                        } else if (pa.equals("java.lang.Short")) {
                            value = new Short(value.toString());
                        } else if (pa.equals("java.lang.Byte")) {
                            value = new Byte(value.toString());
                        } else if (pa.equals("java.lang.Boolean")) {
                            value = new Boolean(value.toString());
                        }
                        method.invoke(javabean, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
//        PinganWxRequestVO pinganWxRequestVO = new PinganWxRequestVO();
//        pinganWxRequestVO.setData("hahahah");
//        pinganWxRequestVO.setOpen_id("ddddd");
//        pinganWxRequestVO.setSign("eeeeee");
//        pinganWxRequestVO.setTimestamp("44546");
//        TreeMap<String, String> map = objectToMap(pinganWxRequestVO);
//        System.out.println(map);
//        System.out.println(map.get("data"));
//        System.out.println(map.get("open_id"));
//        System.out.println(map.get("sign"));
//        System.out.println(map.get("timestamp"));
        PinganMchRegisterReqVO reqVO = new PinganMchRegisterReqVO();

    }
}

