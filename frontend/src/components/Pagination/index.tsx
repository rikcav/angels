import * as S from './styles';
import {
  CaretCircleLeft,
  CaretCircleRight,
  DotsThreeOutline
} from '@phosphor-icons/react';

interface PaginationProps {
  itemsPerPage: number;
  currentPage: number;
  totalItems: number;
  onPrevious: () => void;
  onNext: () => void;
}

export const Pagination: React.FC<PaginationProps> = ({
  currentPage,
  totalItems,
  itemsPerPage,
  onPrevious,
  onNext
}) => {
  const totalPages = Math.ceil(totalItems / itemsPerPage);

  return (
    <S.Pagination>
      <CaretCircleLeft size={32} color="#b1488a" onClick={onPrevious} />
      <label>{currentPage}</label>
      <DotsThreeOutline size={32} color="#b1488a" />
      <label>{totalPages}</label>
      <CaretCircleRight size={32} color="#b1488a" onClick={onNext} />
    </S.Pagination>
  );
};
