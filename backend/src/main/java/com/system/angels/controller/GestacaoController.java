package com.system.angels.controller;

import com.system.angels.domain.Gestacao;
import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.CadastrarGestacaoDTO;
import com.system.angels.dto.response.GestacaoComGestanteDTO;
import com.system.angels.dto.response.VisualizarGestacaoDTO;
import com.system.angels.dto.update.AtualizarGestacaoDTO;
import com.system.angels.service.impl.GestacaoService;
import com.system.angels.service.impl.GestanteService;
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
    private final GestanteService gestanteService;

    @GetMapping
    public ResponseEntity<List<GestacaoComGestanteDTO>> obterTodasGestacoes() {
        var gestacoes = service.obterTodasGestacoes();
        var gestacoesDTO = gestacoes.stream()
            .map(GestacaoComGestanteDTO::new)
            .toList();
        return ResponseEntity.ok(gestacoesDTO);
    }

    @GetMapping("/gestacao/{gestanteId}")
    public ResponseEntity<List<Gestacao>> listarGestacaoPorGestanteId(@PathVariable Long gestanteId) {
        var gestacaos = service.listarGestacaoPorGestanteId(gestanteId);
        return ResponseEntity.status(HttpStatus.OK).body(gestacaos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisualizarGestacaoDTO> obterGestacaoPorId(@PathVariable Long id) {
        var gestacaoDTO = service.obterGestacaoPorId(id);
        var visualizarGestacaoDTO = new VisualizarGestacaoDTO(gestacaoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(visualizarGestacaoDTO);
    }

//    @PostMapping("/{gestanteId}")
//    public ResponseEntity<CadastrarGestacaoDTO> adicionarGestacao(@PathVariable Long gestanteId, @RequestBody CadastrarGestacaoDTO gestacaoDTO) {
//        var gestacao = new Gestacao();
//        var gestante = gestanteService.buscarGestantePorId(gestanteId);
//
//        gestacao.setGestante(gestante);
//        gestacao.setConsumoAlcool(gestacaoDTO.isConsumoAlcool());
//        gestacao.setFrequenciaUsoAlcool(gestacaoDTO.getFrequenciaUsoAlcool());
//        gestacao.setDataUltimaMenstruacao(gestacaoDTO.getDataUltimaMenstruacao());
//        gestacao.setDataInicioGestacao(gestacaoDTO.getDataInicioGestacao());
//        gestacao.setFatorRh(gestacaoDTO.getFatorRh());
//        gestacao.setFuma(gestacaoDTO.isFuma());
//        gestacao.setQuantidadeCigarrosDia(gestacaoDTO.getQuantidadeCigarrosDia());
//        gestacao.setUsoDrogas(gestacaoDTO.getUsoDrogas());
//        gestacao.setGravidezPlanejada(gestacaoDTO.isGravidezPlanejada());
//        gestacao.setGrupoSanguineo(gestacaoDTO.getGrupoSanguineo());
//        gestacao.setPesoAntesGestacao(gestacaoDTO.getPesoAntesGestacao());
//        gestacao.setRiscoGestacional(gestacaoDTO.getRiscoGestacional());
//        gestacao.setVacinaHepatiteB(gestacaoDTO.isVacinaHepatiteB());
//        gestacao.setSituacaoGestacional(gestacaoDTO.getSituacaoGestacional());
//
//        var gestacaoAdicionada = service.adicionarGestacao(gestacao);
//
//        var gestacaoAdicionadaDTO = new CadastrarGestacaoDTO(gestacaoAdicionada);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(gestacaoAdicionadaDTO);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarGestacaoDTO> atualizarGestacao(@PathVariable Long id, @RequestBody Gestacao atualizarGestacaoDTO) {
        var gestacao = service.atualizarGestacao(id, atualizarGestacaoDTO);
        var gestacaoDTO = new AtualizarGestacaoDTO(gestacao);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gestacaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGestacao(@PathVariable Long id) {
        service.deletarGestacao(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
