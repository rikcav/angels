package com.system.angels.controller;


import com.system.angels.domain.Acompanhamento;
import com.system.angels.dto.create.CadastrarAcompanhamentoDTO;
import com.system.angels.dto.response.VisualizarAcompanhamentoDTO;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.service.impl.AcompanhamentoService;
import com.system.angels.service.impl.GestacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acompanhamentos")
@RequiredArgsConstructor
public class AcompanhamentoController {
    private final AcompanhamentoService service;
    private final GestacaoService gestacaoService;
    private final GestacaoRepository gestacaoRepository;

    @GetMapping
    public ResponseEntity<List<Acompanhamento>> listarAcompanhamentos() {
        var acompanhamentos = service.listarAcompanhamentos();
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarAcompanhamentoDTO> buscarAcompanhamentoPorId(@PathVariable Long id) {
        var acompanhamento = service.buscarAcompanhamentoPorId(id);
        var acompanhamentoDTO = new VisualizarAcompanhamentoDTO(acompanhamento);
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentoDTO);
    }

    @GetMapping("/listar-acompanhamento-por-gestao/{gestacaoId}")
    public ResponseEntity<List<Acompanhamento>> listarAcompanhamentoPorGestacaoId(@PathVariable Long gestacaoId) {
        var acompanhamentos = service.listarAcompanhamentoPorGestacaoId(gestacaoId);
        return ResponseEntity.status(HttpStatus.OK).body(acompanhamentos);
    }

    @PostMapping("/{gestacaoId}")
    public ResponseEntity<CadastrarAcompanhamentoDTO> cadastrarAcompanhamento(@PathVariable Long gestacaoId, @RequestBody CadastrarAcompanhamentoDTO cadastroAcompanhamentoDTO) {
        var acompanhamento = new Acompanhamento();
        var gestacao = gestacaoRepository.findById(gestacaoId).orElseThrow(() -> new GestacaoNotFoundException("Gestação com id " + gestacaoId + " não encontrada"));

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

        var adicionadoAcompanhamento = service.registrarAcompanhamento(acompanhamento);

        var adicionadoAcompnhamentoDTO = new CadastrarAcompanhamentoDTO(adicionadoAcompanhamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(adicionadoAcompnhamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisualizarAcompanhamentoDTO> atualizarAcompanhamento(@PathVariable Long id, @RequestBody Acompanhamento acompanhamentoAtualziado) {
        var acompanhamento = service.atualizarAcompanhamento(id, acompanhamentoAtualziado);
        var acompanhamentoDTO = new VisualizarAcompanhamentoDTO(acompanhamento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(acompanhamentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcompanhamento(@PathVariable Long id) {
        service.deletarAcompanhamento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
