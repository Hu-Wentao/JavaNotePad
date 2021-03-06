package utils;


import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/6/1
 */
public class StrUtils {
  static final String[] KEYWORD = ("abstract_class_extends_implements_null_strictfp_true_assert_const_false_import_package_super_try_boolean_continue_final_instanceof_private_switch_void_break_default_finally_int_protected_synchronized_volatile_byte_do_float_interface_public_this_while_case_double_for_long_String_return_throw_catch_else_goto_native_short_throws_char_enum_if_new_static_transient").split("_");
  static final char[] KEY_CHAR = ("+-/* ><=!^|&}{".toCharArray());
  public static final int MAX_KEY_LENGTH = 12;  // 空格+最长关键字长度

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

  /**
   * 向左查找 非变量 字符
   *
   * @param doc 文档
   * @return 返回左侧第一个 非单词字符 相对于 doc起点 的 偏移量
   */
  public static int searchLeft(Document doc, int location) {
//    while (location > 0 && !isKeyChar(doc, --location)) ;
    while (location > 0 ){
      if(!isKeyChar(doc, location-1)){
        location--;
        continue;
      }
      return location;
    }
    return location;
  }

  public static int searchRight(Document doc, int location) {
//    while (location < doc.getLength() && !isKeyChar(doc, location++)) ;
    while (location  < doc.getLength()){
      if(!isKeyChar(doc, location+1)){
        location++;
        continue;
      }
      return location;
    }
    return location;
  }

  /**
   * @param doc
   * @param location currentCursor
   * @return
   */
  public static int[] searchSection(Document doc, int location) {
    System.out.printf("当前指针: %s", location);  //todo
    return new int[]{searchLeft(doc, location), searchRight(doc, location)};
  }


  /**
   * 如果是 空格, +-* ><=!^|&"
   * 变量: 字母, 数字, 下划线, 汉字
   *
   * @return 如果是 空格, +-* ><=!^|&" 返回 true
   */
  private static boolean isKeyChar(Document doc, int location) {
    try {
      return isKeyChar((doc.getText(location, 1)).toCharArray()[0]);
    } catch (BadLocationException e) {
      e.printStackTrace();
      System.err.println("出现错误!!");
    }
    return false;
  }

  private static boolean isKeyChar(char ch) {
    for (char c : KEY_CHAR) {
      if (ch == c)
        return true;
    }
    return false;
  }

  /**
   * 检查 传输的字符串里有没有包含 java 关键字, 如果有, 则返回关键字长度, 否则返回 -1
   *
   * @param candidate 可能包含关键字的串 (注意: 需要先保证该串的关键字的前面有空格或 分号
   * @return 关键字
   */
  public static int getKeywordLength(String candidate) {
//    if(candidate.startsWith(" "))
//      candidate = candidate.substring(1);
    for (int i = 0; i < KEYWORD.length; i++) {
      if (candidate.equals(KEYWORD[i])) {
        return KEYWORD[i].length();
      }
    }
    return -1;
  }
}
