import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GetFollowUpsByPregnancyId } from '../../../services/FollowUpServices';
import { GetPregnantInfo } from '../../../services/PregnantServices';
import { GetPregnancyById } from '../../../services/PregnancyServices';
import { PregnancyInterface } from '../../../types/interfaces/PregnanciesType';
import { FollowUpInterface } from '../../../types/interfaces/PregnancyFollowUpType';
import Cookies from 'js-cookie';

export function usePregnancyInfoHandlers(
  pregnantId: number,
  pregnancyId: number
) {
  const [followUpInfo, setFollowUpInfo] = useState<FollowUpInterface[]>([]);
  const [pregnancyInfo, setPregnancyInfo] = useState<PregnancyInterface>();
  const [name, setName] = useState<string>('');
  const [cpf, setCpf] = useState<string>('');
  const [toggleInfo, setToggleInfo] = useState(false);
  const navigate = useNavigate();
  const authToken = Cookies.get('token');

  useEffect(() => {
    const getFollowUps = async () => {
      const response = await GetFollowUpsByPregnancyId(
        pregnancyId,
        authToken || ''
      );
      if (response?.status === 200) {
        setFollowUpInfo(response.data);
      }
    };

    const getPregnantInfo = async () => {
      const response = await GetPregnantInfo(pregnantId, authToken || '');
      if (response?.status === 200) {
        setName(response.data.nome);
        setCpf(response.data.cpf);
      }
    };

    const getPregnancyInfo = async () => {
      const response = await GetPregnancyById(pregnancyId, authToken || '');
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
