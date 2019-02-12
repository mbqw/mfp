package com.task.common;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 常用工具类
 * add zhupeng
 */
public class ToolUtils {

    /**
     * 获取两个日期之间的所有的n分钟,方便chart图表补0或者补null操作
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getHourToRangesExcludeCurrentDate(String start, String end, String format, int n) throws Exception {
        List<String> dateList = new ArrayList<String>();

        Calendar startCalendar = Calendar.getInstance();
        Calendar currentCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date startDate = df.parse(start);
        startCalendar.setTime(startDate);
        currentCalendar.setTime(startDate);
        Date endDate = df.parse(end);
        endCalendar.setTime(endDate);
        while (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {
            dateList.add(df.format(startCalendar.getTime()));
            startCalendar.add(Calendar.MINUTE, n);
        }

        return dateList;
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }


    public static void main(String[] args) {

        Map<String, Object> map = new TreeMap<String, Object>();

        map.put("123", "123");
        map.put("456", "4545");
        map.put("789", "45");
        map.put("1212", "454");

        Map<String, Object> resultMap = sortMapByKey(map);

        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
