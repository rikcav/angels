package com.system.angels.service;

import com.system.angels.domain.Gestacao;
import com.system.angels.domain.Gestante;
import com.system.angels.domain.User;
import com.system.angels.domain.enums.*;
import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.dto.response.GestacaoComGestanteDTO;
import com.system.angels.dto.response.GestacaoRO;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.repository.UserRepository;
import com.system.angels.service.impl.EmailService;
import com.system.angels.service.impl.GestacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class GestacaoServiceTest {

    @Mock
    private GestacaoRepository gestacaoRepository;

    @Mock
    private GestanteRepository gestanteRepository;

    @Mock
    private UserRepository userRepository; // Mock do UserRepository

    @InjectMocks
    private GestacaoService gestacaoService;

    @Mock
    private EmailService emailService;

    private GestacaoDTO gestacaoDTO;
    private Gestacao gestacao;
    private Gestante gestante;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        gestante = new Gestante();
        gestante.setId(1L);

        gestacaoDTO = new GestacaoDTO(
                1L, true, UsoAlcool.NENHUM_CONSUMO, new Date(), new Date(),
                FatorRH.POSITIVO, false, 10, UsoDrogas.NENHUM_CONSUMO, true,
                GrupoSanguineo.A, new BigDecimal("60.5"), RiscoIA.NAO, 2,
                true, "mariazinha", SituacaoGestacional.EM_ANDAMENTO
        );

        gestacao = new Gestacao();
        gestacao.setId(1L);
        gestacao.setGestante(gestante);
        gestacao.setConsumoAlcool(true);

        // Mock do retorno do userRepository
        User user = new User();
        user.setUsername("mariazinha");
        when(userRepository.findByUsername("mariazinha")).thenReturn(Optional.of(user));
    }

    @Test
    void testRegistrarGestacao() {
        when(gestanteRepository.findById(anyLong())).thenReturn(Optional.of(gestante));
        when(gestacaoRepository.save(any(Gestacao.class))).thenReturn(gestacao);

        GestacaoRO result = gestacaoService.registrarGestacao(gestacaoDTO);

        assertNotNull(result);
        assertEquals(gestacao.getGestante().getId(), result.gestanteId());
        verify(gestanteRepository, times(1)).findById(anyLong());
        verify(gestacaoRepository, times(1)).save(any(Gestacao.class));
    }

    @Test
    void testRegistrarGestacaoThrowsGestanteNotFoundException() {
        when(gestanteRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GestanteNotFoundException.class, () -> gestacaoService.registrarGestacao(gestacaoDTO));
        verify(gestanteRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGestacaoPorId() {
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.of(gestacao));

        GestacaoRO result = gestacaoService.gestacaoPorId(1L);

        assertNotNull(result);
        assertEquals(gestacao.getGestante().getId(), result.gestanteId());
        verify(gestacaoRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGestacaoPorIdThrowsGestacaoNotFoundException() {
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GestacaoNotFoundException.class, () -> gestacaoService.gestacaoPorId(1L));
        verify(gestacaoRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGestacaoExiste() {
        when(gestacaoRepository.existsById(anyLong())).thenReturn(true);

        assertTrue(gestacaoService.gestacaoExiste(1L));
        verify(gestacaoRepository, times(1)).existsById(anyLong());
    }

    @Test
    void testGestacoes() {
        when(gestacaoRepository.findAll()).thenReturn(Collections.singletonList(gestacao));

        List<GestacaoComGestanteDTO> result = gestacaoService.gestacoes();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(gestacaoRepository, times(1)).findAll();
    }

    @Test
    void testAtualizarGestacao() {
        when(gestanteRepository.findById(anyLong())).thenReturn(Optional.of(gestante));
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.of(gestacao));
        when(gestacaoRepository.save(any(Gestacao.class))).thenReturn(gestacao);

        GestacaoRO result = gestacaoService.atualizarGestacao(1L, gestacaoDTO);

        assertNotNull(result);
        assertEquals(gestacao.getGestante().getId(), result.gestanteId());
        verify(gestacaoRepository, times(1)).findById(anyLong());
        verify(gestacaoRepository, times(1)).save(any(Gestacao.class));
        verify(gestanteRepository, times(1)).findById(anyLong());
    }

    @Test
    void testAtualizarGestacaoThrowsGestacaoNotFoundException() {
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GestacaoNotFoundException.class, () -> gestacaoService.atualizarGestacao(1L, gestacaoDTO));
        verify(gestacaoRepository, times(1)).findById(anyLong());
    }

    @Test
    void testDeletarGestacao() {
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.of(gestacao));

        gestacaoService.deletarGestacao(1L);

        verify(gestacaoRepository, times(1)).findById(anyLong());
        verify(gestacaoRepository, times(1)).delete(any(Gestacao.class));
    }

    @Test
    void testDeletarGestacaoThrowsGestacaoNotFoundException() {
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GestacaoNotFoundException.class, () -> gestacaoService.deletarGestacao(1L));
        verify(gestacaoRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGestacaoPorGestanteId() {
        when(gestacaoRepository.findByGestanteId(anyLong())).thenReturn(Collections.singletonList(gestacao));

        List<GestacaoRO> result = gestacaoService.gestacaoPorGestanteId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(gestacaoRepository, times(1)).findByGestanteId(anyLong());
    }
}
