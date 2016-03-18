/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;

/**
 *
 * @author c0662366
 */
public class Post {
    /*
     CREATE TABLE posts (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     user_id INT NOT NULL,
     title VARCHAR(255) NOT NULL UNIQUE,
     created_time DATETIME NOT NULL,
     contents TEXT,
     FOREIGN KEY posts(user_id) REFERENCES users(id)
     );
     */

    private int id;
    private int user_id;
    private String title;
    private Date created_time; 
    private String contents;

    public Post(int id, int user_id, String title, Date created_time, String contents) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.created_time = created_time;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    
    
}
