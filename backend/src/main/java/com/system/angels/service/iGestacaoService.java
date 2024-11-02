package com.system.angels.service;

import com.system.angels.domain.Gestacao;

import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.dto.response.GestacaoComGestanteDTO;
import com.system.angels.dto.response.GestacaoRO;
import java.util.List;

public interface iGestacaoService {
    GestacaoRO gestacaoPorId(Long id);
    
    boolean gestacaoExiste(Long id);

    List<GestacaoComGestanteDTO> gestacoes();

    GestacaoRO registrarGestacao(GestacaoDTO gestacaoDTO);

    GestacaoRO atualizarGestacao(Long id, GestacaoDTO gestacaoDTO);

    void deletarGestacao(Long id);

    List<GestacaoRO> gestacaoPorGestanteId(Long gestanteId);
}
