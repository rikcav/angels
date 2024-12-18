import styled from 'styled-components';
import InputMask from 'react-input-mask';

export const Container = styled.div`
  padding: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  gap: 12px;
  width: 100%;
`;

export const InputArea = styled(InputMask)`
  width: 100%;
  border-radius: 1rem !important;
  border: 2px var(--gray-100) solid;
  padding: 0.45rem;

  &::placeholder {
    color: var(--gray-100);
  }

  &:focus {
    border-color: var(--red-500);
    outline: none;
  }

  &:hover {
    border-color: var(--red-500);
  }
`;

export const Label = styled.label`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-self: start;
  font-weight: 600;
  color: var(--red-500);
`;
