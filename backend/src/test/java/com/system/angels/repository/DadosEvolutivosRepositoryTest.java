package com.system.angels.repository;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.domain.Gestante;
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
public class DadosEvolutivosRepositoryTest {

    @Autowired
    private DadosEvolutivosRepository dadosEvolutivosRepository;

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

    private DadosEvolutivos createDadosEvolutivos(Gestante gestante, String municipio) {
        DadosEvolutivos dados = new DadosEvolutivos();
        dados.setGestante(gestante);
        dados.setMunicipio(municipio);
        dados.setDiagnosticoDesnutricao(2);
        dados.setEnergiaEletricaDomicilio(true);
        dados.setEscolaridade(3);
        dados.setTipoMoradia(1);
        dados.setMoradiaRedeEsgoto(true);
        dados.setRendaFamiliar(new BigDecimal("1500.00"));
        dados.setTratamentoAgua(true);
        dados.setAmamentacao(true);
        dados.setChefeFamilia(false);
        dados.setDataUltimaGestacao(new Date());
        dados.setEmRisco(false);
        dados.setEstadoCivil(1);
        dados.setQuantidadeAbortos(0);
        dados.setQuantidadeFilhosVivos(1);
        dados.setQuantidadeGemelares(0);
        dados.setQuantidadeGestacao(2);
        dados.setQuantidadeNascidosMortos(0);
        dados.setQuantidadeNascidosVivos(1);
        dados.setQuantidadeObitosSemana1(0);
        dados.setQuantidadeObitosAposSemana1(0);
        dados.setQuantidadePartos(1);
        dados.setQuantidadePartosCesarios(0);
        dados.setQuantidadePartosVaginais(1);
        dados.setQuantidadeRnPeso2500_4000(1);
        dados.setQuantidadeRnPesoMaior4000(0);
        dados.setQuantidadeRnPesoMenor2500(0);
        dados.setHipertensao(false);
        dados.setDiabetes(false);
        dados.setCirurgiaPelvica(false);
        dados.setInfeccaoUrinaria(false);
        dados.setMaFormacaoCongenita(false);
        dados.setFamiliarGemeos(false);
        dados.setContato("123456789");
        dados.setContatoEmergencia("987654321");
        return dados;
    }

    @Test
    public void testFindAllByGestante_id() {
        DadosEvolutivos dados1 = createDadosEvolutivos(gestante, "City A");
        DadosEvolutivos dados2 = createDadosEvolutivos(gestante, "City B");
        dadosEvolutivosRepository.save(dados1);
        dadosEvolutivosRepository.save(dados2);

        List<DadosEvolutivos> result = dadosEvolutivosRepository.findAllByGestante_id(gestante.getId());

        assertThat(result).hasSize(2);
        assertThat(result).extracting(DadosEvolutivos::getMunicipio).contains("City A", "City B");
    }

    @Test
    public void testFindFirstByGestante_idOrderByIdDesc() {
        DadosEvolutivos dados1 = createDadosEvolutivos(gestante, "City A");
        DadosEvolutivos dados2 = createDadosEvolutivos(gestante, "City B");
        dadosEvolutivosRepository.save(dados1);
        dadosEvolutivosRepository.save(dados2);

        Optional<DadosEvolutivos> result = dadosEvolutivosRepository.findFirstByGestante_idOrderByIdDesc(gestante.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getMunicipio()).isEqualTo("City B");
    }

    @Test
    public void testSaveDadosEvolutivos() {
        DadosEvolutivos dados = createDadosEvolutivos(gestante, "City A");

        DadosEvolutivos savedDados = dadosEvolutivosRepository.save(dados);

        assertThat(savedDados.getId()).isNotNull();
        assertThat(savedDados.getMunicipio()).isEqualTo("City A");
        assertThat(savedDados.getGestante().getId()).isEqualTo(gestante.getId());
    }

    @Test
    public void testUpdateDadosEvolutivos() {
        DadosEvolutivos dados = createDadosEvolutivos(gestante, "City A");
        DadosEvolutivos savedDados = dadosEvolutivosRepository.save(dados);

        savedDados.setMunicipio("Updated City");
        DadosEvolutivos updatedDados = dadosEvolutivosRepository.save(savedDados);

        assertThat(updatedDados.getMunicipio()).isEqualTo("Updated City");
    }

    @Test
    public void testDeleteDadosEvolutivos() {
        DadosEvolutivos dados = createDadosEvolutivos(gestante, "City A");
        DadosEvolutivos savedDados = dadosEvolutivosRepository.save(dados);

        dadosEvolutivosRepository.delete(savedDados);

        Optional<DadosEvolutivos> deletedDados = dadosEvolutivosRepository.findById(savedDados.getId());
        assertThat(deletedDados).isNotPresent();
    }
}
