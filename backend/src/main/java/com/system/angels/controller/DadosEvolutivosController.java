package com.system.angels.controller;

import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.dto.response.DadosEvolutivosRO;
import com.system.angels.service.iDadosEvolutivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dados-evolutivos")
public class DadosEvolutivosController {
    private final iDadosEvolutivosService dadosEvolutivosService;

    @Autowired
    public DadosEvolutivosController(iDadosEvolutivosService dadosEvolutivosService) {
        this.dadosEvolutivosService = dadosEvolutivosService;
    }

    @GetMapping("/{id}")
    ResponseEntity<DadosEvolutivosRO> dadosEvolutivosPorId(@PathVariable Long id) {
        var dadosEvolutivosRO = dadosEvolutivosService.dadosEvolutivosPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosEvolutivosRO);
    }

    @GetMapping("/gestante/{id}")
    ResponseEntity<List<DadosEvolutivosRO>> dadosEvolutivosPorGestante(@PathVariable Long id) {
        var dadosEvolutivos = dadosEvolutivosService.dadosEvolutivosPorGestante(id);
        return ResponseEntity.status(HttpStatus.OK).body(dadosEvolutivos);
    }

    @PostMapping
    ResponseEntity<DadosEvolutivosRO> atualizarDadosEvolutivos(@RequestBody DadosEvolutivosDTO dadosEvolutivosDTO) {
        var dadosEvolutivosRO = dadosEvolutivosService.registrarDadosEvolutivos(dadosEvolutivosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosEvolutivosRO);
    }
}
