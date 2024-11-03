export interface FollowUpInterface {
  id?: number;
  gestacaoId?: number | undefined;
  dataAcompanhamento?: string | string[] | undefined;
  realizadoPor?: string;
  pesoAtual?: number;
  idadeGestacional?: number;
  pressaoArterial?: string;
  batimentosCardiacosFeto?: number;
  alturaUterina?: number;
  tipo?: string;
  riscoIA?: boolean;
}
