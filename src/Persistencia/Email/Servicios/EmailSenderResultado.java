/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Email.Servicios;

import Persistencia.Email.ConfiguracionEmail.EmailConfig;
import Persistencia.Email.DTO.EmailDTO;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 *
 * @author Osvaldo
 */
public class EmailSenderResultado {
    
    public void enviarResultadoMedico(EmailDTO emailDTO) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", EmailConfig.getHost());
        props.put("mail.smtp.port", EmailConfig.getPort());

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    EmailConfig.getUsername(),
                    EmailConfig.getPassword()
                );
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EmailConfig.getUsername()));
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(emailDTO.getDestinatario())
        );
        message.setSubject(emailDTO.getAsunto());

        // Crear el cuerpo del mensaje y el adjunto
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(emailDTO.getCuerpo());

        // Parte para el archivo adjunto
        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(emailDTO.getRutaAdjunto());

        // Combinar las partes
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        // Enviar el mensaje
        Transport.send(message);
    }
}
