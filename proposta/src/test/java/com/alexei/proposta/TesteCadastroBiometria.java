package com.alexei.proposta;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import com.alexei.proposta.controllers.BiometriaForm;
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
public class TesteCadastroBiometria {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropostaRepository propostaRepository;

    private BiometriaForm form;

    private Proposta proposta;

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

    public void deveRetornarUmaMensagem() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void deveRetornarBadRequest() throws Exception {
        Long id = (long) 0;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/biometria/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveReceberFormularioDaRequestERetornarNotFound() throws Exception {
        String fingerPrint = "000000000000000000000000000000";
        form = new BiometriaForm();
        form.setFingerPrint(fingerPrint);
        Long id = (long) 0;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/biometria/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form))).andExpect(status().isNotFound());
    }

    @Test
    public void deveCadastrarBiometriaNoSistema() throws Exception {
        String fingerPrint = "000000000000000000000000000000";
        form = new BiometriaForm();
        form.setFingerPrint(fingerPrint);
        Long id = proposta.getId();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/biometria/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form))).andExpect(status().isCreated());
    }

}
