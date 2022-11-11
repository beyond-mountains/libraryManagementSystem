package GUI;

import dao.ManagerDao;
import entity.Book;
import entity.Borrow;
import entity.Manager;
import entity.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ManGUI extends JFrame implements ActionListener {

    private Manager manager;//记录登录的用户

    private JPanel contentPane;//背景板

    private JScrollPane todayBorPane;//今日借出背景板
    private JScrollPane overduePane;//超期未还背景板

    private JTable todayBorTable;//今日借出表格
    private JTable overdueTable;//超期未还表格

    private JButton todayBorBut;//今日借出刷新按钮
    private JButton overdueBut;//超期未还刷新按钮

    private JButton addBook;//添加图书按钮
    private JButton addBookFromFile;//从文件中添加图书
    private JButton delBook;//删除图书按钮
    private JTextField delText;//删除图书
    private JButton addStu;//添加学生
    private JButton addStuFromFile;//从文件添加学生

    public ManGUI(){
        setResizable(false);
        setTitle("管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 150, 1300, 800);
        //背景板
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//panel与边界的距离为5
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        //今日借出
        Font f1 = new Font("楷体", Font.BOLD,24);
        JLabel todayBorLabel = new JLabel("今日借出:");
        todayBorLabel.setFont(f1);
        todayBorLabel.setBounds(40,20,200,20);
        contentPane.add(todayBorLabel);

        //显示今日借出图书
        this.todayBor();

        Font f3 = new Font("楷体", Font.BOLD,20);
        todayBorBut = new JButton("刷新");
        todayBorBut.setFont(f3);
        todayBorBut.setBounds(800,14,100,30);
        contentPane.add(todayBorBut);

        todayBorBut.addActionListener(this);


        //超期未还的同学
        JLabel overDueLabel = new JLabel("逾期未还:");
        overDueLabel.setFont(f1);
        overDueLabel.setBounds(40,390,200,20);
        contentPane.add(overDueLabel);

        //显示超期未还
        this.overDue();

        overdueBut = new JButton("刷新");
        overdueBut.setFont(f3);
        overdueBut.setBounds(630,384,100,30);
        contentPane.add(overdueBut);

        overdueBut.addActionListener(this);


        /**********************
         ******* 按钮  *********
         **********************/
        Font f2 = new Font("楷体", Font.BOLD,24);
        //添加图书按钮
        addBook = new JButton("添加图书");
        addBook.setFont(f2);
        addBook.setBounds(1030,240,150,40);
        contentPane.add(addBook);

        addBook.addActionListener(this);

        //从文件添加图书
        addBookFromFile = new JButton("从文件中导入图书");
        addBookFromFile.setFont(f2);
        addBookFromFile.setBounds(920,420,260,40);
        contentPane.add(addBookFromFile);

        addBookFromFile.addActionListener(this);

        //删除图书
        JLabel delLabel = new JLabel("需要删除的图书编号:");
        delLabel.setFont(f1);
        delLabel.setBounds(800,620,400,20);
        contentPane.add(delLabel);

        delText = new JTextField();
        delText.setFont(f1);
        delText.setBounds(800,650,300,50);
        contentPane.add(delText);

        delBook = new JButton("删除");
        delBook.setFont(f2);
        delBook.setBounds(1130,650,100,40);
        contentPane.add(delBook);

        delBook.addActionListener(this);

        //添加学生
        addStu = new JButton("添加学生");
        addStu.setFont(f2);
        addStu.setBounds(1030,320,150,40);
        contentPane.add(addStu);

        addStu.addActionListener(this);

        //从文件中添加学生信息
        addStuFromFile = new JButton("从文件中导入学生");
        addStuFromFile.setFont(f2);
        addStuFromFile.setBounds(920,500,260,40);
        contentPane.add(addStuFromFile);

        addStuFromFile.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == todayBorBut){
            //今日借书刷新
            this.todayBor();
        }else if(e.getSource() == overdueBut){
            //逾期未还刷新
            this.overDue();
        }else if(e.getSource() == addBook){
            //添加图书
            AddBookGUI addBookGUI = new AddBookGUI();
            addBookGUI.setManager(manager);
            addBookGUI.setVisible(true);
        }else if(e.getSource() == addBookFromFile){
            //从文件中添加图书
            BookFileGUI bookFileGUI = new BookFileGUI();
            bookFileGUI.setManager(manager);
            bookFileGUI.setVisible(true);

        }else if(e.getSource() == delBook){
            //删除图书
            String id = delText.getText();
            ManagerDao managerDao = new ManagerDao();
            int flag = managerDao.deletebook(manager, id);

            if(flag == 1){
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>删除成功", "提示",
                        JOptionPane.INFORMATION_MESSAGE,null);
            }else{
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>请检查图书编号", "提示",
                        JOptionPane.WARNING_MESSAGE,null);
            }

        }else if(e.getSource() == addStu){
            //添加学生
            AddStuGUI addStuGUI = new AddStuGUI();
            addStuGUI.setManager(manager);
            addStuGUI.setVisible(true);
        }else if(e.getSource() == addStuFromFile){
            //从文件添加学生
            StuFileGUI stuFileGUI = new StuFileGUI();
            stuFileGUI.setManager(manager);
            stuFileGUI.setVisible(true);
        }

    }

    public void todayBor(){
        ManagerDao managerDao = new ManagerDao();
        List<Book> bookList = managerDao.todayBor();
        String[] label = {"编号","书名","类别","作者","地址"};
        int size = bookList.size();
        String[][] text = new String[size][5];
        //System.out.println(size);
        for(int i = 0; i < size; i++){
            text[i][0] = bookList.get(i).getId();
            text[i][1] = bookList.get(i).getName();
            text[i][2] = bookList.get(i).getCategory();
            text[i][3] = bookList.get(i).getAuthor();
            text[i][4] = bookList.get(i).getPlace();
        }
        Font ft = new Font("楷体", Font.PLAIN,25);
        todayBorTable = new JTable(text,label);
        todayBorTable.setFont(ft);
        todayBorTable.setRowHeight(29);

        todayBorPane = new JScrollPane(todayBorTable);
        todayBorPane.setBounds(40,45,880,315);
        todayBorPane.setBorder(BorderFactory.createLineBorder(Color.black));
        todayBorPane.setBackground(Color.white);
        contentPane.add(todayBorPane);

    }

    public void overDue(){
        ManagerDao managerDao = new ManagerDao();
        List<Student> stuList = managerDao.overdue();
        String[] label = {"书籍编号","学号","姓名","学院","邮箱"};
        int size = stuList.size();
        String[][] text = new String[size][5];

        for(int i = 0; i < size; i++){
            text[i][0] = stuList.get(i).getPwd();
            text[i][1] = stuList.get(i).getId();
            text[i][2] = stuList.get(i).getName();
            text[i][3] = stuList.get(i).getInstitute();
            text[i][4] = stuList.get(i).getEmail();
        }
        Font ft = new Font("楷体", Font.PLAIN,25);
        overdueTable  = new JTable(text,label);
        overdueTable.setFont(ft);
        overdueTable.setBackground(Color.white);
        overdueTable.setRowHeight(29);

        TableColumnModel tcm = overdueTable.getColumnModel();
        TableColumn tc = tcm.getColumn(4);
        tc.setPreferredWidth(200);

        overduePane = new JScrollPane(overdueTable);
        overduePane.setBounds(40,415,700,305);
        overduePane.setBorder(BorderFactory.createLineBorder(Color.black));
        overduePane.setBackground(Color.white);
        contentPane.add(overduePane);

    }

    public void setManager(Manager man){
        this.manager = man;
    }

    public Manager getManager(){
        return this.manager;
    }
}
