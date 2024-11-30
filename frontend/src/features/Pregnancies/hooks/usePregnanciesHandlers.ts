import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GetPregnantInfo } from '../../../services/PregnantServices';
import { GetPregnanciesByPregnantId } from '../../../services/PregnancyServices';
import { PregnancyInterface } from '../../../types/interfaces/PregnanciesType';
import { patchGestacao } from '../../../services/PregnancyServices';

export function usePregnanciesHandlers(pregnantId: number) {
  const navigate = useNavigate();

  const [currentPage, setCurrentPage] = useState<number>(0);
  const [page, setPage] = useState<number>(1);
  const [name, setName] = useState<string>('');
  const [cpf, setCpf] = useState<string>('');
  const [pregnanciesData, setPregnanciesData] = useState<PregnancyInterface[]>(
    []
  );
  const [toggleInfo, setToggleInfo] = useState(false);

  useEffect(() => {
    const pregnanciesRequest = async () => {
      const requestResponse = await GetPregnanciesByPregnantId(pregnantId);
      if (requestResponse?.status === 200) {
        setPregnanciesData(requestResponse.data);
      }
    };

    const getPregnantInfo = async () => {
      const response = await GetPregnantInfo(pregnantId);
      if (response?.status === 200) {
        setName(response.data.nome);
        setCpf(response.data.cpf);
      }
    };

    pregnanciesRequest();
    if (pregnantId) {
      getPregnantInfo();
    }
  }, [pregnantId]);

  const previous = () => {
    if (currentPage - 4 >= 0) {
      setCurrentPage(currentPage - 4);
      setPage((prev) => prev - 1);
    }
  };

  const next = () => {
    if (currentPage + 4 < pregnanciesData.length) {
      setCurrentPage(currentPage + 4);
      setPage((prev) => prev + 1);
    }
  };

  const handleNewPregnancy = () => {
    navigate(`/pregnancyRegister/${pregnantId}`);
  };

  const handleFollowUp = (gestacaoId: number) => {
    navigate(`/pregnancyFollowUp/${gestacaoId}`);
  };

  const handlePregnancyScreen = (gestacaoId: number, gestanteId: number) => {
    navigate(`/pregnancyInfo/${gestacaoId}/${gestanteId}`);
  };

  const handleBackArrow = () => {
    navigate('/dashboard');
  };

  const toggleExpandInfo = () => {
    setToggleInfo((prev) => !prev);
  };

  const handlePatchGestacao = async (gestacaoId: number, situacaoGestacional: string) => {
      const response = await patchGestacao(gestacaoId, situacaoGestacional);
      if (response?.status === 200) {
        setPregnanciesData((prev) =>
          prev.map((gestacao) =>
            gestacao.id === gestacaoId
              ? { ...gestacao, situacaoGestacional }
              : gestacao
          )
        );
      }
  };
  return {
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
    toggleExpandInfo,
    handlePatchGestacao,
  };
}
