import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // JDBC URL, usuario y contraseña de MySQL
        String url = "jdbc:mysql://localhost:3306/estudiantes2024A";
        String usuario = "root";
        String password = "123456";
        String cedula = "1234567890";
        Connection conn = null;
        PreparedStatement pstmt = null;

        String deleteSql = "DELETE FROM estudiante WHERE cedula = ?";

        try {
            conn = DriverManager.getConnection(url, usuario, password);
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setString(1, cedula);

            int filasEliminadas = pstmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("El estudiante " + cedula + " ha sido eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún estudiante con cédula " + cedula + " para eliminar.");
            }

            // Actualización del bimestreUno
            String updateSql = "UPDATE estudiante SET bimestreUno = ? WHERE cedula = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setInt(1, 14);
            pstmt.setString(2, "1726195207");
            int n = pstmt.executeUpdate();
            System.out.println("Se modificaron: " + n + " líneas.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Cerramos la conexión
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
