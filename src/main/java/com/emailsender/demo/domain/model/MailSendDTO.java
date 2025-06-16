package com.emailsender.demo.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record MailSendDTO(@NotBlank(message = "O Corpo não deve ser vazio.") String body,
                          @Email(message = "Formato do e-mail está inválido",
                                  regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$") String to,
                          @NotBlank(message = "O Assunto não deve ser vazio.") String subject) {}
