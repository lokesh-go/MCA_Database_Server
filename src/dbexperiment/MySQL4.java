/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbexperiment;

import static dbexperiment.MySQL3.stmt;
import static dbexperiment.MySQLConnection.count;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class MySQL4 {
    static Connection conn4 = null,conn41=null;
    static int count=0;
    static String passw,usr;
    public static String backup;
    static Statement stmt1=null,stmt2=null;
    MySQL4(String src,String dest,String tbl)
    {
        try
        {
           
            count++;
            String CONN_STRING="jdbc:mysql://172.31.100.41:3306/"+"db"+dest;
            usr = dest;
            passw= MySQLConnection.passw;
            conn4 = DriverManager.getConnection(CONN_STRING,usr,passw);//Connection Established between netbeans and SQL
            System.out.println("Connected Database Dest");    
            
            String CONN_STRING1="jdbc:mysql://172.31.100.41:3306/"+"db"+src;
            usr = src;
            passw=MySQL2.passw;
            conn41 = DriverManager.getConnection(CONN_STRING1,usr,passw);//Connection Established between netbeans and SQL
            System.out.println("Connected Database Src");    
            
            System.out.println(src+" "+dest+" "+tbl);
            String dat ="select * from "+tbl;
          
            
            System.out.println(dat);
       //     stmt1 = conn41.createStatement();      
        //    stmt2 = conn4.createStatement();
       //     stmt.executeUpdate(qr);
       //   System.out.println(rs);
       System.out.println("sucesss");
      
       stmt2 = conn4.createStatement();
       stmt2.executeUpdate(DBWorking.querytable.getText());
       System.out.println("sucesss2");
        if(DBWorking.test!="on")
            JOptionPane.showMessageDialog(null, "Create Table in Your Database Successfully..");
       if(DBWorking.test=="on")
       {
           System.out.println("result exe");
     //      ResultSet rs = stmt1.executeQuery(dat);
        //   System.out.println(rs.getString(""));
         //  stmt2.executeUpdate("insert into MOBLIE "+rs);
  /*      PreparedStatement loadStatement = conn41.prepareStatement("SELECT * FROM "+tbl);
        PreparedStatement storeStatement = conn4.prepareStatement("INSERT INTO "+tbl);

        ResultSet loadedData = loadStatement.executeQuery();

        while (loadedData.next()) {
                  storeStatement.setString(1, loadedData.getString(1));
                  storeStatement.executeUpdate();
           }*/
        //  String qr ="insert into "+tbl+"("+"'rs()'"+")";
      //    System.out.println(qr);
   /*   while(rs.next())
      {
        String colval = rs.getString(1);
         System.out.println(colval);
      //    stmt2.executeUpdate("insert into "+tbl+"(1) values "+"("+colval+")");
       
      }
*/  
   PreparedStatement pstm1 = conn41.prepareStatement("select * from "+tbl);
   ResultSet rs= pstm1.executeQuery();
   ResultSetMetaData meta = rs.getMetaData();
   
   List<String> columns = new ArrayList<>();
   for(int i=1;i<=meta.getColumnCount();i++)
       columns.add(meta.getColumnName(i));
   
   PreparedStatement pstm2 = conn4.prepareStatement("insert into "+tbl+" ("+columns.stream().collect(Collectors.joining(", "))+ ") values ("+columns.stream().map(c -> "?").collect(Collectors.joining(", "))+")");
   
   while(rs.next())
   {
       for(int i=1;i<=meta.getColumnCount();i++)
           pstm2.setObject(i,rs.getObject(i));
       pstm2.addBatch();
   }
   pstm2.executeBatch();
   JOptionPane.showMessageDialog(null, "Create Table with all data in Your Database Successfully..");
   System.out.println("sucesss21");
       
       
       }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error.............!!!!!");
            System.out.println(e);
        }
    }
    
}
