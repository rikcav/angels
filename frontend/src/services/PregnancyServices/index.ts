import { errorNotification } from '../../components/Notification';
import { PregnancyRegisterInterface } from '../../types/interfaces/PregnanciesType';
import { api } from '../api';

export const GetPregnancies = async () => {
  try {
    const response = await api.get(`/gestacoes`);
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestações');
  }
};

export const GetPregnancyById = async (id: number) => {
  try {
    const response = api.get(`/gestacoes/${id}`);
    return response;
  } catch (error) {
    errorNotification('Gestação não encontrada');
  }
};

export const GetPregnanciesByPregnantId = async (id: number) => {
  try {
    const response = api.get(`/gestacoes/gestacao/${id}`);
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestações');
  }
};

export const postGestacao = async (gestacao: PregnancyRegisterInterface) => {
  try {
    const response = await api.post(`/gestacoes`, gestacao);
    return response;
  } catch (error) {
    errorNotification('Erro ao cadastrar gestação, tente novamente');
  }
};
