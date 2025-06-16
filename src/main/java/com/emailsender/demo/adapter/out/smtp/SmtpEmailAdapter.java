package com.emailsender.demo.adapter.out.smtp;

import com.emailsender.demo.domain.model.MailSendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SmtpEmailAdapter {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String remetente;

    public String sendMail(MailSendDTO dto){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(dto.to());
            message.setSubject(dto.subject());
            message.setText(dto.body());
            mailSender.send(message);

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao enviar e-mail", ex);
        }
        return "Enviado com sucesso";
    }
}
