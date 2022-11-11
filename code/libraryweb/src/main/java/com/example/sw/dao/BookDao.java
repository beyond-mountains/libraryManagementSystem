package com.example.sw.dao;

import com.example.sw.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Groza
 * @ClassName BookDao
 * @Descreaption
 * @Date 18:01 2022/6/27
 */
public class BookDao {
    public List<Book> getSampleByName(String key) {
        Connection conn = ConnectionGetter.get();
        String sql = "select * from libmanager.book where name like ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer();
        List<Book> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+key+"%");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getString(1));
                book.setName(rs.getString(2));
                book.setCategory(rs.getString(3));
                book.setAuthor(rs.getString(4));
                book.setPlace(rs.getString(5));
                book.setPhoto(rs.getString(6));
                book.setState(rs.getString(7));
                list.add(book);
            }
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
        return list;
    }
    public List<Book> getSampleByAuthor(String key) {
        Connection conn = ConnectionGetter.get();
        String sql = "select * from libmanager.book where author like ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer();
        List<Book> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+key+"%");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setName(rs.getString("name"));
                book.setCategory(rs.getString("category"));
                book.setAuthor(rs.getString("author"));
                book.setPlace(rs.getString("place"));
                book.setPhoto(rs.getString("photo"));
                book.setState(rs.getString("state"));
                list.add(book);
            }
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
        return list;
    }
}
