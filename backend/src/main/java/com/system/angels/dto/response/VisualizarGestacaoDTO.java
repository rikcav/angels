package com.system.angels.dto.response;

import com.system.angels.domain.enums.FatorRH;
import java.math.BigDecimal;
import java.util.Date;

import com.system.angels.domain.Gestacao;
import com.system.angels.domain.Gestante;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VisualizarGestacaoDTO {
    private Long id;
    private Long gestante_id;
    private boolean consumoAlcool;
    private int frequenciaUsoAlcool;
    private Date dataUltimaMenstruacao;
    private Date dataInicioGestacao;
    private FatorRH fatorRh;
    private boolean fuma;
    private int quantidadeCigarrosDia;
    private int usoDrogas;
    private boolean gravidezPlanejada;
    private int grupoSanguineo;
    private BigDecimal pesoAntesGestacao;
    private int riscoGestacional;
    private boolean vacinaHepatiteB;
    private int situacaoGestacional;

    public VisualizarGestacaoDTO(Gestacao gestacao) {
        this.id = gestacao.getId();
        this.gestante_id = gestacao.getGestante().getId();
        this.consumoAlcool = gestacao.isConsumoAlcool();
        this.frequenciaUsoAlcool = gestacao.getFrequenciaUsoAlcool();
        this.dataUltimaMenstruacao = gestacao.getDataUltimaMenstruacao();
        this.dataInicioGestacao = gestacao.getDataInicioGestacao();
        this.fatorRh = gestacao.getFatorRh();
        this.fuma = gestacao.isFuma();
        this.quantidadeCigarrosDia = gestacao.getQuantidadeCigarrosDia();
        this.usoDrogas = gestacao.getUsoDrogas();
        this.gravidezPlanejada = gestacao.isGravidezPlanejada();
        this.grupoSanguineo = gestacao.getGrupoSanguineo();
        this.pesoAntesGestacao = gestacao.getPesoAntesGestacao();
        this.riscoGestacional = gestacao.getRiscoGestacional();
        this.vacinaHepatiteB = gestacao.isVacinaHepatiteB();
        this.situacaoGestacional = gestacao.getSituacaoGestacional();
    }

}
