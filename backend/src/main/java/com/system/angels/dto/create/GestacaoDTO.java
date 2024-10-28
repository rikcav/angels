package com.system.angels.dto.create;

import com.system.angels.domain.enums.FatorRH;
import java.math.BigDecimal;
import java.util.Date;

public record GestacaoDTO(
        Long gestante_id,
        boolean consumoAlcool,
        int frequenciaUsoAlcool,
        Date dataUltimaMenstruacao,
        Date dataInicioGestacao,
        FatorRH fatorRh,
        boolean fuma,
        int quantidadeCigarrosDia,
        int usoDrogas,
        boolean gravidezPlanejada,
        int grupoSanguineo,
        BigDecimal pesoAntesGestacao,
        boolean riscoIA,
        int riscoGestacional,
        boolean vacinaHepatiteB,
        int situacaoGestacional) {
}
