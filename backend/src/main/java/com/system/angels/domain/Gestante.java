package com.system.angels.domain;

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

    @Column(nullable = false)
    private int raca;

    @Column(nullable = false)
    private Sexo sexo;

    @OneToMany(mappedBy = "gestante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DadosEvolutivos> dadosEvolutivos;
}
