/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbexperiment;

import static dbexperiment.MySQL2.count;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class MySQL3 {
    
    static Connection conn3 = null;
    static int count=0;
    static String passw,usr;
    public static String backup;
    static Statement stmt=null;
    MySQL3(String reg)
    {
        try
        { 
         
                usr=reg;
                passw=MySQL2.passw;
            
          //  System.out.println(usr+" "+passw);
            String CONN_STRING="jdbc:mysql://172.31.100.41:3306/"+"db"+usr;
            conn3 = DriverManager.getConnection(CONN_STRING,usr,passw);//Connection Established between netbeans and SQL
            System.out.println("Connected Database3");                          //Database connection Successful message
          //  PreparedStatement ptmt = conn.prepareStatement("show tables");
          //  ResultSet rs = ptmt.executeQuery();
            
           try
            {
        //   DatabaseMetaData dbmd = conn2.getMetaData();
         //  String[] types = {"TABLE"};
            //    DBWorking dbw = new DBWorking(); 
                String tnme = DBWorking.tname;
                String qr ="show create table "+tnme;
                System.out.println("TEST");
                System.out.println(usr+" "+tnme);
                stmt = conn3.createStatement();
             //   DBWorking.querytable.setText("WElcome");
                ResultSet rs = stmt.executeQuery(qr);
                while(rs.next())
               {
                  String res = rs.getString(2); 
                 DBWorking.querytable.setText(res);
               //   dbw.querytable.setText("WEl");
                 // DBWorking.ftable.append("\n");
              //   System.out.println(res);
               }
                DBWorking.jLabel13.setVisible(true);
                DBWorking.jLabel15.setVisible(true);
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
                Password3 pass3 = new Password3();
                pass3.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Password mismatch...OR Please check you LAN connection..!!");
             //ssss   count=0;
            }
            System.out.println(e);
        }
        finally
        {
            try{
            conn3.close();
            }
            catch(Exception e2){}
        }   
        
    }
}
