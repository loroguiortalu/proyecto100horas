/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.SPA;

import Controller.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Loro
 */
public class OperacionesPropietario {

    private static Connection conn = null;

    public static boolean insertPropietario(String dni, String n, String t, String email) throws SQLException {


        try {
            conn = ConnectionDB.obtainConnection();
            
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

    public static boolean borrPropietario(int id) throws SQLException {


        try {
            conn = ConnectionDB.obtainConnection();
            String b = "DELETE FROM owner WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(b);

            ps.setInt(1, id);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;

        }

    }

    public static boolean modifyPropietario(int id) throws SQLException {


        try {
            conn = ConnectionDB.obtainConnection();
            
            String b = "DELETE FROM owner WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(b);

            ps.setInt(1, id);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;

        }

    }
    
    
    
    public static ResultSet mostrarPropietarios(int id) throws SQLException {
    
        ResultSet rs = null;
        
        try {
            conn = ConnectionDB.obtainConnection();
            
            String s = "SELECT * FROM owner WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            
            return rs;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return rs;
        
        }      
    }
    
    
    
    
    
    
    
    
    
    
    
    

}
