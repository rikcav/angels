package com.system.angels.service.impl;

import com.system.angels.domain.Acompanhamento;
import com.system.angels.dto.create.CadastrarAcompanhamentoDTO;
import com.system.angels.exceptions.AcompanhamentoNotFoundException;
import com.system.angels.exceptions.GestacaoNotFoundException;
import com.system.angels.repository.AcompanhamentoRepository;
import com.system.angels.repository.GestacaoRepository;
import com.system.angels.service.iAcompanhamentoService;
import com.system.angels.service.iEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcompanhamentoService implements iAcompanhamentoService {
    private final AcompanhamentoRepository acompanhamentoRepository;
    private final GestacaoRepository gestacaoRepository;
    private final iEmailService emailService;

    @Autowired
    public AcompanhamentoService(AcompanhamentoRepository acompanhamentoRepository, GestacaoRepository gestacaoRepository, iEmailService emailService) {
        this.acompanhamentoRepository = acompanhamentoRepository;
        this.gestacaoRepository = gestacaoRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Acompanhamento> listarAcompanhamentos() {
        return acompanhamentoRepository.findAll();
    }

    @Override
    public Acompanhamento buscarAcompanhamentoPorId(Long id) {
        return acompanhamentoRepository.findById(id).orElseThrow(
                () -> new AcompanhamentoNotFoundException("Acompanhamento com id " + id + " não encontrado"));
    }

    @Override
    public Acompanhamento registrarAcompanhamento(Acompanhamento acompanhamento) {
        var savedAcompanhamento = acompanhamentoRepository.save(acompanhamento);

        var gestante = acompanhamento.getGestacao().getGestante();

        var message = "Olá, " + gestante.getNome() + "!\n" +
                "\n" +
                "Informamos que um novo acompanhamento gestacional foi registrado. Confira os detalhes:\n" +
                "\n" +
                "- Data do Acompanhamento: " + acompanhamento.getDataAcompanhamento() + "\n" +
                "- Realizado por: " + acompanhamento.getRealizadoPor() + "\n" +
                "- Tipo de Acompanhamento: " + acompanhamento.getTipo() + "\n" +
                "- Peso Atual: " + acompanhamento.getPesoAtual() + " kg\n" +
                "- Idade Gestacional: " + acompanhamento.getIdadeGestacional() + " semanas\n" +
                "- Pressão Arterial: " + acompanhamento.getPressaoArterial() + "\n" +
                "- Batimentos Cardíacos do Feto: " +
                (acompanhamento.getBatimentosCardiacosFeto() != null ? acompanhamento.getBatimentosCardiacosFeto() + " bpm" : "Não medido") + "\n" +
                "- Altura Uterina: " +
                (acompanhamento.getAlturaUterina() != null ? acompanhamento.getAlturaUterina() + " cm" : "Não medido") + "\n" +
                "- Risco IA Identificado: " + (acompanhamento.getRiscoIA() ? "Sim" : "Não") + "\n" +
                "\n" +
                "Continue com o acompanhamento regular para um cuidado completo.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe de Controle de Gestação.";

        emailService.sendEmail(gestante.getEmail(), "Registro de acompanhamento", message);

        return savedAcompanhamento;
    }

    @Override
    public void deletarAcompanhamento(Long id) {
        var acompanhamento = acompanhamentoRepository.findById(id).orElseThrow(
                () -> new AcompanhamentoNotFoundException("Acompanhamento com id " + id + " não encontrado"));
        acompanhamentoRepository.delete(acompanhamento);

        var gestante = acompanhamento.getGestacao().getGestante();

        var message = "Olá, " + gestante.getNome() + "!\n" +
                "\n" +
                "Informamos que um acompanhamento gestacional foi deletado com sucesso. Confira os detalhes:\n" +
                "\n" +
                "- Data do Acompanhamento: " + acompanhamento.getDataAcompanhamento() + "\n" +
                "- Realizado por: " + acompanhamento.getRealizadoPor() + "\n" +
                "- Tipo de Acompanhamento: " + acompanhamento.getTipo() + "\n" +
                "- Peso Atual: " + acompanhamento.getPesoAtual() + " kg\n" +
                "- Idade Gestacional: " + acompanhamento.getIdadeGestacional() + " semanas\n" +
                "- Pressão Arterial: " + acompanhamento.getPressaoArterial() + "\n" +
                "- Batimentos Cardíacos do Feto: " +
                (acompanhamento.getBatimentosCardiacosFeto() != null ? acompanhamento.getBatimentosCardiacosFeto() + " bpm" : "Não medido") + "\n" +
                "- Altura Uterina: " +
                (acompanhamento.getAlturaUterina() != null ? acompanhamento.getAlturaUterina() + " cm" : "Não medido") + "\n" +
                "- Risco IA Identificado: " + (acompanhamento.getRiscoIA() ? "Sim" : "Não") + "\n" +
                "\n" +
                "Continue com o acompanhamento regular para um cuidado completo.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe Angels.";

        emailService.sendEmail(gestante.getEmail(), "Dados de acompanhamento deletados", message);
    }

    @Override
    public Acompanhamento atualizarAcompanhamento(Long id, Acompanhamento acompanhamentoAtualizado) {
        Acompanhamento acompanhamento = buscarAcompanhamentoPorId(id);
        acompanhamento.setDataAcompanhamento(acompanhamentoAtualizado.getDataAcompanhamento());
        acompanhamento.setRealizadoPor(acompanhamentoAtualizado.getRealizadoPor());
        acompanhamento.setPesoAtual(acompanhamentoAtualizado.getPesoAtual());
        acompanhamento.setIdadeGestacional(acompanhamentoAtualizado.getIdadeGestacional());
        acompanhamento.setPressaoArterial(acompanhamentoAtualizado.getPressaoArterial());
        acompanhamento.setBatimentosCardiacosFeto(acompanhamentoAtualizado.getBatimentosCardiacosFeto());
        acompanhamento.setAlturaUterina(acompanhamentoAtualizado.getAlturaUterina());
        acompanhamento.setTipo(acompanhamentoAtualizado.getTipo());

        var savedAcompanhamento = acompanhamentoRepository.save(acompanhamento);


        var gestante = acompanhamento.getGestacao().getGestante();

        var message = "Olá, " + gestante.getNome() + "!\n" +
                "\n" +
                "Informamos que um acompanhamento gestacional foi atualizado com sucesso. Confira os detalhes:\n" +
                "\n" +
                "- Data do Acompanhamento: " + acompanhamento.getDataAcompanhamento() + "\n" +
                "- Realizado por: " + acompanhamento.getRealizadoPor() + "\n" +
                "- Tipo de Acompanhamento: " + acompanhamento.getTipo() + "\n" +
                "- Peso Atual: " + acompanhamento.getPesoAtual() + " kg\n" +
                "- Idade Gestacional: " + acompanhamento.getIdadeGestacional() + " semanas\n" +
                "- Pressão Arterial: " + acompanhamento.getPressaoArterial() + "\n" +
                "- Batimentos Cardíacos do Feto: " +
                (acompanhamento.getBatimentosCardiacosFeto() != null ? acompanhamento.getBatimentosCardiacosFeto() + " bpm" : "Não medido") + "\n" +
                "- Altura Uterina: " +
                (acompanhamento.getAlturaUterina() != null ? acompanhamento.getAlturaUterina() + " cm" : "Não medido") + "\n" +
                "- Risco IA Identificado: " + (acompanhamento.getRiscoIA() ? "Sim" : "Não") + "\n" +
                "\n" +
                "Continue com o acompanhamento regular para um cuidado completo.\n" +
                "\n" +
                "Atenciosamente,\n" +
                "Equipe Angels.";

        emailService.sendEmail(gestante.getEmail(), "Dados de acompanhamento atualizados", message);

        return savedAcompanhamento;
    }

    @Override
    public List<Acompanhamento> listarAcompanhamentoPorGestacaoId(Long gestacaoId) {
        return acompanhamentoRepository.findByGestacaoId(gestacaoId);
    }

    @Override
    public CadastrarAcompanhamentoDTO cadastrarAcompanhamento(Long gestacaoId, CadastrarAcompanhamentoDTO cadastroAcompanhamentoDTO) {
        var acompanhamento = new Acompanhamento();
        var gestacao = gestacaoRepository.findById(gestacaoId).orElseThrow(() -> new GestacaoNotFoundException("Gestação com id " + gestacaoId + " não encontrada"));

        acompanhamento.setGestacao(gestacao);
        acompanhamento.setDataAcompanhamento(cadastroAcompanhamentoDTO.getDataAcompanhamento());
        acompanhamento.setRealizadoPor(cadastroAcompanhamentoDTO.getRealizadoPor());
        acompanhamento.setPesoAtual(cadastroAcompanhamentoDTO.getPesoAtual());
        acompanhamento.setIdadeGestacional(cadastroAcompanhamentoDTO.getIdadeGestacional());
        acompanhamento.setPressaoArterial(cadastroAcompanhamentoDTO.getPressaoArterial());
        acompanhamento.setBatimentosCardiacosFeto(cadastroAcompanhamentoDTO.getBatimentosCardiacosFeto());
        acompanhamento.setAlturaUterina(cadastroAcompanhamentoDTO.getAlturaUterina());
        acompanhamento.setTipo(cadastroAcompanhamentoDTO.getTipo());
        acompanhamento.setRiscoIA(cadastroAcompanhamentoDTO.getRiscoIA());

        var adicionadoAcompanhamento = registrarAcompanhamento(acompanhamento);

        return new CadastrarAcompanhamentoDTO(adicionadoAcompanhamento);

    }
}

