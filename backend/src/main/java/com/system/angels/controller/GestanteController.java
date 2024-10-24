package com.system.angels.controller;

import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.dto.response.GestanteRO;
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

    @Autowired
    public GestanteController(GestanteService gestanteService) {
        this.gestanteService = gestanteService;
    }

    @GetMapping
    public ResponseEntity<List<GestanteRO>> gestantes() {
        var gestantesRO = gestanteService.gestantes();
        return ResponseEntity.status(HttpStatus.OK).body(gestantesRO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestanteRO> gestantePorId(@PathVariable Long id) {
        var gestanteRO = gestanteService.gestantePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(gestanteRO);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<GestanteRO> gestantePorCpf(@PathVariable String cpf) {
        var gestanteRO = gestanteService.gestantePorCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(gestanteRO);
    }

    @PostMapping
    public ResponseEntity<GestanteRO> cadastrarGestante(@RequestBody GestanteDTO gestanteDTO) {
        var gestanteRO = gestanteService.registrarGestante(gestanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(gestanteRO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestanteRO> atualizarGestante(@PathVariable Long id, @RequestBody GestanteDTO gestanteDTO) {
        var gestanteRO = gestanteService.atualizarGestante(id, gestanteDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gestanteRO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGestante(@PathVariable Long id) {
        gestanteService.deletarGestante(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
