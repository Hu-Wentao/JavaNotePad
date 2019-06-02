package utils;

import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

/**
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/6/2
 */
public class SetStyle implements Runnable {
  private StyledDocument doc;
  private int start;
  private int length;
  private Style style;

  public SetStyle(StyledDocument doc, int start, int length, Style style) {
    this.doc = doc;
    this.start = start;
    this.length = length;
    this.style = style;
  }

  @Override
  public void run() {
    doc.setCharacterAttributes(start, length, style, true);
  }
}
