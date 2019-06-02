import utils.EditUtils;
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

  private int mProcessed = 0; // 小于该坐标的位置 ,表示已经处理过(变色等)或无需处理, 大于表示尚未处理(如果 mProcessed == mDocLength, 表示...)
  private int mDocLength = 0; // pane中 文本 的总长度
  private StringBuilder inputCatch = new StringBuilder(16);  // 适用于连续输入的情况

//  private ProcSpace mProcSpace = new ProcSpace();


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
    if (mDocLength == currentCursor) {                                                // 先处理连续输入的情况
      int[] spaceLocation;  // 表示 字符串中 空格 的位置 { 前导空格, 尾部空格 }

      if (input.length == 1) {  // 如果只输入了一个字符
        checkAndSetStyle(document, mProcessed, currentCursor, normalStyle, keywordStyle);

        mProcessed ++;   // 更新 processed (该变量只更新4次, (插入3次(单字符,前导空格, 尾部空格), 删除1次) )
      } else {                  // 如果输入了一串字符
        // 判断嫌疑字符串是否是空格开头, 或者 是文档的开头, 如果是, 则开始判断是否为关键字
        if ((spaceLocation = StrUtils.trim(input)) != null) {
          if (spaceLocation[0] != 0) {  // 只有 前导空格
            // 更新已处理 文档的位置
            mProcessed = start + spaceLocation[0];  // 更新 处理过的位置
            if (spaceLocation[0] == input.length) { // 输入了一串空格
              return;
            }
            //todo
          } else {                     // 只有 尾部空格
            // todo 检查 前导字符串 是否是关键字
            // 获取 前导字符串, 进行比对

            mProcessed = currentCursor; // 更新 处理过的位置
          }
        }
      }
    } else {                                                                        // 在文档的中间 进行插入 的情况
      // 如果是 在文档中间 插入单个字符

      int left, right;
      // todo 向左 搜索空格或者";", 赋值给left
      // todo 向右 搜索空格或者";", 赋值给right (注意, 如果插入的就是一个空格, 则向右搜索可能产生BUG)

      // todo 如果向左 搜索到
    }
  }

  public void procDocRemove(Document document, int currentCursor, int start, int length) {
    this.mDocLength = document.getLength();

    // 处理从最右侧删除的情况
    if (mDocLength == currentCursor) {
      mProcessed -= length; // 更新 processed
      // 处理删除单个字符的情况
      if (length == 1) {
//        // 向左检测
//        int checkLocation = StrUtils.searchLeft(document, currentCursor);
//        // 检测是否为关键字
//        if (EditUtils.getKeywordLength())

//        System.out.printf("currentCursor:%s, start%s, length%s\n", currentCursor, start, length);
      } else {
        // todo 选中了多个字符 进行删除
      }

    } else { // 从中间开始删除
      // todo 处理删除单个字符的情况
      if (length == 1) {

      } else {
        // todo 选中了多个字符 进行删除
      }
    }
  }

  /**
   * 检查 start end 区间内的字符串是否是关键字, 如果是,则设为关键字style, 否则设为普通style
   *
   * @param doc           文档
   * @param start   嫌疑字符串
   * @param end  字符串长度
   */
  private static void checkAndSetStyle(Document doc, int start, int end, Style normalStyle, Style keywordStyle) {
    int keyLength;

    System.out.printf("start: %s end: %s", start, end);
    keyLength = EditUtils.getKeywordLength(getString(doc, start, end-start));

    SwingUtilities.invokeLater(new SetStyle((StyledDocument) doc,
            start,
            keyLength,
            keyLength == -1 ? normalStyle : keywordStyle));
  }

  static String getString(Document doc, int pos, int len){
    try {
      return doc.getText(pos, len);
    } catch (BadLocationException e) {
      e.printStackTrace();
    }
    throw new RuntimeException();
  }

}
