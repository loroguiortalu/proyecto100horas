/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.SPA;

import Controller.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



/**
 *
 * @author Loro
 */
public class OperacionesPropietario {
    
    
    public static boolean insertPropietario(String dni, String n, String t, String email) throws SQLException{
        

        Connection conn = ConnectionDB.obtainConnection(); 
        
        try {
            String b = "INSERT INTO owner (dni, name, phonenumber, email) VALUES (?, ?, ?, ?)";
        
        PreparedStatement ps = conn.prepareStatement(b);
        
        ps.setString(1, dni);
        ps.setString(2, n);
        ps.setString(3, t);
        ps.setString(4, email);

        
        
        ps.executeUpdate();
        return true;
        
        
        
        } catch (Exception e) {
            
            return false;
            
        }

    }
    
    

    
    
    
}
