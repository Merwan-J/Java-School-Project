/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package db;
import java.sql.*;
/**
 *
 * @author Merwan
 */
public interface user {
    String firstName = "";
    String lastName = "";
    String email = "";
    String username = "";
    String password = "";
    String phoneNumber = "";
    String userType = "";
    
    public ResultSet login(String username, String password);
    public void signup(String query) throws Exception;
    public ResultSet getAll() throws Exception;
    public void delete(int id) throws Exception;
    public void edit(String query);
    public ResultSet searchFor(String searchQuery);
    
}
