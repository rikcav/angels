package com.system.angels.controller;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.CadastrarGestanteEDadosEvolutivosDTO;
import com.system.angels.dto.response.VisualizarGestanteDTO;
import com.system.angels.service.impl.DadosEvolutivosService;
import com.system.angels.service.impl.GestanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestantes")
@RequiredArgsConstructor
public class GestanteController {
    private final GestanteService service;
    private final DadosEvolutivosService dadosEvolutivosService;

    @GetMapping
    public ResponseEntity<List<Gestante>> listarGestantes() {
        var gestantes = service.listarGestantes();
        return ResponseEntity.status(HttpStatus.OK).body(gestantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarGestanteDTO> buscarGestantePorId(@PathVariable Long id) {
        var gestante = service.buscarGestantePorId(id);
        var dadosEvolutivos = dadosEvolutivosService.ultimosDadosEvolutivosPorGestante(gestante);
        var gestanteDTO = new VisualizarGestanteDTO(gestante, dadosEvolutivos);

        return ResponseEntity.status(HttpStatus.OK).body(gestanteDTO);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<VisualizarGestanteDTO> buscarGestantePorCpf(@PathVariable String cpf) {
        var gestante = service.buscarGestantePorCpf(cpf);
        var dadosEvolutivos = dadosEvolutivosService.ultimosDadosEvolutivosPorGestante(gestante);
        var gestanteDTO = new VisualizarGestanteDTO(gestante, dadosEvolutivos);

        return ResponseEntity.status(HttpStatus.OK).body(gestanteDTO);
    }

    @PostMapping
    public ResponseEntity<CadastrarGestanteEDadosEvolutivosDTO> cadastrarGestante(@RequestBody CadastrarGestanteEDadosEvolutivosDTO gestanteEDadosEvolutivosDTO) {
        var gestante = gestanteEDadosEvolutivosDTO.getGestante();
        var dadosEvolutivos = gestanteEDadosEvolutivosDTO.getDadosEvolutivos();
        dadosEvolutivos.setGestante(gestante);

        service.registrarGestante(gestante);
        dadosEvolutivosService.registrarDadosEvolutivos(dadosEvolutivos);

        return ResponseEntity.status(HttpStatus.CREATED).body(gestanteEDadosEvolutivosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisualizarGestanteDTO> atualizarGestante(@PathVariable Long id, @RequestBody Gestante gestanteAtualizada) {
        var gestante = service.atualizarGestante(id, gestanteAtualizada);
        var dadosEvolutivos = dadosEvolutivosService.ultimosDadosEvolutivosPorGestante(gestante);
        var gestanteDTO = new VisualizarGestanteDTO(gestante, dadosEvolutivos);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gestanteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGestante(@PathVariable Long id) {
        service.deletarGestante(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
