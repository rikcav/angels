package com.system.angels.dto.create;

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
public class CadastrarAcompanhamentoDTO {

    private Long gestacao_id;
    private Date dataAcompanhamento;
    private RealizadoPor realizadoPor;  // Pode ser "médico" ou "enfermeiro"
    private BigDecimal pesoAtual;
    private int idadeGestacional;
    private String pressaoArterial;
    private Integer batimentosCardiacosFeto;  // Pode ser vazio
    private Integer alturaUterina;  // Pode ser vazio
    private TipoAcompanhamento tipo;  // Pode ser "pré-natal de rotina", "ocorrência" ou "volta"
    private Boolean riscoIA;

    public CadastrarAcompanhamentoDTO(Acompanhamento acompanhamento) {
        this.gestacao_id = acompanhamento.getGestacao().getId();
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
