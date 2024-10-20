package com.system.angels.service;

import com.system.angels.domain.DadosEvolutivos;
import com.system.angels.domain.Gestante;
import java.util.List;

public interface iDadosEvolutivosService {
  List<DadosEvolutivos> listarDadosEvolutivosPorGestante(Gestante gestante);

  DadosEvolutivos buscarDadosEvolutivosPorId(Long id);

  DadosEvolutivos registrarDadosEvolutivos(DadosEvolutivos dadosEvolutivos);
}
