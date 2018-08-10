package com.ih2ome.common.utils;

import com.ih2ome.common.PageVO.PinganWxPayVO.PinganWxRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.TreeMap;

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

    public static void main(String[] args) {
        PinganWxRequestVO pinganWxRequestVO = new PinganWxRequestVO();
        pinganWxRequestVO.setData("hahahah");
        pinganWxRequestVO.setOpen_id("ddddd");
        pinganWxRequestVO.setSign("eeeeee");
        pinganWxRequestVO.setTimestamp("44546");
        TreeMap<String, String> map = objectToMap(pinganWxRequestVO);
        System.out.println(map);
        System.out.println(map.get("data"));
        System.out.println(map.get("open_id"));
        System.out.println(map.get("sign"));
        System.out.println(map.get("timestamp"));

    }
}

