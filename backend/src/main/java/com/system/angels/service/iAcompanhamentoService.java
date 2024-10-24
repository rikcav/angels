package com.system.angels.service;

import com.system.angels.domain.Acompanhamento;
import java.util.List;

public interface iAcompanhamentoService {
  List<Acompanhamento> listarAcompanhamentos();

  Acompanhamento buscarAcompanhamentoPorId(Long id);

  Acompanhamento registrarAcompanhamento(Acompanhamento acompanhamento);

  void deletarAcompanhamento(Long id);

  List<Acompanhamento> listarAcompanhamentoPorGestacaoId(Long gestacaoId);

  Acompanhamento atualizarAcompanhamento(Long id, Acompanhamento acompanhamentoAtualizado);

}
