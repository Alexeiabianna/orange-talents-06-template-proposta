package com.alexei.proposta;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import com.alexei.proposta.controllers.BloqueioForm;
import com.alexei.proposta.controllers.client.documento.StatusCliente;
import com.alexei.proposta.models.Proposta;
import com.alexei.proposta.repository.PropostaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class TesteBloqueioCartao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropostaRepository propostaRepository;

    private Proposta proposta;
    private BloqueioForm form;

    @BeforeEach
    public void init() {
        String documento = "02814359010";
        String email = "email@email.com";
        String nome = "Fulano de Tal";
        BigDecimal salario = new BigDecimal("5000");
        String rua = "Rua A";
        String cep = "91420560";
        String bairro = "Gloria";
        String numero = "241";
        String complemento = "casa";
        String cidade = "Florianopolis";
        StatusCliente status = StatusCliente.SEM_RESTRICAO;
        proposta = new Proposta(documento, email, nome, salario, rua, cep, bairro, numero, complemento, cidade, status);

        propostaRepository.save(proposta);

    }

    @Test
    public void deveRetornarNotFound() throws Exception {
        Long id = (long) 0;
        String userAgente = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/bloqueio/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString("value")).header("X-Forward-For", "192.168.0.1")
                .header("User-Agent", userAgente)).andExpect(status().isNotFound());
    }

    @Test
    public void deveCadastrarOBloqueio() throws Exception {
        Long id = proposta.getId();
        String userAgente = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
        String ipClient = "192.168.0.1";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/bloqueio/{id}", id)
                .header("X-Forward-For", ipClient)
                .header("User-Agent", userAgente)).andExpect(status().isCreated());
    }

}
