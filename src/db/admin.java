/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import connection.connectionProvider;
import java.sql.*;

/**
 *
 * @author Merwan
 */
    public class admin implements user{
        static Connection conn = null;
        static String query = null;
        static Statement stmt = null;
        static ResultSet res;

        @Override
        public void signup(String query) {

        }

        @Override
        public ResultSet getAll() {
            return null;
        }


        @Override
        public void delete(int id) {

        }

        @Override
        public void edit(String query) {

        }

        @Override
        public ResultSet searchFor(String searchQuery) {
            return null;
        }

        @Override
        public ResultSet login(String username, String password) {
            return null;
        }

        public void approveEmployer(String query){

                conn = connectionProvider.getCon();
                try{
                stmt = conn.createStatement();
                stmt.executeQuery(query);
                }
                catch(Exception e){
                    System.out.println("---------- CANNOT APPROVE EMPLOYER -----------");
                    e.printStackTrace();
                    
                }


        }
        
        public void deleteUser(String query){}
        
        public void deleteJob(){}

}
