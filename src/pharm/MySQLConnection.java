/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharm;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author asimz
 */
public class MySQLConnection {
    Connection con = null;

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
       //     JOptionPane.showMessageDialog(null, "Driver command pass");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pharma", "root", "");
       //     JOptionPane.showMessageDialog(null, "DB Connected");
            return con;

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Exception occured during DB connection");
        //    JOptionPane.showMessageDialog(null, e);
            
            return null;
        }
        finally{
         //   con.close();
    //    JOptionPane.showMessageDialog(null, "MYSQLConnection class Finally block run");
        }
        
    
    }
}
