package com.system.angels.service;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.dto.response.DadosEvolutivosRO;
import com.system.angels.exceptions.DadosEvolutivosNotFoundException;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.DadosEvolutivosRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.service.impl.DadosEvolutivosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DadosEvolutivosServiceTest {

    @Mock
    private DadosEvolutivosRepository dadosEvolutivosRepository;

    @Mock
    private GestanteRepository gestanteRepository;

    @InjectMocks
    private DadosEvolutivosService dadosEvolutivosService;

    private Gestante gestante;
    private DadosEvolutivos dadosEvolutivos;
    private DadosEvolutivosDTO dadosEvolutivosDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        gestante = new Gestante();
        gestante.setId(1L);
        gestante.setNome("Maria");

        dadosEvolutivos = new DadosEvolutivos();
        dadosEvolutivos.setId(1L);
        dadosEvolutivos.setGestante(gestante);
        dadosEvolutivos.setMunicipio("City A");
        dadosEvolutivos.setDiagnosticoDesnutricao(1);
        dadosEvolutivos.setEnergiaEletricaDomicilio(true);
        dadosEvolutivos.setEscolaridade(2);
        dadosEvolutivos.setTipoMoradia(1);
        dadosEvolutivos.setMoradiaRedeEsgoto(true);
        dadosEvolutivos.setRendaFamiliar(new BigDecimal("1500.50"));
        dadosEvolutivos.setTratamentoAgua(true);
        dadosEvolutivos.setAmamentacao(true);
        dadosEvolutivos.setChefeFamilia(false);
        dadosEvolutivos.setDataUltimaGestacao(new Date());
        dadosEvolutivos.setEmRisco(false);
        dadosEvolutivos.setEstadoCivil(1);
        dadosEvolutivos.setQuantidadeAbortos(0);
        dadosEvolutivos.setQuantidadeFilhosVivos(1);
        dadosEvolutivos.setQuantidadeGemelares(0);
        dadosEvolutivos.setQuantidadeGestacao(1);
        dadosEvolutivos.setQuantidadeNascidosMortos(0);
        dadosEvolutivos.setQuantidadeNascidosVivos(1);
        dadosEvolutivos.setQuantidadeObitosSemana1(0);
        dadosEvolutivos.setQuantidadeObitosAposSemana1(0);
        dadosEvolutivos.setQuantidadePartos(1);
        dadosEvolutivos.setQuantidadePartosCesarios(0);
        dadosEvolutivos.setQuantidadePartosVaginais(1);
        dadosEvolutivos.setQuantidadeRnPeso2500_4000(1);
        dadosEvolutivos.setQuantidadeRnPesoMaior4000(0);
        dadosEvolutivos.setQuantidadeRnPesoMenor2500(0);
        dadosEvolutivos.setHipertensao(false);
        dadosEvolutivos.setDiabetes(false);
        dadosEvolutivos.setCirurgiaPelvica(false);
        dadosEvolutivos.setInfeccaoUrinaria(false);
        dadosEvolutivos.setMaFormacaoCongenita(false);
        dadosEvolutivos.setFamiliarGemeos(false);
        dadosEvolutivos.setContato("1111111111");
        dadosEvolutivos.setContatoEmergencia("2222222222");

        dadosEvolutivosDTO = new DadosEvolutivosDTO(
                1L, "City A", 1, true, 2, 1, true, new BigDecimal("1500.50"), true,
                true, false, new Date(), false, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1,
                1, 0, 0, false, false, false, false, false, false, "1111111111", "2222222222"
        );
    }

    @Test
    public void testDadosEvolutivosPorGestante() {
        when(dadosEvolutivosRepository.findAllByGestante_id(1L)).thenReturn(List.of(dadosEvolutivos));

        List<DadosEvolutivosRO> result = dadosEvolutivosService.dadosEvolutivosPorGestante(1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).municipio()).isEqualTo("City A");
    }

    @Test
    public void testDadosEvolutivosPorId() {
        when(dadosEvolutivosRepository.findById(1L)).thenReturn(Optional.of(dadosEvolutivos));

        DadosEvolutivosRO result = dadosEvolutivosService.dadosEvolutivosPorId(1L);

        assertThat(result).isNotNull();
        assertThat(result.municipio()).isEqualTo("City A");
    }

    @Test
    public void testDadosEvolutivosPorId_NotFound() {
        when(dadosEvolutivosRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DadosEvolutivosNotFoundException.class, () -> dadosEvolutivosService.dadosEvolutivosPorId(1L));
    }

    @Test
    public void testRegistrarDadosEvolutivos() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.of(gestante));
        when(dadosEvolutivosRepository.save(any(DadosEvolutivos.class))).thenReturn(dadosEvolutivos);

        DadosEvolutivosRO result = dadosEvolutivosService.registrarDadosEvolutivos(dadosEvolutivosDTO);

        assertThat(result).isNotNull();
        assertThat(result.municipio()).isEqualTo("City A");
        assertThat(result.gestanteId()).isEqualTo(1L);
    }

    @Test
    public void testRegistrarDadosEvolutivos_GestanteNotFound() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GestanteNotFoundException.class, () -> dadosEvolutivosService.registrarDadosEvolutivos(dadosEvolutivosDTO));
    }
}

