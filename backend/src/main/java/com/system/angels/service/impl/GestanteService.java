package com.system.angels.service.impl;

import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.service.iGestanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GestanteService implements iGestanteService {
    public final GestanteRepository gestanteRepository;

    @Autowired
    public GestanteService(GestanteRepository gestanteRepository) {
        this.gestanteRepository = gestanteRepository;
    }

    @Override
    public List<Gestante> listarGestantes() {
        return gestanteRepository.findAll();
    }

    @Override
    public Gestante buscarGestantePorId(Long id) {
        return gestanteRepository.findById(id).orElseThrow(() -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));
    }

    @Override
    public Gestante buscarGestantePorCpf(String cpf) {
        return gestanteRepository.findGestanteByCpf(cpf).orElseThrow(() -> new GestanteNotFoundException("Gestante com o cpf " + cpf + " não foi encontrada"));
    }

    @Override
    public Gestante registrarGestante(Gestante gestante) {
        return gestanteRepository.save(gestante);
    }

    @Override
    public Gestante atualizarGestante(Long id, Gestante gestanteAtualizada) {
        var gestante = gestanteRepository.findById(id).orElseThrow(() -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));

        gestante.setNome(gestanteAtualizada.getNome());
        gestante.setDataNascimento(gestanteAtualizada.getDataNascimento());
        gestante.setCpf(gestanteAtualizada.getCpf());
        gestante.setRaca(gestanteAtualizada.getRaca());
        gestante.setSexo(gestanteAtualizada.getSexo());

        return registrarGestante(gestante);
    }

    @Override
    public void deletarGestante(Long id) {
        var gestante = gestanteRepository.findById(id).orElseThrow(() -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));
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
}
