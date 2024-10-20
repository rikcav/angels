package com.system.angels.service;

import com.system.angels.domain.Gestacao;

import java.util.List;

public interface iGestacaoService {
    
    Gestacao obterGestacaoPorId(Long id);
    
    boolean gestacaoExiste(Long id);
    
    List<Gestacao> obterTodasGestacoes();

    Gestacao adicionarGestacao(Gestacao gestacao);

    Gestacao atualizarGestacao(Long id, Gestacao atualizarGestacao);

    void deletarGestacao(Long id);

    List<Gestacao> listarGestacaoPorGestanteId(Long gestanteId);
}
