package com.system.angels.dto.response;

import com.system.angels.domain.Gestacao;
import com.system.angels.domain.enums.FatorRH;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class GestacaoComGestanteDTO {
  private Long id;
  private Long gestanteId;
  private String nomeGestante;
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

  public GestacaoComGestanteDTO(Gestacao gestacao) {
    this.id = gestacao.getId();
    this.gestanteId = gestacao.getGestante().getId();
    this.nomeGestante = gestacao.getGestante().getNome();
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
