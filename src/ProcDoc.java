import utils.SetStyle;
import utils.StrUtils;

import javax.swing.*;
import javax.swing.text.*;

/**
 * 将 TextPane 中所有的文字作为处理内容
 * <p>
 * 将本类设为非静态处理类, 这样可以支持同时打开多个实例的情况
 *
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/5/30
 */
public class ProcDoc {
  ProcDoc(Style normalStyle, Style keywordStyle) {
    this.normalStyle = normalStyle;
    this.keywordStyle = keywordStyle;
  }

  private Style normalStyle;
  private Style keywordStyle;

  private int mDocLength = 0; // pane中 文本 的总长度


  /**
   * 处理插入字符 的事件
   *
   * @param document
   * @param currentCursor
   * @param start
   * @param length
   */
  public void procDocInsert(Document document, int currentCursor, int start, int length) {
    this.mDocLength = document.getLength();
    char[] input = null;  // 新输入的字符数组
    try {
      input = document.getText(start, length).toCharArray();
    } catch (BadLocationException e) {
      e.printStackTrace();
    } // 给 input 赋值

    if (input == null)
      return;
    if (mDocLength == currentCursor) {        // 先处理连续输入的情况
      // 考虑 对 空格 进行特殊处理, 以提高性能
      if (input.length == 1) {  // 如果只输入了一个字符
        // 特殊处理(提高性能)
        if (input[0] == ' ') {
          return;
        }
        checkAndSetStyle(normalStyle, keywordStyle, document, StrUtils.searchSection(document, currentCursor));
      } else {                  // 如果输入了一串字符
        // 判断嫌疑字符串是否是空格开头, 或者 是文档的开头, 如果是, 则开始判断是否为关键字
//        if ((spaceLocation = StrUtils.trim(input)) != null) {
//          if (spaceLocation[0] != 0) {  // 只有 前导空格
//            // 更新已处理 文档的位置
//            mProcessed = start + spaceLocation[0];  // 更新 处理过的位置
//            if (spaceLocation[0] == input.length) { // 输入了一串空格
//              return;
//            }
//          } else {                     // 只有 尾部空格
//            // todo 检查 前导字符串 是否是关键字
//            // 获取 前导字符串, 进行比对
//
//            mProcessed = currentCursor; // 更新 处理过的位置
//          }
//        }
      }
    } else {        // 在文档的中间 进行插入 的情况
      // 如果是 在文档中间 插入单个字符
      if (input.length == 1) {
        checkAndSetStyle(normalStyle, keywordStyle, document, StrUtils.searchSection(document, currentCursor - 1));
        checkAndSetStyle(normalStyle, keywordStyle, document, StrUtils.searchSection(document, currentCursor + 1));
      } else {
        // todo 从插入串的左侧位置开始 循环查找匹配, 直到匹配到右侧位置

      }
    }
  }

  public void procDocRemove(Document document, int currentCursor, int start, int length) {
    this.mDocLength = document.getLength();
    if (mDocLength == currentCursor) {    // 处理从最右侧删除的情况
      checkAndSetStyle(normalStyle, keywordStyle, document, StrUtils.searchSection(document, currentCursor));
    } else {                              // 从中间开始删除
      checkAndSetStyle(normalStyle, keywordStyle, document, StrUtils.searchSection(document, currentCursor));
    }
  }

  /**
   * 检查 [section end] 区间内的字符串是否是关键字, 如果是,则设为关键字style, 否则设为普通style
   *
   * @param doc     文档
   * @param section 字符串{ 开始, 结束}
   */
  private static void checkAndSetStyle(Style normalStyle, Style keywordStyle, Document doc, int... section) {
    if (section[1] - section[0] <= 0) {
//      System.err.printf("被选中的区间: start: %s end: %s", section[0], section[1]);                                /// test
      return;
    }
    int keyLength;
    keyLength = StrUtils.getKeywordLength(getString(doc, section[0], section[1] - section[0]));
//    System.out.printf("截取的字符串: %s", getString(doc, section[0], section[1] - section[0]));                    /// test
    SwingUtilities.invokeLater(new SetStyle((StyledDocument) doc,
            section[0],
            section[1] - section[0],
            keyLength == -1 ? normalStyle : keywordStyle));
  }

  static String getString(Document doc, int pos, int len) {
    try {
      return doc.getText(pos, len);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
    throw new RuntimeException();
  }
}
