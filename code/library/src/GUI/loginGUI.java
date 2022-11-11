package GUI;

import dao.ManagerDao;
import dao.StudentDao;
import entity.Manager;
import entity.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginGUI extends JFrame implements ActionListener {

    private JPanel contentPane;//背景板
    private JTabbedPane tabbedPane;//填写用户名，密码区
    //学生
    private JTextField stuText = null;
    private JTextField stuPwdText = null;
    private JButton stuLoginBut = null;
    //管理员
    private JTextField managerText = null;
    private JTextField manPwdText = null;
    private JButton manLoginBut = null;

    ManagerDao managerDao = new ManagerDao();
    StudentDao studentDao = new StudentDao();

    public loginGUI(){
        setResizable(false);
        setTitle("图书馆登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 900, 700);//设置窗口坐标(500,200)、大小(900,700)
        //背景板
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//panel与边界的距离为5
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        //上侧图片
        JPanel photoPanel = new JPanel();
        ImageIcon icon = new ImageIcon("src/resources/bg.jpg");
        JLabel titleLabel = new JLabel();
        photoPanel.setBounds(0,0,882,380);
        icon.setImage(icon.getImage().getScaledInstance(photoPanel.getWidth(),photoPanel.getHeight(),Image.SCALE_DEFAULT));//设置图像大小
        titleLabel.setIcon(icon);
        photoPanel.add(titleLabel);
        contentPane.add(photoPanel);
        //下侧选项卡
        Font f = new Font("楷体", Font.BOLD+ Font.ITALIC,24);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(BorderFactory.createLineBorder(Color.white));
        tabbedPane.setBounds(0, 380, 882, 272);
        tabbedPane.setFont(f);
        contentPane.add(tabbedPane);

        //学生选项
        Font f2 = new Font("楷体", Font.BOLD,26);
        JPanel loginPaneStu = new JPanel();//登录面板
        loginPaneStu.setBackground(Color.white);
        loginPaneStu.setLayout(null);
        tabbedPane.addTab("  学生  ", null, loginPaneStu, null);//选项卡
        //填写学号
        JLabel stuLabel = new JLabel("学号:");
        stuLabel.setFont(f2);
        stuLabel.setBounds(260,40,70,30);
        loginPaneStu.add(stuLabel);

        stuText = new JTextField();
        stuText.setFont(f2);
        stuText.setBounds(350,40,240,35);
        loginPaneStu.add(stuText);
        //填写密码
        JLabel stuPwdLabel = new JLabel("密码:");
        stuPwdLabel.setFont(f2);
        stuPwdLabel.setBounds(260,100,70,30);
        loginPaneStu.add(stuPwdLabel);

        stuPwdText = new JPasswordField();
        //stuPwdText.setFont(f2);
        stuPwdText.setBounds(350,100,240,35);
        loginPaneStu.add(stuPwdText);
        //学生登录按钮
        stuLoginBut = new JButton("登录");
        stuLoginBut.setFont(f2);
        stuLoginBut.setBounds(380,155,150,40);
        loginPaneStu.add(stuLoginBut);

        stuLoginBut.addActionListener(this);

        //管理员选项
        JPanel loginPaneMan = new JPanel();//登录面板
        loginPaneMan.setBackground(Color.white);
        loginPaneMan.setLayout(null);
        tabbedPane.addTab("  管理员  ", null, loginPaneMan, null);//选项卡

        Font f3 = new Font("楷体", Font.BOLD,26);
        //填写用户名
        JLabel managerLabel = new JLabel("用户名:");
        managerLabel.setFont(f3);
        managerLabel.setBounds(235,40,100,30);
        loginPaneMan.add(managerLabel);

        managerText = new JTextField();
        managerText.setFont(f2);
        managerText.setBounds(350,40,240,35);
        loginPaneMan.add(managerText);
        //填写密码
        JLabel manPwdLabel = new JLabel("密码:");
        manPwdLabel.setFont(f2);
        manPwdLabel.setBounds(260,100,70,30);
        loginPaneMan.add(manPwdLabel);

        manPwdText = new JPasswordField();
        //manPwdText.setFont(f2);
        manPwdText.setBounds(350,100,240,35);
        loginPaneMan.add(manPwdText);
        //登录按钮
        manLoginBut = new JButton("登录");
        manLoginBut.setFont(f2);
        manLoginBut.setBounds(380,155,150,40);
        loginPaneMan.add(manLoginBut);

        manLoginBut.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == stuLoginBut){
            String id = stuText.getText();
            String pwd = stuPwdText.getText();
            Student stu = studentDao.login(id,pwd);
            if(stu != null){
                StuGUI stuGUI = new StuGUI();
                stuGUI.setStudent(stu);
                stuGUI.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>请检查用户名或密码", "提示",
                        JOptionPane.WARNING_MESSAGE,null);
            }
        }
        else if(e.getSource() == manLoginBut){
            String id  = managerText.getText();
            String pwd = manPwdText.getText();
            Manager man = managerDao.login(id, pwd);

            if(man != null){
                ManGUI manGUI = new ManGUI();
                manGUI.setManager(man);
                manGUI.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>请检查用户名或密码", "提示",
                        JOptionPane.WARNING_MESSAGE,null);
            }

        }

    }
}
