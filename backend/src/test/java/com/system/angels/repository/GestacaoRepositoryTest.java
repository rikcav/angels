package com.system.angels.repository;

import com.system.angels.domain.Gestacao;
import com.system.angels.domain.Gestante;
import com.system.angels.domain.enums.FatorRH;
import com.system.angels.domain.enums.Sexo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GestacaoRepositoryTest {

    @Autowired
    private GestacaoRepository gestacaoRepository;

    @Autowired
    private GestanteRepository gestanteRepository;

    private Gestante gestante;

    @BeforeEach
    public void setUp() {
        gestante = new Gestante();
        gestante.setCpf("12345678901");
        gestante.setNome("Maria");
        gestante.setDataNascimento(new Date());
        gestante.setRaca(0);
        gestante.setSexo(Sexo.FEMININO);
        gestanteRepository.save(gestante);
    }

    private Gestacao createGestacao(Gestante gestante) {
        Gestacao gestacao = new Gestacao();
        gestacao.setGestante(gestante);
        gestacao.setConsumoAlcool(true);
        gestacao.setFrequenciaUsoAlcool(2);
        gestacao.setDataUltimaMenstruacao(new Date());
        gestacao.setDataInicioGestacao(new Date());
        gestacao.setFatorRh(FatorRH.POSITIVO);
        gestacao.setFuma(true);
        gestacao.setQuantidadeCigarrosDia(5);
        gestacao.setUsoDrogas(1);
        gestacao.setGravidezPlanejada(false);
        gestacao.setGrupoSanguineo(1);
        gestacao.setPesoAntesGestacao(new BigDecimal("65.5"));
        gestacao.setRiscoGestacional(1);
        gestacao.setRiscoIA(false);
        gestacao.setVacinaHepatiteB(true);
        gestacao.setSituacaoGestacional(1);
        return gestacao;
    }

    @Test
    public void testFindByGestanteId() {
        Gestacao gestacao1 = createGestacao(gestante);
        Gestacao gestacao2 = createGestacao(gestante);
        gestacaoRepository.save(gestacao1);
        gestacaoRepository.save(gestacao2);

        List<Gestacao> result = gestacaoRepository.findByGestanteId(gestante.getId());

        assertThat(result).hasSize(2);
    }

    @Test
    public void testSaveGestacao() {
        Gestacao gestacao = createGestacao(gestante);

        Gestacao savedGestacao = gestacaoRepository.save(gestacao);

        assertThat(savedGestacao.getId()).isNotNull();
        assertThat(savedGestacao.getGestante().getId()).isEqualTo(gestante.getId());
        assertThat(savedGestacao.getFatorRh()).isEqualTo(FatorRH.POSITIVO);
    }

    @Test
    public void testUpdateGestacao() {
        Gestacao gestacao = createGestacao(gestante);
        Gestacao savedGestacao = gestacaoRepository.save(gestacao);

        savedGestacao.setConsumoAlcool(false);
        Gestacao updatedGestacao = gestacaoRepository.save(savedGestacao);

        assertThat(updatedGestacao.isConsumoAlcool()).isFalse();
    }

    @Test
    public void testDeleteGestacao() {
        Gestacao gestacao = createGestacao(gestante);
        Gestacao savedGestacao = gestacaoRepository.save(gestacao);

        gestacaoRepository.delete(savedGestacao);

        Optional<Gestacao> deletedGestacao = gestacaoRepository.findById(savedGestacao.getId());
        assertThat(deletedGestacao).isNotPresent();
    }

    @Test
    public void testFindGestacaoByGestanteId_NotFound() {
        List<Gestacao> result = gestacaoRepository.findByGestanteId(-1L);

        assertThat(result).isEmpty();
    }
}
