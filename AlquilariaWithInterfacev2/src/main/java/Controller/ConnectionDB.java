
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionDB {

    private static Connection conexion = null;
    
    private ConnectionDB() {}
    
    // función que hace que si la conexión está cerrada o es nula (como es por defecto) la crea conectandose a la base de datos pi_actfinaldb
    public static Connection obtainConnection() throws SQLException {

        if (conexion == null || conexion.isClosed()) {
            try {

                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/alquilariadb", "root", "");
            } catch (SQLException e) {
                throw new SQLException("Error: " + e.getMessage());
            }
        }
        return conexion;
        
        
    }
    
    
    
    // función que sirve para cerrar la conexión
    public static void closeConnection() throws SQLException {
            conexion.close();
    }
}
