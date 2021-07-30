package com.alexei.proposta;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import com.alexei.proposta.controllers.client.documento.StatusCliente;
import com.alexei.proposta.controllers.form.CarteiraForm;
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
public class TesteAssociaPaypal {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropostaRepository propostaRepository;

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
        proposta.addCartao("5565-6046-7953-5866");

        propostaRepository.save(proposta);

    }

    @Test
    public void deveRetornarBadRequest() throws Exception {
        Long id = (long) 0;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/carteira/paypal/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarOk() throws Exception {
        CarteiraForm form = new CarteiraForm();
        form.setCarteira("paypal");
        form.setEmail("email@email.com");
        Long id = (long) 1;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/carteira/paypal/{id}", id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
            .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarErroDeValidadaoNotBlank() throws Exception {
        CarteiraForm form = new CarteiraForm();
        form.setCarteira("");
        form.setEmail("");
        Long id = (long) 1;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/carteira/paypal/{id}", id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deveCadastrarNovaCarteira() throws Exception {
        CarteiraForm form = new CarteiraForm();
        form.setCarteira("paypal");
        form.setEmail("email@email.com");
        Long id = (long) 1;
        this.mockMvc.perform(MockMvcRequestBuilders.post("/carteira/paypal/{id}", id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form)))
            .andExpect(status().isOk());
    }

}
