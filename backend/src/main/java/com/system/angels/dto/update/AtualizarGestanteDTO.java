package com.system.angels.dto.update;

import com.system.angels.domain.enums.Raca;
import com.system.angels.domain.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AtualizarGestanteDTO {
    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private Raca raca;
    private Sexo sexo;
}
