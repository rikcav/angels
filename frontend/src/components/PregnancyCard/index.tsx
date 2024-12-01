import { DotsThree, Plus, PencilSimple } from '@phosphor-icons/react';
import WhiteLogo from '../../assets/angelsWhiteLogo.svg';
import * as S from './styles';
import EditStatusModal from '../EditStatusModal';
import { useState } from 'react';

interface InfoProps {
  id: number;
  weeks: string;
  pregnantName?: string;
  gestationalRisk: string;
  pregnancyStatus: string;
  onClickFunc?: React.MouseEventHandler<HTMLDivElement>;
  onClickAdd?: React.MouseEventHandler<HTMLButtonElement>;
  onClickThreeDots?: React.MouseEventHandler<HTMLButtonElement>;
  handlePatchGestacao: (
    gestacaoId: number,
    situacaoGestacional: string
  ) => Promise<void>;
}

export const PregnancyCard: React.FC<InfoProps> = ({
  id,
  weeks,
  pregnantName,
  pregnancyStatus,
  gestationalRisk,
  onClickFunc,
  onClickAdd,
  onClickThreeDots,
  handlePatchGestacao
}) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [status, setStatus] = useState(pregnancyStatus);

  const handleSaveStatus = async (newStatus: string) => {
    if (newStatus !== status) {
      try {
        await handlePatchGestacao(id, newStatus);
        setStatus(newStatus);
        setIsModalOpen(false);
      } catch (error) {
        console.error('Erro ao atualizar o status da gestação:', error);
      }
    }
  };

  const pregnancyStatusDescription =
    pregnancyStatus === 'EM_ANDAMENTO'
      ? 'Em andamento'
      : pregnancyStatus === 'FINALIZADA_COM_SUCESSO'
      ? 'Finalizada com sucesso'
      : 'Finalizada com desfecho negativo';

  return (
    <S.PregnancyCardContainer>
      <S.PregnancyInfoContainer onClick={onClickFunc}>
        <S.LineContainer>
          {gestationalRisk == 'NAO' ? (
            <S.GreenRiskLine />
          ) : gestationalRisk == 'SIM' ? (
            <S.RedRiskLine />
          ) : (
            <S.RiskLine />
          )}
        </S.LineContainer>
        <S.ImageContainer>
          <img src={WhiteLogo} />
        </S.ImageContainer>
        {pregnantName ? (
          <S.PregnancyInfoTextContainer>
            <S.PregnancyInfoTitle>Nome da gestante:</S.PregnancyInfoTitle>
            <S.PregnancyInfoContent>{pregnantName}</S.PregnancyInfoContent>
          </S.PregnancyInfoTextContainer>
        ) : (
          <S.PregnancyInfoTitle>Gestação {id}</S.PregnancyInfoTitle>
        )}
        <S.PregnancyInfoTextContainer>
          <S.PregnancyInfoTitle>Semanas:</S.PregnancyInfoTitle>
          <S.PregnancyInfoContent>{weeks} semanas</S.PregnancyInfoContent>
        </S.PregnancyInfoTextContainer>
        <S.PregnancyInfoTextContainer>
          <S.PregnancyInfoTitle>Status da gestação:</S.PregnancyInfoTitle>
          <S.PregnancyInfoContent>
            {pregnancyStatusDescription}
          </S.PregnancyInfoContent>
        </S.PregnancyInfoTextContainer>
      </S.PregnancyInfoContainer>
      <S.PregnancyButtonsContainer>
        {pregnancyStatus === 'EM_ANDAMENTO' && (
          <S.PregnancyCardButton onClick={onClickAdd}>
            <Plus weight="bold" size={28} color="#B1488A" />
          </S.PregnancyCardButton>
        )}
        <S.PregnancyCardButton onClick={() => setIsModalOpen(true)}>
          <PencilSimple weight="bold" size={28} color="#B1488A" />
        </S.PregnancyCardButton>
        <S.PregnancyCardButton onClick={onClickThreeDots}>
          <DotsThree weight="bold" size={28} color="#B1488A" />
        </S.PregnancyCardButton>
      </S.PregnancyButtonsContainer>
      <EditStatusModal
        open={isModalOpen}
        currentStatus={status}
        onClose={() => setIsModalOpen(false)}
        onSave={handleSaveStatus}
      />
    </S.PregnancyCardContainer>
  );
};
