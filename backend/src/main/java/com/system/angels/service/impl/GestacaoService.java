package com.system.angels.service.impl;

import com.system.angels.domain.Gestacao;
import com.system.angels.domain.enums.SituacaoGestacional;
import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.dto.response.GestacaoComGestanteDTO;
import com.system.angels.dto.response.GestacaoRO;
import com.system.angels.dto.update.AtualizarSitGestacionalDTO;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.exceptions.InvalidRequestException;
import com.system.angels.exceptions.UserNotFoundException;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.repository.UserRepository;
import com.system.angels.service.iEmailService;
import com.system.angels.service.iGestacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GestacaoService implements iGestacaoService {
    public final GestacaoRepository gestacaoRepository;
    public final GestanteRepository gestanteRepository;
    public final UserRepository userRepository;
    private final iEmailService emailService;

    @Autowired
    public GestacaoService(GestacaoRepository gestacaoRepository, GestanteRepository gestanteRepository, iEmailService emailService, UserRepository userRepository) {
        this.gestacaoRepository = gestacaoRepository;
        this.gestanteRepository = gestanteRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public GestacaoRO registrarGestacao(GestacaoDTO gestacaoDTO) {
        var gestacao = dtoToEntity(gestacaoDTO);
        System.out.println(gestacaoDTO.riscoIA());
        var savedGestacao = gestacaoRepository.save(gestacao);

        var gestante = gestacao.getGestante();

        var message = "Olá, " + gestante.getNome() + "!\n" +
                "\n" +
                "Informamos que os dados da sua gestação foram cadastrados com sucesso. Confira os detalhes registrados:\n" +
                "\n" +
                "- Data da Última Menstruação: " + gestacao.getDataUltimaMenstruacao() + "\n" +
                "- Data de Início da Gestação: " + gestacao.getDataInicioGestacao() + "\n" +
                "- Grupo Sanguíneo: " + gestacao.getGrupoSanguineo() + "\n" +
                "- Fator RH: " + gestacao.getFatorRh() + "\n" +
                "- Peso Antes da Gestação: " + gestacao.getPesoAntesGestacao() + " kg\n" +
                "- Gestação Planejada: " + (gestacao.isGravidezPlanejada() ? "Sim" : "Não") + "\n" +
                "- Situação Gestacional: " + gestacao.getSituacaoGestacional() + "\n" +
                "\n" +
                "Acompanhe regularmente as consultas e exames necessários.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe de Angels.";

        emailService.sendEmail(gestante.getEmail(), "Registro de gestação", message);

        return entityToRo(savedGestacao);
    }

    @Override
    public GestacaoRO gestacaoPorId(Long id) {
        var gestacao = gestacaoRepository.findById(id).orElseThrow(
                () -> new GestacaoNotFoundException("Gestação com id " + id + " não encontrada"));

        return entityToRo(gestacao);
    }

    @Override
    public boolean gestacaoExiste(Long id) {
        return gestacaoRepository.existsById(id);
    }

    @Override
    public List<GestacaoComGestanteDTO> gestacoes() {
        return gestacaoRepository.findAll().stream().map(GestacaoComGestanteDTO::new).toList();
    }

    public List<GestacaoComGestanteDTO> gestacoesPorUsuario(String username) {
        return gestacaoRepository.findByUser_Username(username).stream().map(GestacaoComGestanteDTO::new).toList();
    }

    @Override
    public GestacaoRO atualizarGestacao(Long id, GestacaoDTO gestacaoDTO) {
        try {
            var gestacao = gestacaoRepository.findById(id).orElseThrow(
                    () -> new GestacaoNotFoundException("Gestação com id " + id + " não encontrada"));

            var updatedGestacao = dtoToEntity(gestacaoDTO);
            updatedGestacao.setId(gestacao.getId());

            var savedGestacao = gestacaoRepository.save(updatedGestacao);

            var gestante = gestacao.getGestante();

            var message = "Olá, " + gestante.getNome() + "!\n" +
                    "\n" +
                    "Informamos que os dados da sua gestação foram atualizados com sucesso. Confira os detalhes atualizados:\n" +
                    "\n" +
                    "- Data da Última Menstruação: " + gestacao.getDataUltimaMenstruacao() + "\n" +
                    "- Data de Início da Gestação: " + gestacao.getDataInicioGestacao() + "\n" +
                    "- Grupo Sanguíneo: " + gestacao.getGrupoSanguineo() + "\n" +
                    "- Fator RH: " + gestacao.getFatorRh() + "\n" +
                    "- Peso Antes da Gestação: " + gestacao.getPesoAntesGestacao() + " kg\n" +
                    "- Gestação Planejada: " + (gestacao.isGravidezPlanejada() ? "Sim" : "Não") + "\n" +
                    "- Situação Gestacional: " + gestacao.getSituacaoGestacional() + "\n" +
                    "\n" +
                    "Acompanhe regularmente as consultas e exames necessários.\n" +
                    "\n" +
                    "Atenciosamente,\n" +
                    "Equipe Angels.";

            emailService.sendEmail(gestante.getEmail(), "Atualização de dados de gestação", message);

            return entityToRo(savedGestacao);
        } catch (InvalidRequestException e) {
            throw new InvalidRequestException(e.getMessage(), e);
        }
    }

    @Override
    public void deletarGestacao(Long id) {
        var gestacao = gestacaoRepository.findById(id).orElseThrow(
                () -> new GestacaoNotFoundException("Gestação com id " + id + " não encontrada"));
        gestacaoRepository.delete(gestacao);

        var gestante = gestacao.getGestante();

        var message = "Olá, " + gestante.getNome() + "!\n" +
                "\n" +
                "Informamos que os dados da sua gestação foram deletados do sistema com sucesso. Confira os dados deletados:\n" +
                "\n" +
                "- Data da Última Menstruação: " + gestacao.getDataUltimaMenstruacao() + "\n" +
                "- Data de Início da Gestação: " + gestacao.getDataInicioGestacao() + "\n" +
                "- Grupo Sanguíneo: " + gestacao.getGrupoSanguineo() + "\n" +
                "- Fator RH: " + gestacao.getFatorRh() + "\n" +
                "- Peso Antes da Gestação: " + gestacao.getPesoAntesGestacao() + " kg\n" +
                "- Gestação Planejada: " + (gestacao.isGravidezPlanejada() ? "Sim" : "Não") + "\n" +
                "- Situação Gestacional: " + gestacao.getSituacaoGestacional() + "\n" +
                "\n" +
                "Acompanhe regularmente as consultas e exames necessários.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe de Angels.";

        emailService.sendEmail(gestante.getEmail(), "Dados de gestação deletados", message);
    }

    @Override
    public void atualizarSituacaoGestacional(Long id, AtualizarSitGestacionalDTO sitGestacionalDTO) {
        var gestacao = gestacaoRepository.findById(id).orElseThrow(
            () -> new GestacaoNotFoundException("Gestação com id " + id + " não encontrada"));

        gestacao.setSituacaoGestacional(sitGestacionalDTO.getSituacaoGestacional());

        gestacaoRepository.save(gestacao);
    }

    @Override
    public List<GestacaoRO> gestacaoPorGestanteId(Long gestanteId) {
        return gestacaoRepository.findByGestanteId(gestanteId).stream()
                .map(this::entityToRo)
                .toList();
    }

    private Gestacao dtoToEntity(GestacaoDTO gestacaoDTO) {
        var gestante = gestanteRepository.findById(gestacaoDTO.gestante_id()).orElseThrow(
                () -> new GestanteNotFoundException("Gestante com o id " + gestacaoDTO.gestante_id() + " não foi encontrada"));
        var user = userRepository.findByUsername(gestacaoDTO.username()).orElseThrow(
            () -> new UserNotFoundException("Usuário não encontrado"));

        var gestacao = new Gestacao();

        gestacao.setId(new Random().nextLong());
        gestacao.setGestante(gestante);
        gestacao.setConsumoAlcool(gestacaoDTO.consumoAlcool());
        gestacao.setFrequenciaUsoAlcool(gestacaoDTO.frequenciaUsoAlcool());
        gestacao.setDataUltimaMenstruacao(gestacaoDTO.dataUltimaMenstruacao());
        gestacao.setDataInicioGestacao(gestacaoDTO.dataInicioGestacao());
        gestacao.setFatorRh(gestacaoDTO.fatorRh());
        gestacao.setFuma(gestacaoDTO.fuma());
        gestacao.setQuantidadeCigarrosDia(gestacaoDTO.quantidadeCigarrosDia());
        gestacao.setUsoDrogas(gestacaoDTO.usoDrogas());
        gestacao.setGravidezPlanejada(gestacaoDTO.gravidezPlanejada());
        gestacao.setGrupoSanguineo(gestacaoDTO.grupoSanguineo());
        gestacao.setPesoAntesGestacao(gestacaoDTO.pesoAntesGestacao());
        gestacao.setRiscoGestacional(gestacaoDTO.riscoGestacional());
        gestacao.setVacinaHepatiteB(gestacaoDTO.vacinaHepatiteB());
        gestacao.setSituacaoGestacional(gestacaoDTO.situacaoGestacional());
        gestacao.setRiscoIA(gestacaoDTO.riscoIA());
        gestacao.setUser(user);

        return gestacao;
    }

    private GestacaoRO entityToRo(Gestacao gestacao) {
        return new GestacaoRO(
                gestacao.getId(),
                gestacao.getGestante().getId(),
                gestacao.isConsumoAlcool(),
                gestacao.getFrequenciaUsoAlcool(),
                gestacao.getDataUltimaMenstruacao(),
                gestacao.getDataInicioGestacao(),
                gestacao.getFatorRh(),
                gestacao.isFuma(),
                gestacao.getQuantidadeCigarrosDia(),
                gestacao.getUsoDrogas(),
                gestacao.isGravidezPlanejada(),
                gestacao.getGrupoSanguineo(),
                gestacao.getPesoAntesGestacao(),
                gestacao.getRiscoGestacional(),
                gestacao.getRiscoIA(),
                gestacao.isVacinaHepatiteB(),
                gestacao.getSituacaoGestacional()
        );
    }
}
