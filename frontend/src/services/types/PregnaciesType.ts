export interface PregnancyInterface {
  id?: number;
  gestanteId?: number;
  nomeGestante: string;
  riscoIA?: boolean;
  // gestante?: PregnantInfoInterface;
  consumoAlcool: boolean;
  frequenciaUsoAlcool: number;
  dataUltimaMenstruacao: string;
  dataInicioGestacao: string;
  fatorRh: string;
  fuma: boolean;
  quantidadeCigarrosDia: number;
  usoDrogas: number;
  gravidezPlanejada: boolean;
  grupoSanguineo: number;
  pesoAntesGestacao: number;
  riscoGestacional: number;
  vacinaHepatiteB: boolean;
  situacaoGestacional: number;
}
