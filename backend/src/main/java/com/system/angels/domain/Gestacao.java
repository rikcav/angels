package com.system.angels.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.angels.domain.enums.FatorRH;
import com.system.angels.domain.enums.GrupoSanguineo;
import com.system.angels.domain.enums.SituacaoGestacional;
import com.system.angels.domain.enums.UsoAlcool;
import com.system.angels.domain.enums.UsoDrogas;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Gestacao")
public class Gestacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gestante_id", nullable = false)
    @JsonIgnore
    private Gestante gestante;

    @Column(nullable = false)
    private boolean consumoAlcool;

    @Column(nullable = false)
    private UsoAlcool frequenciaUsoAlcool;

    @Column(nullable = false)
    private Date dataUltimaMenstruacao;

    @Column(nullable = false)
    private Date dataInicioGestacao;

    @Column(nullable = false)
    private FatorRH fatorRh;

    @Column(nullable = false)
    private boolean fuma;

    @Column(nullable = false)
    private int quantidadeCigarrosDia;

    @Column(nullable = false)
    private UsoDrogas usoDrogas;

    @Column(nullable = false)
    private boolean gravidezPlanejada;

    @Column(nullable = false)
    private GrupoSanguineo grupoSanguineo;

    @Column(nullable = false)
    private BigDecimal pesoAntesGestacao;

    @Column(nullable = false)
    private int riscoGestacional;

    @Column(nullable = false)
    private boolean riscoIA;

    @Column(nullable = false)
    private boolean vacinaHepatiteB;

    @Column(nullable = false)
    private SituacaoGestacional situacaoGestacional;

    @OneToMany(mappedBy = "gestacao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Acompanhamento> acompanhamentos;
}
