package com.system.angels.service.impl;

import com.system.angels.domain.Exame;
import com.system.angels.dto.create.ExameDTO;
import com.system.angels.exceptions.AcompanhamentoNotFoundException;
import com.system.angels.exceptions.ExameNotFoundException;
import com.system.angels.repository.AcompanhamentoRepository;
import com.system.angels.repository.ExameRepository;
import com.system.angels.service.iExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ExameService implements iExameService {
    private final ExameRepository exameRepository;
    private final AcompanhamentoRepository acompanhamentoRepository;

    @Autowired
    public ExameService(ExameRepository exameRepository, AcompanhamentoRepository acompanhamentoRepository) {
        this.exameRepository = exameRepository;
        this.acompanhamentoRepository = acompanhamentoRepository;
    }

    public Exame criarExame(Exame exame) {
        return exameRepository.save(exame);
    }

    @Override
    public List<Exame> obterTodosExames() {
        return exameRepository.findAll();
    }

    @Override
    public Exame buscarExamePorId(Long id) {
        return exameRepository.findById(id).orElseThrow(
                () -> new ExameNotFoundException("Exame com id " + id + " não encontrado"));
    }

    public void deleteExame(Long id) {
        exameRepository.deleteById(id);
    }

    private Exame dtoToEntity(ExameDTO exameDTO) {
        var acompanhamento = acompanhamentoRepository.findById(exameDTO.acompanhamentoId()).orElseThrow(
                () -> new AcompanhamentoNotFoundException("Acompanhamento com id " + exameDTO.acompanhamentoId() + " não encontrado"));

        var exame = new Exame();

        exame.setAcompanhamento(acompanhamento);
        exame.setId(new Random().nextLong());
        exame.setTipo(exameDTO.tipo());
        exame.setResultado(exameDTO.resultado());
        exame.setObservacao(exameDTO.observacao());

        return exame;
    }
}
