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

    public List<Gestante> listarGestantes() {
        return gestanteRepository.findAll();
    }

    public GestanteRO buscarGestantePorId(Long id) {
        var gestante = gestanteRepository.findById(id).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));

        return entityToRO(gestante);
    }

    public Gestante buscarGestantePorCpf(String cpf) {
        return gestanteRepository.findGestanteByCpf(cpf).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o cpf " + cpf + " não foi encontrada"));
    }

    public Gestante registrarGestante(GestanteDTO gestanteDTO) {
        var gestante = dtoToEntity(gestanteDTO);
        return gestanteRepository.save(gestante);
    }

    public Gestante atualizarGestante(Long id, GestanteDTO gestanteDTO) {
        var gestante = gestanteRepository.findById(id).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));

        var updatedGestante = dtoToEntity(gestanteDTO);
        updatedGestante.setId(gestante.getId());

        return gestanteRepository.save(updatedGestante);
    }

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
        var dadosEvolutivosLista = dadosEvolutivosRepository.findAllByGestante_id(gestante.getId());
        var dadosEvolutivos = dadosEvolutivosLista.get(dadosEvolutivosLista.size() - 1);

        return new GestanteRO(
                gestante.getId(),
                gestante.getNome(),
                gestante.getDataNascimento(),
                gestante.getCpf(),
                gestante.getSexo(),
                dadosEvolutivos.getMunicipio(),
                dadosEvolutivos.isEmRisco(),
                dadosEvolutivos.getQuantidadeAbortos(),
                dadosEvolutivos.getQuantidadeFilhosVivos(),
                dadosEvolutivos.getQuantidadeGemelares(),
                dadosEvolutivos.getQuantidadeGestacao(),
                dadosEvolutivos.getQuantidadeNascidosMortos(),
                dadosEvolutivos.getQuantidadeNascidosVivos(),
                dadosEvolutivos.isHipertensao(),
                dadosEvolutivos.isDiabetes(),
                dadosEvolutivos.isMaFormacaoCongenita());
    }
}
