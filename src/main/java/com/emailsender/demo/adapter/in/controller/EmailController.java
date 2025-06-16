package com.emailsender.demo.adapter.in.controller;

import com.emailsender.demo.domain.model.MailSendDTO;
import com.emailsender.demo.domain.model.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailsender.demo.application.service.EmailServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name="Controller E-mails", description = "Controller para enviar e-mail")
@RestController
@RequestMapping("/mailSender")
public class EmailController {
    @Autowired
    private EmailServiceImpl emailService;

    @Operation(summary = "Enviar e-mail", description = "Envia um e-mail para um destinatário informado.")
    @PostMapping()
    public ResponseEntity<ResponseDTO> mailSender(@RequestBody @Valid MailSendDTO dto) {
        return ResponseEntity.ok().body(new ResponseDTO(emailService.sendMail(dto), HttpStatus.OK));
    }
    
    
}
