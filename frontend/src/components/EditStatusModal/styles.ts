import styled from 'styled-components';
import { Button } from 'antd';

export const subButton = styled(Button)`
    background: var(--red-500);
    color: white;
    font-weight: 600;
    cursor: pointer;
    border-color: var(--red-500) !important;
    &:hover {
        background: var(--red-700) !important;
    }
`;

export const Container = styled.div`
  margin: 50px
`;

export const title = styled.h1`
    color: var(--red-700);
    font-size: 25px;
    font-weight: 600;
    margin-bottom: 1rem;

`
