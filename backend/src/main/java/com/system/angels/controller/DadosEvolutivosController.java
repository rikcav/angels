package com.system.angels.controller;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.CadastrarDadosEvolutivosDTO;
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
        var gestante = gestanteService.buscarGestantePorId(id);
        var listaDeDadosEvolutivos = service.listarDadosEvolutivosPorGestante(gestante);
        return ResponseEntity.status(HttpStatus.OK).body(listaDeDadosEvolutivos);
    }

    @PostMapping("/gestante/{id}")
    ResponseEntity<CadastrarDadosEvolutivosDTO> atualizarDadosEvolutivos(@PathVariable Long id, @RequestBody DadosEvolutivos dadosEvolutivos) {
        var gestante = gestanteService.buscarGestantePorId(id);
        dadosEvolutivos.setGestante(gestante);
        var cadastrarDadosEvolutivosDTO = new CadastrarDadosEvolutivosDTO(dadosEvolutivos);
        service.registrarDadosEvolutivos(dadosEvolutivos);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarDadosEvolutivosDTO);
    }
}
