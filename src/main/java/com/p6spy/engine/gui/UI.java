package com.p6spy.engine.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: UI界面
 * @Description:
 * @Author: 庞吉祥
 * @Date: 2021/4/22 11:10
 */
public class UI extends JFrame {
  private JPanel contentPane;
  private JScrollPane scrollPane;
  private JTextArea textArea;
  private JButton clearBtn;

  private static volatile UI instance = new UI();

  private UI() {
    try {
      this.setTitle("SQL监控（基于p6spy，开发者pangJx）");
      this.setSize(600, 600);

      Image image= ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("icon.jpg"));
      this.setIconImage(image);

      componentInit();

      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
    } else {
      super.processWindowEvent(e);
    }
  }

  public static UI getInstance() {
    return instance;
  }

  /**
   * 向文本域中写数据
   * @param preFix 前缀
   * @param text 文本
   */
  public void writeToPanel(String preFix,String text) {
    String ot = textArea.getText();
    if (ot != null && !"".equals(ot.trim())) {
      textArea.append("\n");
    }

    // 数据打印
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    textArea.append(sdf.format(new Date()));
    textArea.append("  ");
    textArea.append(preFix);
    textArea.append("  ");
    textArea.append(text);

    // 定位到最后一行
    textArea.setCaretPosition(textArea.getText().length());
  }

  private void componentInit(){
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5,5,5,5));
    contentPane.setLayout(new BorderLayout(0,0));
    this.setContentPane(contentPane);

    clearBtn = new JButton("清除");
    clearBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText("");
      }
    });
    contentPane.add(clearBtn,BorderLayout.NORTH);

    scrollPane = new JScrollPane();
    contentPane.add(scrollPane,BorderLayout.CENTER);
    textArea = new JTextArea();
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    scrollPane.setViewportView(textArea);
  }
}
