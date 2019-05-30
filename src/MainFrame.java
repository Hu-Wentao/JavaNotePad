import javax.swing.*;
import java.awt.*;

/**
 * @Author: hu.wentao@outlook.com
 * @Date: 2019/5/29
 */
public class MainFrame extends JFrame {
  MainFrame builder(String title){
    this.setTitle(title);
    return this;
  }

  MainFrame build() {
    this.setVisible(true);
    this.setSize(800, 600); // 先设置窗口大小
    this.setLocationRelativeTo(null);   // 然后再设置居中, 如果反过来, 则会出现错误
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    return this;
  }

  MainFrame addEdit(JTextPane mainEdit) {
    JScrollPane scroll = new JScrollPane(mainEdit);   //把定义的JTextArea放到JScrollPane里面去
    this.getContentPane().add(scroll, BorderLayout.CENTER);
    return this;
  }

  public static void main(String[] args) {
    new MainFrame()
            .builder("Java编辑器")
            .addEdit(new MainEdit())
            .build();
  }
}
