package com.system.angels.controller;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.dto.response.VisualizarDadosEvolutivosDTO;
import com.system.angels.service.impl.DadosEvolutivosService;
import com.system.angels.service.impl.GestanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dados-evolutivos")
@RequiredArgsConstructor
public class DadosEvolutivosController {
    private final DadosEvolutivosService service;
    private final GestanteService gestanteService;

    @GetMapping("/{id}")
    ResponseEntity<VisualizarDadosEvolutivosDTO> buscarDadosEvolutivosPorId(@PathVariable Long id) {
        var dadosEvolutivos = service.buscarDadosEvolutivosPorId(id);
        var visualizarDadosEvolutivosDTO = new VisualizarDadosEvolutivosDTO(dadosEvolutivos);
        return ResponseEntity.status(HttpStatus.OK).body(visualizarDadosEvolutivosDTO);
    }

    @GetMapping("/gestante/{id}")
    ResponseEntity<List<DadosEvolutivos>> listarDadosEvolutivosPorGestante(@PathVariable Long id) {
        var dadosEvolutivos = service.listarDadosEvolutivosPorGestante(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosEvolutivos);
    }

    @PostMapping("/gestante/{id}")
    ResponseEntity<DadosEvolutivos> atualizarDadosEvolutivos(@PathVariable Long id, @RequestBody DadosEvolutivosDTO dadosEvolutivosDTO) {
        var dadosEvolutivos = service.registrarDadosEvolutivos(dadosEvolutivosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosEvolutivos);
    }
}
