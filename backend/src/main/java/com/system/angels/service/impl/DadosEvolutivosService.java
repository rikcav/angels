package com.system.angels.service.impl;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.exceptions.DadosEvolutivosNotFoundException;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.DadosEvolutivosRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.service.iDadosEvolutivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DadosEvolutivosService implements iDadosEvolutivosService {
    private final DadosEvolutivosRepository dadosEvolutivosRepository;
    private final GestanteRepository gestanteRepository;

    @Autowired
    public DadosEvolutivosService(DadosEvolutivosRepository dadosEvolutivosRepository, GestanteRepository gestanteRepository) {
        this.dadosEvolutivosRepository = dadosEvolutivosRepository;
        this.gestanteRepository = gestanteRepository;
    }

    public List<DadosEvolutivos> listarDadosEvolutivosPorGestante(Long gestanteId) {
        return dadosEvolutivosRepository.findAllByGestante_id(gestanteId);
    }

    public DadosEvolutivos ultimosDadosEvolutivosPorGestante(Long gestanteId) {
        var listaDeDadosEvolutivos = dadosEvolutivosRepository.findFirstByGestante_idOrderByGestanteIdDesc(gestanteId).orElseThrow(
                () -> new DadosEvolutivosNotFoundException("Não foram encontrados dados evolutivos para a gestante com id " + gestanteId));
        return listaDeDadosEvolutivos;
    }

    public DadosEvolutivos buscarDadosEvolutivosPorId(Long id) {
        return dadosEvolutivosRepository.findById(id).orElseThrow(
                () -> new DadosEvolutivosNotFoundException("Dados evolutivos com o id " + id + " não foram encontrados"));
    }

    public DadosEvolutivos registrarDadosEvolutivos(DadosEvolutivosDTO dadosEvolutivosDTO) {
        var dadosEvolutivos = dtoToEntity(dadosEvolutivosDTO);
        return dadosEvolutivosRepository.save(dadosEvolutivos);
    }

    private DadosEvolutivos dtoToEntity(DadosEvolutivosDTO dadosEvolutivosDTO) {
        var gestante = gestanteRepository.findById(dadosEvolutivosDTO.gestanteId()).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com id " + dadosEvolutivosDTO.gestanteId() + " não encontrada"));

        var dadosEvolutivos = new DadosEvolutivos();

        dadosEvolutivos.setGestante(gestante);
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

        return dadosEvolutivosRepository.save(dadosEvolutivos);
    }
}
