package com.system.angels.dto.create;

import java.math.BigDecimal;
import java.util.Date;

public record AcompanhamentoDTO(
        Long gestacao_id,
        Date dataAcompanhamento,
        String realizadoPor,  // Pode ser "médico" ou "enfermeiro"
        BigDecimal pesoAtual,
        int idadeGestacional,
        String pressaoArterial,
        Integer batimentosCardiacosFeto,  // Pode ser vazio
        Integer alturaUterina,  // Pode ser vazio
        String tipo,  // Pode ser "pré-natal de rotina", "ocorrência" ou "volta"
        Boolean riscoIA) {
}
