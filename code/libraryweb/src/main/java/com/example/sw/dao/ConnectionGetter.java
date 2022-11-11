package com.example.sw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Groza
 * @ClassName ConnectionGetter
 * @Descreaption
 * @Date 18:02 2022/6/27
 */
public class ConnectionGetter {
    private static final String url = "jdbc:oracle:thin:@192.168.56.1:1521:orcl";
    private static final String username = "libvisitor";
    private static final String password = "123456";
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection get(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}