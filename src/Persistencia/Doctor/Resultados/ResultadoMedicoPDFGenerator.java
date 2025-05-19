/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Doctor.Resultados;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

/**
 *
 * @author Osvaldo
 */
public class ResultadoMedicoPDFGenerator {

    private static final String BASE_PATH = System.getProperty("user.dir") + "/resultados/";

    public String generarPDF(String nombrePaciente, String idPaciente,
            String nombreMedico, String especialidad, String motivo,
            String presionArterial, String frecuenciaCardiaca,
            String temperatura, String pesoYTalla, String motivoConsulta,
            String estudios, String alergias, String enfermedadesPrevias,
            String medicamentosActuales, String recomendaciones,
            String medicamentosPrescritos) throws Exception {

        // Crear directorio si no existe
        File directorio = new File(BASE_PATH);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        Document document = new Document();
        String fileName = BASE_PATH + "resultado_" + idPaciente + "_"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Logo y encabezado
            try {
                Image logo = Image.getInstance("src/resources/LogoHospital.png");
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            } catch (Exception e) {
                System.out.println("No se pudo cargar el logo");
            }

            // Título
            com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("RESULTADO MÉDICO", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Información básica
            com.itextpdf.text.Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            document.add(new Paragraph("Paciente: " + nombrePaciente, normalFont));
            document.add(new Paragraph("ID Paciente: " + idPaciente, normalFont));
            document.add(new Paragraph("Médico: " + nombreMedico, normalFont));
            document.add(new Paragraph("Especialidad: " + especialidad, normalFont));
            document.add(new Paragraph("Fecha: " + LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), normalFont));
            document.add(new Paragraph("\n"));

            // Signos vitales
            com.itextpdf.text.Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            document.add(new Paragraph("SIGNOS VITALES", sectionFont));
            document.add(new Paragraph("Presión Arterial: " + presionArterial, normalFont));
            document.add(new Paragraph("Frecuencia Cardíaca: " + frecuenciaCardiaca, normalFont));
            document.add(new Paragraph("Temperatura: " + temperatura, normalFont));
            document.add(new Paragraph("Peso y Talla: " + pesoYTalla, normalFont));
            document.add(new Paragraph("\n"));

            // Resto de la información médica
            document.add(new Paragraph("MOTIVO DE CONSULTA", sectionFont));
            document.add(new Paragraph(motivoConsulta, normalFont));

            document.add(new Paragraph("ESTUDIOS SOLICITADOS", sectionFont));
            document.add(new Paragraph(estudios, normalFont));

            document.add(new Paragraph("ALERGIAS IMPORTANTES", sectionFont));
            document.add(new Paragraph(alergias, normalFont));

            document.add(new Paragraph("ENFERMEDADES PREVIAS", sectionFont));
            document.add(new Paragraph(enfermedadesPrevias, normalFont));

            document.add(new Paragraph("MEDICAMENTOS ACTUALES", sectionFont));
            document.add(new Paragraph(medicamentosActuales, normalFont));

            document.add(new Paragraph("RECOMENDACIONES", sectionFont));
            document.add(new Paragraph(recomendaciones, normalFont));

            document.add(new Paragraph("MEDICAMENTOS PRESCRITOS", sectionFont));
            document.add(new Paragraph(medicamentosPrescritos, normalFont));

            // Firma
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("_______________________", normalFont));
            document.add(new Paragraph("Firma del médico", normalFont));

            document.close();
            return fileName; // Retornamos el nombre del archivo para confirmar ubicación

        } catch (Exception e) {
            throw new Exception("Error al generar PDF: " + e.getMessage());
        }
    }
}
