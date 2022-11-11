package dao;

import java.sql.*;

public class Basedao {
    protected Connection conn = null;

    protected void openConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@192.168.56.1:1521:orcl";
            String userid = "libvisitor";
            String passwd = "123456";
            System.out.println("arrangeed");
            conn = DriverManager.getConnection(url, userid, passwd);
            System.out.println("connected");
        }
        catch(Exception e){
            throw e;
        }
    }
    public void beginTransaction() throws Exception{
        this.openConnection();
        conn.setAutoCommit(false);
    }

    public void rollback() throws Exception{
        if(conn != null){
            conn.rollback();
        }
    }
    public void commit() throws Exception{
        if(conn != null){
            conn.commit();
        }
    }
    public void closeResource() throws Exception{
        if(conn != null){
            conn.close();
        }
    }

}
