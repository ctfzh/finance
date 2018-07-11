package com.ih2ome.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Sky
 * create 2018/07/04
 * email sky.li@ixiaoshuidi.com
 **/
public class ConstUtils {
    /**
     * 对象深层复制，写的不是很好，待优化
     *
     * @param obj
     * @return
     */
    public static Object deepClone(Object obj) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        Object object = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bi = new ByteArrayInputStream(bo.toByteArray());
            oi = new ObjectInputStream(bi);
            object = oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     * MD5加密
     *
     * @param text
     * @return
     */
    public static String md5(String text) {
        return DigestUtils.md2Hex(text);
    }


    /**
     * 响应数据随机数
     *
     * @return
     */
    public static String getResponseRandomStr() {
        return RandomUtil.generateString(16);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static Object mergeObject(Object master, Object follow) {
        if (master == null && follow == null) {
            return null;

        } else if (master == null && null != follow) {
            return follow;

        } else if (null != master && null == follow) {
            return master;

        } else {
            Object[] objectNone = new Object[]{};
            Class<?> c1 = master.getClass();
            Field[] fields = c1.getDeclaredFields();
            Method[] methods = c1.getDeclaredMethods();
            for (Field f : fields) {
                String fName = f.getName();
                Object o1 = null;
                Object o2 = null;
                for (Method m : methods) {
                    String mName = m.getName();
                    if (mName.contains("get") && mName.equalsIgnoreCase("get" + fName)) {
                        try {
                            o1 = m.invoke(master, objectNone);
                            o2 = m.invoke(follow, objectNone);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                o1 = getFitObject(o1, o2);
                for (Method m : methods) {
                    String mName = m.getName();
                    if (mName.contains("set") && mName.equalsIgnoreCase("set" + fName)) {
                        try {
                            m.invoke(master, new Object[]{o1});
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
        return master;
    }


    private static Object getFitObject(Object o1, Object o2) {
        if (null == o1 && null == o2) {
            return o1;
        } else if (null != o1 && null == o2) {
            return o1;
        } else if (null == o1 && null != o2) {
            return o2;
        } else {
            String s1 = o1.toString();
            String s2 = o2.toString();
            if (s1.equals("0")) {
                s1 = "";
            }
            if (StringUtils.isBlank(s1) && StringUtils.isBlank(s2)) {
                return o1;
            } else if (StringUtils.isNotBlank(s1) && StringUtils.isBlank(s2)) {
                return o1;
            } else if (StringUtils.isBlank(s1) && StringUtils.isNotBlank(s2)) {
                return o2;
            } else {
                return o1;
            }
        }
    }

    public static String urlEncode(Object o) {
        return URLEncoder.encode(o.toString());
    }

    //Date类型转String yyyy-MM-dd HH:mm:ss类型
    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    //String 【yyyy-MM-dd HH:mm:ss】类型转Date类型

    public static Date stringToDate(String str) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    //得到本月的第一天
    public static String getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat firstDay = new SimpleDateFormat("yyyy-MM-dd");
        return firstDay.format(calendar.getTime());
    }

    //得到本月的最后一天
    public static String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat lastDay = new SimpleDateFormat("yyyy-MM-dd");
        return lastDay.format(calendar.getTime());
    }

    //保留两位小数
    public static Double getDecimalFormat(Double param) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        String format = decimalFormat.format(param);
        return Double.valueOf(format);
    }

    //计算两个日期相差多少天(date2比date1多的天数)
    public static int differentDays(String dateString1, String dateString2, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = dateFormat.parse(dateString1);
            date2 = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1) + 1;
        } else    //不同年
        {
            return day2 - day1 + 1;
        }
    }

    //日期加上天数后的日期
    public static String plusDay(String newDate, int num, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date currdate = null;
        try {
            currdate = dateFormat.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println("现在的日期是：" + currdate);
        Calendar ca = Calendar.getInstance();
        ca.setTime(currdate);
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = dateFormat.format(currdate);
//        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }

    //判断两个日期是否是同一个月
    public static boolean equalsMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }
}
