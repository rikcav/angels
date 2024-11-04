import { errorNotification } from '../../components/Notification';
import { api } from '../api';
import {
  EvolutionDataInterface,
  PregnantInterface
} from '../../types/interfaces/PregnantType';

export const GetPregnantByCpf = async (cpf: string) => {
  try {
    const response = await api.get(`/gestantes/cpf/${cpf}`);
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestante pelo cpf, tente novamente.');
  }
};

export const GetPregnantInfo = async (id: number) => {
  try {
    const response = await api.get(`/gestantes/${id}`);
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestante, tente novamente.');
  }
};

export const PostPregnant = async (data: PregnantInterface) => {
  try {
    const response = await api.post(`/gestantes`, data);
    return response;
  } catch (error) {
    console.log(error);
    errorNotification('Erro ao cadastrar uma gestante, tente novamente.');
  }
};

export const PutPregnant = async (id: number, data: PregnantInterface) => {
  try {
    const response = await api.put(`/gestantes/${id}`, data);
    return response;
  } catch (error) {
    errorNotification('Erro ao atualizar uma gestante, tente novamente.');
  }
};

export const DeletePregnant = async (id: number) => {
  try {
    const response = await api.delete(`/gestantes/${id}`);
    return response;
  } catch (error) {
    errorNotification('Erro ao deletar gestante, tente novamente.');
  }
};

export const GetPregnantEvolutionData = async (id: number) => {
  try {
    const response = await api.get(`/dados-evolutivos/gestante/${id}`);
    console.log(response)
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestante, tente novamente.');
  }
};

export const PostPregnantEvolutionData = async (
  data: EvolutionDataInterface
) => {
  try {
    const response = await api.post(`/dados-evolutivos`, data);
    return response;
  } catch (error) {
    errorNotification('Erro ao atualizar dados evolutivos, tente novamente.');
  }
};
