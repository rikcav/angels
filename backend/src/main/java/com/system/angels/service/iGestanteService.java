package com.system.angels.service;

import com.system.angels.domain.Gestante;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.dto.response.GestanteRO;
import java.util.List;

public interface iGestanteService {
  List<GestanteRO> gestantes();

  GestanteRO gestantePorId(Long id);

  GestanteRO gestantePorCpf(String cpf);

  GestanteRO registrarGestante(GestanteDTO gestanteDTO);

  GestanteRO atualizarGestante(Long id, GestanteDTO gestanteDTO);

  void deletarGestante(Long id);
}
