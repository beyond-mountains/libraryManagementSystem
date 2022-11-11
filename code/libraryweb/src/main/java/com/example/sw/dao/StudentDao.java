package com.example.sw.dao;

import com.example.sw.entity.Student;

import java.sql.*;

/**
 * @author Groza
 * @ClassName StudentDao
 * @Descreaption
 * @Date 19:04 2022/6/27
 */
public class StudentDao {
    public Student findUserWithAccountAndPassword(String account, String password){
        Student stu = null;
        Connection conn = ConnectionGetter.get();
        String sql = "select * from libmanager.student b " +
                "where b.id = '" +account + "' and b.pwd = '"+ password +"'";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("找到用户");
                stu = new Student();
                stu.setId(rs.getString(1));
                stu.setName(rs.getString(2));
                stu.setPwd(rs.getString(3));
                stu.setInstitute(rs.getString(4));
                stu.setEmail(rs.getString(5));
            }
            return stu;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return stu;
    }

    public void test(){
        System.out.println("test");
        Connection conn = ConnectionGetter.get();

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT *" + "FROM LIBMANAGER.book "
            );
            while(rs.next()){
                System.out.println("ID = " + rs.getString(1) + " " +
                        "name = " + rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
