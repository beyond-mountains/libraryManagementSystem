package GUI;

import dao.ManagerDao;
import entity.Manager;
import entity.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStuGUI extends JFrame implements ActionListener {

    private Manager manager;

    private JPanel contentPane;//背景板

    private JTextField id;
    private JTextField name;
    private JTextField pwd;
    private JTextField institute;
    private JTextField email;

    private JButton submit;

    public AddStuGUI(){
        setResizable(false);
        setTitle("添加学生");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700, 200, 600, 700);
        //背景板
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//panel与边界的距离为5
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        Font f2 = new Font("楷体", Font.BOLD,26);

        //学号
        JLabel idLabel = new JLabel("学号:");
        idLabel.setFont(f2);
        idLabel.setBounds(130,40,70,30);
        contentPane.add(idLabel);

        id = new JTextField();
        id.setFont(f2);
        id.setBounds(230,40,240,35);
        contentPane.add(id);

        //姓名
        JLabel nameLabel = new JLabel("姓名:");
        nameLabel.setFont(f2);
        nameLabel.setBounds(130,110,70,30);
        contentPane.add(nameLabel);

        name = new JTextField();
        name.setFont(f2);
        name.setBounds(230,110,240,35);
        contentPane.add(name);

        //密码
        JLabel pwdLabel = new JLabel("密码:");
        pwdLabel.setFont(f2);
        pwdLabel.setBounds(130,180,70,30);
        contentPane.add(pwdLabel);

        pwd = new JTextField();
        pwd.setFont(f2);
        pwd.setBounds(230,180,240,35);
        contentPane.add(pwd);

        //学院
        JLabel insLabel = new JLabel("学院:");
        insLabel.setFont(f2);
        insLabel.setBounds(130,250,70,30);
        contentPane.add(insLabel);

        institute = new JTextField();
        institute.setFont(f2);
        institute.setBounds(230,250,240,35);
        contentPane.add(institute);

        //邮箱
        JLabel emailLabel = new JLabel("邮箱:");
        emailLabel.setFont(f2);
        emailLabel.setBounds(130,320,70,30);
        contentPane.add(emailLabel);

        email = new JTextField();
        email.setFont(f2);
        email.setBounds(230,320,240,35);
        contentPane.add(email);

        //提交
        submit = new JButton("提交");
        submit.setFont(f2);
        submit.setBounds(240,500,150,40);
        contentPane.add(submit);

        submit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit) {
            Student student = new Student();
            student.setId(id.getText());
            student.setName(name.getText());
            student.setPwd(pwd.getText());
            student.setInstitute(institute.getText());
            student.setEmail(email.getText());

            ManagerDao managerDao = new ManagerDao();
            int flag = managerDao.addstudent(manager, student);

            if(flag == 1){
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>添加成功", "提示",
                        JOptionPane.INFORMATION_MESSAGE,null);

            }else {
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>添加失败", "提示",
                        JOptionPane.WARNING_MESSAGE, null);
            }
        }
    }

    public void setManager(Manager manager){
        this.manager = manager;
    }
}
