package com.system.angels.dto.create;

import com.system.angels.domain.enums.Sexo;
import java.util.Date;

public record GestanteDTO(
        String nome,
        Date dataNascimento,
        String cpf,
        int raca,
        Sexo sexo) {
}
