package com.example.sw.dao;

import com.example.sw.entity.Borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Groza
 * @ClassName BorrowDao
 * @Descreaption
 * @Date 18:36 2022/6/27
 */
public class BorrowDao {
    public List<Borrow> getBorrowByID(String id) {
        Connection conn = ConnectionGetter.get();
        String sql = "select * from libmanager.borrow where stuid = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer();
        List<Borrow> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Borrow borror = new Borrow();
                borror.setId(rs.getString("id"));
                borror.setStuid(rs.getString("stuid"));
                borror.setBookid(rs.getString("bookid"));
                borror.setLenddate(rs.getString("lenddate"));
                borror.setDuedate(rs.getString("duedate"));
                borror.setReturndate(rs.getString("returndate"));
                list.add(borror);
            }
            return list.size()== 0 ? null : list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs!=null){
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
        return null;
    }
    public int updateDelayDate(String id){
        System.out.println("开始更新啦");
        Connection conn = ConnectionGetter.get();
        String sql1 = "select * from libmanager.borrow where id = ?";
        String sql2 = "update libmanager.borrow set duedate = ? where id = ?";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer();
        List<Borrow> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            String dateStr = null;
            while(rs.next()) {

                dateStr = rs.getString("duedate");

            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date date = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 30);
            date = cal.getTime();
            String newTime = sdf.format(date);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, newTime);
            pstmt2.setString(2, id);
            return pstmt2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pstmt2!=null){
                try {
                    pstmt2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs!=null){
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
        return 0;
    }
}
