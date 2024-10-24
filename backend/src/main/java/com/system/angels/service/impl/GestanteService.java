package com.system.angels.service.impl;

import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.dto.response.GestanteRO;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.DadosEvolutivosRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.service.iGestanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GestanteService implements iGestanteService {
    public final GestanteRepository gestanteRepository;
    private final DadosEvolutivosRepository dadosEvolutivosRepository;

    @Autowired
    public GestanteService(GestanteRepository gestanteRepository, DadosEvolutivosRepository dadosEvolutivosRepository) {
        this.gestanteRepository = gestanteRepository;
        this.dadosEvolutivosRepository = dadosEvolutivosRepository;
    }

    public List<GestanteRO> gestantes() {
        return gestanteRepository.findAll().stream().map(this::entityToRO).toList();
    }

    public GestanteRO gestantePorId(Long id) {
        var gestante = gestanteRepository.findById(id).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));

        return entityToRO(gestante);
    }

    public GestanteRO gestantePorCpf(String cpf) {
        var gestante = gestanteRepository.findGestanteByCpf(cpf).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o cpf " + cpf + " não foi encontrada"));
        return entityToRO(gestante);
    }

    public GestanteRO registrarGestante(GestanteDTO gestanteDTO) {
        var gestante = dtoToEntity(gestanteDTO);
        var savedGestante = gestanteRepository.save(gestante);
        return entityToRO(savedGestante);
    }

    public GestanteRO atualizarGestante(Long id, GestanteDTO gestanteDTO) {
        var gestante = gestanteRepository.findById(id).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));

        var updatedGestante = dtoToEntity(gestanteDTO);
        updatedGestante.setId(gestante.getId());

        var savedGestante = gestanteRepository.save(updatedGestante);

        return entityToRO(savedGestante);
    }

    @Override
    public void deletarGestante(Long id) {
        var gestante = gestanteRepository.findById(id).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));
        gestanteRepository.delete(gestante);
    }

    private Gestante dtoToEntity(GestanteDTO gestanteDTO) {
        var gestante = new Gestante();

        gestante.setId(new Random().nextLong());
        gestante.setNome(gestanteDTO.nome());
        gestante.setDataNascimento(gestanteDTO.dataNascimento());
        gestante.setCpf(gestanteDTO.cpf());
        gestante.setRaca(gestanteDTO.raca());
        gestante.setSexo(gestanteDTO.sexo());

        return gestante;
    }

    private GestanteRO entityToRO(Gestante gestante) {
        var dadosEvolutivos = dadosEvolutivosRepository.findFirstByGestante_idOrderByGestanteIdDesc(gestante.getId())
                .orElse(null);

        return new GestanteRO(
                gestante.getId(),
                gestante.getNome(),
                gestante.getDataNascimento(),
                gestante.getCpf(),
                gestante.getSexo(),
                dadosEvolutivos != null ? dadosEvolutivos.getMunicipio() : null,
                dadosEvolutivos != null && dadosEvolutivos.isEmRisco(),
                dadosEvolutivos != null ? dadosEvolutivos.getQuantidadeAbortos() : 0,
                dadosEvolutivos != null ? dadosEvolutivos.getQuantidadeFilhosVivos() : 0,
                dadosEvolutivos != null ? dadosEvolutivos.getQuantidadeGemelares() : 0,
                dadosEvolutivos != null ? dadosEvolutivos.getQuantidadeGestacao() : 0,
                dadosEvolutivos != null ? dadosEvolutivos.getQuantidadeNascidosMortos() : 0,
                dadosEvolutivos != null ? dadosEvolutivos.getQuantidadeNascidosVivos() : 0,
                dadosEvolutivos != null && dadosEvolutivos.isHipertensao(),
                dadosEvolutivos != null && dadosEvolutivos.isDiabetes(),
                dadosEvolutivos != null && dadosEvolutivos.isMaFormacaoCongenita());
    }
}
