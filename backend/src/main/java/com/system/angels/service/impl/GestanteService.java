package com.system.angels.service.impl;

import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.service.iGestanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GestanteService implements iGestanteService {
    public final GestanteRepository repositorio;

    public List<Gestante> listarGestantes() {
        return repositorio.findAll();
    }

    public Gestante buscarGestantePorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));
    }

    public Gestante buscarGestantePorCpf(String cpf) {
        return repositorio.findGestanteByCpf(cpf).orElseThrow(() -> new GestanteNotFoundException("Gestante com o cpf " + cpf + " não foi encontrada"));
    }

    public Gestante registrarGestante(Gestante gestante) {
        return repositorio.save(gestante);
    }

    public Gestante atualizarGestante(Long id, Gestante gestanteAtualizada) {
        var gestante = repositorio.findById(id).orElseThrow(() -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));

        gestante.setNome(gestanteAtualizada.getNome());
        gestante.setDataNascimento(gestanteAtualizada.getDataNascimento());
        gestante.setCpf(gestanteAtualizada.getCpf());
        gestante.setRaca(gestanteAtualizada.getRaca());
        gestante.setSexo(gestanteAtualizada.getSexo());

        return registrarGestante(gestante);
    }

    public void deletarGestante(Long id) {
        var gestante = repositorio.findById(id).orElseThrow(() -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));
        repositorio.delete(gestante);
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
