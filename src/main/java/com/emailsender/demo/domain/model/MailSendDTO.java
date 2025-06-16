package com.emailsender.demo.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Schema(description = "Detalhes de requisição de um e-mail")
public record MailSendDTO(@NotBlank(message = "O Corpo não deve ser vazio.")
                          @Schema(description = "Exemplo de corpo do e-mail", example = "E-mail enviado",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          String body,
                          @Email(message = "Formato do e-mail está inválido",
                                  regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
                          @Schema(description = "Exemplo destinatário", example = "destinatario@gmail.com",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          String to,
                          @NotBlank(message = "O Assunto não deve ser vazio.")
                          @Schema(description = "Exemplo de assunto", example = "Exemplo de e-mail.",
                                  requiredMode = Schema.RequiredMode.REQUIRED)
                          String subject) {}
