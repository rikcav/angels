export interface PregnancyInterface {
  id?: number;
  gestanteId?: number;
  nomeGestante?: string;
  riscoIA?: boolean;
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
  situacaoGestacional: string;
}

export interface PregnancyRegisterInterface {
  dataUltimaMenstruacao: string | string[] | undefined;
  gestante_id: number | undefined;
  dataInicioGestacao: string | string[] | undefined;
  fatorRh: string | undefined;
  frequenciaUsoAlcool: string | undefined;
  fuma: boolean | undefined;
  gravidezPlanejada: boolean | undefined;
  grupoSanguineo: string | undefined;
  pesoAntesGestacao: number | undefined;
  quantidadeCigarrosDia: number | undefined;
  riscoGestacional: number | undefined;
  situacaoGestacional: string | undefined;
  usoDrogas: string | undefined;
  vacinaHepatiteB: boolean | undefined;
  consumoAlcool: boolean | undefined;
  riscoIA: boolean | undefined;
}
