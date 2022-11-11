package dao;

import entity.Borrow;
import entity.Student;
import jdk.internal.org.objectweb.asm.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class StudentDao extends Basedao{

    /*
    学生登录
    输入 学生id  密码
    返回 学生对象
     */
    public Student login(String id, String pwd){

        Student stu = null;

        String sql = "select name,institute from libmanager.student" +
                " where id = '" + id + "' and pwd = '" + pwd + "'";

        try {
            this.openConnection();
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(sql);

            if(rs != null){
                while(rs.next()){
                    stu = new Student();
                    stu.setId(id);
                    stu.setName(rs.getString("name"));
                    stu.setPwd(pwd);
                    stu.setInstitute(rs.getString("institute"));
                }
            }
            st.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println("name:" + stu.getName());
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stu;
    }

    /*
    学生借书
    函数完成     1.insert borrow  2.update book 借出
    输入        1.学生对象 2.图书编号
    返回        借阅成功返回1   借阅失败返回0  提示检查输入的书籍id
     */
    public int borrow(Student stu, String bookid){
        try {
            //手动提交事务
            this.openConnection();
            conn.setAutoCommit(false);

            //验证图书是否为在库的状态
            String selectsql = "select state from libmanager.book " +
                    " where id = '" + bookid + "'";
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(selectsql);
            if(rs != null){
                String state = null;
                while(rs.next()){
                    state = rs.getString("state");
                }
                if(state == null){
                    throw new Exception();
                }
                if(state.equals("借出")){
                    System.out.println("图书已经借出");
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }

            //update book
            String updatesql = "update libmanager.book " +
                    "set state = '借出' " +
                    "where id = '" + bookid + "'";
            Statement st = conn.createStatement();
            st.executeUpdate(updatesql);

            //insert borrow
            Borrow bor = new Borrow();
            bor.setStuid(stu.getId());
            bor.setBookid(bookid);
            Date date = bor.setLenddate();
            bor.setDuedate(date);
            String borrowsql = "insert into libmanager.BORROW" +
                    "(stuid, bookid, lenddate, duedate, returndate)" +
                    " values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(borrowsql);
            ps.setString(1,bor.getStuid());
            ps.setString(2,bor.getBookid());
            ps.setString(3,bor.getLenddate());
            ps.setString(4,bor.getDuedate());
            ps.setNull(5, Type.CHAR);

            ps.executeUpdate();

            conn.commit();

        }catch(Exception e){
            try{
                conn.rollback();
                return 0;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }

    /*
    学生还书
    函数完成   1.将book的状态改为'在库'  2.borrow条目添加还书日期，判断是否逾期
    输入      1.学生对象 2.图书编号
    返回      还书成功返回1   还书逾期返回2   还书失败返回0
     */
    public int returnbook(Student stu, String bookid){
        try{
            //手动提交事务
            this.openConnection();
            conn.setAutoCommit(false);

            //验证图书是否为在库的状态
            String selectsql = "select state from libmanager.book " +
                    " where id = '" + bookid + "'";
            Statement st0 = conn.createStatement();
            ResultSet rs = st0.executeQuery(selectsql);
            if(rs != null){
                String state = null;
                while(rs.next()){
                    state = rs.getString("state");
                }
                if(state == null){
                    throw new Exception();
                }
                if(state.equals("在库")){
                    System.out.println("图书在库，请检查编号是否输入正确");
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }

            //update book
            String updatesql = "update libmanager.book " +
                    "set state = '在库' " +
                    "where id = '" + bookid + "'";
            Statement st1 = conn.createStatement();
            st1.executeUpdate(updatesql);

            //select borrow
            String selectbor = "select duedate,ID from libmanager.borrow " +
                    "where stuid = '" + stu.getId() + "' and bookid = '" +
                    bookid + "' and returndate is null";
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(selectbor);
            if(rs2 != null){
                Borrow bor = new Borrow();
                String borid = null;
                while(rs2.next()){
                    bor.setDuedate(rs2.getString("duedate"));
                    borid = rs2.getString("id");
                }
                bor.setReturndate();
                System.out.println("还书时检查到borrow");
                System.out.println(bor.getDuedate());
                System.out.println(bor.getReturndate());
                //更新还书时间
                String updatebor = "update libmanager.borrow " +
                        "set returndate = '" + bor.getReturndate() + "' " +
                        "where id = '" + borid + "'";
                Statement st3 = conn.createStatement();
                st3.executeUpdate(updatebor);
                conn.commit();
                //逾期的标志，0是没有逾期
                int flag = 0;
                flag = bor.overdue();

                if(flag == 0) {
                    //没有超期
                    return 1;
                }else if(flag == 1){
                    //还书超期
                    return 2;
                }
            }else{
                System.out.println("还书时查询borrow失败");
                throw new Exception();
            }

        }catch(Exception e){
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
}
