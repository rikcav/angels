package com.system.angels.service;

import com.system.angels.domain.Acompanhamento;
import com.system.angels.domain.Gestacao;
import com.system.angels.dto.create.CadastrarAcompanhamentoDTO;
import com.system.angels.exceptions.AcompanhamentoNotFoundException;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.repository.AcompanhamentoRepository;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.service.impl.AcompanhamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AcompanhamentoServiceTest {

    @Mock
    private AcompanhamentoRepository acompanhamentoRepository;

    @Mock
    private GestacaoRepository gestacaoRepository;

    @InjectMocks
    private AcompanhamentoService acompanhamentoService;

    private Acompanhamento acompanhamento;
    private Gestacao gestacao;
    private CadastrarAcompanhamentoDTO acompanhamentoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup mock data
        gestacao = new Gestacao();
        gestacao.setId(1L);

        acompanhamento = new Acompanhamento();
        acompanhamento.setId(1L);
        acompanhamento.setGestacao(gestacao);

        acompanhamentoDTO = new CadastrarAcompanhamentoDTO(
                1L,
                null,
                null,
                null,
                0,
                null,
                null,
                null, null,
                true
        );
    }

    @Test
    void listarAcompanhamentos() {
        when(acompanhamentoRepository.findAll()).thenReturn(List.of(acompanhamento));

        List<Acompanhamento> result = acompanhamentoService.listarAcompanhamentos();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(acompanhamentoRepository, times(1)).findAll();
    }

    @Test
    void buscarAcompanhamentoPorId_Success() {
        when(acompanhamentoRepository.findById(anyLong())).thenReturn(Optional.of(acompanhamento));

        Acompanhamento result = acompanhamentoService.buscarAcompanhamentoPorId(1L);

        assertNotNull(result);
        assertEquals(acompanhamento.getId(), result.getId());
        verify(acompanhamentoRepository, times(1)).findById(anyLong());
    }

    @Test
    void buscarAcompanhamentoPorId_NotFound() {
        when(acompanhamentoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AcompanhamentoNotFoundException.class, () -> acompanhamentoService.buscarAcompanhamentoPorId(1L));
        verify(acompanhamentoRepository, times(1)).findById(anyLong());
    }

    @Test
    void registrarAcompanhamento() {
        when(acompanhamentoRepository.save(any(Acompanhamento.class))).thenReturn(acompanhamento);

        Acompanhamento result = acompanhamentoService.registrarAcompanhamento(acompanhamento);

        assertNotNull(result);
        assertEquals(acompanhamento.getId(), result.getId());
        verify(acompanhamentoRepository, times(1)).save(acompanhamento);
    }

    @Test
    void deletarAcompanhamento_Success() {
        when(acompanhamentoRepository.findById(anyLong())).thenReturn(Optional.of(acompanhamento));

        assertDoesNotThrow(() -> acompanhamentoService.deletarAcompanhamento(1L));
        verify(acompanhamentoRepository, times(1)).findById(anyLong());
        verify(acompanhamentoRepository, times(1)).delete(acompanhamento);
    }

    @Test
    void deletarAcompanhamento_NotFound() {
        when(acompanhamentoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AcompanhamentoNotFoundException.class, () -> acompanhamentoService.deletarAcompanhamento(1L));
        verify(acompanhamentoRepository, times(1)).findById(anyLong());
    }

    @Test
    void atualizarAcompanhamento_Success() {
        when(acompanhamentoRepository.findById(anyLong())).thenReturn(Optional.of(acompanhamento));
        when(acompanhamentoRepository.save(any(Acompanhamento.class))).thenReturn(acompanhamento);

        Acompanhamento result = acompanhamentoService.atualizarAcompanhamento(1L, acompanhamento);

        assertNotNull(result);
        assertEquals(acompanhamento.getId(), result.getId());
        verify(acompanhamentoRepository, times(1)).findById(anyLong());
        verify(acompanhamentoRepository, times(1)).save(acompanhamento);
    }

    @Test
    void atualizarAcompanhamento_NotFound() {
        when(acompanhamentoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AcompanhamentoNotFoundException.class, () -> acompanhamentoService.atualizarAcompanhamento(1L, acompanhamento));
        verify(acompanhamentoRepository, times(1)).findById(anyLong());
    }

    @Test
    void cadastrarAcompanhamento_Success() {
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.of(gestacao));
        when(acompanhamentoRepository.save(any(Acompanhamento.class))).thenReturn(acompanhamento);

        CadastrarAcompanhamentoDTO result = acompanhamentoService.cadastrarAcompanhamento(1L, acompanhamentoDTO);

        assertNotNull(result);
        assertEquals(acompanhamentoDTO.getGestacao_id(), result.getGestacao_id());
        verify(gestacaoRepository, times(1)).findById(anyLong());
        verify(acompanhamentoRepository, times(1)).save(any(Acompanhamento.class));
    }

    @Test
    void cadastrarAcompanhamento_GestacaoNotFound() {
        when(gestacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GestacaoNotFoundException.class, () -> acompanhamentoService.cadastrarAcompanhamento(1L, acompanhamentoDTO));
        verify(gestacaoRepository, times(1)).findById(anyLong());
        verify(acompanhamentoRepository, never()).save(any(Acompanhamento.class));
    }
}
