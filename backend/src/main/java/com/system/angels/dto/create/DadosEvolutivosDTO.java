package com.system.angels.dto.create;

import java.math.BigDecimal;
import java.util.Date;

public record DadosEvolutivosDTO(
        Long gestanteId,
        String municipio,
        int diagnosticoDesnutricao,
        boolean energiaEletricaDomicilio,
        int escolaridade,
        int tipoMoradia,
        boolean moradiaRedeEsgoto,
        BigDecimal rendaFamiliar,
        boolean tratamentoAgua,
        boolean amamentacao,
        boolean chefeFamilia,
        Date dataUltimaGestacao,
        boolean emRisco,
        int estadoCivil,
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
