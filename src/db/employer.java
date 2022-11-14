/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.*;
import java.util.ArrayList;
import connection.connectionProvider;

/**
 *
 * @author Merwan
 */
public class employer implements user {
    static Connection conn = null;
    static String query = null;
    static Statement stmt = null;
    static ResultSet res;
    
    @Override
    
    public void signup(String query) throws Exception {
        conn = connectionProvider.getCon();
        
       
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("------------SUCCESSFULLY ADDED EMPLOYER TO WAITLIST, WAIT UNTIL APPROVED BY ADMIN ---------");
          
                conn.close();
                stmt.close();

    
   
}
    
    @Override
    public ResultSet login(String username, String password) {
        conn = connectionProvider.getCon();
        try{
            stmt = conn.createStatement();
            query = "select * from users where usertype = 'employer' and username='"+username+"' and password='"+password+"'";
            res = stmt.executeQuery(query);
            
            return res;
        }catch(Exception e){
            
            System.out.println("------------ERROR CREATING STATEMENT IN EMPLOYER LOGIN METHOD---------");
            e.printStackTrace();
            return null;
            
        }
    }
    
    public void approveEmployer(int id) throws Exception{
        query = "update users set status= 1 where id="+id;
        conn = connectionProvider.getCon();
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
                
        
    }
   
    public ResultSet getById(int id) throws Exception{
    
        query = "select * from users where usertype='employer' and id="+id;
        conn = connectionProvider.getCon();
        stmt = conn.createStatement();
        
        res = stmt.executeQuery(query);
        return res;

        
    }
    @Override
    public ResultSet searchFor(String searchQuery){
        query = "select * from users where usertype = 'employer' and (email like '%" + searchQuery+
                "%' or username like '%" + searchQuery+
                "%' or firstname like '%" + searchQuery+
                "%' or lastname like '%" + searchQuery+"%' or company like '%"+ searchQuery+"%' )";
                 
        conn = connectionProvider.getCon();
        try{
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            return res;
            
        }catch(Exception e){
            System.out.println("------FAILED TO CREATE A STATMENT IN SEARCHFOR METHOD OF EMPLOYER CLASS---------");
            e.printStackTrace();
            return null;
        }
        
    }
    
    @Override
    public void edit(String query){
        conn = connectionProvider.getCon();
        
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
            System.out.println("------------SUCCESSFULLY EIDTED EMPLOYER ---------");
        }catch(Exception e){
            System.out.println("-----------ERROR CREATING STATEMENT IN EMPLOYER EDIT METHOD ----------");
            e.printStackTrace();
        }
        finally{
            try {   
                conn.close();
                stmt.close();
            }catch(Exception e){}
        }
    
    
   
}
    
    
    @Override
    public void delete(int id) throws Exception{
        query = "delete from users where id="+id;
        conn = connectionProvider.getCon();
        stmt = conn.createStatement();
        
        stmt.executeUpdate(query);
    
    
    }

    @Override
    public ResultSet getAll() throws Exception{
        conn = connectionProvider.getCon();
        res = null;
        
            stmt = conn.createStatement();
            query = "select * from users where usertype='employer'";
            res = stmt.executeQuery(query);
            return res;
            
        
    }
    
    public ResultSet getNotApproved(){
    
        conn = connectionProvider.getCon();
        
        try{
        
            stmt = conn.createStatement();
            query = "select * from users where usertype= 'employer' and status = 'false' ";
            res = stmt.executeQuery(query);
            
            return res;
            
        }catch(Exception e){
            System.out.println("-----------CANNOT CREATE A STAMTENT IN GETNOTAPPROVED METHOD IN EMPLOYER--------");
            e.printStackTrace();
            return null;
            }
    
    }
    
    public  ArrayList<ResultSet> myJobs(int id){
        
        conn = connectionProvider.getCon();
        ArrayList<Integer> ids = new ArrayList();
        ArrayList<ResultSet> jobs = new ArrayList();

        
        try {
            stmt = conn.createStatement();
            query = "select * from jobemployer where employer_id="+id;
            res = stmt.executeQuery(query);
            
            while(res.next()){
                System.out.println("##################suiiiiiiiiiiiiiiiiiiiiiiiiiiiiii---------------------");
                System.out.println(res.getString("job_id"));
                ids.add(res.getInt(1));
                System.out.println(ids.toString());
            }
            System.out.println("out of the while loop");
            for (int i : ids) {
                
                query = "select * from job where id="+i;
                res = stmt.executeQuery(query);
                jobs.add(res);
                
            }
            
            return jobs;
            
        }catch(Exception e){
            System.out.println("---------- CANNOT RETRIEVE MYJOBS FROM DATABASE ----------");
            e.printStackTrace();
            return null;
        }
        
    }
    
    public static void main(String[] args)throws Exception{
            
        ResultSet res;
        employer emp = new employer();
        
        ArrayList<ResultSet> arr = new ArrayList();
        System.out.println("----------------- the main function 1------------------");
        arr = emp.myJobs(3);
        
        System.out.println(arr.toString());
        
        for(ResultSet r : arr){
            if (r.next()){
            System.out.println("this is the result set of arr variable"+" : "+ r.getString(2));
        }}
        
        res = emp.getAll();
        while(res.next()){
        
            System.out.println(res.getString(2)+" "+ res.getString(3)+" "+ res.getString(4));
        }
        
        res = emp.getNotApproved();
        while(res.next()){
        
            System.out.println(res.getString(2));
        }
        
        
        res = emp.login("dani", "dani");
        
        while(res.next()){
            System.out.println(res.getString(2)+" "+ res.getString(3)+" "+ res.getString(4));
        }
        
        
        res = emp.searchFor("Tilahun");
        
        while(res.next()){
            System.out.println(res.getString(2)+" "+ res.getString(3)+" "+ res.getString(4));
        }
        
       
    }
}

    