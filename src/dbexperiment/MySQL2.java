/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbexperiment;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class MySQL2 {
    static Connection conn2 = null;
    static int count=0;
    static String passw,usr;
    public static String backup;
    MySQL2(String reg)
    {
        try
        {   
            if(count==1)
            {
                
                passw=reg;
                usr = Password2.packup; 
            }
            else
            {
                usr=reg;
                passw=reg;
            }
            count++;
          //  System.out.println(usr+" "+passw);
            String CONN_STRING="jdbc:mysql://172.31.100.41:3306/"+"db"+usr;
            conn2 = DriverManager.getConnection(CONN_STRING,usr,passw);//Connection Established between netbeans and SQL
            System.out.println("Connected Database123");                          //Database connection Successful message
          //  PreparedStatement ptmt = conn.prepareStatement("show tables");
          //  ResultSet rs = ptmt.executeQuery();
          count=0;
           try
            {
           DatabaseMetaData dbmd = conn2.getMetaData();
           String[] types = {"TABLE"};
           ResultSet rs = dbmd.getTables(null,null, "%", types);
           DBWorking.ftable.setText("");
           while(rs.next())
            {
               
             DBWorking.ftable.append(rs.getString("TABLE_NAME"));
             DBWorking.ftable.append("\n");
            }
            }
        catch(Exception e)
        {}
        finally
        {
            try
            {
              MySQLConnection.conn.close();
            }
            catch(Exception e)
            {}
        }
        }
        catch(SQLException e)
        {
           
            if(count==1)
            {
                
                backup=reg;
                Password2 pass2 = new Password2();
                pass2.setVisible(true);
            }
            else
            {
                
                JOptionPane.showMessageDialog(null, "Password mismatch...OR Please check you LAN connection..!!");
                count=0;
            }
            System.out.println(e);
        }
        finally
        {
            try{
            conn2.close();
            }
            catch(Exception e2){}
        }
    
        
    }
    
}
