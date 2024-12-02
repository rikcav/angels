package com.system.angels.repository;

import com.system.angels.domain.Acompanhamento;
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
public class AcompanhamentoRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcompanhamentoRepository acompanhamentoRepository;

    @Autowired
    private GestacaoRepository gestacaoRepository;

    @Autowired
    private GestanteRepository gestanteRepository;

    private Gestacao gestacao;

    @BeforeEach
    public void setUp() {
        // Criação e persistência do User
        User user = new User();
        user.setUsername("maria_user"); // Defina um username válido
        user.setPassword("securePassword"); // Defina uma senha ou outros campos obrigatórios
        user.setName("Maria Silva"); // Defina o campo 'name'
        user.setRole("USER"); // Defina um valor para o campo 'role', se necessário
        userRepository.save(user);

        // Criação e persistência do Gestante
        Gestante gestante = new Gestante();
        gestante.setCpf("12345678901");
        gestante.setNome("Maria");
        gestante.setDataNascimento(new Date());
        gestante.setEmail("maria.silva@gmail.com");
        gestante.setRaca(Raca.NEGRO);
        gestante.setSexo(Sexo.FEMININO);
        gestante.setUser(user); // Associe o User já salvo
        gestanteRepository.save(gestante);

        // Criação e persistência do Gestacao
        gestacao = new Gestacao();
        gestacao.setId(99L);
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
        gestacaoRepository.save(gestacao);
    }

    private Acompanhamento createAcompanhamento(Gestacao gestacao, Date data, BigDecimal peso) {
        Acompanhamento acompanhamento = new Acompanhamento();
        acompanhamento.setGestacao(gestacao);
        acompanhamento.setDataAcompanhamento(data);
        acompanhamento.setRealizadoPor(RealizadoPor.MEDICO);
        acompanhamento.setPesoAtual(peso);
        acompanhamento.setIdadeGestacional(20);
        acompanhamento.setPressaoArterial("120/80");
        acompanhamento.setBatimentosCardiacosFeto(140);
        acompanhamento.setAlturaUterina(30);
        acompanhamento.setTipo(TipoAcompanhamento.PRENATAL_ROTINA);
        acompanhamento.setRiscoIA(false);
        return acompanhamento;
    }

    @Test
    public void testFindByGestacaoId() {
        Acompanhamento acompanhamento1 = createAcompanhamento(gestacao, new Date(), new BigDecimal("70.0"));
        Acompanhamento acompanhamento2 = createAcompanhamento(gestacao, new Date(), new BigDecimal("71.0"));
        acompanhamentoRepository.save(acompanhamento1);
        acompanhamentoRepository.save(acompanhamento2);

        List<Acompanhamento> result = acompanhamentoRepository.findByGestacaoId(gestacao.getId());

        assertThat(result).hasSize(2);
    }

    @Test
    public void testSaveAcompanhamento() {
        Acompanhamento acompanhamento = createAcompanhamento(gestacao, new Date(), new BigDecimal("72.0"));

        Acompanhamento savedAcompanhamento = acompanhamentoRepository.save(acompanhamento);

        assertThat(savedAcompanhamento.getId()).isNotNull();
        assertThat(savedAcompanhamento.getGestacao().getId()).isEqualTo(gestacao.getId());
        assertThat(savedAcompanhamento.getPesoAtual()).isEqualTo(new BigDecimal("72.0"));
    }

    @Test
    public void testUpdateAcompanhamento() {
        Acompanhamento acompanhamento = createAcompanhamento(gestacao, new Date(), new BigDecimal("73.0"));
        Acompanhamento savedAcompanhamento = acompanhamentoRepository.save(acompanhamento);

        savedAcompanhamento.setPesoAtual(new BigDecimal("75.0"));
        Acompanhamento updatedAcompanhamento = acompanhamentoRepository.save(savedAcompanhamento);

        assertThat(updatedAcompanhamento.getPesoAtual()).isEqualTo(new BigDecimal("75.0"));
    }

    @Test
    public void testDeleteAcompanhamento() {
        Acompanhamento acompanhamento = createAcompanhamento(gestacao, new Date(), new BigDecimal("74.0"));
        Acompanhamento savedAcompanhamento = acompanhamentoRepository.save(acompanhamento);

        acompanhamentoRepository.delete(savedAcompanhamento);

        Optional<Acompanhamento> deletedAcompanhamento = acompanhamentoRepository.findById(savedAcompanhamento.getId());
        assertThat(deletedAcompanhamento).isNotPresent();
    }

    @Test
    public void testFindAcompanhamentoByGestacaoId_NotFound() {
        List<Acompanhamento> result = acompanhamentoRepository.findByGestacaoId(-1L);  // Non-existing ID

        assertThat(result).isEmpty();
    }
}
