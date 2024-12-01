package com.system.angels.service.impl;

import com.system.angels.domain.Gestante;
import com.system.angels.domain.User;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.dto.response.GestanteRO;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.exceptions.InvalidRequestException;
import com.system.angels.exceptions.UserNotFoundException;
import com.system.angels.repository.DadosEvolutivosRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.repository.UserRepository;
import com.system.angels.service.iEmailService;
import com.system.angels.service.iGestanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GestanteService implements iGestanteService {
    public final GestanteRepository gestanteRepository;
    private final DadosEvolutivosRepository dadosEvolutivosRepository;
    private final UserRepository userRepository;
    private final iEmailService emailService;

    @Autowired
    public GestanteService(GestanteRepository gestanteRepository, DadosEvolutivosRepository dadosEvolutivosRepository, iEmailService emailService, UserRepository userRepository) {
        this.gestanteRepository = gestanteRepository;
        this.dadosEvolutivosRepository = dadosEvolutivosRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
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
        var user = userRepository.findByUsername(gestanteDTO.username()).orElseThrow(
            () -> new UserNotFoundException("Usuário não encontrado"));

        try {
            var gestante = dtoToEntity(gestanteDTO, user);
            var savedGestante = gestanteRepository.save(gestante);

            var message = "Olá, " + gestante.getNome() + "!\n" +
                    "\n" +
                    "Estamos felizes em informar que o seu cadastro foi realizado com sucesso. Aqui estão os detalhes registrados:\n" +
                    "\n" +
                    "- Nome: " + gestante.getNome() + "\n" +
                    "- Data de Nascimento: " + gestante.getDataNascimento() + "\n" +
                    "- CPF: " + gestante.getCpf() + "\n" +
                    "- Email: " + gestante.getEmail() + "\n" +
                    "- Sexo: " + gestante.getSexo() + "\n" +
                    "- Raça: " + gestante.getRaca() + "\n" +
                    "\n" +
                    "Caso tenha dúvidas ou necessite de suporte, estamos à disposição.\n" +
                    "\n" +
                    "Atenciosamente,\n" +
                    "Equipe Angels.";

            emailService.sendEmail(gestanteDTO.email(), "Registro de gestante", message);

            return entityToRO(savedGestante);
        } catch (InvalidRequestException e) {
            throw new InvalidRequestException(e.getMessage(), e);
        }
    }

    public GestanteRO atualizarGestante(Long id, GestanteDTO gestanteDTO) {
        try {
            var gestante = gestanteRepository.findById(id).orElseThrow(
                    () -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));
            var user = userRepository.findByUsername(gestanteDTO.username()).orElseThrow(
                () -> new UserNotFoundException("Usuário não encontrado"));

            var updatedGestante = dtoToEntity(gestanteDTO, user);
            updatedGestante.setId(gestante.getId());

            var savedGestante = gestanteRepository.save(updatedGestante);

            var message = "Olá, " + gestante.getNome() + "!\n" +
                    "\n" +
                    "Estamos felizes em informar que a sua atualização de dados foi realizado com sucesso. Aqui estão os detalhes atualizados:\n" +
                    "\n" +
                    "- Nome: " + gestante.getNome() + "\n" +
                    "- Data de Nascimento: " + gestante.getDataNascimento() + "\n" +
                    "- CPF: " + gestante.getCpf() + "\n" +
                    "- Email: " + gestante.getEmail() + "\n" +
                    "- Sexo: " + gestante.getSexo() + "\n" +
                    "- Raça: " + gestante.getRaca() + "\n" +
                    "\n" +
                    "Caso tenha dúvidas ou necessite de suporte, estamos à disposição.\n" +
                    "\n" +
                    "Atenciosamente,\n" +
                    "Equipe Angels.";

            emailService.sendEmail(gestanteDTO.email(), "Atualização de dados de gestante", message);

            return entityToRO(savedGestante);
        } catch (InvalidRequestException e) {
            throw new InvalidRequestException(e.getMessage(), e);
        }
    }

    @Override
    public void deletarGestante(Long id) {
        var gestante = gestanteRepository.findById(id).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + id + " não foi encontrada"));
        gestanteRepository.delete(gestante);

        var message = "Olá, " + gestante.getNome() + "!\n" +
                "\n" +
                "Estamos felizes em informar que o seu cadastro foi deletado com sucesso. Aqui estão os dados deletados:\n" +
                "\n" +
                "- Nome: " + gestante.getNome() + "\n" +
                "- Data de Nascimento: " + gestante.getDataNascimento() + "\n" +
                "- CPF: " + gestante.getCpf() + "\n" +
                "- Email: " + gestante.getEmail() + "\n" +
                "- Sexo: " + gestante.getSexo() + "\n" +
                "- Raça: " + gestante.getRaca() + "\n" +
                "\n" +
                "Caso tenha dúvidas ou necessite de suporte, estamos à disposição.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe Angels.";

        emailService.sendEmail(gestante.getEmail(), "Dados da gestante deletados", message);
    }

    private Gestante dtoToEntity(GestanteDTO gestanteDTO, User user) {

        var gestante = new Gestante();

        gestante.setId(new Random().nextLong());
        gestante.setNome(gestanteDTO.nome());
        gestante.setDataNascimento(gestanteDTO.dataNascimento());
        gestante.setEmail(gestanteDTO.email());
        gestante.setCpf(gestanteDTO.cpf());
        gestante.setRaca(gestanteDTO.raca());
        gestante.setSexo(gestanteDTO.sexo());
        gestante.setUser(user);

        return gestante;
    }

    private GestanteRO entityToRO(Gestante gestante) {
        var dadosEvolutivos = dadosEvolutivosRepository.findFirstByGestante_idOrderByIdDesc(gestante.getId())
                .orElse(null);

        return new GestanteRO(
                gestante.getId(),
                gestante.getNome(),
                gestante.getDataNascimento(),
                gestante.getCpf(),
                gestante.getEmail(),
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
