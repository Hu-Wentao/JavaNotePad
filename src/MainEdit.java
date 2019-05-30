import utils.EditUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;

/**
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/5/29
 */
public class MainEdit extends JTextPane implements DocumentListener {
  private int currentCursor;

  MainEdit() {
//    this.
    this.setFont(new Font("宋体", Font.PLAIN, 16));
    this.setCaretColor(Color.WHITE);  //光标色
    this.setBackground(new Color(43, 43, 43));  // 背景色
    this.setDisabledTextColor(Color.WHITE);   // ???
    this.setSelectionColor(new Color(33, 66, 131));     // 被选中字体的 背景色
    this.setForeground(Color.WHITE);
    this.setSelectedTextColor(Color.gray);  // 被选中字体的 字体色

    this.getDocument().addDocumentListener(this);
  }

  /**
   * 给关键字上色 todo 应当读取 当前行 与 上一行 作为参数
   *
   * @param candidate
   */
  void setKeyWordColor(String candidate) {
    String keyword;
    //todo 此处应判断 candidate 有无空格, 或分号 (保证关键字是有效的)

    if (candidate == null || (keyword = EditUtils.getKeyWord(candidate)) == null) {
      return;
    }

    System.out.println(keyword);
  }

  void procInput(Document doc, int offset, int inputLength) {
    if (inputLength <= EditUtils.MAX_KEY_LENGTH) {  // 当 粘贴/输入 的文本长度 <= 最大关键字长度时
      int start = (currentCursor) - EditUtils.MAX_KEY_LENGTH;
      int length = EditUtils.MAX_KEY_LENGTH;
      if (start < 0) {
        length = currentCursor;
        start = 0;
      }

      String candidate = null;
      try {
        candidate = doc.getText(start, length);
      } catch (BadLocationException e1) {
        System.out.println("开始: " + start + " 当前光标: " + currentCursor);
        e1.printStackTrace();
      }
//      // 为关键字上色
//      System.out.println(candidate + "  长度:" + candidate.length());
      setKeyWordColor(candidate);
    } else {
      // todo 当 粘贴/输入 的文本长度 > 最大关键字长度时

    }
  }

  /**
   * todo 未来应当考虑用定时刷新取代监听器, 因为监听器很难处理一次性粘贴进大量文本的事件
   */
  @Override
  public void insertUpdate(DocumentEvent e) {
    currentCursor = e.getOffset() + e.getLength();
    procInput(e.getDocument(), e.getOffset(), e.getLength());
  }

  @Override
  public void removeUpdate(DocumentEvent e) {

  }

  @Override
  public void changedUpdate(DocumentEvent e) {

  }
}
