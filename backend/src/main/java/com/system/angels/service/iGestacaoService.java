package com.system.angels.service;

import com.system.angels.domain.Gestacao;

import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.dto.response.GestacaoRO;
import java.util.List;

public interface iGestacaoService {
    
    Gestacao obterGestacaoPorId(Long id);
    
    boolean gestacaoExiste(Long id);
    
    List<Gestacao> obterTodasGestacoes();

    GestacaoRO adicionarGestacao(GestacaoDTO gestacaoDTO);

    Gestacao atualizarGestacao(Long id, Gestacao atualizarGestacao);

    void deletarGestacao(Long id);

    List<Gestacao> listarGestacaoPorGestanteId(Long gestanteId);
}
