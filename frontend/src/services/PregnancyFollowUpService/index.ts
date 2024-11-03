import { errorNotification } from '../../components/Notification';
import { FollowUpInterface } from '../../types/interfaces/PregnancyFollowUpType';
import { api } from '../api';

export const postAcompanhamento = async (
  gestanteId: number,
  data: FollowUpInterface
) => {
  try {
    const response = await api.post(`/acompanhamentos/${gestanteId}`, data);
    return response;
  } catch (error) {
    errorNotification('Erro ao cadastrar acompanhamento, tente novamente');
    console.log(error);
  }
};
