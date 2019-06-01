package utils;


/**
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/6/1
 */
public class StrUtils {
  /**
   * 判断输入的字符串有没有前导空格 或者 尾部空格
   *
   * @param chars 字符串
   * @return null: 说明没有前导空格与尾部空格
   * {0, n} : 说明 有 尾部空格
   * {n, length}: 说明 有 前导空格
   */
  public static int[] trim(char[] chars) {
    int len = chars.length;
    int st = 0;

    while ((st < len) && (chars[st] <= ' ')) {
      st++;
    }
    while ((st < len) && (chars[len - 1] <= ' ')) {
      len--;
    }
    return ((st > 0) || (len < chars.length)) ? new int[]{st, len} : null;
  }
}
