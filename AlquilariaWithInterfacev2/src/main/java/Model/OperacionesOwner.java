/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Loro
 */
public class OperacionesOwner {

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

    public static boolean modifyPropietario(int id, String dni, String n, String phonenumber, String email) throws SQLException {

        boolean boo = false;

        try {
            conn = ConnectionDB.obtainConnection();
            
        String query = "";

        if (!dni.isEmpty() && dni != null) {// comprobando si los strings enviados se les ha dado intro simplemente, y si se ha introducido algo entonces se modifica esa parte del cliente, una por una, así puedes modificar desde solo un dato del cliente a cambiar el cliente completamente (excepto el id)
            query = "UPDATE owner SET dni = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dni);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;
        }
        
        if (!n.isEmpty() && n != null) {
            query = "UPDATE owner SET name = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, n);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;
        }        
      
        if (!email.isEmpty() && email != null) {
            query = "UPDATE owner SET email = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;
        }        
        if (!phonenumber.isEmpty() && phonenumber != null) {
            query = "UPDATE owner SET phonenumber = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, phonenumber);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;

        }                
            
            
            
            
            
            

        } catch (Exception e) {
            return false;
        }
        
        return boo;

    }
    
    
    
    public static ResultSet showOwner(int id) throws SQLException {
    
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
    
        public static ResultSet showAllOwners() throws SQLException {
    
        ResultSet rs = null;
        
        try {
            conn = ConnectionDB.obtainConnection();
            
            String s = "SELECT * FROM owner ";
            PreparedStatement ps = conn.prepareStatement(s);

            rs = ps.executeQuery();
            
            return rs;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return rs;
        
        }      
    }
    
    
    
    
    
    
    
    
    
    
    
    

}
