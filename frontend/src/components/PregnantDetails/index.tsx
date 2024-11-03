import React from 'react';
import moment from 'moment';
import * as S from './styles';
import { InfoContainer } from '../InfoContainer';

interface PregnantDetailsProps {
  pregnantInfo?: {
    nome?: string;
    cpf?: string;
    dataNascimento?: string;
    sexo?: string;
    municipio?: string;
    emRisco?: boolean;
    hipertensao?: boolean;
    diabetes?: boolean;
    maFormacaoCongenita?: boolean;
    quantidadeAbortos?: number;
    quantidadeFilhosVivos?: number;
    quantidadeGemelares?: number;
    quantidadeGestacao?: number;
    quantidadeNascidosVivos?: number;
    quantidadeNascidosMortos?: number;
  };
}

export const PregnantDetails: React.FC<PregnantDetailsProps> = ({
  pregnantInfo
}) => {
  return (
    <S.ContentContainer>
      <S.LineContainer>
        <InfoContainer name="Nome" value={pregnantInfo?.nome} />
        <InfoContainer name="CPF" value={pregnantInfo?.cpf} />
      </S.LineContainer>
      <S.LineContainer>
        <InfoContainer
          name="Nascimento"
          value={moment(pregnantInfo?.dataNascimento).format('DD/MM/YYYY')}
        />
        <InfoContainer name="Gênero" value={pregnantInfo?.sexo} />
      </S.LineContainer>
      <S.LineContainer>
        <InfoContainer name="Município" value={pregnantInfo?.municipio} />
        <InfoContainer
          name="Risco"
          value={pregnantInfo?.emRisco ? 'Sim' : 'Não'}
        />
        <InfoContainer
          name="Hipertensão"
          value={pregnantInfo?.hipertensao ? 'Sim' : 'Não'}
        />
        <InfoContainer
          name="Diabetes"
          value={pregnantInfo?.diabetes ? 'Sim' : 'Não'}
        />
        <InfoContainer
          name="Má formação congênita"
          value={pregnantInfo?.maFormacaoCongenita ? 'Sim' : 'Não'}
        />
      </S.LineContainer>
      <S.LineContainer>
        <InfoContainer
          name="Abortos"
          value={pregnantInfo?.quantidadeAbortos?.toString()}
        />
        <InfoContainer
          name="Filhos Vivos"
          value={pregnantInfo?.quantidadeFilhosVivos?.toString()}
        />
        <InfoContainer
          name="Gemelares"
          value={pregnantInfo?.quantidadeGemelares?.toString()}
        />
        <InfoContainer
          name="Gestações"
          value={pregnantInfo?.quantidadeGestacao?.toString()}
        />
        <InfoContainer
          name="Nascidos vivos"
          value={pregnantInfo?.quantidadeNascidosVivos?.toString()}
        />
        <InfoContainer
          name="Nascidos mortos"
          value={pregnantInfo?.quantidadeNascidosMortos?.toString()}
        />
      </S.LineContainer>
    </S.ContentContainer>
  );
};
