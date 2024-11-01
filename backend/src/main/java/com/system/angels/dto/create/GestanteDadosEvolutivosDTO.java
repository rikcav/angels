package com.system.angels.dto.create;

import com.system.angels.domain.enums.DiagnosticoDesnutricao;
import com.system.angels.domain.enums.EstadoCivil;
import com.system.angels.domain.enums.Raca;
import com.system.angels.domain.enums.TipoMoradia;
import java.math.BigDecimal;
import java.util.Date;

public record GestanteDadosEvolutivosDTO(
        String nome,
        Date dataNascimento,
        String cpf,
        Raca raca,
        String sexo,
        String municipio,
        DiagnosticoDesnutricao diagnosticoDesnutricao,
        boolean energiaEletricaDomicilio,
        int escolaridade,
        TipoMoradia tipoMoradia,
        boolean moradiaRedeEsgoto,
        BigDecimal rendaFamiliar,
        boolean tratamentoAgua,
        boolean amamentacao,
        boolean chefeFamilia,
        Date dataUltimaGestacao,
        boolean emRisco,
        EstadoCivil estadoCivil,
        int quantidadeAbortos,
        int quantidadeFilhosVivos,
        int quantidadeGemelares,
        int quantidadeGestacao,
        int quantidadeNascidosMortos,
        int quantidadeNascidosVivos,
        int quantidadeObitosSemana1,
        int quantidadeObitosAposSemana1,
        int quantidadePartos,
        int quantidadePartosCesarios,
        int quantidadePartosVaginais,
        int quantidadeRnPeso2500_4000,
        int quantidadeRnPesoMaior4000,
        int quantidadeRnPesoMenor2500,
        boolean hipertensao,
        boolean diabetes,
        boolean cirurgiaPelvica,
        boolean infeccaoUrinaria,
        boolean maFormacaoCongenita,
        boolean familiarGemeos,
        String contato,
        String contatoEmergencia) {
}
