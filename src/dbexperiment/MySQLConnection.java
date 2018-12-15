/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbexperiment;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class MySQLConnection {
    static Connection conn = null;
    static int count=0;
    public static String passw,usr;
    public static String backup;
    MySQLConnection(String reg)
    {
       
        try
        {   
            if(count==1)
            {
                
                passw=reg;
                usr = Password.packup; 
            }
            else
            {
                usr=reg;
                passw=reg;
            }
            count++;
            String CONN_STRING="jdbc:mysql://172.31.100.41:3306/"+"db"+usr;
            conn = DriverManager.getConnection(CONN_STRING,usr,passw);//Connection Established between netbeans and SQL
            System.out.println("Connected Database");                          //Database connection Successful message
          //  PreparedStatement ptmt = conn.prepareStatement("show tables");
          //  ResultSet rs = ptmt.executeQuery();
          count=0;
          if(DBWorking.ref==0)
          {
            DBWorking dbw = new DBWorking();
            dbw.setVisible(true);
          }
          else
          {
              DatabaseMetaData dbmd = MySQLConnection.conn.getMetaData();
              String[] types = {"TABLE"};
              ResultSet rs = dbmd.getTables(null,null, "%", types);
              DBWorking.ytable.setText("");
              while(rs.next())
              {
                 DBWorking.ytable.append(rs.getString("TABLE_NAME"));
                 DBWorking.ytable.append("\n");
            }
          }
        }
        catch(SQLException e)
        {
           
            if(count==1)
            {
                
                backup=reg;
                Password pass = new Password();
                pass.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Password mismatch OR Please check you LAN connection...please try again!!!");
                count=0;
            }
            System.out.println(e);
        }
        finally
        {
            try{
            conn.close();
            }
            catch(Exception e2){}
        }
    
    }
 /*   public static void main(String args[])
    {
        new MySQLConnection("2017CA21");
    }*/
}
