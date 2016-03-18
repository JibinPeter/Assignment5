/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author c0662366
 */
public class Login {
    private String username;
    private String password;
    private boolean loggedIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String doLogin() {
        for (User u : new UserController().getUsers()) {
            if (username.equals(u.getUsername())
                    && password.equals(u.getPassword())) {
                loggedIn = true;
                return "editPost";
            }
        }
        loggedIn = false;
        return "viewPost";
    }
}
