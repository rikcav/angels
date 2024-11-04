import { errorNotification } from '../../components/Notification';
import { IAInterface } from '../../types/interfaces/IAPregnancyType';
import { apiIA } from '../api';

export const postIA = async (data: IAInterface) => {
  try {
    const response = await apiIA.post(`/predict`, data);
    console.log(response)
    return response;
  } catch (error) {
    errorNotification('Erro ao recuperar informações de risco');
    console.log(error);
  }
};
