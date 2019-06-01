import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

/**
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/5/29
 */
public class MainEdit extends JTextPane implements DocumentListener {
  private ProcDoc procDoc = new ProcDoc();
  private int currentCursor;

  MainEdit() {
    this.setFont(new Font("宋体", Font.PLAIN, 16));
    this.setCaretColor(Color.WHITE);  //光标色
    this.setBackground(new Color(43, 43, 43));  // 背景色
    this.setDisabledTextColor(Color.WHITE);   // ???
    this.setSelectionColor(new Color(33, 66, 131));     // 被选中字体的 背景色
    this.setForeground(Color.WHITE);
    this.setSelectedTextColor(Color.gray);  // 被选中字体的 字体色

    this.getDocument().addDocumentListener(this);

    this.setStyledDocument(new DefaultStyledDocument());  // 设置风格...
  }

//  /**
//   * 给关键字上色
//   *
//   * @param aboveLineOverBool 上一行是否有";" 结束
//   * @param candidate 可能包含 关键字 的字符串
//   */
//  void setKeyWordColor(boolean aboveLineOverBool, String candidate) {
//    String keyword;
//
//    if (candidate == null || (keyword = EditUtils.getKeyWord(candidate)) == null) {
//      return;
//    }
//    System.out.println(keyword);
//  }

//  void procInput(Document doc, int offset, int inputLength) {
//    if (inputLength <= EditUtils.MAX_KEY_LENGTH) {  // 当 粘贴/输入 的文本长度 < 最大关键字长度时
//      int start = (currentCursor) - EditUtils.MAX_KEY_LENGTH;
//      int length = EditUtils.MAX_KEY_LENGTH;
//      if (start < 0) {
//        length = currentCursor;
//        start = 0;
//      }
//
//      String candidate = null;
//      try {
//        candidate = doc.getText(start, length);
//      } catch (BadLocationException e1) {
//        System.out.println("开始: " + start + " 当前光标: " + currentCursor);
//        e1.printStackTrace();
//      }
////      // 为关键字上色
////      System.out.println(candidate + "  长度:" + candidate.length());
////      setKeyWordColor(candidate);
//    } else {
//
//    }
//  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    currentCursor = e.getOffset() + e.getLength();

    procDoc.procDocInsert(e.getDocument(), currentCursor, e.getOffset(), e.getLength());
//    procInput(e.getDocument(), e.getOffset(), e.getLength());

    System.out.print("当前光标:" + currentCursor);
    System.out.printf("输入事件: 起点:%s, 长度:%s, 文档起始位置:%s, 文档结束位置:%s, 文档总长:%s\n", e.getOffset(), e.getLength(), e.getDocument().getStartPosition(), e.getDocument().getEndPosition(), e.getDocument().getLength()); // 输入更新事件
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    currentCursor = e.getOffset();

    procDoc.procDocRemove(e.getDocument(), currentCursor, e.getOffset(), e.getLength());
    System.out.print("当前光标:" + currentCursor);
    System.out.printf("删除事件: 起点:%s, 长度:%s, 文档起始位置:%s, 文档结束位置:%s, 文档总长:%s\n", e.getOffset(), e.getLength(), e.getDocument().getStartPosition(), e.getDocument().getEndPosition(), e.getDocument().getLength()); // 输入更新事件
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    System.out.println("在文本的属性(颜色,字体等)改变时被调用");
  }
}
