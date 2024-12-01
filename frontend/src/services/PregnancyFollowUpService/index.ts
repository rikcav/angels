import { errorNotification } from '../../components/Notification';
import { FollowUpInterface } from '../../types/interfaces/PregnancyFollowUpType';
import { api } from '../api';

export const postAcompanhamento = async (
  gestanteId: number,
  data: FollowUpInterface,
  token: String
) => {
  try {
    const response = await api.post(
      `/acompanhamentos/gestacoes/${gestanteId}`,
      data,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
    return response;
  } catch (error) {
    errorNotification('Erro ao cadastrar acompanhamento, tente novamente');
    console.log(error);
  }
};
