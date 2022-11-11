package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testdao extends Basedao{
    public void test(){
        try {
            this.openConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
