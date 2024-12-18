package com.system.angels.dto.update;

import com.system.angels.domain.enums.FatorRH;
import com.system.angels.domain.enums.GrupoSanguineo;
import com.system.angels.domain.enums.RiscoIA;
import com.system.angels.domain.enums.SituacaoGestacional;
import com.system.angels.domain.enums.UsoAlcool;
import com.system.angels.domain.enums.UsoDrogas;
import java.math.BigDecimal;
import java.util.Date;

import com.system.angels.domain.Gestacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AtualizarGestacaoDTO {
    private Long id;
    private Long gestante_id;
    private boolean consumoAlcool;
    private UsoAlcool frequenciaUsoAlcool;
    private Date dataUltimaMenstruacao;
    private Date dataInicioGestacao;
    private FatorRH fatorRh;
    private boolean fuma;
    private int quantidadeCigarrosDia;
    private UsoDrogas usoDrogas;
    private boolean gravidezPlanejada;
    private GrupoSanguineo grupoSanguineo;
    private BigDecimal pesoAntesGestacao;
    private int riscoGestacional;
    private boolean vacinaHepatiteB;
    private SituacaoGestacional situacaoGestacional;
    private RiscoIA riscoIA;
    private String username;

    public AtualizarGestacaoDTO(Gestacao gestacao) {
        this.id = gestacao.getId();
        this.gestante_id = gestacao.getGestante().getId();
        this.consumoAlcool = gestacao.isConsumoAlcool();
        this.frequenciaUsoAlcool = gestacao.getFrequenciaUsoAlcool();
        this.dataUltimaMenstruacao = gestacao.getDataUltimaMenstruacao();
        this.dataInicioGestacao = gestacao.getDataInicioGestacao();
        this.fatorRh = gestacao.getFatorRh();
        this.fuma = gestacao.isFuma();
        this.quantidadeCigarrosDia = gestacao.getQuantidadeCigarrosDia();
        this.usoDrogas = gestacao.getUsoDrogas();
        this.gravidezPlanejada = gestacao.isGravidezPlanejada();
        this.grupoSanguineo = gestacao.getGrupoSanguineo();
        this.pesoAntesGestacao = gestacao.getPesoAntesGestacao();
        this.riscoGestacional = gestacao.getRiscoGestacional();
        this.vacinaHepatiteB = gestacao.isVacinaHepatiteB();
        this.situacaoGestacional = gestacao.getSituacaoGestacional();
        this.riscoIA = gestacao.getRiscoIA();
    }
    
}
