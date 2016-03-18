/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class PostController {
    private List<Post> posts;
    private Post currentPost;

    public List<Post> getPosts() {
        return posts;
    }

    public Post getCurrentPost() {
        return currentPost;
    }

    public PostController() {
        getPostsFromDatabase();
    }
    
    private void getPostsFromDatabase() {
        posts = new ArrayList<>();
        
        try {
            Connection conn = Utils.getConnection();
            String sql = "SELECT * FROM posts";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) { 
                Post p = new Post(
                            rs.getInt("id"),
                            rs.getInt("user_id"), 
                            rs.getString("title"), 
                            rs.getDate("created_time"), 
                            rs.getString("contents")
                );
                posts.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Post getPostById(int id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Post> getPostsByUserId(int userId) {
        List<Post> result = new ArrayList<>();
        for (Post p : posts) {
            if (p.getUser_id() == userId) {
                result.add(p);
            }
        }
        return result;
    }
    
    public String savePost(User u) {
        try {
            Connection conn = Utils.getConnection();
            if(currentPost.getId()>=0) {
                String sqlUpdate = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
                PreparedStatement psmt = conn.prepareStatement(sqlUpdate);
                psmt.setString(1, currentPost.getTitle());
                psmt.setString(2, currentPost.getContents());
                psmt.setInt(3, currentPost.getId());
                psmt.executeUpdate();
            }
            else {
                String sqlInsert = "INSERT INTO posts (user_id, title, created_time, contents) VALUES (?, ?, NOW(), ?";
                PreparedStatement psmt = conn.prepareStatement(sqlInsert);
                psmt.setInt(1, u.getUserId());
                psmt.setString(2, currentPost.getTitle());
                psmt.setString(3, currentPost.getContents());
                psmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewPost";
    }
    public String viewPost(Post post) {
        currentPost = post;
        return "viewPost";
    }
    public String addPost() {
        currentPost = new Post(-1, -1, "", null, "");
        return "editPost";
    }
    public String editPost() {
        return "editPost";
    }
    
}
