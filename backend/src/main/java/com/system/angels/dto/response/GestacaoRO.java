package com.system.angels.dto.response;

import com.system.angels.domain.enums.FatorRH;
import java.math.BigDecimal;
import java.util.Date;

public record GestacaoRO(
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
  int riscoGestacional,
  boolean vacinaHepatiteB,
  int situacaoGestacional) {
}
