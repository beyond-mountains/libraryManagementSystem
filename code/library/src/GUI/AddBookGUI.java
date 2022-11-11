package GUI;

import dao.ManagerDao;
import entity.Book;
import entity.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookGUI extends JFrame implements ActionListener {

    Manager manager = new Manager();

    private JPanel contentPane;//背景板

    private JTextField id;
    private JTextField name;
    private JTextField category;
    private JTextField author;
    private JTextField place;
    private JTextField photo;

    private JButton submit;

    public AddBookGUI(){
        setResizable(false);
        setTitle("添加书籍");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700, 200, 600, 700);
        //背景板
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//panel与边界的距离为5
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        Font f2 = new Font("楷体", Font.BOLD,26);
        //编号
        JLabel idLabel = new JLabel("编号:");
        idLabel.setFont(f2);
        idLabel.setBounds(130,40,70,30);
        contentPane.add(idLabel);

        id = new JTextField();
        id.setFont(f2);
        id.setBounds(230,40,240,35);
        contentPane.add(id);

        //书名
        JLabel nameLabel = new JLabel("书名:");
        nameLabel.setFont(f2);
        nameLabel.setBounds(130,110,70,30);
        contentPane.add(nameLabel);

        name = new JTextField();
        name.setFont(f2);
        name.setBounds(230,110,240,35);
        contentPane.add(name);

        //类别
        JLabel cateLabel = new JLabel("类别:");
        cateLabel.setFont(f2);
        cateLabel.setBounds(130,180,70,30);
        contentPane.add(cateLabel);

        category = new JTextField();
        category.setFont(f2);
        category.setBounds(230,180,240,35);
        contentPane.add(category);

        //作者
        JLabel authorLabel = new JLabel("作者:");
        authorLabel.setFont(f2);
        authorLabel.setBounds(130,250,70,30);
        contentPane.add(authorLabel);

        author = new JTextField();
        author.setFont(f2);
        author.setBounds(230,250,240,35);
        contentPane.add(author);

        //地点
        JLabel placeLabel = new JLabel("地点:");
        placeLabel.setFont(f2);
        placeLabel.setBounds(130,320,70,30);
        contentPane.add(placeLabel);

        place = new JTextField();
        place.setFont(f2);
        place.setBounds(230,320,240,35);
        contentPane.add(place);

        //照片
        JLabel photoLabel = new JLabel("照片:");
        photoLabel.setFont(f2);
        photoLabel.setBounds(130,400,70,30);
        contentPane.add(photoLabel);

        photo = new JTextField();
        photo.setFont(f2);
        photo.setBounds(230,400,240,35);
        contentPane.add(photo);

        //提交
        submit = new JButton("提交");
        submit.setFont(f2);
        submit.setBounds(240,500,150,40);
        contentPane.add(submit);

        submit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit){
            Book book = new Book();
            book.setId(id.getText());
            book.setName(name.getText());
            book.setCategory(category.getText());
            book.setAuthor(author.getText());
            book.setPlace((place.getText()));
            book.setPhoto(photo.getText());

            ManagerDao managerDao = new ManagerDao();
            int flag = managerDao.addBook(manager,book);
            if(flag == 1){
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>添加成功", "提示",
                        JOptionPane.INFORMATION_MESSAGE,null);

            }else{
                JOptionPane.showMessageDialog(null,
                        "<html><font size=12>添加失败", "提示",
                        JOptionPane.WARNING_MESSAGE,null);
            }
        }
    }

    void setManager(Manager manager){
        this.manager = manager;
    }
}
