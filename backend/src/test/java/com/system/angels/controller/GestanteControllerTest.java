package com.system.angels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.angels.domain.enums.Sexo;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.dto.response.GestanteRO;
import com.system.angels.service.impl.GestanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GestanteController.class)
public class GestanteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestanteService gestanteService;

    @Autowired
    private ObjectMapper objectMapper;

    private GestanteDTO gestanteDTO;
    private GestanteRO gestanteRO;

    @BeforeEach
    public void setup() {
        gestanteDTO = new GestanteDTO(
                "Maria Silva",
                new Date(),
                "12345678900",
                1,
                Sexo.FEMININO);
        gestanteRO = new GestanteRO(
                1L,
                "Maria Silva",
                new Date(),
                "12345678900",
                Sexo.FEMININO,
                "City",
                false,
                0,
                1,
                0,
                1,
                0,
                1,
                false,
                false,
                false);
    }

    @Test
    public void testGestantes() throws Exception {
        Mockito.when(gestanteService.gestantes()).thenReturn(Collections.singletonList(gestanteRO));

        mockMvc.perform(get("/gestantes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(gestanteRO.id()))
                .andExpect(jsonPath("$[0].nome").value(gestanteRO.nome()));
    }

    @Test
    public void testGestantePorId() throws Exception {
        Mockito.when(gestanteService.gestantePorId(anyLong())).thenReturn(gestanteRO);

        mockMvc.perform(get("/gestantes/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(gestanteRO.id()))
                .andExpect(jsonPath("$.nome").value(gestanteRO.nome()));
    }

    @Test
    public void testGestantePorCpf() throws Exception {
        Mockito.when(gestanteService.gestantePorCpf(anyString())).thenReturn(gestanteRO);

        mockMvc.perform(get("/gestantes/cpf/{cpf}", "12345678900")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(gestanteRO.id()))
                .andExpect(jsonPath("$.nome").value(gestanteRO.nome()));
    }

    @Test
    public void testCadastrarGestante() throws Exception {
        Mockito.when(gestanteService.registrarGestante(any(GestanteDTO.class))).thenReturn(gestanteRO);

        mockMvc.perform(post("/gestantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gestanteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(gestanteRO.id()))
                .andExpect(jsonPath("$.nome").value(gestanteRO.nome()));
    }

    @Test
    public void testAtualizarGestante() throws Exception {
        Mockito.when(gestanteService.atualizarGestante(anyLong(), any(GestanteDTO.class))).thenReturn(gestanteRO);

        mockMvc.perform(put("/gestantes/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gestanteDTO)))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").value(gestanteRO.id()))
                .andExpect(jsonPath("$.nome").value(gestanteRO.nome()));
    }

    @Test
    public void testDeletarGestante() throws Exception {
        mockMvc.perform(delete("/gestantes/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
