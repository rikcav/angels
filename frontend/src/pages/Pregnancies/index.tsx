import {
  ArrowCircleDown,
  ArrowCircleUp,
  ArrowUUpLeft,
  CaretCircleDoubleLeft,
  CaretCircleDoubleRight,
  PlusCircle
} from '@phosphor-icons/react';
import { PregnancyCard } from '../../components/PregnancyCard';
import { PregnantPersonalInfo } from '../../features/Pregnancies/PregnantPersonalInfo';
import * as S from './styles';
import { PregnantInfo } from '../../features/Pregnancies/PregnantInfo';
import moment from 'moment';
import { useParams } from 'react-router-dom';
import { Empty } from 'antd';
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
    toggleExpandInfo
  } = usePregnanciesHandlers(pregnantId);

  const renderCards = () => {
    const currentDate = moment();
    return pregnanciesData
      .filter((item) => item.gestanteId === parseInt(params.id || ''))
      .slice(currentPage, currentPage + 4)
      .map((item, index) => (
        <PregnancyCard
          key={index}
          id={item.id}
          gestationalRisk={true}
          pregnancyStatus={item.situacaoGestacional}
          weeks={currentDate.diff(item.dataInicioGestacao, 'weeks').toString()}
          onClickAdd={() => handleFollowUp(item?.id)}
          onClickThreeDots={() =>
            handlePregnancyScreen(item?.id, parseInt(params.id || ''))
          }
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

      {pregnanciesData.length > 4 ? (
        <S.ArrowsContainer>
          {page == 1 ? (
            <></>
          ) : (
            <CaretCircleDoubleLeft
              size={40}
              color="#B1488A"
              onClick={previous}
              cursor={'Pointer'}
            />
          )}
          <CaretCircleDoubleRight
            size={40}
            color="#B1488A"
            onClick={next}
            cursor={'Pointer'}
          />
        </S.ArrowsContainer>
      ) : (
        <></>
      )}
    </S.Container>
  );
}
