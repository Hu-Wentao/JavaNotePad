package utils;

/**
 * 仅使用部分文本进行处理
 *
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/5/29
 */
public class EditUtils {
  private static final String[] KEY_WORD = ("abstract_class_extends_implements_null_strictfp_true_assert_const_false_import_package_super_try_boolean_continue_final_instanceof_private_switch_void_break_default_finally_int_protected_synchronized_volatile_byte_do_float_interface_public_this_while_case_double_for_long_String_return_throw_catch_else_goto_native_short_throws_char_enum_if_new_static_transient").split("_");
  public static final int MAX_KEY_LENGTH = 12;  // 空格+最长关键字长度
  /**
   * 检查 传输的字符串里有没有包含 java 关键字 (注意: 需要先保证该串的关键字的前面有空格或 分号
   *
   * @param candidate 可能包含关键字的串
   * @return 关键字
   */
  public static String getKeyWord(String candidate) {
    for (int i = 0; i < KEY_WORD.length; i++) {
      if (candidate.endsWith(KEY_WORD[i])) {
        return KEY_WORD[i];
      }
    }
    return null;
  }

  public static void main(String[] args) {
    int k = 0;
    for (int i = 0; i < KEY_WORD.length; i++) {
      if(KEY_WORD[i].length()>k)
        k = KEY_WORD[i].length();
    }
    System.out.println(k);
  }
}
