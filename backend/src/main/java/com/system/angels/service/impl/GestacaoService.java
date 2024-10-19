package com.system.angels.service.impl;

import com.system.angels.domain.Gestacao;
import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.service.iGestacaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GestacaoService implements iGestacaoService {
    public GestacaoRepository gestacaoRepository;
    public GestanteRepository gestanteRepository;

    public GestacaoService(GestacaoRepository gestacaoRepository, GestanteRepository gestanteRepository) {
        this.gestacaoRepository = gestacaoRepository;
        this.gestanteRepository = gestanteRepository;
    }

    public Gestacao adicionarGestacao(Gestacao gestacao) {
        return gestacaoRepository.save(gestacao);
    }

    @Override
    public Gestacao obterGestacaoPorId(Long id) {
        return gestacaoRepository.findById(id).orElseThrow(
                () -> new GestacaoNotFoundException("Gestação com id " + id + " não encontrada"));
    }

    @Override
    public boolean gestacaoExiste(Long id) {
        return gestacaoRepository.existsById(id);
    }

    @Override
    public List<Gestacao> obterTodasGestacoes() {
        return gestacaoRepository.findAll();
    }

    public Gestacao atualizarGestacao(Long id, Gestacao atualizarGestacao) {
        var gestacao = gestacaoRepository.findById(atualizarGestacao.getId()).orElseThrow(
                () -> new GestacaoNotFoundException("Gestação com id " + id + " não encontrada"));

        gestacao.setConsumoAlcool(atualizarGestacao.isConsumoAlcool());
        gestacao.setFrequenciaUsoAlcool(atualizarGestacao.getFrequenciaUsoAlcool());
        gestacao.setDataUltimaMenstruacao(atualizarGestacao.getDataUltimaMenstruacao());
        gestacao.setDataInicioGestacao(atualizarGestacao.getDataInicioGestacao());
        gestacao.setFatorRh(atualizarGestacao.getFatorRh());
        gestacao.setFuma(atualizarGestacao.isFuma());
        gestacao.setQuantidadeCigarrosDia(atualizarGestacao.getQuantidadeCigarrosDia());
        gestacao.setUsoDrogas(atualizarGestacao.getUsoDrogas());
        gestacao.setGravidezPlanejada(atualizarGestacao.isGravidezPlanejada());
        gestacao.setGrupoSanguineo(atualizarGestacao.getGrupoSanguineo());
        gestacao.setPesoAntesGestacao(atualizarGestacao.getPesoAntesGestacao());
        gestacao.setRiscoGestacional(atualizarGestacao.getRiscoGestacional());
        gestacao.setVacinaHepatiteB(atualizarGestacao.isVacinaHepatiteB());
        gestacao.setSituacaoGestacional(atualizarGestacao.getSituacaoGestacional());

        return atualizarGestacao;
    }

    public void deletarGestacao(Long id) {
        gestacaoRepository.deleteById(id);
    }

    public List<Gestacao> listarGestacaoPorGestanteId(Long gestanteId) {
        return gestacaoRepository.findByGestanteId(gestanteId);
    }

    private Gestacao dtoToEntity(GestacaoDTO gestacaoDTO) {
        var gestante = gestanteRepository.findById(gestacaoDTO.gestante_id()).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + gestacaoDTO.gestante_id() + " não foi encontrada"));

        var gestacao = new Gestacao();

        gestacao.setId(new Random().nextLong());
        gestacao.setGestante(gestante);
        gestacao.setConsumoAlcool(gestacaoDTO.consumoAlcool());
        gestacao.setFrequenciaUsoAlcool(gestacaoDTO.frequenciaUsoAlcool());
        gestacao.setDataUltimaMenstruacao(gestacaoDTO.dataUltimaMenstruacao());
        gestacao.setDataInicioGestacao(gestacaoDTO.dataInicioGestacao());
        gestacao.setFatorRh(gestacaoDTO.fatorRh());
        gestacao.setFuma(gestacaoDTO.fuma());
        gestacao.setQuantidadeCigarrosDia(gestacaoDTO.quantidadeCigarrosDia());
        gestacao.setUsoDrogas(gestacaoDTO.usoDrogas());
        gestacao.setGravidezPlanejada(gestacaoDTO.gravidezPlanejada());
        gestacao.setGrupoSanguineo(gestacaoDTO.grupoSanguineo());
        gestacao.setPesoAntesGestacao(gestacaoDTO.pesoAntesGestacao());
        gestacao.setRiscoGestacional(gestacaoDTO.riscoGestacional());
        gestacao.setVacinaHepatiteB(gestacaoDTO.vacinaHepatiteB());
        gestacao.setSituacaoGestacional(gestacaoDTO.situacaoGestacional());

        return gestacao;
    }
}
