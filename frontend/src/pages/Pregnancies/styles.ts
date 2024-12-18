import { Empty } from 'antd';
import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 170px;
  padding-right: 190px;
  padding-top: 50px;
  gap: 28px;

  @media (max-width: 1000px) {
    padding-right: 0;
  }
`;

export const Header = styled.section`
  display: flex;
  justify-content: space-between;
  align-items: center;

  @media (max-width: 1000px) {
    flex-direction: column;
    gap: 10px;
    align-items: flex-start;
  }

  svg {
    cursor: pointer;
  }
`;

export const PregnantInfoContainer = styled.section`
  display: flex;
  justify-content: space-between;
  align-items: center;

  @media (max-width: 1000px) {
    flex-direction: column;
    gap: 10px;
    justify-content: flex-start;
    align-items: flex-start;
  }
`;

export const PregnanciesText = styled.span`
  color: #7c3a66;
  font-family: 'Fira Sans Condensed', sans-serif;
  font-size: 25px;
  font-weight: 700;
`;

export const NewPregnancyButton = styled.button`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #b1488a;
  border: none;
  cursor: pointer;
  border-radius: 40px;
  color: white;
  gap: 8px;
  padding: 10px;
  font-weight: 700;
`;

export const ArrowsContainer = styled.section`
  display: flex;
  justify-content: flex-end;
  gap: 15px;
`;

export const CardsContainer = styled.section`
  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 15px;

  @media (max-width: 1000px) {
    flex-direction: column;
    justify-content: flex-start;
  }
`;

export const PaginationContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;

  @media (max-width: 1000px) {
    justify-content: flex-start;
  }
`;

export const EmptyBox = styled(Empty)`
  .ant-empty-description {
    color: var(--red-500) !important;
    font-weight: bold;
  }
`;
