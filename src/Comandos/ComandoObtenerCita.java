package Comandos;
import Model.ConexionBD;
import Model.Cita;
import Model.Consultorio;
import Model.Medico;
import Model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ComandoObtenerCita implements IObtenerCita{
   
    @Override
    public List<Cita> getCita(){
        
        List<Cita> listaCitas = new ArrayList<>();
        String sql = "SELECT idConsultorio, identificacion, motivo, fechaCita, hora, idCita, fechaRegistro, paciente, numeroDocumento ,estado FROM citas";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                
            int idConsultorio = rs.getInt("idConsultorio");
            
            String identificacion  = rs.getString("identificacion");
                        
            String motivo = rs.getString("motivo");
            Date fechaCita = rs.getDate("fechaCita");
            String hora = rs.getString("hora");
            int idCita = rs.getInt("idCita");
            Date fechaRegistro = rs.getDate("fechaResgitro");
            
            String nombrePaciente = rs.getString("paciente");
    
            
            String numeroDocumento = rs.getString("numeroDocumento");
            
            boolean estado = rs.getBoolean("estado");

             

                //Llenar paramétros del constructor.
                Cita cita = new Cita(idConsultorio, identificacion, motivo, fechaCita, hora, idCita, fechaRegistro, nombrePaciente, numeroDocumento, estado);
                listaCitas.add(cita);
            }

        } catch (SQLException e) {
           JOptionPane.showConfirmDialog(null, "Error al obtener la cita " + e.getMessage());
        }

        return listaCitas;
        
}
    
    
    
    
    
    
    
}
