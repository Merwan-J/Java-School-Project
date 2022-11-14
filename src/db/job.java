/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.*;
import connection.connectionProvider;
import javax.swing.JOptionPane;


/**
 *
 * @author Merwan
 */
public class job {
    static Connection conn;
    static Statement stmt;
    static ResultSet res;
    static String query;
    
    public static void post(String query) throws Exception{
        conn = connectionProvider.getCon();
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
        
                conn.close();
                stmt.close();

       
   
    }

    public static  void closeJob(int id){
        conn = connectionProvider.getCon();
        try{
            stmt = conn.createStatement();
            query = "update job set jobstatus = 'closed' where id = "+id;
            stmt.executeUpdate(query);
            System.out.println("---------------- JOB STATUS CHANGE SUCCESCC FULLY ----------");
        }
        
        catch(Exception e){
            System.out.println("---------- CANNOT CHANGE THE STATUS OF THE JOB-----------");
        }
    
    }
    public void edit(String query ){
    
        conn = connectionProvider.getCon();
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            
        }catch(Exception e){
            System.out.println("------------- CANNOT EDIT GIVEN JOB ITEM--------");
            e.printStackTrace();
          
        }
    
    }
    public void delete(){}
    
    public static ResultSet getOpenJobs() throws Exception{
        conn = connectionProvider.getCon();
        
            stmt = conn.createStatement();
            query = "select * from job where jobstatus='open' ";
            res = stmt.executeQuery(query);
            return res;
                    
        }
    
        
    public static ResultSet getClosedJobs(){
        conn = connectionProvider.getCon();
        try{
            stmt = conn.createStatement();
            query = "select * from job where jobstatus='closed' ";
            res = stmt.executeQuery(query);
            return res;
                    
        }catch(Exception e){
            System.out.println("----------CNNOT FETCH CLOSED JOBS ----------");
            e.printStackTrace();
            return null;
            }
    
        }
    public static ResultSet getJobsByType(){return null;}
    public static ResultSet getJobsByCompany(){return null;}
    public static ResultSet getJobsByTitle(){return null;}
        
    
    
     
    
}
