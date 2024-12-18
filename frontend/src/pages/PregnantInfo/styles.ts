import { Modal } from 'antd';
import styled from 'styled-components';

export const Title = styled.h1`
  color: #7c3a66;
  font-family: 'Fira Sans Condensed', sans-serif;
  font-size: 25px;
  font-weight: 700;
`;

export const InfoEmptyContainer = styled.div`
  width: 14.4rem;

  @media (max-width: 850px) {
    width: 0;
  }
`;

export const Container = styled.div`
  padding-top: 50px;
  padding-left: 110px;
  padding-right: 110px;

  @media (max-width: 550px) {
    padding-left: 50px;
    padding-right: 50px;
  }
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
`;

export const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 25px;
`;

export const ArrowButton = styled.div`
  display: flex;
  background-color: #7c3a66;
  color: white;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  font-size: larger;
  font-weight: 700;
  width: 50px;
  height: 50px;
  border-radius: 25px;
  margin-top: 20px;
`;

export const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  svg {
    cursor: pointer;
  }

  section {
    display: flex;
    gap: 8px;
  }
`;

export const InfoModal = styled(Modal)`
  .ant-btn-primary {
    background-color: var(--red-500) !important;
    border-radius: 20px;
  }

  .ant-btn-default {
    border-color: var(--red-500) !important;
    border-radius: 20px;
  }
`;
