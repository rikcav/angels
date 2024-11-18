package com.system.angels.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.angels.domain.enums.Raca;
import com.system.angels.domain.enums.Sexo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Gestante")
public class Gestante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Raca raca;

    @Column(nullable = false)
    private Sexo sexo;

    @OneToMany(mappedBy = "gestante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DadosEvolutivos> dadosEvolutivos;
}
