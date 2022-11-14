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
public class seeker implements user{
    static Connection conn = null;
    static String query = null;
    static Statement stmt = null;
    static ResultSet res;
    @Override
    public void signup(String query) throws Exception{
        conn = connectionProvider.getCon();
        
       
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        
  
                conn.close();
                stmt.close();
           
    
    
    }
    public ResultSet getById(int id) throws Exception{
    
        query = "select * from users where usertype='seeker' and id="+id;
        conn = connectionProvider.getCon();
        stmt = conn.createStatement();
        
        res = stmt.executeQuery(query);
        return res;

        
    }
    @Override
    public ResultSet login(String username, String password){
        conn = connectionProvider.getCon();
        query = "select * from users where usertype = 'seeker' and username = '"+username+"' and password = '"+password+"'";
        try{
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);
            return res;
            
            
        }catch(Exception e){
            
            System.out.println("------------ERROR CREATING STATEMENT IN SEEKER LOGIN METHOD---------");
            e.printStackTrace();
            return null;
            
        }
    }
    @Override
    public ResultSet getAll() throws Exception{
        conn = connectionProvider.getCon();
 
            stmt = conn.createStatement();
            query = "select * from users where usertype='seeker'";
            res = stmt.executeQuery(query);
            return res;
            
       
    }
    @Override
    public ResultSet searchFor(String searchQuery){
        query = "select * from users where usertype = 'seeker' and (email like '%" + searchQuery+
                "%' or username like '%" + searchQuery+
                "%' or firstname like '%" + searchQuery+
                "%' or lastname like '%" + searchQuery+"%')";
        conn = connectionProvider.getCon();
        
        try{
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            return res;
            
        }catch(Exception e){
            System.out.println("------FAILED TO CREATE A STATMENT IN SEARCHFOR METHOD OF SEEKER CLASS---------");
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
        }catch(Exception e){
            System.out.println("-----------ERROR CREATING STATEMENT IN SEEKER EDIT METHOD ----------");
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
    
    public ArrayList<ResultSet> getJobsApplied(int id){
        ArrayList<Integer> ids = new ArrayList();
        ArrayList<ResultSet> jobs = new ArrayList();
        
        conn = connectionProvider.getCon();
        try{
            query = "select * from jobseeker where seeker_id = "+id;
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);
            
            while(res.next()){
                ids.add(res.getInt(1));
            }
            
            for (int i : ids) {
                query = "select * from job where id="+i;
                res = stmt.executeQuery(query);
                jobs.add(res);
            }
            return jobs;
            
        }catch(Exception e){
            System.out.println("----------- CANNOT FETCH JOBS APPLIED OF THE USER --------------");
            e.printStackTrace();
                return null;
        }
    
    }
    
    public ArrayList<ResultSet> getJobsRejected(int id)throws Exception{
        conn = connectionProvider.getCon();
        String query;
        ArrayList<Integer> ids = new ArrayList();
        ArrayList<ResultSet> jobs = new ArrayList();
        
        
        
            stmt = conn.createStatement();
            query = "select * from jobseeker where seeker_id = "+id+" and status='rejected'";
            res = stmt.executeQuery(query);
            ResultSet res2;
            
            
            while(res.next()){
      
                ids.add(res.getInt("job_id"));
            }
            res.close();
            stmt.close();
            System.out.println(ids.toString());
            for(int i : ids){
                query = "select id,title,jobtype,description,address,company from job where id ="+i;
                    Statement stmt2 = connectionProvider.getCon().createStatement();
                    res2 = stmt2.executeQuery(query);
                jobs.add(res2);
                
            }
            
            System.out.println(jobs.toString());

            
            return jobs;
    
    
    
    }
        
    public ArrayList<ResultSet> getJobsHired(int id) throws Exception{
    
    
        conn = connectionProvider.getCon();
        String query;
        ArrayList<Integer> ids = new ArrayList();
        ArrayList<ResultSet> jobs = new ArrayList();
        
        
        
            stmt = conn.createStatement();
            query = "select * from jobseeker where seeker_id = "+id+" and status='hired'";
            res = stmt.executeQuery(query);
            ResultSet res2;
            
            
            while(res.next()){
      
                ids.add(res.getInt("job_id"));
            }
            res.close();
            stmt.close();
            System.out.println(ids.toString());
            for(int i : ids){
                query = "select id,title,jobtype,description,address,company from job where id ="+i;
                    Statement stmt2 = connectionProvider.getCon().createStatement();
                    res2 = stmt2.executeQuery(query);
                jobs.add(res2);
                
            }
            
            System.out.println(jobs.toString());

            
            return jobs;
    }
    
    public ArrayList<ResultSet> getPendingJobs(int id) throws Exception{
        conn = connectionProvider.getCon();
        ArrayList<Integer> ids = new ArrayList();
        ArrayList<ResultSet> jobs = new ArrayList();
        
        
        
            stmt = conn.createStatement();
            query = "select * from jobseeker where seeker_id = "+id+" and status='pending'";
            res = stmt.executeQuery(query);
            ResultSet res2;
            
            
            while(res.next()){
      
                ids.add(res.getInt("job_id"));
            }
            res.close();
            stmt.close();
            System.out.println(ids.toString());
            for(int i : ids){
                query = "select id,title,jobtype,description,address,company from job where id ="+i;
                    Statement stmt2 = connectionProvider.getCon().createStatement();
                    res2 = stmt2.executeQuery(query);
                    
//                    ResultSetMetaData rsMetaData = res2.getMetaData();
//                    System.out.println("List of column names in the current table: ");
//                    //Retrieving the list of column names
//                    int count = rsMetaData.getColumnCount();
//                    for(int j = 1; j<=count; j++) {
//                       System.out.println(rsMetaData.getColumnName(j)+" "+j);
//                       
//                    }
                jobs.add(res2);
                
            }
            
            System.out.println(jobs.toString());

            
            return jobs;
            
    }
        
    
    
    
    
    
    
    public static void main(String[] args) throws SQLException{
//        ResultSet res;
//        ArrayList<ResultSet> arr = new ArrayList();
//        
//        seeker s = new seeker();
//        
//        res = s.login("doju","doju");
//        
//        while(res.next()){
//            System.out.println(res.getString(2)+" "+ res.getString(3)+" "+ res.getString(4));
//        }
//        
//        res = s.getAll();
//        while(res.next()){
//            System.out.println(res.getString(2)+" "+ res.getString(3)+" "+ res.getString(4));
//        }
//        
//        res = s.searchFor("tura");
//        while(res.next()){
//            System.out.println(res.getString(2)+" "+ res.getString(3)+" "+ res.getString(4));
//        }
//        
//        arr = s.getJobsApplied(2);
//        System.out.println("JObs applied");
//        for(ResultSet r : arr){
//            r.next();
//            System.out.println(r.getString(2)+" "+ r.getString(3)+" "+ r.getString(4));
//        }
        
        
//        arr = s.getJobsHired(2);
//        System.out.println("Jobs Hired ");
//        for(ResultSet r : arr){
//            r.next();
//            System.out.println(r.getString(2)+" "+ r.getString(3)+" "+ r.getString(4));
//        }
//        
//        arr = s.getJobsRejected(2);
//        System.out.println("Jobs Rejected");
//        for(ResultSet r : arr){
//            r.next();
//            System.out.println(r.getString(2)+" "+ r.getString(3)+" "+ r.getString(4));
//        }
//        
//        arr = s.getPendingJobs(2);
//        System.out.println("Jobs Pending");
//        for(ResultSet r : arr){
//            r.next();
//            System.out.println(r.getString(2)+" "+ r.getString(3)+" "+ r.getString(4));
//        }
//        
        
        
        
        
    }
    
   
   
    
}
