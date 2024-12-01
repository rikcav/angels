import { errorNotification } from '../../components/Notification';
import { api } from '../api';
import {
  EvolutionDataInterface,
  PregnantInterface
} from '../../types/interfaces/PregnantType';

export const GetPregnantByCpf = async (cpf: string, token: string) => {
  try {
    const response = await api.get(`/gestantes/cpf/${cpf}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestante pelo cpf, tente novamente.');
  }
};

export const GetPregnantInfo = async (id: number, token: String) => {
  try {
    const response = await api.get(`/gestantes/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestante, tente novamente.');
  }
};

export const PostPregnant = async (data: PregnantInterface, token: String) => {
  try {
    const response = await api.post(`/gestantes`, data, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao cadastrar uma gestante, tente novamente.');
  }
};

export const PutPregnant = async (
  id: number,
  data: PregnantInterface,
  token: string
) => {
  try {
    const response = await api.put(`/gestantes/${id}`, data, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao atualizar uma gestante, tente novamente.');
  }
};

export const DeletePregnant = async (id: number, token: string) => {
  try {
    const response = await api.delete(`/gestantes/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao deletar gestante, tente novamente.');
  }
};

export const GetPregnantEvolutionData = async (id: number, token: String) => {
  try {
    const response = await api.get(`/dados-evolutivos/gestante/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestante, tente novamente.');
  }
};

export const PostPregnantEvolutionData = async (
  data: EvolutionDataInterface,
  token: String
) => {
  try {
    const response = await api.post(`/dados-evolutivos`, data, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao atualizar dados evolutivos, tente novamente.');
  }
};
