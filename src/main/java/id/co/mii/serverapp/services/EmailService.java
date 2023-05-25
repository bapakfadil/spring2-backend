package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.dto.request.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender mailSender;

    public EmailRequest sendSimpleRequest(EmailRequest emailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());

        mailSender.send(message);
        return emailRequest;
    }

    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText());

            FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttachment()));
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(message);
        } catch (Exception e){
            throw new IllegalStateException("Email failed to send!");
        }
        return emailRequest;
    }

    public EmailRequest sendMessageWithHTML(EmailRequest emailRequest){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            FileSystemResource htmlFile = new FileSystemResource(new File(emailRequest.getText()));
            Scanner scanner = new Scanner(htmlFile.getFile());
            scanner.useDelimiter("\\Z");
            String htmlBody = scanner.next();
            scanner.close();

            helper.setText(htmlBody, true);

            mailSender.send(message);

        } catch (Exception e){
            throw new IllegalStateException("Email failed to send!");
            //e.printStackTrace();
        }
        return emailRequest;
    }
}
