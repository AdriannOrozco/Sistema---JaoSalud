package Persistencia.Doctor.Medicamentos;

import Persistencia.Database.ConexionBD;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Osvaldo
 */
public class MedicamentosDAO {

    public void cargarMedicamentosDisponibles(JComboBox<String> comboBox) {
        String sql = "SELECT idMedicamento, nombre FROM medicamentos WHERE disponible = 1";

        try (Connection conn = new ConexionBD().conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            comboBox.removeAllItems();
            comboBox.addItem(""); // Agregar item vacío inicial

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                if (nombre != null && !nombre.trim().isEmpty()) {
                    comboBox.addItem(nombre);
                }
            }

            if (comboBox.getItemCount() > 0) {
                comboBox.setSelectedIndex(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al cargar medicamentos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
