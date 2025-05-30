/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.ConnectionDB;
import java.sql.CallableStatement;
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
            //conn.close();
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

            int a = ps.executeUpdate();

            if (a > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            //conn.close();
            return false;

        }

    }

    public static boolean modifyVivienda(String address, Integer rent, Integer surface, String description, Boolean allowsPets, String code, Integer housetyp, Integer id_owner) throws SQLException {
    boolean modified = false;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        conn = ConnectionDB.obtainConnection();

        if (address != null && !address.trim().isEmpty()) {
            String sql = "UPDATE house SET address = ? WHERE code = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, address);
            ps.setString(2, code);
            modified |= (ps.executeUpdate() > 0);
            ps.close();
        }

        if (rent != null && rent > 0) {
            String sql = "UPDATE house SET rent = ? WHERE code = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rent);
            ps.setString(2, code);
            modified |= (ps.executeUpdate() > 0);
            ps.close();
        }

        if (surface != null && surface > 0) {
            String sql = "UPDATE house SET surface = ? WHERE code = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, surface);
            ps.setString(2, code);
            modified |= (ps.executeUpdate() > 0);
            ps.close();
        }

        if (description != null && !description.trim().isEmpty()) {
            String sql = "UPDATE house SET description = ? WHERE code = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, description);
            ps.setString(2, code);
            modified |= (ps.executeUpdate() > 0);
            ps.close();
        }

        if (allowsPets != null) {
            String sql = "UPDATE house SET allowsPets = ? WHERE code = ?";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, allowsPets);
            ps.setString(2, code);
            modified |= (ps.executeUpdate() > 0);
            ps.close();
        }

        if (housetyp != null && housetyp > 0) {
            String sql = "UPDATE house SET housetyp = ? WHERE code = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, housetyp);
            ps.setString(2, code);
            modified |= (ps.executeUpdate() > 0);
            ps.close();
        }

        if (id_owner != null && id_owner > 0) {
            String sql = "UPDATE house SET id_owner = ? WHERE code = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_owner);
            ps.setString(2, code);
            modified |= (ps.executeUpdate() > 0);
            ps.close();
        }

    } finally {
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    }

    return modified;
}

    public static ResultSet showHouse(String code) throws SQLException {

        ResultSet rs = null;

        try {
            conn = ConnectionDB.obtainConnection();

            String s = "SELECT * FROM house WHERE code = ? ";
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setString(1, code);

            rs = ps.executeQuery();
            //conn.close();
            return rs;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            //conn.close();
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
            //conn.close();
            return rs;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            //conn.close();
            return rs;

        }
    }

    public static String getHouseType(int id, int idioma) throws SQLException {

        ResultSet rs = null;
        String sb = "";

        switch (idioma) {
            case 1://english
                try {
                    conn = ConnectionDB.obtainConnection();

                    String s = "SELECT wenglish FROM housetype WHERE id = ? ";
                    PreparedStatement ps = conn.prepareStatement(s);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        sb = rs.getString("wspanish");
                    }
                    //conn.close();

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    //conn.close();
                    sb = "error";

                }
                break;
            case 2://spanish
                try {
                    conn = ConnectionDB.obtainConnection();

                    String s = "SELECT wspanish FROM housetype WHERE id = ? ";
                    PreparedStatement ps = conn.prepareStatement(s);
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        sb = rs.getString("wspanish");
                    }
                    //conn.close();

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    //conn.close();
                    sb = "error";

                }
                break;
        }

        return sb;

    }

}

