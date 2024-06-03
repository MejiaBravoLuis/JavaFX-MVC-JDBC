/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luismejia.utils;

import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author Luis Mejia
 */
public class PasswordUtils {
    
    private static PasswordUtils instance;
    
    private PasswordUtils(){
    
    }

    public static PasswordUtils getInstance() {
        
        if(instance == null){
            instance = new PasswordUtils();
        }
        return instance;
    }
    
    public String encryptedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public boolean checkPassword(String pass, String encryptedPass){
        return BCrypt.checkpw(pass, encryptedPass);
    }
}
