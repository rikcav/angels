package com.system.angels.dto.update;

import com.system.angels.domain.enums.SituacaoGestacional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarSitGestacionalDTO {
  private SituacaoGestacional situacaoGestacional;
}
