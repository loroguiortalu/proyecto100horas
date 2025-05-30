
package Model;

import Controller.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Loro
 */
public class OperacionesTenant {
    private static Connection conn = null;

    public static boolean insertTenant(String dni, String n, String t, String email, boolean haspets) throws SQLException {


        try {
            conn = ConnectionDB.obtainConnection();
            
            String b = "INSERT INTO tenant (dni, name, phonenumber, email, haspets) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(b);

            ps.setString(1, dni);
            ps.setString(2, n);
            ps.setString(3, t);
            ps.setString(4, email);
            ps.setBoolean(5, haspets);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;

        }

    }

    public static boolean borrTenant(int id) throws SQLException {


        try {
            conn = ConnectionDB.obtainConnection();
            String b = "DELETE FROM tenant WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(b);

            ps.setInt(1, id);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;

        }

    }

    public static boolean modifyTenant(int id, String dni, String n, String phonenumber, String email, Boolean hasPets) throws SQLException {

        boolean boo = false;

        try {
            conn = ConnectionDB.obtainConnection();
            
        String query = "";

        if (!dni.isEmpty() && dni != null) {// comprobando si los strings enviados se les ha dado intro simplemente, y si se ha introducido algo entonces se modifica esa parte del cliente, una por una, así puedes modificar desde solo un dato del cliente a cambiar el cliente completamente (excepto el id)
            query = "UPDATE tenant SET dni = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, dni);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;
        }
        
        if (!n.isEmpty() && n != null) {
            query = "UPDATE tenant SET name = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, n);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;
        }        
      
        if (!email.isEmpty() && email != null) {
            query = "UPDATE tenant SET email = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;
        }        
        if (!phonenumber.isEmpty() && phonenumber != null) {
            query = "UPDATE tenant SET phonenumber = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, phonenumber);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            boo = true;

        }                
            
        if (hasPets != null) {
            query = "UPDATE tenant SET haspets = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, hasPets);
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
    
    
    
    public static ResultSet showTenant(int id) throws SQLException {
    
        ResultSet rs = null;
        
        try {
            conn = ConnectionDB.obtainConnection();
            
            String s = "SELECT * FROM tenant WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            
            return rs;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return rs;
        
        }      
    }
    
        public static ResultSet showAllTenants() throws SQLException {
    
        ResultSet rs = null;
        
        try {
            conn = ConnectionDB.obtainConnection();
            
            String s = "SELECT * FROM tenant ";
            PreparedStatement ps = conn.prepareStatement(s);

            rs = ps.executeQuery();
            
            return rs;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return rs;
        
        }      
    }
    
    
    
    
    
    }
