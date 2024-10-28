import { errorNotification } from '../../components/Notification';
import { apiIA } from '../api';
import { IAInterface } from './interface';

export const postIA = async (data: IAInterface) => {
  try {
    const response = await apiIA.post(`/predict`, data);
    return response;
  } catch (error) {
    errorNotification('Erro ao recuperar informações de risco');
    console.log(error);
  }
};
