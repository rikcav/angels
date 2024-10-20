package com.system.angels.controller;

import com.system.angels.domain.Exame;
import com.system.angels.dto.create.CadastrarExameDTO;
import com.system.angels.dto.response.VisualizarExameDTO;
import com.system.angels.service.impl.AcompanhamentoService;
import com.system.angels.service.impl.ExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
@RequiredArgsConstructor
public class ExameController {
    private final ExameService exameService;
    private final AcompanhamentoService acompanhamentoService;

    @GetMapping
    public ResponseEntity<List<Exame>> obterTodosExames() {
        var exames = exameService.obterTodosExames();
        return ResponseEntity.status(HttpStatus.OK).body(exames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarExameDTO> buscarExamePorId(@PathVariable Long id) {
        var exame = exameService.buscarExamePorId(id);
        var visualizarExameDTO = new VisualizarExameDTO(exame);
        return ResponseEntity.status(HttpStatus.OK).body(visualizarExameDTO);
    }

    @PostMapping("/{acompanhamentoId}")
    public ResponseEntity<CadastrarExameDTO> criarExame(@PathVariable Long acompanhamentoId, @RequestBody CadastrarExameDTO exameDTO) {
        var exame = new Exame();
        var acompanhamento = acompanhamentoService.buscarAcompanhamentoPorId(acompanhamentoId);

        exame.setAcompanhamento(acompanhamento);
        exame.setTipo(exameDTO.getTipo());
        exame.setResultado(exameDTO.getResultado());
        exame.setObservacao(exameDTO.getObservacao());

        var adicionaExame = exameService.criarExame(exame);

        var adicionaExameDTO = new CadastrarExameDTO(adicionaExame);

        return ResponseEntity.status(HttpStatus.CREATED).body(adicionaExameDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExame(@PathVariable Long id) {
        exameService.deleteExame(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
