/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

// Importaciones específicas de iText
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

// Importaciones de Java
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Osvaldo
 */
public class PDFGenerator {

    public void generarReporteMedico(GenerarReporteMedicoCommand comando) throws IOException {
        if (comando == null) {
            throw new IllegalArgumentException("El comando no puede ser nulo");
        }
        if (comando.getIdPaciente() == null || comando.getIdPaciente().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del paciente es requerido");
        }

        Document document = new Document();
        try {
            // Crear directorio para reportes si no existe
            File reportesDir = new File("reportes");
            if (!reportesDir.exists()) {
                reportesDir.mkdirs();
            }

            String fileName = String.format("reportes/REPORTE_%s_%s.pdf",
                    comando.getIdPaciente(),
                    comando.getFechaGeneracion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")));

            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Título del reporte (quitamos el logo temporalmente)
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("REPORTE MÉDICO", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Información del doctor
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            document.add(new Paragraph("Doctor: " + comando.getNombreDoctor(), normalFont));
            document.add(new Paragraph("Especialidad: " + comando.getEspecialidadDoctor(), normalFont));
            document.add(new Paragraph("Identificación: " + comando.getIdDoctor(), normalFont));
            document.add(new Paragraph("\n"));

            // Información del paciente
            document.add(new Paragraph("Paciente: " + comando.getNombrePaciente(), normalFont));
            document.add(new Paragraph("Identificación: " + comando.getIdPaciente(), normalFont));
            document.add(new Paragraph("Fecha: "
                    + comando.getFechaGeneracion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    normalFont));
            document.add(new Paragraph("\n"));

            // Contenido del reporte
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 11);
            document.add(new Paragraph("REPORTE MÉDICO:", contentFont));
            document.add(new Paragraph(comando.getReporteTexto(), contentFont));

            // Firma del doctor
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("_______________________", normalFont));
            document.add(new Paragraph("Firma del médico", normalFont));

            // Agregar logo (después de document.open())
            try {
                Image logo = Image.getInstance("src/resources/LogoHospital.png");
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            } catch (IOException e) {
                // Si no encuentra el logo, continuamos sin él
                System.out.println("No se pudo cargar el logo: " + e.getMessage());
            }

        } catch (DocumentException e) {
            throw new IOException("Error al generar el PDF: " + e.getMessage(), e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}
