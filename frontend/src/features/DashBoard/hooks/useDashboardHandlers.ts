import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useData } from '../../../config/Data/UseData';

export function useDashboardHandlers() {
  const { pregnanciesList } = useData();
  const navigate = useNavigate();

  const [currentPage, setCurrentPage] = useState<number>(0);
  const [page, setPage] = useState<number>(1);
  const [searchName, setSearchName] = useState<string>('');

  const handleChangeName = (e: { target: { value: string } }) => {
    setSearchName(e.target.value);
  };

  const filteredList = pregnanciesList.filter((item) =>
    item.nomeGestante?.toLowerCase().includes(searchName.toLowerCase())
  );

  const handleFollowUp = (gestacaoId: number) => {
    navigate(`/pregnancyFollowUp/${gestacaoId}`);
  };

  const handlePregnancyScreen = (gestacaoId: number, gestanteId: number) => {
    navigate(`/pregnancyInfo/${gestacaoId}/${gestanteId}`);
  };

  const previous = () => {
    if (currentPage - 3 >= 0) {
      setCurrentPage(currentPage - 3);
      setPage((prev) => prev - 1);
    }
  };

  const next = () => {
    if (currentPage + 3 < filteredList.length) {
      setCurrentPage(currentPage + 3);
      setPage((prev) => prev + 1);
    }
  };

  const handleCleanSearch = () => {
    setSearchName('');
  };

  const handleNavigatePregnancies = (value: number | undefined) => {
    navigate(`/pregnancies/${value}`);
  };

  return {
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
  };
}
