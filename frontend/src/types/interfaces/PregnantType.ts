export interface PregnantInterface {
  id?: number;
  gestante?: PregnantInfoInterface;
  dadosEvolutivos?: EvolutionDataInterface;
}

export interface PregnantInfoInterface {
  id?: number;
  nome?: string;
  dataNascimento?: string;
  cpf?: string;
  raca?: string;
  sexo?: string;
}

export interface EvolutionDataInterface {
  id?: number;
  gestanteId?: number;
  municipio?: string;
  diagnosticoDesnutricao?: string;
  energiaEletricaDomicilio?: boolean;
  escolaridade?: number;
  tipoMoradia?: string;
  moradiaRedeEsgoto?: boolean;
  rendaFamiliar?: number;
  tratamentoAgua?: boolean;
  amamentacao?: boolean;
  chefeFamilia?: boolean;
  dataUltimaGestacao?: string;
  emRisco?: boolean;
  estadoCivil?: string;
  quantidadeAbortos?: number;
  quantidadeFilhosVivos?: number;
  quantidadeGemelares?: number;
  quantidadeGestacao?: number;
  quantidadeNascidosMortos?: number;
  quantidadeNascidosVivos?: number;
  quantidadeObitosSemana1?: number;
  quantidadeObitosAposSemana1?: number;
  quantidadePartos?: number;
  quantidadePartosCesarios?: number;
  quantidadePartosVaginais?: number;
  quantidadeRnPeso2500_4000?: number;
  quantidadeRnPesoMaior4000?: number;
  quantidadeRnPesoMenor2500?: number;
  hipertensao?: boolean;
  diabetes?: boolean;
  cirurgiaPelvica?: boolean;
  infeccaoUrinaria?: boolean;
  maFormacaoCongenita?: boolean;
  familiarGemeos?: boolean;
  contato?: string;
  contatoEmergencia?: string;
}

export interface PregantResponseInterface {
  id?: number;
  nome?: string;
  dataNascimento?: string;
  cpf?: string;
  sexo?: string;
  municipio?: string;
  emRisco?: boolean;
  quantidadeAbortos?: number;
  quantidadeFilhosVivos?: number;
  quantidadeGemelares?: number;
  quantidadeGestacao?: number;
  quantidadeNascidosMortos?: number;
  quantidadeNascidosVivos?: number;
  hipertensao?: boolean;
  diabetes?: boolean;
  maFormacaoCongenita?: boolean;
}
