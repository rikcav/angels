package com.system.angels.dto.response;

import com.system.angels.domain.enums.FatorRH;
import com.system.angels.domain.enums.GrupoSanguineo;
import com.system.angels.domain.enums.SituacaoGestacional;
import com.system.angels.domain.enums.UsoAlcool;
import com.system.angels.domain.enums.UsoDrogas;
import java.math.BigDecimal;
import java.util.Date;

public record GestacaoRO(
  Long id,
  Long gestanteId,
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
  int riscoGestacional,
  boolean riscoIA,
  boolean vacinaHepatiteB,
  SituacaoGestacional situacaoGestacional) {
}
