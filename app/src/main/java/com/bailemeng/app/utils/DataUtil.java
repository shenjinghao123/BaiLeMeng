package com.bailemeng.app.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 常用的数据非空检查工具类
 * @author 续写经典
 * @date 2015/11/3
 */
public final class DataUtil {
  private DataUtil(){}

  /** 检查数组是否为空(去掉前后空格) */
  public static boolean isEmpty(String string){
    return (string == null || string.length() == 0 || string.equals("null")||string.trim().length() == 0);
  }
  /** 获取字符串长度(去掉前后空格) */
  public static int getSize(String string) {
    return string == null  ? 0 : string.trim().length();
  }

  /** 检查数组是否为空 */
  public static <V> boolean isEmpty(V[] sourceArray) {
    return (sourceArray == null || sourceArray.length == 0);
  }
  /** 获取数组长度 */
  public static <V> int getSize(V[] sourceArray) {
    return sourceArray == null ? 0 : sourceArray.length;
  }

  /** 检查Collection是否为空 */
  public static <V> boolean isEmpty(Collection<V> c) {
    return (c == null || c.size() == 0);
  }
  /** 获取Collection长度 */
  public static <V> int getSize(Collection<V> c) {
    return c == null ? 0 : c.size();
  }

  /** 检查List是否为空 */
  public static <V> boolean isEmpty(List<V> sourceList) {
    return (sourceList == null || sourceList.size() == 0);
  }
  /** 获取List长度 */
  public static <V> int getSize(List<V> sourceList) {
    return sourceList == null ? 0 : sourceList.size();
  }

  /** 检查Map是否为空 */
  public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
    return (sourceMap == null || sourceMap.size() == 0);
  }
  /** 获取Map长度 */
  public static <K, V> int getSize(Map<K, V> sourceMap) {
    return sourceMap == null ? 0 : sourceMap.size();
  }

  /** 从list中随机取num个数据 */
  public static <V> List<V> getRandomData(int num, List<V> list) {
    if (list.size() <= num) return list;
    List<Integer> positions = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      addRandomNum(list.size(), positions);
    }
    List<V> showList = new ArrayList<>();
    for (Integer position : positions) {
      showList.add(list.get(position));
    }
    return showList;
  }

  /**
   * 添加随机数到numList
   * @param max 随机数上限，如max=100，则随机数取值范围为0-99
   * @param numList 保存随机数的列表
   */
  private static void addRandomNum(int max, List<Integer> numList) {
    int randomNum = new Random().nextInt(max);
    if (numList == null) return;
    if (numList.size() > 0)
    {
      boolean isRepeat = false;
      for (Integer num : numList) {
        if (num == randomNum)
        {
          isRepeat = true;
          break;
        }
      }
      if (isRepeat)
      {
        //数字重复，重新生成
        addRandomNum(max, numList);
      }
      else
      {
        numList.add(randomNum);
      }
    }
    else
    {
      numList.add(randomNum);
    }
  }
  /** 获取字符串长度(去掉前后空格) */
  public static String getName(String string) {
    return string.trim().replace("《","").replace("》","");
  }

}
