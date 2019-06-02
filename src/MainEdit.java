import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/5/29
 */
public class MainEdit extends JTextPane implements DocumentListener {
  private ProcDoc procDoc;
  private int currentCursor;
  private Style keywordStyle, normalStyle;

  private static final Font FONT_NORMAL = new Font("宋体", Font.PLAIN, 16);
  private static final Color COLOR_BG_DARK = new Color(43, 43, 43);
  private static final Color COLOR_SELECTION = new Color(33, 66, 131);
  private static final Color COLOR_FG_KEYWORD_DARK = new Color(204, 120, 50);
  private static final Color COLOR_FG_NORMAL_DARK = Color.WHITE;


  MainEdit() {
    this.setFont(FONT_NORMAL);
    this.setCaretColor(Color.WHITE);  //光标色
    this.setBackground(COLOR_BG_DARK);  // 背景色
    this.setDisabledTextColor(Color.WHITE);   // ???
    this.setSelectionColor(COLOR_SELECTION);     // 被选中字体的 背景色
    this.setForeground(COLOR_FG_NORMAL_DARK);
    this.setSelectedTextColor(Color.gray);  // 被选中字体的 字体色

    this.getDocument().addDocumentListener(this);

    keywordStyle = ((StyledDocument) this.getDocument()).addStyle("KeywordStyle", null);
    normalStyle = ((StyledDocument) this.getDocument()).addStyle("NormalStyle", null);
    StyleConstants.setForeground(keywordStyle, COLOR_FG_KEYWORD_DARK);  // 关键字的 暗橙色
    StyleConstants.setForeground(normalStyle, COLOR_FG_NORMAL_DARK);  // 普通字符 白色

    procDoc =  new ProcDoc(normalStyle, keywordStyle);
  }


  @Override
  public void insertUpdate(DocumentEvent e) {
    currentCursor = e.getOffset() + e.getLength();

    procDoc.procDocInsert(e.getDocument(), currentCursor, e.getOffset(), e.getLength());

//    System.out.print("当前光标:" + currentCursor);
//    System.out.printf("输入事件: 起点:%s, 长度:%s, 文档起始位置:%s, 文档结束位置:%s, 文档总长:%s\n", e.getOffset(), e.getLength(), e.getDocument().getStartPosition(), e.getDocument().getEndPosition(), e.getDocument().getLength()); // 输入更新事件
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    currentCursor = e.getOffset();

    procDoc.procDocRemove(e.getDocument(), currentCursor, e.getOffset(), e.getLength());
//    System.out.print("当前光标:" + currentCursor);
//    System.out.printf("删除事件: 起点:%s, 长度:%s, 文档起始位置:%s, 文档结束位置:%s, 文档总长:%s\n", e.getOffset(), e.getLength(), e.getDocument().getStartPosition(), e.getDocument().getEndPosition(), e.getDocument().getLength()); // 输入更新事件
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    System.out.println("在文本的属性(颜色,字体等)改变时被调用");
  }
}
