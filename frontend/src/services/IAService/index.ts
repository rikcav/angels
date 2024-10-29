import { errorNotification } from '../../components/Notification';
import { api } from '../api';
import { IAInterface } from './interface';

export const postIA = async (data: IAInterface) => {
  try {
    const response = await api.post(`/predict`, data);
    return response;
  } catch (error) {
    errorNotification('Erro ao recuperar informações de risco');
    console.log(error);
  }
};
