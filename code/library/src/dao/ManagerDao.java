package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.*;

public class ManagerDao extends Basedao{

    /*
    管理员登录
    输入 管理员id 密码
    返回 管理员对象
     */
    public Manager login(String id, String pwd){

        Manager manager = null;

        String sql = "select name from libmanager.manager " +
                "where id = '" + id + "' and pwd = '" + pwd + "'";

        try {
            this.openConnection();
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(sql);

            if(rs != null){

                while(rs.next()) {
                    manager = new Manager();
                    manager.setId(id);
                    manager.setPwd(pwd);
                    manager.setName(rs.getString("name"));
                }
                //System.out.println("name:" + manager.getName());
            }
            st.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return manager;
    }

    /*
    添加图书 并生成maintain操作记录
    参数 管理员对象 图书对象
    返回 1成功 0失败
     */
    public int addBook(Manager manager, Book book){
        try{
            this.openConnection();
            conn.setAutoCommit(false);
            //插入图书
            String addsql = "insert into libmanager.book" +
                    "(id, name, category, author, place, photo, state) " +
                    "values(?, ?, ?, ? ,?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(addsql);

            ps.setString(1, book.getId());
            ps.setString(2, book.getName());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getAuthor());
            ps.setString(5, book.getPlace());
            ps.setString(6, book.getPhoto());
            ps.setString(7, "在库");

            ps.executeUpdate();
            //生成操作记录
            Maintain maintain = new Maintain();
            maintain.setManid(manager.getId());
            maintain.setBookid(book.getId());
            maintain.setMaintaintime();
            maintain.setMaintainbook("入库");

            String maintainsql = "insert into libmanager.maintain" +
                    "(manid, bookid, maintaintime, maintainbook) " +
                    "values(?, ?, ?, ?)";

            PreparedStatement ps2 = conn.prepareStatement(maintainsql);

            ps2.setString(1, maintain.getManid());
            ps2.setString(2, maintain.getBookid());
            ps2.setString(3, maintain.getMaintaintime());
            ps2.setString(4, maintain.getMaintainbook());

            ps2.executeUpdate();

            conn.commit();

            return 1;
        }catch(Exception e){
            System.out.println("添加图书出错");
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /*
    删除图书 并生成maintain记录
    参数 管理员对象 图书id
    返回 1成功 0失败
     */
    public int deletebook(Manager manager, String bookid){
        //String bookid = String.format("%-20s",bookidtmp);
        try{
            this.openConnection();

            //生成记录
            Maintain maintain = new Maintain();
            maintain.setManid(manager.getId());
            maintain.setBookid(bookid);
            maintain.setMaintaintime();
            maintain.setMaintainbook("删除");

            String maintainsql = "insert into libmanager.maintain" +
                    "(manid, bookid, maintaintime, maintainbook) " +
                    "values(?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(maintainsql);

            ps.setString(1, maintain.getManid());
            ps.setString(2, maintain.getBookid());
            ps.setString(3, maintain.getMaintaintime());
            ps.setString(4, maintain.getMaintainbook());

            ps.executeUpdate();
            System.out.println("生成记录");
            //将图书标记为删除
            String delsql = "update libmanager.book " +
                    "set state = '删除' " +
                    "where id = '" + bookid + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(delsql);

        }catch(Exception e){
            System.out.println("删除图书时出现错误");
            e.printStackTrace();
            return 0;
        }
        try {
            conn.close();
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /*
    添加学生信息 并生成operate记录
    参数 管理员对象 学生对象
    返回 1成功 0失败
     */
    public int addstudent(Manager manager, Student student){
        try{
            this.openConnection();
            conn.setAutoCommit(false);

            //插入学生
            String addstu= "insert into libmanager.student" +
                    "(id, name, pwd, institute, email) " +
                    "values(?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(addstu);

            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getPwd());
            ps.setString(4, student.getInstitute());
            ps.setString(5, student.getEmail());

            ps.executeUpdate();
            //生成记录
            Operate operate = new Operate();

            operate.setStuid(student.getId());
            operate.setManid(manager.getId());
            operate.setOperatetime();
            operate.setOperatestu("入库");

            String addoperate = "insert into libmanager.operate" +
                    "(stuid, manid, operatetime, operatestu) " +
                    "values(?, ?, ?, ?)";

            PreparedStatement ps2 = conn.prepareStatement(addoperate);

            ps2.setString(1, operate.getStuid());
            ps2.setString(2, operate.getManid());
            ps2.setString(3, operate.getOperatetime());
            ps2.setString(4, operate.getOperatestu());

            ps2.executeUpdate();

            conn.commit();

            conn.close();

            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    /*
    统计今日借出图书
    返回 book的list
     */
    public List<Book> todayBor(){
        List<Book> todaylist = new ArrayList<>();
        //获取今日
        long now=System.currentTimeMillis();
        Date date=new Date();
        date.setTime(now);
        SimpleDateFormat datef=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = datef.format(date);

        String todaydate = time.substring(0,10);
        System.out.println(todaydate);

        String sql = "select id,name,category,author,place " +
                "from libmanager.book bo " +
                "where bo.id in " +
                "(select bookid from libmanager.borrow t where " +
                "t.lenddate like '"+ todaydate + "%' )";

        try {
            try {
                this.openConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs != null){
                Book book = null;
                while(rs.next()){
                    book  = new Book();
                    String id = rs.getString("id");
                    String nid = id.substring(0,8);
                    book.setId(nid);
                    //System.out.println("书的id:" + book.getId());
                    book.setAuthor(rs.getString("author"));
                    book.setName(rs.getString("name"));
                    book.setCategory(rs.getString("category"));
                    book.setPlace(rs.getString("place"));

                    todaylist.add(book);
                }

            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("返回今日借书测试：" +todaylist.get(0).getId()+" "+ todaylist.get(0).getName());
        //System.out.println("返回今日借书测试：" + todaylist.get(1).getName());
        return todaylist;
    }


    /*
    统计逾期未还的borrow
    返回 student的list
     */
    public List<Student> overdue(){
        List<Student> overlist = new ArrayList<>();
        //当前时间time
        long now=System.currentTimeMillis();
        Date date=new Date();
        date.setTime(now);
        SimpleDateFormat datef=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = datef.format(date);
        System.out.println("time:"+time);
        try{
            this.openConnection();

            String sql = "select t.id,t.name,t.institute,t.email,b.name " +
                    "from LIBMANAGER.student t, LIBMANAGER.book b " +
                    "where (t.id, b.id) in" +
                    "( " +
                    "select m.stuid, m.bookid from LIBMANAGER.borrow m " +
                    "where m.returndate is null and " +
                    "to_date(m.duedate,'yyyy/mm/dd hh24:mi:ss') " +
                    "< to_date('" + time + "','yyyy/mm/dd hh24:mi:ss') " +
                    ")";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs != null){
                Student stu = null;
                while(rs.next()){
                    stu = new Student();
                    //System.out.println("未还书非空");
                    stu.setId(rs.getString(1));
                    stu.setName(rs.getString(2));
                    stu.setInstitute(rs.getString(3));
                    stu.setEmail(rs.getString(4));
                    //这里传入的是书籍名字
                    stu.setPwd(rs.getString(5));

                    overlist.add(stu);
                    System.out.println("返回未还书测试：" + overlist.get(0).getName());
                }

            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return overlist;
    }
}
