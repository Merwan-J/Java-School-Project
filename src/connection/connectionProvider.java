package connection;
import java.sql.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Merwan
 */
public class connectionProvider {
    static final String DB_URL = "jdbc:mysql://localhost:3306/hotel?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASS = "";
    
    public static Connection getCon(){
        //        LOAD THE MYSQL DRIVER
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            return conn;
            
        }catch (Exception e){
            System.out.println("#### error in loading driver ####");
            e.printStackTrace();
            System.out.println("----------------------");
            return null;
        }
    }}
    


