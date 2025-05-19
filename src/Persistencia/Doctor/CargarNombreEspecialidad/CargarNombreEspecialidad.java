package Persistencia.Doctor.CargarNombreEspecialidad;

import Persistencia.Database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;

public class CargarNombreEspecialidad {

    // En CargarNombreEspecialidad.java
    public void cargarNombreYEspecialidadDoctores(String identificacionDoctor,
            JTextField txtNombre, JTextField txtEspecialidad) {

        if (identificacionDoctor == null || identificacionDoctor.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación del doctor no puede estar vacía");
        }

        String sql = "SELECT CONCAT(primerNombre, ' ', primerApellido) as nombre, "
                + "especialidad FROM medicos WHERE identificacionDoctor = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, identificacionDoctor);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new IllegalStateException("No existe un doctor con la identificación: " + identificacionDoctor);
            }

            String nombre = rs.getString("nombre");
            String especialidad = rs.getString("especialidad");

            // Actualizar los JTextField
            txtNombre.setText(nombre);
            txtEspecialidad.setText(especialidad);

        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar datos del médico: " + e.getMessage());
        }
    }
}
