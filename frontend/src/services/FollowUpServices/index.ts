import { errorNotification } from '../../components/Notification';
import { api } from '../api';

export const GetFollowUpsByPregnancyId = async (id: number, token: String) => {
  try {
    const response = await api.get(`acompanhamentos/gestacao/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar os acompanhamentos da gestação.');
  }
};
