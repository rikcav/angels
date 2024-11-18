import styled from 'styled-components';
import { Button } from 'antd';

export const Container = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
`;

export const Card = styled.div`
  width: 80%;
  height: 80%;
  background: rgba(177, 72, 138, 0.2);
  align-self: center;
  justify-self: center;
  border-radius: 26px;
  padding: 30px;
  overflow-y: scroll;
  @media (min-width: 426px) and (max-width: 768px) {
    overflow-y: scroll;
  }
  @media (min-width: 1024px) {
    overflow-y: hidden;
    height: auto;
  }
`;

export const Title = styled.h2`
  text-align: center;
  color: #9D0B67;
  font-weight: 600;
  font-size:30px;
  margin: 20px;
`;

export const AngelsImage = styled.div`
    display: flex;
    justify-content:center;
    img {
        margin-top: 1rem;
        width: 10%;
    }
`;

export const buttonDiv = styled.div`
    display: flex;
    justify-content:center;
`;


export const StyledButton = styled(Button)`
  background-color: var(--red-500);
  color: white;
  border: none;
  width: 30%;
  height: 40px;
  margin-top: 20px;
  border-radius: 20px;

  &:hover {
    background-color: var(--red-700) !important;
    color: white !important;
  }
`;

export const TopContainer = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 1rem;

  .arrow {
    display: flex;
    flex-direction: row;
    align-self: start;
    align-items: center;
    justify-content: space-between;
    width: 52%;
  }

  div {
    svg {
      cursor: pointer;
    }
  }
`;