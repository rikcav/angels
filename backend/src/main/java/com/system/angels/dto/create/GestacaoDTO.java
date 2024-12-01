package com.system.angels.dto.create;

import com.system.angels.domain.enums.FatorRH;
import com.system.angels.domain.enums.GrupoSanguineo;
import com.system.angels.domain.enums.SituacaoGestacional;
import com.system.angels.domain.enums.UsoAlcool;
import com.system.angels.domain.enums.UsoDrogas;
import java.math.BigDecimal;
import java.util.Date;

public record GestacaoDTO(
        Long gestante_id,
        boolean consumoAlcool,
        UsoAlcool frequenciaUsoAlcool,
        Date dataUltimaMenstruacao,
        Date dataInicioGestacao,
        FatorRH fatorRh,
        boolean fuma,
        int quantidadeCigarrosDia,
        UsoDrogas usoDrogas,
        boolean gravidezPlanejada,
        GrupoSanguineo grupoSanguineo,
        BigDecimal pesoAntesGestacao,
        boolean riscoIA,
        int riscoGestacional,
        boolean vacinaHepatiteB,
        String username,
        SituacaoGestacional situacaoGestacional) {
}
