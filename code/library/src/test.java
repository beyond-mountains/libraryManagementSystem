import GUI.ManGUI;
import GUI.StuGUI;
import GUI.loginGUI;
import dao.ManagerDao;
import dao.StudentDao;
import dao.testdao;
import entity.Book;
import entity.Manager;
import entity.Student;

public class test {
    public static void main(String[] args){
        /*
        测试数据库连接
         */
        testdao dao = new testdao();
        dao.test();
        /*
        测试管理员登录
         */
        ManagerDao mdao = new ManagerDao();
        Manager manager = mdao.login("manager1", "123456");
        /*
        测试 今日借书
         */
        //mdao.todayBor();
        /*
        测试 未还书信息列表
         */
        //mdao.overdue();
        /*
        测试添加学生
         */
//        Student student = new Student();
//        student.setId("55199002");
//        student.setName("尔尔");
//        student.setPwd("123456");
//        student.setEmail("erer@jlu.edu.cn");
//        student.setInstitute("计算机科学与技术学院");
//
//        int teststu = 7;
//        teststu = mdao.addstudent(manager, student);
//        System.out.println("测试添加学生返回:" + teststu);
        /*
        测试添加图书，并生成操作记录
         */
//        Book book = new Book();
//        book.setId("jlu003");
//        book.setName("世界通史");
//        book.setCategory("历史");
//        book.setAuthor("无名氏");
//        book.setPlace("中心馆3楼");
//        book.setPhoto("./image/image003");
//        mdao.addBook(manager, book);

//        System.out.println("删除图书代码:" + mdao.deletebook(manager, "jlu002"));

//        /*
//        测试学生登录
//         */
//        StudentDao sdao = new StudentDao();
//        Student stu = sdao.login("55199001","123456");
//        /*
//        测试学生借书
//         */
//        sdao.borrow(stu, "jlu001");
//        /*
//        测试学生还书
//         */
//        System.out.println(sdao.returnbook(stu,"jlu001"));

        loginGUI gui = new loginGUI();
        gui.setVisible(true);

//        StuGUI sgui = new StuGUI();
//        sgui.setVisible(true);

//        ManGUI mgui = new ManGUI();
//        mgui.setVisible(true);
    }
}
