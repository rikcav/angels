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
