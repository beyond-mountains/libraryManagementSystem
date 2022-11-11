package GUI;

import Utils.FileUtils;
import dao.ManagerDao;
import entity.Book;
import entity.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BookFileGUI extends JFrame implements ActionListener {
    private Manager manager;

    private JPanel contentPane;//背景板

    private JTextArea text;//文本区

    private JButton submit;//提交按钮
    private JButton open;//打开按钮

    private JFileChooser fileChooser=new JFileChooser();//文件选择器
    private FileUtils fileUtils=new FileUtils();//文件工具包对象

    public BookFileGUI(){
        setResizable(false);
        setTitle("添加书籍");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(600, 200, 800, 700);
        //背景板
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//panel与边界的距离为5
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        Font f = new Font("楷体", Font.BOLD,26);

        text = new JTextArea();
        text.setFont(f);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        text.setBounds(10,10,760,550);
        contentPane.add(text);

        //提交
        submit = new JButton("提交");
        submit.setFont(f);
        submit.setBounds(430,580,150,40);
        contentPane.add(submit);

        submit.addActionListener(this);

        //打开文件
        open = new JButton("打开");
        open.setFont(f);
        open.setBounds(190,580,150,40);
        contentPane.add(open);

        open.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit){
            String[] lines = text.getText().split("\n");
            int flag = 0;
            for(String line : lines){
                String info[] = line.split(",");
                Book book = new Book();
                book.setId(info[0]);
                book.setName(info[1]);
                book.setCategory(info[2]);
                book.setAuthor(info[3]);
                book.setPlace(info[4]);
                book.setPhoto(info[5]);

                ManagerDao managerDao = new ManagerDao();
                flag = managerDao.addBook(manager,book);
                System.out.println(flag);
            }
            if(flag == 1){
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>添加成功", "提示",
                        JOptionPane.INFORMATION_MESSAGE,null);
            }else{
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>添加失败", "提示",
                        JOptionPane.WARNING_MESSAGE,null);
            }

        }else if(e.getSource() == open){
            this.readFile();
        }
    }

    private void readFile(){
        File selectedFile = selectFile();
        if (selectedFile == null || selectedFile.isDirectory()) {
            System.out.println("请选择文件...");

        } else {
            fileUtils.readFileByLine(text, selectedFile);
        }
    }
    private File selectFile() {
        fileChooser.showDialog(new JLabel(), "打开书籍信息文件");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File selectedFile = fileChooser.getSelectedFile();
        fileChooser.setCurrentDirectory(selectedFile);
        return selectedFile;
    }

    public void setManager(Manager manager){
        this.manager = manager;
    }
}
