package com.system.angels.service.impl;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.exceptions.DadosEvolutivosNotFoundException;
import com.system.angels.repository.DadosEvolutivosRepository;
import com.system.angels.service.iDadosEvolutivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DadosEvolutivosService implements iDadosEvolutivosService {
    private final DadosEvolutivosRepository dadosEvolutivosRepository;

    @Autowired
    public DadosEvolutivosService(DadosEvolutivosRepository dadosEvolutivosRepository) {
        this.dadosEvolutivosRepository = dadosEvolutivosRepository;
    }

    public List<DadosEvolutivos> listarDadosEvolutivos() {
        return dadosEvolutivosRepository.findAll();
    }

    @Override
    public List<DadosEvolutivos> listarDadosEvolutivosPorGestante(Gestante gestante) {
        return dadosEvolutivosRepository.findAllByGestante_id(gestante.getId());
    }

    public DadosEvolutivos ultimosDadosEvolutivosPorGestante(Gestante gestante) {
        List<DadosEvolutivos> listaDeDadosEvolutivos = listarDadosEvolutivosPorGestante(gestante);

        return listaDeDadosEvolutivos.get(listaDeDadosEvolutivos.size() - 1);
    }

    @Override
    public DadosEvolutivos buscarDadosEvolutivosPorId(Long id) {
        return dadosEvolutivosRepository.findById(id).orElseThrow(() -> new DadosEvolutivosNotFoundException("Dados evolutivos com o id " + id + " não foram encontrados"));
    }

    @Override
    public DadosEvolutivos registrarDadosEvolutivos(DadosEvolutivos dadosEvolutivos) {
        return dadosEvolutivosRepository.save(dadosEvolutivos);
    }

    private DadosEvolutivos dtoToEntity(DadosEvolutivosDTO dadosEvolutivosDTO) {
        var dadosEvolutivos = new DadosEvolutivos();

        dadosEvolutivos.setId(new Random().nextLong());
        dadosEvolutivos.setMunicipio(dadosEvolutivosDTO.municipio());
        dadosEvolutivos.setDiagnosticoDesnutricao(dadosEvolutivosDTO.diagnosticoDesnutricao());
        dadosEvolutivos.setEnergiaEletricaDomicilio(dadosEvolutivosDTO.energiaEletricaDomicilio());
        dadosEvolutivos.setEscolaridade(dadosEvolutivosDTO.escolaridade());
        dadosEvolutivos.setTipoMoradia(dadosEvolutivosDTO.tipoMoradia());
        dadosEvolutivos.setMoradiaRedeEsgoto(dadosEvolutivosDTO.moradiaRedeEsgoto());
        dadosEvolutivos.setRendaFamiliar(dadosEvolutivosDTO.rendaFamiliar());
        dadosEvolutivos.setTratamentoAgua(dadosEvolutivosDTO.tratamentoAgua());
        dadosEvolutivos.setAmamentacao(dadosEvolutivosDTO.amamentacao());
        dadosEvolutivos.setChefeFamilia(dadosEvolutivosDTO.chefeFamilia());
        dadosEvolutivos.setDataUltimaGestacao(dadosEvolutivosDTO.dataUltimaGestacao());
        dadosEvolutivos.setEmRisco(dadosEvolutivosDTO.emRisco());
        dadosEvolutivos.setEstadoCivil(dadosEvolutivosDTO.estadoCivil());
        dadosEvolutivos.setQuantidadeAbortos(dadosEvolutivosDTO.quantidadeAbortos());
        dadosEvolutivos.setQuantidadeFilhosVivos(dadosEvolutivosDTO.quantidadeFilhosVivos());
        dadosEvolutivos.setQuantidadeGemelares(dadosEvolutivosDTO.quantidadeGemelares());
        dadosEvolutivos.setQuantidadeGestacao(dadosEvolutivosDTO.quantidadeGestacao());
        dadosEvolutivos.setQuantidadeNascidosMortos(dadosEvolutivosDTO.quantidadeNascidosMortos());
        dadosEvolutivos.setQuantidadeNascidosVivos(dadosEvolutivosDTO.quantidadeNascidosVivos());
        dadosEvolutivos.setQuantidadeObitosSemana1(dadosEvolutivosDTO.quantidadeObitosSemana1());
        dadosEvolutivos.setQuantidadeObitosAposSemana1(dadosEvolutivosDTO.quantidadeObitosAposSemana1());
        dadosEvolutivos.setQuantidadePartos(dadosEvolutivosDTO.quantidadePartos());
        dadosEvolutivos.setQuantidadePartosCesarios(dadosEvolutivosDTO.quantidadePartosCesarios());
        dadosEvolutivos.setQuantidadePartosVaginais(dadosEvolutivosDTO.quantidadePartosVaginais());
        dadosEvolutivos.setQuantidadeRnPeso2500_4000(dadosEvolutivosDTO.quantidadeRnPeso2500_4000());
        dadosEvolutivos.setQuantidadeRnPesoMaior4000(dadosEvolutivosDTO.quantidadeRnPesoMaior4000());
        dadosEvolutivos.setQuantidadeRnPesoMenor2500(dadosEvolutivosDTO.quantidadeRnPesoMenor2500());
        dadosEvolutivos.setHipertensao(dadosEvolutivosDTO.hipertensao());
        dadosEvolutivos.setDiabetes(dadosEvolutivosDTO.diabetes());
        dadosEvolutivos.setCirurgiaPelvica(dadosEvolutivosDTO.cirurgiaPelvica());
        dadosEvolutivos.setInfeccaoUrinaria(dadosEvolutivosDTO.infeccaoUrinaria());
        dadosEvolutivos.setMaFormacaoCongenita(dadosEvolutivosDTO.maFormacaoCongenita());
        dadosEvolutivos.setFamiliarGemeos(dadosEvolutivosDTO.familiarGemeos());
        dadosEvolutivos.setContato(dadosEvolutivosDTO.contato());
        dadosEvolutivos.setContatoEmergencia(dadosEvolutivosDTO.contatoEmergencia());

        return registrarDadosEvolutivos(dadosEvolutivos);
    }
}
