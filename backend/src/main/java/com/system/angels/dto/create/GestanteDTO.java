package com.system.angels.dto.create;

import java.util.Date;

public record GestanteDTO(
        String nome,
        Date dataNascimento,
        String cpf,
        int raca,
        String sexo) {
}
