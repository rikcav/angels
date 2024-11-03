import * as S from './styles';

interface CardProps {
  dataUltimaMenstruacao: string;
  dataInicioGestacao: string;
  gravidezPlanejada: string;
  fuma: string;
  quantidadeCigarrosDia: number;
  consumoAlcool: string;
  frequenciaUsoAlcool: string;
  grupoSanguineo: string;
  fatorRh: string;
  pesoAntesGestacao: number;
  riscoGestacional: string;
  vacinaHepatiteB: string;
  situacaoGestacional: string;
  usoDrogas: string;
}

export const PregnancyDisplayInfos: React.FC<CardProps> = ({
  consumoAlcool,
  dataInicioGestacao,
  dataUltimaMenstruacao,
  fatorRh,
  frequenciaUsoAlcool,
  fuma,
  gravidezPlanejada,
  grupoSanguineo,
  pesoAntesGestacao,
  quantidadeCigarrosDia,
  riscoGestacional,
  situacaoGestacional,
  vacinaHepatiteB,
  usoDrogas
}) => {
  return (
    <S.PregnancyInfo>
      <S.LineContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>
            Última menstruação
          </S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {dataUltimaMenstruacao}
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>
            Inicio da gestação
          </S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {dataInicioGestacao}
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>
            Gravidez planejada
          </S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {gravidezPlanejada}
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Fuma</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>{fuma}</S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Cigarros por dia</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {quantidadeCigarrosDia}
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
      </S.LineContainer>
      <S.LineContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Consumo de Álcool</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>{consumoAlcool}</S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>
            Frequência Consumo de Álcool
          </S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {frequenciaUsoAlcool}
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Uso de drogas</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>{usoDrogas}</S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Grupo Sanguíneo</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>{grupoSanguineo}</S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Fator RH</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>{fatorRh}</S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
      </S.LineContainer>
      <S.LineContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>
            Peso antes da gestação
          </S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {pesoAntesGestacao} kg
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Risco Gestacional</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {riscoGestacional}
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>Vacina Hepatite B</S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>{vacinaHepatiteB}</S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
        <S.InfoPregnancyContainer>
          <S.InfoPregnancyTitleText>
            Situação Gestacional
          </S.InfoPregnancyTitleText>
          <S.InfoPregnancyValueText>
            {situacaoGestacional}
          </S.InfoPregnancyValueText>
        </S.InfoPregnancyContainer>
      </S.LineContainer>
    </S.PregnancyInfo>
  );
};
