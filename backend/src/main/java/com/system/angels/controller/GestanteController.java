package com.system.angels.controller;

import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.dto.response.GestanteRO;
import com.system.angels.dto.response.VisualizarGestanteDTO;
import com.system.angels.service.impl.DadosEvolutivosService;
import com.system.angels.service.impl.GestanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestantes")
public class GestanteController {
    private final GestanteService gestanteService;
    private final DadosEvolutivosService dadosEvolutivosService;

    @Autowired
    public GestanteController(GestanteService gestanteService, DadosEvolutivosService dadosEvolutivosService) {
        this.gestanteService = gestanteService;
        this.dadosEvolutivosService = dadosEvolutivosService;
    }

    @GetMapping
    public ResponseEntity<List<Gestante>> listarGestantes() {
        var gestantes = gestanteService.listarGestantes();
        return ResponseEntity.status(HttpStatus.OK).body(gestantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestanteRO> buscarGestantePorId(@PathVariable Long id) {
        var gestanteRO = gestanteService.buscarGestantePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(gestanteRO);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<VisualizarGestanteDTO> buscarGestantePorCpf(@PathVariable String cpf) {
        var gestante = gestanteService.buscarGestantePorCpf(cpf);
        var dadosEvolutivos = dadosEvolutivosService.ultimosDadosEvolutivosPorGestante(gestante.getId());
        var gestanteDTO = new VisualizarGestanteDTO(gestante, dadosEvolutivos);

        return ResponseEntity.status(HttpStatus.OK).body(gestanteDTO);
    }

    @PostMapping
    public ResponseEntity<Gestante> cadastrarGestante(@RequestBody GestanteDTO gestanteDTO) {
        var gestante = gestanteService.registrarGestante(gestanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(gestante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestante> atualizarGestante(@PathVariable Long id, @RequestBody GestanteDTO gestanteDTO) {
        var gestante = gestanteService.atualizarGestante(id, gestanteDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gestante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGestante(@PathVariable Long id) {
        gestanteService.deletarGestante(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
