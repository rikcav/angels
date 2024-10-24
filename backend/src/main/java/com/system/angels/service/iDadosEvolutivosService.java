package com.system.angels.service;

import com.system.angels.dto.create.DadosEvolutivosDTO;
import com.system.angels.dto.response.DadosEvolutivosRO;

import java.util.List;

public interface iDadosEvolutivosService {
    DadosEvolutivosRO dadosEvolutivosPorId(Long id);

    List<DadosEvolutivosRO> dadosEvolutivosPorGestante(Long gestanteId);

    DadosEvolutivosRO registrarDadosEvolutivos(DadosEvolutivosDTO dadosEvolutivosDTO);
}
