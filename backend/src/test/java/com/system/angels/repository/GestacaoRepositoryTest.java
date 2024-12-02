package com.system.angels.repository;

import com.system.angels.domain.Gestacao;
import com.system.angels.domain.Gestante;
import com.system.angels.domain.User;
import com.system.angels.domain.enums.*;
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
    private UserRepository userRepository;

    @Autowired
    private GestacaoRepository gestacaoRepository;

    @Autowired
    private GestanteRepository gestanteRepository;

    private Gestante gestante;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("maria_user"); // Defina um username válido
        user.setPassword("securePassword"); // Defina uma senha ou outros campos obrigatórios
        user.setName("Maria Silva"); // Defina o campo 'name'
        user.setRole("USER"); // Defina um valor para o campo 'role', se necessário
        user = userRepository.save(user); // Salva o User no banco

        gestante = new Gestante();
        gestante.setCpf("12345678901");
        gestante.setNome("Maria");
        gestante.setDataNascimento(new Date());
        gestante.setEmail("maria.silva@gmail.com");
        gestante.setRaca(Raca.NEGRO);
        gestante.setSexo(Sexo.FEMININO);
        gestante.setUser(user);
        gestanteRepository.save(gestante);
    }

    private Gestacao createGestacao(Gestante gestante) {
        Gestacao gestacao = new Gestacao();
        gestacao.setGestante(gestante);
        gestacao.setConsumoAlcool(true);
        gestacao.setFrequenciaUsoAlcool(UsoAlcool.NENHUM_CONSUMO);
        gestacao.setDataUltimaMenstruacao(new Date());
        gestacao.setDataInicioGestacao(new Date());
        gestacao.setFatorRh(FatorRH.POSITIVO);
        gestacao.setFuma(true);
        gestacao.setQuantidadeCigarrosDia(5);
        gestacao.setUsoDrogas(UsoDrogas.NENHUM_CONSUMO);
        gestacao.setGravidezPlanejada(false);
        gestacao.setGrupoSanguineo(GrupoSanguineo.O);
        gestacao.setPesoAntesGestacao(new BigDecimal("65.5"));
        gestacao.setRiscoGestacional(1);
        gestacao.setRiscoIA(RiscoIA.NAO);
        gestacao.setVacinaHepatiteB(true);
        gestacao.setSituacaoGestacional(SituacaoGestacional.EM_ANDAMENTO);
        gestacao.setUser(user);
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
