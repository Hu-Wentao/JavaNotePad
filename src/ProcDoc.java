import utils.StrUtils;

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
  private int mProcessed = 0; // 小于该坐标的位置 ,表示已经处理过(变色等)或无需处理, 大于表示尚未处理(如果 mProcessed == mDocLength, 表示...)
  private int mDocLength = 0; // pane中 文本 的总长度
  private SimpleAttributeSet keywordStyle = new SimpleAttributeSet();

//  private ProcSpace mProcSpace = new ProcSpace();


  /**
   * 处理插入字符 的事件
   *  @param document
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
    if (currentCursor == mDocLength) {                                                // 先处理连续输入的情况
      int[] spaceLocation;  // 存储字符串中 空格的 位置 {前导空格, 尾部空格}
      if (input.length == 1) {  // 如果只输入了一个字符
        if(input[0] == ' '){    // 如果输入一个 空格
          mProcessed = start+1;
        }
        //todo
//        EditUtils.getKeyWord(document.getText(mProcessed, ));
//        document.setCharacterAttributes(mProcessed, );
      } else {                  // 如果输入了一串字符
        // 判断嫌疑字符串是否是空格开头, 或者 是文档的开头, 如果是, 则开始判断是否为关键字
        if ((spaceLocation = StrUtils.trim(input)) != null) {
          if (spaceLocation[0] != 0) {  // 只有 前导空格
            // 更新已处理 文档的位置
            mProcessed = start+spaceLocation[0];
            if(spaceLocation[0] == input.length){ // 输入了一串空格
              return;
            }
            //todo
          } else {                     // 只有 尾部空格

          }
        }
      }
    } else {                                                                        // 在文档的中间 进行插入 的情况

    }

//    document.
  }

  public void procDocRemove(Document document, int currentCursor, int start, int length) {
    this.mDocLength = document.getLength();


  }


}
