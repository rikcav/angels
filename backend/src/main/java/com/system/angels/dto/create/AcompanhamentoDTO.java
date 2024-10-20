package com.system.angels.dto.create;

import com.system.angels.domain.enums.RealizadoPor;
import com.system.angels.domain.enums.TipoAcompanhamento;
import java.math.BigDecimal;
import java.util.Date;

public record AcompanhamentoDTO(
    Long gestacao_id,
    Date dataAcompanhamento,
    RealizadoPor realizadoPor,  // Pode ser "médico" ou "enfermeiro"
    BigDecimal pesoAtual,
    int idadeGestacional,
    String pressaoArterial,
    Integer batimentosCardiacosFeto,  // Pode ser vazio
    Integer alturaUterina,  // Pode ser vazio
    TipoAcompanhamento tipo,  // Pode ser "pré-natal de rotina", "ocorrência" ou "volta"
    Boolean riscoIA) {
}
