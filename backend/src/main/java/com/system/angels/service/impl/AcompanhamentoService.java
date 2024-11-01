package com.system.angels.service.impl;

import com.system.angels.domain.Acompanhamento;
import com.system.angels.dto.create.CadastrarAcompanhamentoDTO;
import com.system.angels.dto.response.VisualizarAcompanhamentoDTO;
import com.system.angels.exceptions.AcompanhamentoNotFoundException;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.repository.AcompanhamentoRepository;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.service.iAcompanhamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcompanhamentoService implements iAcompanhamentoService {
    private final AcompanhamentoRepository acompanhamentoRepository;
    private final GestacaoRepository gestacaoRepository;

    @Autowired
    public AcompanhamentoService(AcompanhamentoRepository acompanhamentoRepository, GestacaoRepository gestacaoRepository) {
        this.acompanhamentoRepository = acompanhamentoRepository;
        this.gestacaoRepository = gestacaoRepository;
    }

    @Override
    public List<Acompanhamento> listarAcompanhamentos() {
        return acompanhamentoRepository.findAll();
    }

    @Override
    public Acompanhamento buscarAcompanhamentoPorId(Long id) {
        return acompanhamentoRepository.findById(id).orElseThrow(
                () -> new AcompanhamentoNotFoundException("Acompanhamento com id " + id + " não encontrado"));
    }

    @Override
    public Acompanhamento registrarAcompanhamento(Acompanhamento acompanhamento) {
        return acompanhamentoRepository.save(acompanhamento);
    }

    @Override
    public void deletarAcompanhamento(Long id) {
        var acompanhamento = acompanhamentoRepository.findById(id).orElseThrow(
                () -> new AcompanhamentoNotFoundException("Acompanhamento com id " + id + " não encontrado"));
        acompanhamentoRepository.delete(acompanhamento);
    }

    @Override
    public Acompanhamento atualizarAcompanhamento(Long id, Acompanhamento acompanhamentoAtualizado) {
        Acompanhamento acompanhamento = buscarAcompanhamentoPorId(id);
        acompanhamento.setDataAcompanhamento(acompanhamentoAtualizado.getDataAcompanhamento());
        acompanhamento.setRealizadoPor(acompanhamentoAtualizado.getRealizadoPor());
        acompanhamento.setPesoAtual(acompanhamentoAtualizado.getPesoAtual());
        acompanhamento.setIdadeGestacional(acompanhamentoAtualizado.getIdadeGestacional());
        acompanhamento.setPressaoArterial(acompanhamentoAtualizado.getPressaoArterial());
        acompanhamento.setBatimentosCardiacosFeto(acompanhamentoAtualizado.getBatimentosCardiacosFeto());
        acompanhamento.setAlturaUterina(acompanhamentoAtualizado.getAlturaUterina());
        acompanhamento.setTipo(acompanhamentoAtualizado.getTipo());

        return acompanhamentoRepository.save(acompanhamento);
    }

    @Override
    public List<Acompanhamento> listarAcompanhamentoPorGestacaoId(Long gestacaoId) {
        return acompanhamentoRepository.findByGestacaoId(gestacaoId);
    }

    @Override
    public VisualizarAcompanhamentoDTO cadastrarAcompanhamento(Long gestacaoId, CadastrarAcompanhamentoDTO cadastroAcompanhamentoDTO) {
        var gestacao = gestacaoRepository.findById(gestacaoId).orElseThrow(
                () -> new GestacaoNotFoundException("Gestação com id " + gestacaoId + " não encontrada"));

        var acompanhamento = new Acompanhamento();
        acompanhamento.setGestacao(gestacao);
        acompanhamento.setDataAcompanhamento(cadastroAcompanhamentoDTO.getDataAcompanhamento());
        acompanhamento.setRealizadoPor(cadastroAcompanhamentoDTO.getRealizadoPor());
        acompanhamento.setPesoAtual(cadastroAcompanhamentoDTO.getPesoAtual());
        acompanhamento.setIdadeGestacional(cadastroAcompanhamentoDTO.getIdadeGestacional());
        acompanhamento.setPressaoArterial(cadastroAcompanhamentoDTO.getPressaoArterial());
        acompanhamento.setBatimentosCardiacosFeto(cadastroAcompanhamentoDTO.getBatimentosCardiacosFeto());
        acompanhamento.setAlturaUterina(cadastroAcompanhamentoDTO.getAlturaUterina());
        acompanhamento.setTipo(cadastroAcompanhamentoDTO.getTipo());
        acompanhamento.setRiscoIA(cadastroAcompanhamentoDTO.getRiscoIA());

        var adicionadoAcompanhamento = registrarAcompanhamento(acompanhamento);

        return new VisualizarAcompanhamentoDTO(adicionadoAcompanhamento);
    }
}
