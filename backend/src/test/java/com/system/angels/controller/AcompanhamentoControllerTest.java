package com.system.angels.controller;

import com.system.angels.domain.Acompanhamento;
import com.system.angels.domain.Gestacao;
import com.system.angels.dto.create.CadastrarAcompanhamentoDTO;
import com.system.angels.dto.response.VisualizarAcompanhamentoDTO;
import com.system.angels.dto.update.AtualizarAcompanhamentoDTO;
import com.system.angels.service.impl.AcompanhamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AcompanhamentoControllerTest {

    @Mock
    private AcompanhamentoService acompanhamentoService;

    @InjectMocks
    private AcompanhamentoController acompanhamentoController;

    private Acompanhamento acompanhamento;
    private CadastrarAcompanhamentoDTO cadastrarAcompanhamentoDTO;
    private VisualizarAcompanhamentoDTO visualizarAcompanhamentoDTO;
    private AtualizarAcompanhamentoDTO atualizarAcompanhamentoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Gestacao gestacao = new Gestacao();
        gestacao.setId(1L);

        acompanhamento = new Acompanhamento();
        acompanhamento.setId(1L);
        acompanhamento.setDataAcompanhamento(new Date());
        acompanhamento.setGestacao(gestacao);

        cadastrarAcompanhamentoDTO = new CadastrarAcompanhamentoDTO(acompanhamento);
        visualizarAcompanhamentoDTO = new VisualizarAcompanhamentoDTO(acompanhamento);
        atualizarAcompanhamentoDTO = new AtualizarAcompanhamentoDTO(acompanhamento);
    }

    @Test
    void testListarAcompanhamentos() {
        when(acompanhamentoService.listarAcompanhamentos()).thenReturn(List.of(acompanhamento));

        ResponseEntity<List<Acompanhamento>> response = acompanhamentoController.listarAcompanhamentos();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(acompanhamentoService, times(1)).listarAcompanhamentos();
    }

    @Test
    void testObterAcompanhamentoPorId() {
        when(acompanhamentoService.buscarAcompanhamentoPorId(anyLong())).thenReturn(acompanhamento);

        ResponseEntity<VisualizarAcompanhamentoDTO> response = acompanhamentoController.obterAcompanhamentoPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        VisualizarAcompanhamentoDTO actualDTO = response.getBody();
        assertNotNull(actualDTO);

        assertEquals(visualizarAcompanhamentoDTO.getId(), actualDTO.getId());
        assertEquals(visualizarAcompanhamentoDTO.getGestacaoId(), actualDTO.getGestacaoId());
        assertEquals(visualizarAcompanhamentoDTO.getDataAcompanhamento(), actualDTO.getDataAcompanhamento());
        assertEquals(visualizarAcompanhamentoDTO.getPesoAtual(), actualDTO.getPesoAtual());
        assertEquals(visualizarAcompanhamentoDTO.getIdadeGestacional(), actualDTO.getIdadeGestacional());
        assertEquals(visualizarAcompanhamentoDTO.getPressaoArterial(), actualDTO.getPressaoArterial());
        assertEquals(visualizarAcompanhamentoDTO.getBatimentosCardiacosFeto(), actualDTO.getBatimentosCardiacosFeto());
        assertEquals(visualizarAcompanhamentoDTO.getAlturaUterina(), actualDTO.getAlturaUterina());
        assertEquals(visualizarAcompanhamentoDTO.getRiscoIA(), actualDTO.getRiscoIA());

        verify(acompanhamentoService, times(1)).buscarAcompanhamentoPorId(anyLong());
    }

    @Test
    void testCadastrarAcompanhamento() {
        when(acompanhamentoService.cadastrarAcompanhamento(anyLong(), any(CadastrarAcompanhamentoDTO.class)))
                .thenReturn(cadastrarAcompanhamentoDTO);

        ResponseEntity<CadastrarAcompanhamentoDTO> response = acompanhamentoController
                .cadastrarAcompanhamento(1L, cadastrarAcompanhamentoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cadastrarAcompanhamentoDTO, response.getBody());
        verify(acompanhamentoService, times(1)).cadastrarAcompanhamento(anyLong(), any(CadastrarAcompanhamentoDTO.class));
    }

    @Test
    void testAtualizarAcompanhamento() {
        when(acompanhamentoService.atualizarAcompanhamento(anyLong(), any(Acompanhamento.class))).thenReturn(acompanhamento);

        Acompanhamento atualizarAcompanhamento = new Acompanhamento();
        atualizarAcompanhamento.setId(1L);
        atualizarAcompanhamento.setDataAcompanhamento(new Date());

        ResponseEntity<AtualizarAcompanhamentoDTO> response = acompanhamentoController.atualizarAcompanhamento(1L, atualizarAcompanhamento);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        AtualizarAcompanhamentoDTO actualDTO = response.getBody();
        assertNotNull(actualDTO);

        assertEquals(atualizarAcompanhamentoDTO.getId(), actualDTO.getId());
        assertEquals(atualizarAcompanhamentoDTO.getDataAcompanhamento(), actualDTO.getDataAcompanhamento());

        verify(acompanhamentoService, times(1)).atualizarAcompanhamento(anyLong(), any(Acompanhamento.class));
    }

    @Test
    void testDeletarAcompanhamento() {
        doNothing().when(acompanhamentoService).deletarAcompanhamento(anyLong());

        ResponseEntity<Void> response = acompanhamentoController.deletarAcompanhamento(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(acompanhamentoService, times(1)).deletarAcompanhamento(anyLong());
    }
}
