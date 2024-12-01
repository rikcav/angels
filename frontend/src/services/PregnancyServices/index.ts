import { errorNotification } from '../../components/Notification';
import { PregnancyRegisterInterface } from '../../types/interfaces/PregnanciesType';
import { api } from '../api';

export const GetPregnancies = async (username: String, token: String) => {
  try {
    const response = await api.get(`/gestacoes/usuario/${username}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestações');
  }
};

export const GetPregnancyById = async (id: number, token: String) => {
  try {
    const response = api.get(`/gestacoes/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Gestação não encontrada');
  }
};

export const GetPregnanciesByPregnantId = async (id: number, token: String) => {
  try {
    const response = await api.get(`/gestacoes/gestacao/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao buscar gestações');
  }
};

export const postGestacao = async (
  gestacao: PregnancyRegisterInterface,
  token: String
) => {
  try {
    const response = await api.post(`/gestacoes`, gestacao, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response;
  } catch (error) {
    errorNotification('Erro ao cadastrar gestação, tente novamente');
  }
};

export const patchGestacao = async (
  id: number,
  situacaoGestacional: string,
  token: String
) => {
  try {
    const response = await api.patch(
      `/gestacoes/${id}`,
      {
        situacaoGestacional
      },
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
    console.log(response);
    return response;
  } catch (error) {
    errorNotification(
      'Erro ao atualizar situação gestacional, tente novamente'
    );
    console.log(error);
  }
};
