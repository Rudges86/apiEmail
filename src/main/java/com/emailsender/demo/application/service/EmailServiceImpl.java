package com.emailsender.demo.application.service;
import com.emailsender.demo.adapter.out.smtp.SmtpEmailAdapter;
import com.emailsender.demo.domain.model.MailSendDTO;
import com.emailsender.demo.domain.port.EmailServicePort;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailServicePort {
    private SmtpEmailAdapter emailAdapter;

    public EmailServiceImpl(SmtpEmailAdapter emailAdapter) {
        this.emailAdapter = emailAdapter;
    }

    public String sendMail(MailSendDTO dto) {
            return emailAdapter.sendMail(dto);
        }

}
