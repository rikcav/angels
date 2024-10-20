package com.system.angels.service;

import com.system.angels.domain.Gestante;
import java.util.List;

public interface iGestanteService {
  List<Gestante> listarGestantes();
  Gestante buscarGestantePorId(Long id);
  Gestante buscarGestantePorCpf(String cpf);
  Gestante registrarGestante(Gestante gestante);
  Gestante atualizarGestante(Long id, Gestante gestanteAtualizada);
  void deletarGestante(Long id);
}
