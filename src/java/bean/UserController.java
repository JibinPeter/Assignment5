/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author c0662366
 */
@ApplicationScoped 
@ManagedBean
public class UserController {
    private List<User> users; 

    public UserController() {
        getUsersFromDatabase();
    }

    public List<User> getUsers() {
        return users;
    }
    public void getUsersFromDatabase() {
        users = new ArrayList<>();
        
        try {
            Connection conn = Utils.getConnection();
            String sql = "SELECT * FROM users"; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                User u = new User(
                            rs.getInt("userId"),
                            rs.getString("username"), 
                            rs.getString("password")
                );
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            users = new ArrayList<>();
        }
        
    }
    
        public String getUsernameByUserId(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId)
                return u.getUsername();
        }
        return null;
        }
    
}
