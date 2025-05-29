/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class OperacionesHouse {

    private static Connection conn = null;

    public static void setConn(Connection conn) {
        OperacionesHouse.conn = conn;
    }

    
    
    public static boolean insertVivienda(String address, int rent, int surface, String description,
        boolean allowsPets, String code, int housetyp, int id_owner) throws SQLException {

        try {
            conn = ConnectionDB.obtainConnection();

            String b = "INSERT INTO house (address, rent, surface, description, allowsPets, code, housetyp, id_owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(b);

            ps.setString(1, address);
            ps.setInt(2, rent);
            ps.setInt(3, surface);
            ps.setString(4, description);
            ps.setBoolean(5, allowsPets);
            ps.setString(6, code);
            ps.setInt(7, housetyp);
            ps.setInt(8, id_owner);

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            return false;

        }

    }

    public static boolean borrVivienda(String code) throws SQLException {

        try {
            conn = ConnectionDB.obtainConnection();
            String b = "DELETE FROM house WHERE code = ? ";

            PreparedStatement ps = conn.prepareStatement(b);

            ps.setString(1, code);

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            conn.close();
            return false;

        }

    }

    public static boolean modifyVivienda(String address, int rent, int surface, String description,
        boolean allowsPets, String code, int housetyp, int id_owner) throws SQLException {

        boolean boo = false;

        try {
            conn = ConnectionDB.obtainConnection();

            String query = "";

            if (!address.isEmpty() && address != null) {// comprobando si los strings enviados se les ha dado intro simplemente, y si se ha introducido algo entonces se modifica esa parte del cliente, una por una, así puedes modificar desde solo un dato del cliente a cambiar el cliente completamente (excepto el id)
                query = "UPDATE house SET address = ? WHERE code = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, address);
                ps.setString(2, code);
                ps.executeUpdate();
                ps.close();
                boo = true;
            }

            if (rent > 0) {
                query = "UPDATE house SET rent = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, rent);
                ps.setString(2, code);
                ps.executeUpdate();
                ps.close();
                boo = true;
            }

            if (surface > 0 ) {
                query = "UPDATE house SET surface = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, surface);
                ps.setString(2, code);
                ps.executeUpdate();
                ps.close();
                boo = true;
            }
            if (!description.isEmpty() && description != null) {
                query = "UPDATE house SET description = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, description);
                ps.setString(2, code);
                ps.executeUpdate();
                ps.close();
                boo = true;

            }
            
            if (allowsPets == false || allowsPets == true ) {
                query = "UPDATE house SET allowsPets = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setBoolean(1, allowsPets);
                ps.setString(2, code);
                ps.executeUpdate();
                ps.close();
                boo = true;

            }
            
            if (housetyp > 0) {
                query = "UPDATE house SET housetyp = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, housetyp);
                ps.setString(2, code);
                ps.executeUpdate();
                ps.close();
                boo = true;
            }
            
            if (id_owner > 0) {
                query = "UPDATE house SET id_owner = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id_owner);
                ps.setString(2, code);
                ps.executeUpdate();
                ps.close();
                boo = true;
            }
            
            
            conn.close();

        } catch (Exception e) {
            conn.close();
            return false;
        }

        return boo;

    }

    public static ResultSet showHouse(String code) throws SQLException {

        ResultSet rs = null;

        try {
            conn = ConnectionDB.obtainConnection();

            String s = "SELECT * FROM house WHERE code = ? ";
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setString(1, code);

            rs = ps.executeQuery();
            conn.close();
            return rs;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            conn.close();
            return rs;

        }
    }
    
        public static ResultSet showAllHouses() throws SQLException {

        ResultSet rs = null;

        try {
            conn = ConnectionDB.obtainConnection();

            String s = "SELECT * FROM house ";
            PreparedStatement ps = conn.prepareStatement(s);

            rs = ps.executeQuery();
            conn.close();
            return rs;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            conn.close();
            return rs;

        }
    }

    public static String gettipoviviendaSpanish(int id) throws SQLException {

        ResultSet rs = null;
        String sb;

        try {
            conn = ConnectionDB.obtainConnection();

            String s = "SELECT wspanish FROM housetype WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            sb = rs.getString("wspanish");
            conn.close();
            return sb;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            conn.close();
            return "error";

        }
    }

}


