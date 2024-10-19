package com.system.angels.service.impl;

import com.system.angels.domain.Acompanhamento;
import com.system.angels.dto.create.AcompanhamentoDTO;
import com.system.angels.exceptions.AcompanhamentoNotFoundException;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.repository.AcompanhamentoRepository;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.service.iAcompanhamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AcompanhamentoService implements iAcompanhamentoService {
    private final AcompanhamentoRepository acompanhamentoRepository;
    private final GestacaoRepository gestacaoRepository;

    @Autowired
    public AcompanhamentoService(AcompanhamentoRepository acompanhamentoRepository, GestacaoRepository gestacaoRepository) {
        this.acompanhamentoRepository = acompanhamentoRepository;
        this.gestacaoRepository = gestacaoRepository;
    }

    public List<Acompanhamento> listarAcompanhamentos() {
        return acompanhamentoRepository.findAll();
    }

    public Acompanhamento buscarAcompanhamentoPorId(Long id) {
        return acompanhamentoRepository.findById(id).orElseThrow(
                () -> new AcompanhamentoNotFoundException("Acompanhamento com id" + id + " não encontrado"));
    }

    public Acompanhamento registrarAcompanhamento(Acompanhamento acompanhamento) {
        return acompanhamentoRepository.save(acompanhamento);
    }

    public void deletarAcompanhamento(Long id) {
        var acompanhamento = acompanhamentoRepository.findById(id).orElseThrow(
                () -> new AcompanhamentoNotFoundException("Acompanhamento com id" + id + " não encontrado"));
        acompanhamentoRepository.delete(acompanhamento);
    }

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

    public List<Acompanhamento> listarAcompanhamentoPorGestacaoId(Long gestacaoId) {
        return acompanhamentoRepository.findByGestacaoId(gestacaoId);
    }

    private Acompanhamento dtoToEntity(AcompanhamentoDTO acompanhamentoDTO) {
        var gestacao = gestacaoRepository.findById(acompanhamentoDTO.gestacao_id()).orElseThrow(
                () -> new GestacaoNotFoundException("Gestação com id " + acompanhamentoDTO.gestacao_id() + " não encontrada"));

        var acompanhamento = new Acompanhamento();

        acompanhamento.setGestacao(gestacao);
        acompanhamento.setId(new Random().nextLong());
        acompanhamento.setDataAcompanhamento(acompanhamentoDTO.dataAcompanhamento());
        acompanhamento.setRealizadoPor(acompanhamentoDTO.realizadoPor());
        acompanhamento.setPesoAtual(acompanhamentoDTO.pesoAtual());
        acompanhamento.setIdadeGestacional(acompanhamentoDTO.idadeGestacional());
        acompanhamento.setPressaoArterial(acompanhamentoDTO.pressaoArterial());
        acompanhamento.setBatimentosCardiacosFeto(acompanhamentoDTO.batimentosCardiacosFeto());
        acompanhamento.setAlturaUterina(acompanhamentoDTO.alturaUterina());
        acompanhamento.setTipo(acompanhamentoDTO.tipo());

        return acompanhamento;
    }
}
