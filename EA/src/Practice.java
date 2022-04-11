

import com.sun.jndi.toolkit.url.Uri;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class Practice  extends JFrame {
      JLabel jLabel=new JLabel("选择天数");
      JComboBox jComboBox=new JComboBox();
      JLabel jLabel2=new JLabel("天");
      JButton button1=new JButton("练习",new ImageIcon(ImageIO.read(new File("src\\Icon\\1.png"))));
      JButton button2=new JButton("查看中文",new ImageIcon(ImageIO.read(new File("src\\Icon\\2.png"))));
      JButton button3=new JButton("退出",new ImageIcon(ImageIO.read(new File("src\\Icon\\4.png"))));
      JButton button4=new JButton("斩",new ImageIcon(ImageIO.read(new File("src\\Icon\\3.png"))));
      JButton button5=new JButton("读音",new ImageIcon(ImageIO.read(new File("src\\Icon\\5.png"))));
      AllCount c=new AllCount();
       String co="总共"+c.coutWord+"个单词      ";
       JLabel countWord=new JLabel(co);
      Backgoundpanel panel1=new Backgoundpanel(ImageIO.read(new File("src\\Icon\\bear.jpg")));
      JLabel  show1=new JLabel();
      Backgoundpanel panel2=new Backgoundpanel(ImageIO.read(new File("src\\Icon\\bear.jpg")));
      JLabel  show2=new JLabel();
      Box box1=Box.createHorizontalBox();
      Box box2=Box.createVerticalBox();
      Random random=new Random();
      ArrayList<Word> words=new ArrayList<>();
      Backgoundpanel backgoundpanel=new Backgoundpanel(ImageIO.read(new File("src\\Icon\\bear.jpg")));
      int t;
      int count;
      int nnnn=0;
      public Practice() throws HeadlessException, IOException {
            jLabel.setFont(new Font("宋体",Font.BOLD,20));
            jComboBox.setFont(new Font("宋体",Font.BOLD,20));
            jLabel2.setFont(new Font("宋体",Font.BOLD,20));
            countWord.setFont(new Font("宋体",Font.BOLD,20));
            button1.setFont(new Font("宋体",Font.BOLD,20));
            button2.setFont(new Font("宋体",Font.BOLD,20));
            button3.setFont(new Font("宋体",Font.BOLD,20));
            button4.setFont(new Font("宋体",Font.BOLD,20));
            button5.setFont(new Font("宋体",Font.BOLD,20));


            jComboBox.addItem("请选择单词组");
          try {
              Class.forName("com.mysql.jdbc.Driver");
              /*
              *
              * 这里需要修改
              *
              *
              *
              * */
              Connection connection= DriverManager.getConnection("jdbc:mysql://mysql地址/数据库名?characterEncoding=UTF-8","用户名","密码");
             Statement statement=connection.createStatement();
              String sql="SELECT  table_name FROM information_schema.tables WHERE table_schema = '数据库名' AND table_type = 'base table'";
             ResultSet set=statement.executeQuery(sql);
               while (set.next()){
                   nnnn++;
                   jComboBox.addItem("高频"+nnnn);
               }
          } catch (ClassNotFoundException | SQLException e) {
              e.printStackTrace();
          }

          box1.add(countWord);
            box1.add(Box.createHorizontalStrut(300));
            box1.add(jLabel);
            box1.add(jComboBox);
            box1.add(jLabel2);
            box1.add(button1);
            box1.add(button2);
            box1.add(button4);
            box1.add(button5);
            box1.add(button3);
            box1.add(Box.createHorizontalStrut(450));


            backgoundpanel.setLayout(new BorderLayout());
            backgoundpanel.add(box1,BorderLayout.NORTH);
            panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
            //panel1.add(show1);
            panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
            panel2.add(show2);
            box2.add(Box.createVerticalStrut(60));
            box2.add(show1);
            box2.add(Box.createVerticalStrut(500));
            box2.add(show2);
            panel1.add(box2);

            backgoundpanel.add(panel1,BorderLayout.CENTER);
           add(backgoundpanel);
          try {
              UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          } catch (InstantiationException e) {
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          } catch (UnsupportedLookAndFeelException e) {
              e.printStackTrace();
          }
          jComboBox.addItemListener(new ItemListener() {
                  @Override
                  public void itemStateChanged(ItemEvent e) {
                  if (e.getStateChange()==ItemEvent.SELECTED){
                        words.clear();
                  try {
                              Class.forName("com.mysql.jdbc.Driver");
                              /*
                              *
                              *
                              *
                              *
                              * 这里需要修改！！！！！！！！！！！！！！！！！！！
                              *
                              *
                              *
                              *
                              *
                              * */
                              Connection connection= DriverManager.getConnection("jdbc:mysql://数据库地址/数据库名?characterEncoding=UTF-8","用户名","密码");
                              String str="select * from "+e.getItem().toString();
                              Statement statement=connection.createStatement();
                              ResultSet resultSet=statement.executeQuery(str);

                              while (resultSet.next()){
                                    words.add(new Word(resultSet.getString(2),resultSet.getString(3)));
                                    count++;
                              }
                        } catch (ClassNotFoundException | SQLException e1) {
                              e1.printStackTrace();
                        }
                        System.out.println(e.getItem());
                  }
                  }
            });

          button1.addKeyListener(new KeyAdapter() {
              @Override
              public void keyPressed(KeyEvent e) {
                  int keyCode=e.getKeyCode();
                  if (keyCode==KeyEvent.VK_D){
                      if (jComboBox.getSelectedItem().toString().equals("请选择单词组")) {
                          JOptionPane.showMessageDialog(null, "您还没有选择单词组", "警告", JOptionPane.ERROR_MESSAGE);
                      }
                      t = random.nextInt(words.size());
                      show1.setFont(new Font("宋体", Font.BOLD, 80));
                      show1.setText(words.get(t).wordStr);
                      show2.setText("");
                      words.get(t).wordStr += "*";
                  }
                  if (keyCode==KeyEvent.VK_S){
                      show2.setText(words.get(t).ChineseStr);
                      show2.setFont(new Font("宋体",Font.BOLD,40));
                  }
                  if (keyCode==KeyEvent.VK_A){
                      words.remove(words.get(t));
                      if (!words.isEmpty()) {
                          t = random.nextInt(words.size());
                          show1.setFont(new Font("宋体", Font.BOLD, 80));
                          show1.setText(words.get(t).wordStr);
                          show2.setText("");
                          words.get(t).wordStr += "*";
                      }
                      else {
                          show1.setText("NB!学完了!!");
                      }
                  }
                  if (keyCode==KeyEvent.VK_W){
                      URI uri= null;
                      String string=words.get(t).wordStr.replace("*","");
                      String s="https://fanyi.baidu.com/?aldtype=16047#en/zh/"+string;
                      try {
                          uri = new URI(s);
                          Desktop.getDesktop().browse(uri);
                      } catch (URISyntaxException | IOException uriSyntaxException) {
                          uriSyntaxException.printStackTrace();
                      }
                  }
              }
          });

          button2.addKeyListener(new KeyAdapter() {
              @Override
              public void keyPressed(KeyEvent e) {
                  int keyCode=e.getKeyCode();
                  if (keyCode==KeyEvent.VK_D){
                      if (jComboBox.getSelectedItem().toString().equals("请选择单词组")) {
                          JOptionPane.showMessageDialog(null, "您还没有选择单词组", "警告", JOptionPane.ERROR_MESSAGE);
                      }
                      t = random.nextInt(words.size());
                      show1.setFont(new Font("宋体", Font.BOLD, 80));
                      show1.setText(words.get(t).wordStr);
                      show2.setText("");
                      words.get(t).wordStr += "*";
                  }
                  if (keyCode==KeyEvent.VK_S){
                      show2.setText(words.get(t).ChineseStr);
                      show2.setFont(new Font("宋体",Font.BOLD,40));
                  }
                  if (keyCode==KeyEvent.VK_A){
                      words.remove(words.get(t));
                      if (!words.isEmpty()) {
                          t = random.nextInt(words.size());
                          show1.setFont(new Font("宋体", Font.BOLD, 80));
                          show1.setText(words.get(t).wordStr);
                          show2.setText("");
                          words.get(t).wordStr += "*";
                      }
                      else {
                          show1.setText("NB!学完了!!");
                      }
                  }
                  if (keyCode==KeyEvent.VK_W){
                      URI uri= null;
                      String string=words.get(t).wordStr.replace("*","");
                      String s="https://fanyi.baidu.com/?aldtype=16047#en/zh/"+string;
                      try {
                          uri = new URI(s);
                          Desktop.getDesktop().browse(uri);
                      } catch (URISyntaxException | IOException uriSyntaxException) {
                          uriSyntaxException.printStackTrace();
                      }
                  }
              }
          });
          button4.addKeyListener(new KeyAdapter() {
              @Override
              public void keyPressed(KeyEvent e) {
                  int keyCode=e.getKeyCode();
                  if (keyCode==KeyEvent.VK_D){
                      if (jComboBox.getSelectedItem().toString().equals("请选择单词组")) {
                          JOptionPane.showMessageDialog(null, "您还没有选择单词组", "警告", JOptionPane.ERROR_MESSAGE);
                      }
                      t = random.nextInt(words.size());
                      show1.setFont(new Font("宋体", Font.BOLD, 80));
                      show1.setText(words.get(t).wordStr);
                      show2.setText("");
                      words.get(t).wordStr += "*";
                  }
                  if (keyCode==KeyEvent.VK_S){
                      show2.setText(words.get(t).ChineseStr);
                      show2.setFont(new Font("宋体",Font.BOLD,40));
                  }
                  if (keyCode==KeyEvent.VK_A){
                      words.remove(words.get(t));
                      if (!words.isEmpty()) {
                          t = random.nextInt(words.size());
                          show1.setFont(new Font("宋体", Font.BOLD, 80));
                          show1.setText(words.get(t).wordStr);
                          show2.setText("");
                          words.get(t).wordStr += "*";
                      }
                      else {
                          show1.setText("NB!学完了!!");
                      }
                  }
                  if (keyCode==KeyEvent.VK_W){
                      URI uri= null;
                      String string=words.get(t).wordStr.replace("*","");
                      String s="https://fanyi.baidu.com/?aldtype=16047#en/zh/"+string;
                      try {
                          uri = new URI(s);
                          Desktop.getDesktop().browse(uri);
                      } catch (URISyntaxException | IOException uriSyntaxException) {
                          uriSyntaxException.printStackTrace();
                      }
                  }
              }
          });
          button5.addKeyListener(new KeyAdapter() {
              @Override
              public void keyPressed(KeyEvent e) {
                  int keyCode=e.getKeyCode();
                  if (keyCode==KeyEvent.VK_D){
                      if (jComboBox.getSelectedItem().toString().equals("请选择单词组")) {
                          JOptionPane.showMessageDialog(null, "您还没有选择单词组", "警告", JOptionPane.ERROR_MESSAGE);
                      }
                      t = random.nextInt(words.size());
                      show1.setFont(new Font("宋体", Font.BOLD, 80));
                      show1.setText(words.get(t).wordStr);
                      show2.setText("");
                      words.get(t).wordStr += "*";
                  }
                  if (keyCode==KeyEvent.VK_S){
                      show2.setText(words.get(t).ChineseStr);
                      show2.setFont(new Font("宋体",Font.BOLD,40));
                  }
                  if (keyCode==KeyEvent.VK_A){
                      words.remove(words.get(t));
                      if (!words.isEmpty()) {
                          t = random.nextInt(words.size());
                          show1.setFont(new Font("宋体", Font.BOLD, 80));
                          show1.setText(words.get(t).wordStr);
                          show2.setText("");
                          words.get(t).wordStr += "*";
                      }
                      else {
                          show1.setText("NB!学完了!!");
                      }
                  }
                  if (keyCode==KeyEvent.VK_W){
                      URI uri= null;
                      String string=words.get(t).wordStr.replace("*","");
                      String s="https://fanyi.baidu.com/?aldtype=16047#en/zh/"+string;
                      try {
                          uri = new URI(s);
                          Desktop.getDesktop().browse(uri);
                      } catch (URISyntaxException | IOException uriSyntaxException) {
                          uriSyntaxException.printStackTrace();
                      }
                  }
              }
          });


            button1.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {


                          if (jComboBox.getSelectedItem().toString().equals("请选择单词组")) {
                              JOptionPane.showMessageDialog(null, "您还没有选择单词组", "警告", JOptionPane.ERROR_MESSAGE);
                          }
                          t = random.nextInt(words.size());
                          show1.setFont(new Font("宋体", Font.BOLD, 80));
                          show1.setText(words.get(t).wordStr);
                          show2.setText("");
                          words.get(t).wordStr += "*";


                  }

            });
            button2.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                         show2.setText(words.get(t).ChineseStr);
                         show2.setFont(new Font("宋体",Font.BOLD,40));
                  }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                       words.remove(words.get(t));
                       if (!words.isEmpty()) {
                           t = random.nextInt(words.size());
                           show1.setFont(new Font("宋体", Font.BOLD, 80));
                           show1.setText(words.get(t).wordStr);
                           show2.setText("");
                           words.get(t).wordStr += "*";
                       }
                       else {
                             show1.setText("NB!学完了!!");
                       }
                }
            });
              button5.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      URI uri= null;
                      String string=words.get(t).wordStr.replace("*","");
                      String s="https://fanyi.baidu.com/?aldtype=16047#en/zh/"+string;
                      try {
                          uri = new URI(s);
                          Desktop.getDesktop().browse(uri);
                      } catch (URISyntaxException | IOException uriSyntaxException) {
                          uriSyntaxException.printStackTrace();
                      }

                  }
              });

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("单词助手");
            this.setIconImage(ImageIO.read(new File("src\\Icon\\学习中心.png")));
            setBounds((ScreenTool.getScreenWidth()-1600)/2,(ScreenTool.getScreenHeight()-1000)/2,1600,1000);
            setVisible(true);
      }
      public static void main(String[] args) throws IOException {
            new Practice();
      }
}
