package com.system.angels.dto.response;

import java.util.Date;

public record GestanteRO(
        Long id,
        String nome,
        Date dataNascimento,
        String cpf,
        String sexo,
        String municipio,
        boolean emRisco,
        int quantidadeAbortos,
        int quantidadeFilhosVivos,
        int quantidadeGemelares,
        int quantidadeGestacao,
        int quantidadeNascidosMortos,
        int quantidadeNascidosVivos,
        boolean hipertensao,
        boolean diabetes,
        boolean maFormacaoCongenita) {
}
