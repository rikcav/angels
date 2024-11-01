package com.system.angels.controller;

import com.system.angels.domain.Acompanhamento;
import com.system.angels.dto.create.CadastrarAcompanhamentoDTO;
import com.system.angels.dto.response.VisualizarAcompanhamentoDTO;
import com.system.angels.dto.update.AtualizarAcompanhamentoDTO;
import com.system.angels.service.impl.AcompanhamentoService;
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

    @GetMapping
    public ResponseEntity<List<Acompanhamento>> listarAcompanhamentos() {
        var acompanhamentos = service.listarAcompanhamentos();
        return ResponseEntity.ok(acompanhamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarAcompanhamentoDTO> obterAcompanhamentoPorId(@PathVariable Long id) {
        var acompanhamento = service.buscarAcompanhamentoPorId(id);
        var visualizarAcompanhamentoDTO = new VisualizarAcompanhamentoDTO(acompanhamento);
        return ResponseEntity.ok(visualizarAcompanhamentoDTO);
    }

    @PostMapping
    public ResponseEntity<VisualizarAcompanhamentoDTO> cadastrarAcompanhamento(@RequestBody CadastrarAcompanhamentoDTO cadastrarAcompanhamentoDTO, @PathVariable Long gestacaoId) {
        var acompanhamento = service.cadastrarAcompanhamento(gestacaoId, cadastrarAcompanhamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(acompanhamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarAcompanhamentoDTO> atualizarAcompanhamento(@PathVariable Long id, @RequestBody Acompanhamento atualizarAcompanhamentoDTO) {
        var acompanhamento = service.atualizarAcompanhamento(id, atualizarAcompanhamentoDTO);
        var acompanhamentoDTO = new AtualizarAcompanhamentoDTO(acompanhamento);
        return ResponseEntity.ok(acompanhamentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcompanhamento(@PathVariable Long id) {
        service.deletarAcompanhamento(id);
        return ResponseEntity.noContent().build();
    }
}
