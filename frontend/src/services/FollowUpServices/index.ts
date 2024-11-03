import { errorNotification } from '../../components/Notification';
import { api } from '../api';

export const GetFollowUpsByPregnancyId = async (id: number) => {
  try {
    const response = await api.get(`acompanhamentos/gestacao/${id}`);
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar os acompanhamentos da gestação.');
  }
};
