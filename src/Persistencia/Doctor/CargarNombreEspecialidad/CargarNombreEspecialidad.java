package Persistencia.Doctor.CargarNombreEspecialidad;

import static Comandos.metodos.MetodosUtiles.ContieneSoloNumeros;
import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CargarNombreEspecialidad {

    public void cargarNombreYEspecialidadDoctores(String identificacionDoctor, JTextField txtNombre, JTextField txtEspecialidad) {
        String sql = "SELECT CONCAT(primerNombre, ' ', primerApellido) AS nombre, especialidad FROM medicos WHERE identificacionDoctor = ?";

        if (identificacionDoctor == null) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }
        if (!ContieneSoloNumeros(identificacionDoctor)) {
            throw new IllegalArgumentException("El número de documento sólo contiene números..");
        }

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, identificacionDoctor);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String especialidad = rs.getString("especialidad");

                txtNombre.setText(nombre);
                txtEspecialidad.setText(especialidad);
            } else {
                throw new IllegalArgumentException("No existe un doctor con esa identificación..");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
