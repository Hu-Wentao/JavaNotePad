package utils;

import static utils.StrUtils.KEYWORD;

/**
 * 仅使用部分文本进行处理
 *
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/5/29
 */
public class EditUtils {


  /**
   * 检查 传输的字符串里有没有包含 java 关键字, 如果有, 则返回关键字长度, 否则返回 -1
   *
   * @param candidate 可能包含关键字的串 (注意: 需要先保证该串的关键字的前面有空格或 分号
   * @return 关键字
   */
  public static int getKeywordLength(String candidate) {
    for (int i = 0; i < KEYWORD.length; i++) {
      if (candidate.endsWith(KEYWORD[i])) {
        return KEYWORD[i].length();
      }
    }
    return -1;
  }



//  public static void main(String[] args) {
//    int k = 0;
//    for (int i = 0; i < KEYWORD.length; i++) {
//      if (KEYWORD[i].length() > k)
//        k = KEYWORD[i].length();
//    }
//    System.out.println(k);
//  }
}
