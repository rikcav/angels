package com.system.angels.domain;

import com.system.angels.domain.enums.RealizadoPor;
import com.system.angels.domain.enums.TipoAcompanhamento;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Acompanhamento")
public class Acompanhamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gestacao_id", nullable = false)
    private Gestacao gestacao;

    @Column(nullable = false)
    private Date dataAcompanhamento;

    @Column(nullable = false)
    private RealizadoPor realizadoPor;

    @Column(nullable = false)
    private BigDecimal pesoAtual;

    @Column(nullable = false)
    private int idadeGestacional;

    @Column(nullable = false)
    private String pressaoArterial;

    @Column
    private Integer batimentosCardiacosFeto;

    @Column
    private Integer alturaUterina;

    @Column(nullable = false)
    private TipoAcompanhamento tipo;

    @Column(nullable = false)
    private Boolean riscoIA;

}
