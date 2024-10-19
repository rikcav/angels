package com.system.angels.dto.create;

import java.math.BigDecimal;
import java.util.Date;

public record GestacaoDTO(
        Long gestante_id,
        boolean consumoAlcool,
        int frequenciaUsoAlcool,
        Date dataUltimaMenstruacao,
        Date dataInicioGestacao,
        String fatorRh,
        boolean fuma,
        int quantidadeCigarrosDia,
        int usoDrogas,
        boolean gravidezPlanejada,
        int grupoSanguineo,
        BigDecimal pesoAntesGestacao,
        int riscoGestacional,
        boolean vacinaHepatiteB,
        int situacaoGestacional) {
}
