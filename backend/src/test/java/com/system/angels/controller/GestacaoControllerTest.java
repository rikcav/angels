package com.system.angels.controller;

import com.system.angels.domain.enums.*;
import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.dto.response.GestacaoComGestanteDTO;
import com.system.angels.dto.response.GestacaoRO;
import com.system.angels.service.impl.GestacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class GestacaoControllerTest {

    @Mock
    private GestacaoService gestacaoService;

    @InjectMocks
    private GestacaoController gestacaoController;

    private GestacaoDTO gestacaoDTO;
    private GestacaoRO gestacaoRO;
    private GestacaoComGestanteDTO gestacaoComGestanteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        gestacaoDTO = new GestacaoDTO(
                1L, true, UsoAlcool.NENHUM_CONSUMO, new Date(), new Date(),
                FatorRH.POSITIVO, false, 10, UsoDrogas.NENHUM_CONSUMO, true,
                GrupoSanguineo.A, new BigDecimal("60.5"), true, 2,
                true, SituacaoGestacional.EM_ANDAMENTO
        );

        gestacaoRO = new GestacaoRO(
                1L, 1L, true, UsoAlcool.NENHUM_CONSUMO, new Date(), new Date(),
                FatorRH.POSITIVO, false, 10, UsoDrogas.NENHUM_CONSUMO, true,
                GrupoSanguineo.A, new BigDecimal("60.5"), 2, true,
                true, SituacaoGestacional.EM_ANDAMENTO
        );

        gestacaoComGestanteDTO = new GestacaoComGestanteDTO();
    }

    @Test
    void testGestacoes() {
        when(gestacaoService.gestacoes()).thenReturn(List.of(gestacaoComGestanteDTO));

        ResponseEntity<List<GestacaoComGestanteDTO>> response = gestacaoController.gestacoes();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(gestacaoService, times(1)).gestacoes();
    }

    @Test
    void testGestacaoPorGestanteId() {
        when(gestacaoService.gestacaoPorGestanteId(anyLong())).thenReturn(List.of(gestacaoRO));

        ResponseEntity<List<GestacaoRO>> response = gestacaoController.gestacaoPorGestanteId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(gestacaoService, times(1)).gestacaoPorGestanteId(anyLong());
    }

    @Test
    void testGestacaoPorId() {
        when(gestacaoService.gestacaoPorId(anyLong())).thenReturn(gestacaoRO);

        ResponseEntity<GestacaoRO> response = gestacaoController.gestacaoPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gestacaoRO, response.getBody());
        verify(gestacaoService, times(1)).gestacaoPorId(anyLong());
    }

    @Test
    void testCadastrarGestacao() {
        when(gestacaoService.registrarGestacao(any(GestacaoDTO.class))).thenReturn(gestacaoRO);

        ResponseEntity<GestacaoRO> response = gestacaoController.cadastrarGestacao(gestacaoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gestacaoRO, response.getBody());
        verify(gestacaoService, times(1)).registrarGestacao(any(GestacaoDTO.class));
    }

    @Test
    void testAtualizarGestacao() {
        when(gestacaoService.atualizarGestacao(anyLong(), any(GestacaoDTO.class))).thenReturn(gestacaoRO);

        ResponseEntity<GestacaoRO> response = gestacaoController.atualizarGestacao(1L, gestacaoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gestacaoRO, response.getBody());
        verify(gestacaoService, times(1)).atualizarGestacao(anyLong(), any(GestacaoDTO.class));
    }

    @Test
    void testDeletarGestacao() {
        doNothing().when(gestacaoService).deletarGestacao(anyLong());

        ResponseEntity<Void> response = gestacaoController.deletarGestacao(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(gestacaoService, times(1)).deletarGestacao(anyLong());
    }
}
