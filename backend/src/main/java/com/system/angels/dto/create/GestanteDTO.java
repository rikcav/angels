package com.system.angels.dto.create;

import com.system.angels.domain.enums.Raca;
import com.system.angels.domain.enums.Sexo;
import java.util.Date;

public record GestanteDTO(
        String nome,
        Date dataNascimento,
        String email,
        String cpf,
        Raca raca,
        Sexo sexo,
        String username
) {
}
