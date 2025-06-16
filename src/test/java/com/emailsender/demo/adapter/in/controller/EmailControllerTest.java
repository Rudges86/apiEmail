package com.emailsender.demo.adapter.in.controller;

import com.emailsender.demo.application.service.EmailServiceImpl;
import com.emailsender.demo.controllerAdvice.AdviceController;
import com.emailsender.demo.domain.model.MailSendDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasItem;
/*@Import({AdviceController.class})
@WebMvcTest(controllers = EmailController.class)*/
@SpringBootTest
@AutoConfigureMockMvc
class EmailControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * Toda a parte comentada é utilizando o @WebMvcTest, ele é mais rápido que o @SpringBootTest,
     * É essencial para testes isolados do controller, pois ele só carrega o que é necessário, porém
     * necessita de mais configurações e mocks para testar.
     * No caso desse teste específico eu tenho uma configuração raiz no application para pegar adiconar o /apiemail
     * em todos os constrollers que forem criados. É necessário configurar para pegar o contextPath, coisa que no
     * SpringBootTest não precisaria já que ele recupera tudo
     * */

    /*@Autowired
    private WebApplicationContext wac;*/

    /*@BeforeEach
    void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultRequest(MockMvcRequestBuilders.get("/").contextPath("/apiemail"))
                .build();
    }*/

   /* @Test
    @DisplayName("Testando o valid do spring validation utilizando o @WebMvcTest")
    public void deveRetornarErroQuandoCamposSaoInvalidos() throws Exception {
        MailSendDTO dto = new MailSendDTO("invalido.com", "","");
        String json = objectMapper.writeValueAsString(dto);
        mvc.perform(post("/apiemail/mailSender")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.listaErros").isArray())
                .andExpect(jsonPath("$.listaErros[0]",containsString("Formato do e-mail está inválido")));
    }*/
/**
 * Utilizando o @SpringBootTest com o @AutoConfigureMockMvc
 * Ele carrega todos os beans, carregando todo o contexto sendo mais lento que o @WebMvcTest
 * Mas ele não precisa de configurações adicionais, pois ele carrega tudo no contexto de inicialização
 * */
    @Test
    @DisplayName("Testando o valid do spring validation utilizando o SpringBootTest")
    public void deveRetornarErroQuandoCamposSaoInvalidos() throws Exception {
        MailSendDTO dto = new MailSendDTO("invalido.com", "","");
        String json = objectMapper.writeValueAsString(dto);
        mvc.perform(post("/mailSender")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.listaErros").isArray())
                .andExpect(jsonPath("$.listaErros", hasItem(containsString("O Assunto não deve ser vazio."))));
    }


}