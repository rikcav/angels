package com.system.angels.controller;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.dto.response.VisualizarDadosEvolutivosDTO;
import com.system.angels.service.impl.DadosEvolutivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dados-evolutivos")
public class DadosEvolutivosController {
    private final DadosEvolutivosService dadosEvolutivosService;

    @Autowired
    public DadosEvolutivosController(DadosEvolutivosService dadosEvolutivosService) {
        this.dadosEvolutivosService = dadosEvolutivosService;
    }

    @GetMapping("/{id}")
    ResponseEntity<VisualizarDadosEvolutivosDTO> buscarDadosEvolutivosPorId(@PathVariable Long id) {
        var dadosEvolutivos = dadosEvolutivosService.buscarDadosEvolutivosPorId(id);
        var visualizarDadosEvolutivosDTO = new VisualizarDadosEvolutivosDTO(dadosEvolutivos);
        return ResponseEntity.status(HttpStatus.OK).body(visualizarDadosEvolutivosDTO);
    }

    @GetMapping("/gestante/{id}")
    ResponseEntity<List<DadosEvolutivos>> listarDadosEvolutivosPorGestante(@PathVariable Long id) {
        var dadosEvolutivos = dadosEvolutivosService.listarDadosEvolutivosPorGestante(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosEvolutivos);
    }

    @PostMapping
    ResponseEntity<DadosEvolutivos> atualizarDadosEvolutivos(@RequestBody DadosEvolutivosDTO dadosEvolutivosDTO) {
        var dadosEvolutivos = dadosEvolutivosService.registrarDadosEvolutivos(dadosEvolutivosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosEvolutivos);
    }
}
