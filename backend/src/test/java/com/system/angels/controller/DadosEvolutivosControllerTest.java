package com.system.angels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.angels.domain.enums.DiagnosticoDesnutricao;
import com.system.angels.domain.enums.EstadoCivil;
import com.system.angels.domain.enums.TipoMoradia;
import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.dto.response.DadosEvolutivosRO;
import com.system.angels.service.iDadosEvolutivosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DadosEvolutivosController.class)
public class DadosEvolutivosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private iDadosEvolutivosService dadosEvolutivosService;

    @Autowired
    private ObjectMapper objectMapper;

    private DadosEvolutivosDTO dadosEvolutivosDTO;
    private DadosEvolutivosRO dadosEvolutivosRO;

    @BeforeEach
    public void setup() {
        dadosEvolutivosDTO = new DadosEvolutivosDTO(
                1L, "City", DiagnosticoDesnutricao.SEM_DIAGNOSTICO_DE_DESNUTRICAO, true, 2, TipoMoradia.TIJOLO,
                true, BigDecimal.valueOf(2000), true, true, true, new Date(),
                false, EstadoCivil.CASADA, 1, 1, 0, 1,
                1, 0, 0, 0, 1,
                1, 0, 2, 1, 1,
                false, true, false, false, true, true,
                "123456789", "987654321");

        dadosEvolutivosRO = new DadosEvolutivosRO(
                1L, 1L, "City", DiagnosticoDesnutricao.SEM_DIAGNOSTICO_DE_DESNUTRICAO, true, 2, TipoMoradia.TIJOLO,
                true, BigDecimal.valueOf(2000), true, true, true, new Date(),
                false, EstadoCivil.CASADA, 1, 1, 0, 1,
                1, 0, 0, 0, 1,
                1, 0, 2, 1, 1,
                false, true, false, false, true, true,
                "123456789", "987654321");
    }

    @Test
    public void testDadosEvolutivosPorId() throws Exception {
        Mockito.when(dadosEvolutivosService.dadosEvolutivosPorId(anyLong())).thenReturn(dadosEvolutivosRO);

        mockMvc.perform(get("/dados-evolutivos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dadosEvolutivosRO.id()))
                .andExpect(jsonPath("$.municipio").value(dadosEvolutivosRO.municipio()));
    }

    @Test
    public void testDadosEvolutivosPorGestante() throws Exception {
        Mockito.when(dadosEvolutivosService.dadosEvolutivosPorGestante(anyLong())).thenReturn(Collections.singletonList(dadosEvolutivosRO));

        mockMvc.perform(get("/dados-evolutivos/gestante/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(dadosEvolutivosRO.id()))
                .andExpect(jsonPath("$[0].municipio").value(dadosEvolutivosRO.municipio()));
    }

    @Test
    public void testAtualizarDadosEvolutivos() throws Exception {
        Mockito.when(dadosEvolutivosService.registrarDadosEvolutivos(any(DadosEvolutivosDTO.class))).thenReturn(dadosEvolutivosRO);

        mockMvc.perform(post("/dados-evolutivos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosEvolutivosDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(dadosEvolutivosRO.id()))
                .andExpect(jsonPath("$.municipio").value(dadosEvolutivosRO.municipio()));
    }
}
