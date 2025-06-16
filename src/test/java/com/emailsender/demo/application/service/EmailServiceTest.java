package com.emailsender.demo.application.service;

import com.emailsender.demo.adapter.out.smtp.SmtpEmailAdapter;
import com.emailsender.demo.domain.model.MailSendDTO;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
    @Mock
    private EmailServiceImpl impl;

    @InjectMocks
    private SmtpEmailAdapter smtp;

    @Test
    @DisplayName("Enviar email com sucesso.")
    void enviarEmailSucesso() {
        MailSendDTO email = new MailSendDTO("Sucesso","teste@gmail.com","teste");
        Assertions.assertDoesNotThrow(() -> impl.sendMail(email));
    }

    @Test
    @DisplayName("Deve lançar exceção")
    void deveLancarExcecao() {
        doThrow(new RuntimeException("Falha ao enviar")).when(impl).sendMail(any());
        MailSendDTO email = new MailSendDTO("falho", "falhou@gmail.com","testeFalha");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> impl.sendMail(email));
        assertTrue(exception.getMessage().contains("Falha ao enviar"));
    }
}