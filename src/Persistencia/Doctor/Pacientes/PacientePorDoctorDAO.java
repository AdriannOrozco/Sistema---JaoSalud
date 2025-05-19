package Persistencia.Doctor.Pacientes;

import Model.Paciente;
import Persistencia.Database.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PacientePorDoctorDAO {

    public List<Paciente> obtenerPacientesPorDoctor(String idMedico) {
        List<Paciente> pacientes = new ArrayList<>();
        Set<String> documentosUnicos = new HashSet<>();

        // Consulta para obtener los números de documento de los pacientes que tienen citas con este médico
        String sqlCitas = "SELECT DISTINCT c.numeroDocumento FROM citas c " +
                         "WHERE c.identificacionDoctor = ?";

        // Consulta para obtener los datos completos del paciente
        String sqlPaciente = "SELECT * FROM pacientes p WHERE p.numeroDocumento = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement psCitas = conn.prepareStatement(sqlCitas)) {

            System.out.println("Consultando pacientes para médico: " + idMedico);
            psCitas.setString(1, idMedico);
            ResultSet rsCitas = psCitas.executeQuery();

            while (rsCitas.next()) {
                String numeroDocumento = rsCitas.getString("numeroDocumento");
                System.out.println("Documento encontrado: " + numeroDocumento);

                // Evitar duplicados
                if (!documentosUnicos.add(numeroDocumento)) {
                    continue;
                }

                // Obtener datos completos del paciente
                try (PreparedStatement psPaciente = conn.prepareStatement(sqlPaciente)) {
                    psPaciente.setString(1, numeroDocumento);
                    ResultSet rsPaciente = psPaciente.executeQuery();

                    if (rsPaciente.next()) {
                        Paciente paciente = new Paciente(
                            rsPaciente.getString("primerNombre"),
                            rsPaciente.getString("segundoNombre"),
                            rsPaciente.getString("primerApellido"),
                            rsPaciente.getString("segundoApellido"),
                            rsPaciente.getString("tipoDocumento"),
                            rsPaciente.getString("numeroDocumento"),
                            rsPaciente.getString("telefono"),
                            rsPaciente.getString("direccionResidencia"),
                            rsPaciente.getString("estadoCivil"),
                            rsPaciente.getString("genero"),
                            rsPaciente.getString("email"),
                            rsPaciente.getString("EPS"),
                            rsPaciente.getString("tipoSangre"),
                            rsPaciente.getDate("fechaNacimiento"),
                            rsPaciente.getDate("fechaRegistro"),
                            rsPaciente.getString("edad")
                        );
                        pacientes.add(paciente);
                        System.out.println("Paciente agregado: " + paciente.getPrimerNombre());
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error en obtenerPacientesPorDoctor: " + e.getMessage());
            e.printStackTrace();
        }

        return pacientes;
    }
}
