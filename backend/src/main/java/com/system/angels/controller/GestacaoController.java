package com.system.angels.controller;

import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.dto.response.GestacaoComGestanteDTO;
import com.system.angels.dto.response.GestacaoRO;
import com.system.angels.service.impl.GestacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestacoes")
@RequiredArgsConstructor
public class GestacaoController {
    private final GestacaoService service;

    @GetMapping
    public ResponseEntity<List<GestacaoComGestanteDTO>> gestacoes() {
        var gestacoes = service.gestacoes();
        return ResponseEntity.status(HttpStatus.OK).body(gestacoes);
    }

    @GetMapping("/gestacao/{gestanteId}")
    public ResponseEntity<List<GestacaoRO>> gestacaoPorGestanteId(@PathVariable Long gestanteId) {
        var gestacaos = service.gestacaoPorGestanteId(gestanteId);
        return ResponseEntity.status(HttpStatus.OK).body(gestacaos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestacaoRO> gestacaoPorId(@PathVariable Long id) {
        var gestanteRO = service.gestacaoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(gestanteRO);
    }

    @PostMapping
    public ResponseEntity<GestacaoRO> cadastrarGestacao(@RequestBody
    GestacaoDTO gestacaoDTO) {
        var gestacaoRO = service.registrarGestacao(gestacaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(gestacaoRO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestacaoRO> atualizarGestacao(@PathVariable Long id, @RequestBody GestacaoDTO gestacaoDTO) {
        var gestacaoRO = service.atualizarGestacao(id, gestacaoDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gestacaoRO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGestacao(@PathVariable Long id) {
        service.deletarGestacao(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
