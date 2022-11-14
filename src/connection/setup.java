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
public class setup {
    
    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "";
    static Connection conn = null;
    static Statement stmt = null;
    static String query;
   
   
    public static void setup(){
        //        LOAD THE MYSQL DRIVER
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("-----SUCCESSFULLY LOADED THE DRIVER-----");
            
        }catch (Exception e){
            System.out.println("------------CANNOT LOAD THE DRIVER-----------");
            e.printStackTrace();
            System.out.println("----------------------");
        }
        
        
        //   CREATE HOTEL DATABASE
        try{
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE hotel";
            stmt.executeUpdate(sql);
            System.out.println("-----SUCCESSFULLY CREATED HOTEL DATABASE -----");


        }catch (Exception e){
            System.out.println("----------CANNOT CREATE HOTEL DATABASE-----------");
            e.printStackTrace();
        }
        
        
        
        //      SELECT HOTEL DATABASE AND CREATE USER'S TABLE
        try{
            conn = DriverManager.getConnection(DB_URL+"hotel",USER,PASS);

            System.out.println("-----SUCCESSFULLY SELECTED HOTEL DATABASE -----");

            stmt = conn.createStatement();

           //create USERS table
            query = "CREATE TABLE USERS "+
                    "(id int primary key auto_increment, " +
                    "firstname varchar(20) not null, " +
                    "lastname varchar(20) not null, " +
                    "gender varchar(10) not null,"+
//                    "dateofbirth date not null,"+
                    "email varchar(30) not null, " +
                    "phonenumber int, "+
                    "username varchar(20) not null, " +
                    "password varchar(20) not null, " +
                    "usertype varchar(30) not null, "+
                    "cvlink varchar(255), "+
                    "status boolean default false," +
                    "company varchar(30),"+ 
                    "securityquestion varchar(100),"+
                    "answer varchar(100) )"
                    ;

            stmt.executeUpdate(query);
            System.out.println("-----SUCCESSFULLY CREATED USER TABLE IN HOTEL DATABASE -----");


        }catch (Exception e){
            System.out.println("----------CANNOT SELECT HOTEL DATABASE OR CANNOT CREATE USER TABLE-----------");
            e.printStackTrace();
        }
        
//        create a JOB table

        try{
           conn = DriverManager.getConnection(DB_URL+"hotel",USER,PASS);
           stmt = conn.createStatement();
           
           query = "CREATE TABLE JOB "
                   + "(ID int primary key auto_increment, " +
                    "TITLE varchar(20) not null, " +
                    "JOBTTYPE varchar(20) not null, " +
                    "DESCRIPTION text not null,"+
                    "JOBSTATUS varchar(30) not null, " +
                    "COMPANY varchar(20)  null, " +
                    "ADDRESS varchar(30) not null,"+
                    "DATEPOSTED timestamp not null, "+
                    "POSTEDBY varchar(20)  null )" 
                    ;
           stmt.executeUpdate(query);
           System.out.println("------ SUCCESFULLY CREATED JOB TABLE----------");
                   
        }catch(Exception e){
            System.out.println("-------------- ERROR IN CREATING A JOB TABLE ----------");
            e.printStackTrace();
            
        }
        
        
//        create job-employer table

        try{
           conn = DriverManager.getConnection(DB_URL+"hotel",USER,PASS);
           stmt = conn.createStatement();
           
           query = " create table jobemployer (job_id int, employer_id int)";
           stmt.executeUpdate(query);
            System.out.println("------SUCCESSFULLY CREATED JOB-EMPLOYER TABLE-----------");
        
        }


        catch(Exception e){
        
            System.out.println("----------CANNOT CREATE JOB-EMPLOYER TABLE---------");
            e.printStackTrace();
  
        }
        
        
        
//        create job-seeker table

        try{
            conn=DriverManager.getConnection(DB_URL+"hotel",USER,PASS);
            stmt = conn.createStatement();
            
            query = "create table jobseeker (job_id int, seeker_id int, status varchar(20) )";
            
            stmt.executeUpdate(query);
            System.out.println("------SUCCESSFULLY CREATED JOB-SEEKER TABLE-----------");
            
        } catch(Exception e){
            System.out.println("-------------CANNOT CREATE JOB-SEEKER TABLE ----------");
            e.printStackTrace();
        }
        
        try{
            conn.close();
            stmt.close();
        }catch(Exception e){}
    
    }

    public static void main(String[] args) {
        setup.setup();
    }

    
}
