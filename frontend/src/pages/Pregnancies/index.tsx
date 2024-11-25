import {
  ArrowCircleDown,
  ArrowCircleUp,
  ArrowUUpLeft,
  PlusCircle
} from '@phosphor-icons/react';
import { PregnancyCard } from '../../components/PregnancyCard';
import { PregnantPersonalInfo } from '../../features/Pregnancies/PregnantPersonalInfo';
import * as S from './styles';
import { PregnantInfo } from '../../features/Pregnancies/PregnantInfo';
import moment from 'moment';
import { useParams } from 'react-router-dom';
import { Empty } from 'antd';
import { Pagination } from '../../components/Pagination';
import { usePregnanciesHandlers } from '../../features/Pregnancies/hooks/usePregnanciesHandlers';

export default function Pregnancies() {
  const params = useParams();
  const pregnantId = parseInt(params.id || '');

  const {
    currentPage,
    page,
    name,
    cpf,
    pregnanciesData,
    toggleInfo,
    previous,
    next,
    handleNewPregnancy,
    handleFollowUp,
    handlePregnancyScreen,
    handleBackArrow,
    toggleExpandInfo,
    handlePatchGestacao
  } = usePregnanciesHandlers(pregnantId);

  const renderCards = () => {
    const currentDate = moment();
    return pregnanciesData
      .slice(currentPage, currentPage + 4)
      .map((item, index) => (
        <PregnancyCard
          key={index}
          id={item.id || 0}
          gestationalRisk={item.riscoIA || false}
          pregnancyStatus={item.situacaoGestacional}
          weeks={currentDate.diff(item.dataInicioGestacao, 'weeks').toString()}
          onClickAdd={() => handleFollowUp(item?.id || 0)}
          onClickThreeDots={() =>
            handlePregnancyScreen(item?.id || 0, parseInt(params.id || ''))
          }
          handlePatchGestacao={handlePatchGestacao}
        />
      ));
  };

  return (
    <S.Container>
      <S.Header>
        <ArrowUUpLeft size={22} color="#B1488A" onClick={handleBackArrow} />
        <S.PregnanciesText>GESTAÇÕES</S.PregnanciesText>
        <S.NewPregnancyButton onClick={handleNewPregnancy}>
          <PlusCircle size={18} weight="bold" />
          NOVA GESTAÇÃO
        </S.NewPregnancyButton>
      </S.Header>
      <S.PregnantInfoContainer>
        <PregnantPersonalInfo name="Nome" value={name} />
        <PregnantPersonalInfo name="CPF" value={cpf} />
      </S.PregnantInfoContainer>
      {toggleInfo ? (
        <ArrowCircleUp
          size={25}
          cursor={'Pointer'}
          color="#B1488A"
          weight="bold"
          onClick={toggleExpandInfo}
        />
      ) : (
        <ArrowCircleDown
          size={25}
          cursor={'Pointer'}
          color="#B1488A"
          weight="bold"
          onClick={toggleExpandInfo}
        />
      )}

      {toggleInfo ? <PregnantInfo id={pregnantId} /> : <></>}
      {pregnanciesData.length > 0 ? (
        <S.CardsContainer>{renderCards()}</S.CardsContainer>
      ) : (
        <S.EmptyBox
          image={Empty.PRESENTED_IMAGE_SIMPLE}
          description="Sem gestações cadastradas"
        />
      )}

      <S.PaginationContainer>
        {pregnanciesData.length > 4 && (
          <Pagination
            currentPage={page}
            itemsPerPage={4}
            onNext={next}
            onPrevious={previous}
            totalItems={pregnanciesData.length}
          />
        )}
      </S.PaginationContainer>
    </S.Container>
  );
}
