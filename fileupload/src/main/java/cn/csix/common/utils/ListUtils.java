package cn.csix.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xjd
 * @Date: 2020/6/23 17:17
 * @Description:
 **/
public class ListUtils {

    /**
     *
     * 功能描述:
     *
     * @param: list 传入要拆分的List
     * @param: subLength 传入要拆分的长度
     * @return:
     * @author: xjd
     * @date: 2019/8/22 16:38
     */
    public static List<List<?>> getSubList(List<?> list, int subLength){
        if(null == list || list.isEmpty() || subLength < 1){
            return null;
        }
        List<List<?>> resultLists = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(i!= 0 && i%subLength == 0){
                int count = i/subLength;
                //count一定会大于0,所以就会疏漏掉0至subLength这段的数据,故在第一次的时候将这个数据补齐
                if(count == 1){
                    List<?> collect = list.stream().limit((1) * subLength).skip(0 * subLength).collect(Collectors.toList());
                    resultLists.add(collect);
                }
                List<?> collect = list.stream().limit((count + 1) * subLength).skip(count * subLength).collect(Collectors.toList());
                resultLists.add(collect);
            }
        }
        return resultLists;
    }
}
