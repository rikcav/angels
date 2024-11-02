import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GetFollowUpsByPregnancyId } from '../../../services/FollowUpServices';
import { GetPregnantInfo } from '../../../services/PregnantServices';
import { GetPregnancyById } from '../../../services/PregnancyServices';

interface FollowUpResponseInterface {
  id: number;
  dataAcompanhamento: string;
  realizadoPor: string;
  pesoAtual: number;
  idadeGestacional: number;
  pressaoArterial: string;
  batimentosCardiacosFeto: number;
  alturaUterina: number;
  tipo: string;
}

interface PregnancyResponseInterface {
  id: number;
  gestante_id: number;
  consumoAlcool: boolean;
  frequenciaUsoAlcool: string;
  dataUltimaMenstruacao: string | null;
  dataInicioGestacao: string | null;
  fatorRh: string | null;
  fuma: boolean;
  quantidadeCigarrosDia: number;
  usoDrogas: string;
  gravidezPlanejada: boolean;
  grupoSanguineo: string;
  pesoAntesGestacao: number | null;
  riscoGestacional: number;
  vacinaHepatiteB: boolean;
  situacaoGestacional: string;
}

export function usePregnancyInfoHandlers(
  pregnantId: number,
  pregnancyId: number
) {
  const [followUpInfo, setFollowUpInfo] = useState<FollowUpResponseInterface[]>(
    []
  );
  const [pregnancyInfo, setPregnancyInfo] =
    useState<PregnancyResponseInterface>();
  const [name, setName] = useState<string>('');
  const [cpf, setCpf] = useState<string>('');
  const [toggleInfo, setToggleInfo] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const getFollowUps = async () => {
      const response = await GetFollowUpsByPregnancyId(pregnancyId);
      if (response?.status === 200) {
        setFollowUpInfo(response.data);
      }
    };

    const getPregnantInfo = async () => {
      const response = await GetPregnantInfo(pregnantId);
      if (response?.status === 200) {
        setName(response.data.nome);
        setCpf(response.data.cpf);
      }
    };

    const getPregnancyInfo = async () => {
      const response = await GetPregnancyById(pregnancyId);
      if (response?.status === 200) {
        setPregnancyInfo(response.data);
      }
    };

    getPregnantInfo();
    getFollowUps();
    getPregnancyInfo();
  }, [pregnantId, pregnancyId]);

  const toggleShowInfos = () => {
    setToggleInfo(!toggleInfo);
  };

  const handleBackArrow = () => {
    navigate(`/pregnancies/${pregnantId}`);
  };

  const handleNewPregnancy = () => {
    navigate(`/pregnancyRegister/${pregnantId}`);
  };

  return {
    followUpInfo,
    pregnancyInfo,
    name,
    cpf,
    toggleInfo,
    toggleShowInfos,
    handleBackArrow,
    handleNewPregnancy
  };
}
