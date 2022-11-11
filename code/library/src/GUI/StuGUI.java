package GUI;

import dao.ManagerDao;
import dao.StudentDao;
import entity.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StuGUI extends JFrame implements ActionListener {

    private JPanel contentPane;//背景板
    private JTabbedPane tabbedPane;//借书还书区

    private JButton lendBut;//借书按钮
    private JButton returnBut;//还书按钮

    private JTextField lendText;//所借书的编号
    private JTextField returnText;//所还书的编号

    private Student student;//学生用户

    StudentDao studentDao;

    public StuGUI(){
        setResizable(false);
        setTitle("借书/还书");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 900, 700);//设置窗口坐标(500,200)、大小(900,700)
        //背景板
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//panel与边界的距离为5
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        //下侧选项卡
        Font f = new Font("楷体", Font.BOLD+ Font.ITALIC,34);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(BorderFactory.createLineBorder(Color.white));
        tabbedPane.setBounds(0, 80, 882, 472);
        tabbedPane.setFont(f);
        contentPane.add(tabbedPane);

        //借书
        JPanel lendPane = new JPanel();//借书面板
        lendPane.setBackground(Color.white);
        lendPane.setLayout(null);
        tabbedPane.addTab("  借书  ", null, lendPane, null);//选项卡

        Font f2 = new Font("楷体", Font.BOLD,30);
        //标语
        Font f3 = new Font("楷体", Font.ITALIC,20);
        JLabel senLabel = new JLabel("问渠那得清如许？为有源头活水来。");
        senLabel.setFont(f3);
        senLabel.setBounds(282,70,500,30);
        lendPane.add(senLabel);
        //填写借书编号
        JLabel lendLabel = new JLabel("书籍编号:");
        lendLabel.setFont(f2);
        lendLabel.setBounds(230,160,150,30);
        lendPane.add(lendLabel);

        lendText = new JTextField();
        lendText.setFont(f2);
        lendText.setBounds(400,155,260,50);
        lendPane.add(lendText);

        //借书按钮
        lendBut = new JButton("借书");
        lendBut.setFont(f2);
        lendBut.setBounds(370,280,150,40);
        lendPane.add(lendBut);

        lendBut.addActionListener(this);

        //还书
        JPanel returnPane = new JPanel();//还书面板
        returnPane.setBackground(Color.white);
        returnPane.setLayout(null);
        tabbedPane.addTab("  还书  ", null, returnPane, null);//选项卡

        //标语
        Font f4 = new Font("楷体", Font.ITALIC,20);
        JLabel senLabel2 = new JLabel("知识，传递");
        senLabel2.setFont(f4);
        senLabel2.setBounds(382,70,500,30);
        returnPane.add(senLabel2);
        //填写还书编号
        JLabel returnLabel = new JLabel("要还的书:");
        returnLabel.setFont(f2);
        returnLabel.setBounds(230,160,150,30);
        returnPane.add(returnLabel);

        returnText = new JTextField();
        returnText.setFont(f2);
        returnText.setBounds(400,155,260,50);
        returnPane.add(returnText);

        //还书按钮
        returnBut = new JButton("还书");
        returnBut.setFont(f2);
        returnBut.setBounds(370,280,150,40);
        returnPane.add(returnBut);

        returnBut.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == lendBut){
            studentDao = new StudentDao();
            String id = lendText.getText();
            if(studentDao.borrow(student,id) == 1){
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>借阅成功", "提示",
                        JOptionPane.INFORMATION_MESSAGE,null);
            }else{
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>请检查图书编号", "提示",
                        JOptionPane.WARNING_MESSAGE,null);
            }
        }else if(e.getSource() == returnBut){
            studentDao = new StudentDao();
            String id = returnText.getText();
            if(studentDao.returnbook(student, id) == 1){
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>还书成功", "提示",
                        JOptionPane.INFORMATION_MESSAGE,null);
            }else{
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>请检查图书编号", "提示",
                        JOptionPane.WARNING_MESSAGE,null);
            }
        }
    }

    public void setStudent(Student stu){
        this.student = stu;
    }

    public Student getStudent(){
        return this.student;
    }

}
