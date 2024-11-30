import { errorNotification } from '../../components/Notification';
import { UserLogin, UserRegister } from '../../types/interfaces/UserType';
import { api } from '../api';

export const userLogin = async (data: UserLogin) => {
  try {
    const response = await api.post('/api/auth/login', data);
    return response;
  } catch (error) {
    errorNotification('Usuário e/ou senha inválidos');
  }
};

export const userRegister = async (data: UserRegister) => {
  try {
    const response = await api.post('/api/auth/register', data);

    if (response.status === 201) {
      return response;
    }
  } catch (error: any) {
    if (error.response?.status === 404) {
      errorNotification('Usuário já cadastrado!');
    } else {
      errorNotification('Erro ao registrar o usuário!');
    }
  }
};
