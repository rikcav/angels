package com.system.angels.dto.update;

import com.system.angels.domain.Acompanhamento;
import com.system.angels.domain.enums.RealizadoPor;
import com.system.angels.domain.enums.TipoAcompanhamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AtualizarAcompanhamentoDTO {
    private Long id;
    private Long gestacaoId;
    private Date dataAcompanhamento;
    private RealizadoPor realizadoPor;  // "médico" ou "enfermeiro"
    private BigDecimal pesoAtual;
    private int idadeGestacional;
    private String pressaoArterial;
    private Integer batimentosCardiacosFeto;  // Pode ser vazio
    private Integer alturaUterina;  // Pode ser vazio
    private TipoAcompanhamento tipo;  // "pré-natal de rotina", "ocorrência" ou "volta"
    private Boolean riscoIA;

    public AtualizarAcompanhamentoDTO(Acompanhamento acompanhamento) {
        this.id = acompanhamento.getId();
        this.gestacaoId = acompanhamento.getGestacao().getId(); // Se houver relacionamento
        this.dataAcompanhamento = acompanhamento.getDataAcompanhamento();
        this.realizadoPor = acompanhamento.getRealizadoPor();
        this.pesoAtual = acompanhamento.getPesoAtual();
        this.idadeGestacional = acompanhamento.getIdadeGestacional();
        this.pressaoArterial = acompanhamento.getPressaoArterial();
        this.batimentosCardiacosFeto = acompanhamento.getBatimentosCardiacosFeto();
        this.alturaUterina = acompanhamento.getAlturaUterina();
        this.tipo = acompanhamento.getTipo();
        this.riscoIA = acompanhamento.getRiscoIA();
    }
}
