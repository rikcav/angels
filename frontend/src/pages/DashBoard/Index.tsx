import { MagnifyingGlass } from '@phosphor-icons/react';

import * as S from './styles.ts';
import SideBar from '../../components/SideBar/index.tsx';
import { PregnancyCard } from '../../components/PregnancyCard/index.tsx';
import moment from 'moment';
import { Empty } from 'antd';
import { Input } from '../../components/Input/index.tsx';
import { Button } from '../../components/Button/index.tsx';
import { useDashboardHandlers } from '../../features/DashBoard/hooks/useDashboardHandlers.ts';
import { Pagination } from '../../components/Pagination/index.tsx';

export function Dashboard() {
  const {
    searchName,
    handleChangeName,
    filteredList,
    handleFollowUp,
    handlePregnancyScreen,
    previous,
    next,
    page,
    handleCleanSearch,
    handleNavigatePregnancies,
    currentPage
  } = useDashboardHandlers();

  const renderCards = () => {
    const currentDate = moment();

    return filteredList
      .slice(currentPage, currentPage + 3)
      .map((item, index) => (
        <PregnancyCard
          key={index}
          id={1}
          pregnantName={item.nomeGestante}
          weeks={currentDate.diff(item.dataInicioGestacao, 'weeks').toString()}
          gestationalRisk={item.riscoIA != undefined ? item.riscoIA : false}
          onClickAdd={() => handleFollowUp(item.id || 0)}
          pregnancyStatus={item.situacaoGestacional}
          onClickFunc={() => handleNavigatePregnancies(item.gestanteId)}
          onClickThreeDots={() =>
            handlePregnancyScreen(item.id || 0, item.gestanteId || 0)
          }
        />
      ));
  };

  return (
    <S.Container>
      <S.Content>
        <S.NavBarContainer>
          <SideBar />
        </S.NavBarContainer>
        <S.CardsContainer>
          <S.InputContainer>
            {searchName !== '' && (
              <Button
                label="Limpar filtro"
                buttonFunction={handleCleanSearch}
                shape="round"
              />
            )}
            {filteredList.length > 0 && (
              <Input
                inputFunction={handleChangeName}
                placeHolder="Digite o nome da gestante..."
                value={searchName}
                rightAdd={<MagnifyingGlass size={24} color="#b1488a" />}
                color="#b1488a"
              />
            )}
          </S.InputContainer>

          {filteredList.length > 0 ? (
            <S.Cards>{renderCards()}</S.Cards>
          ) : (
            <S.EmptyBox
              image={Empty.PRESENTED_IMAGE_SIMPLE}
              description="Sem gestações cadastradas"
            />
          )}

          {filteredList.length >= 4 && (
            <Pagination
              currentPage={page}
              itemsPerPage={3}
              onNext={next}
              onPrevious={previous}
              totalItems={filteredList.length}
            />
          )}
        </S.CardsContainer>
      </S.Content>
    </S.Container>
  );
}
