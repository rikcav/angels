import styled from 'styled-components';
import { Button, Input } from 'antd';

export const subButton = styled(Button)`
    background: var(--red-500);
    color: white;
    font-weight: 600;
    cursor: pointer;
    border-color: var(--red-500) !important;
    &:hover {
        color: var(--red-700) !important;
    }
`;

export const container = styled.div`
  margin: 50px
`;

export const input = styled(Input)`
    border-color: var(--red-500) !important;
    border-radius: 20px;
    font-size: 16px
`
export const title = styled.h1`
    color: var(--red-700);
    font-size: 25px;
    font-weight: 600;
    margin-bottom: 1rem;

`
export const link = styled.a`
    color: var(--red-500);
    &:hover {
        color: var(--red-700) !important;
    }
`