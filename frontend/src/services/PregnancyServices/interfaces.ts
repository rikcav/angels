export interface PregnantInterface {
  cpf: string;
  dataNascimento: string;
  id: number;
  nome: string;
  gender: string;
  raca: number;
}

export interface PregnancyInterface {
  dataInicioGestacao: string;
  fatorRh: string;
  frequenciaUsoAlcool: number;
  fuma: boolean;
  gestanteId: number;
  nomeGestante: string;
  gravidezPlanejada: boolean;
  grupoSanguineo: number;
  id: number;
  pesoAntesGestacao: number;
  quantidadeCigarrosDia: number;
  riscoGestacional: number;
  situacaoGestacional: string;
  usoDrogas: number;
  vacinaHepatiteB: number;
  consumoAlcool: boolean;
}
