package com.system.angels.service;

import com.system.angels.domain.Gestacao;

import com.system.angels.domain.enums.SituacaoGestacional;
import com.system.angels.dto.create.GestacaoDTO;
import com.system.angels.dto.response.GestacaoComGestanteDTO;
import com.system.angels.dto.response.GestacaoRO;
import com.system.angels.dto.update.AtualizarSitGestacionalDTO;
import java.util.List;

public interface iGestacaoService {
    GestacaoRO gestacaoPorId(Long id);
    
    boolean gestacaoExiste(Long id);

    List<GestacaoComGestanteDTO> gestacoes();

    GestacaoRO registrarGestacao(GestacaoDTO gestacaoDTO);

    GestacaoRO atualizarGestacao(Long id, GestacaoDTO gestacaoDTO);

    void deletarGestacao(Long id);

    void atualizarSituacaoGestacional(Long id, AtualizarSitGestacionalDTO sitGestacionalDTO);

    List<GestacaoRO> gestacaoPorGestanteId(Long gestanteId);
}
