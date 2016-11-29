package com.wildwolf.mycsdn.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class NumUtil {
    public static String ChineseChangeToNumber(String chnStr) {
        //init map
        java.util.Map<String, Integer> unitMap = new java.util.HashMap<String, Integer>();
        unitMap.put("十", 10);

        java.util.Map<String, Integer> numMap = new java.util.HashMap<String, Integer>();
        numMap.put("零", 0);
        numMap.put("一", 1);
        numMap.put("二", 2);
        numMap.put("三", 3);
        numMap.put("四", 4);
        numMap.put("五", 5);
        numMap.put("六", 6);
        numMap.put("七", 7);
        numMap.put("八", 8);
        numMap.put("九", 9);

        //队列
        List<Long> queue = new ArrayList<Long>();
        long tempNum = 0;
        for (int i = 0; i < chnStr.length(); i++) {
            char bit = chnStr.charAt(i);
            System.out.print(bit);
            //数字
            if (numMap.containsKey(bit + "")) {

                tempNum = tempNum + numMap.get(bit + "");

                //一位数、末位数、亿或万的前一位进队列
                if (chnStr.length() == 1
                        | i == chnStr.length() - 1
                        | (i + 1 < chnStr.length() && (chnStr.charAt(i + 1) == '亿' | chnStr
                        .charAt(i + 1) == '万'))) {
                    queue.add(tempNum);
                }
            }
            //单位
            else if (unitMap.containsKey(bit + "")) {

                //遇到十 转换为一十、临时变量进队列
                if (bit == '十') {
                    if (tempNum != 0) {
                        tempNum = tempNum * unitMap.get(bit + "");
                    } else {
                        tempNum = 1 * unitMap.get(bit + "");
                    }
                    queue.add(tempNum);
                    tempNum = 0;
                }


            }
        }

        //output
        System.out.println();
        long sum = 0;
        for (Long i : queue) {
            sum += i;
        }
        System.out.println(sum);

        return sum + "";

    }
}
