import * as S from './styles';
import React, { useState } from 'react';
import { Form } from 'antd';
import { Input } from '../../components/Input';

import Logo from '../../assets/angelsLogo.svg';
import { ArrowUUpLeft } from '@phosphor-icons/react';
import { useNavigate } from 'react-router-dom';
import { UserRegister } from '../../types/interfaces/UserType';
import { userRegister } from '../../services/UserServices';
import { successNotification } from '../../components/Notification';

export const ProfessionalRegistration: React.FC = () => {
  const navigate = useNavigate();

  const [name, setName] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const [errors, setErrors] = useState({
    name: '',
    username: '',
    password: '',
    confirmPassword: ''
  });

  const validateField = (field: string, value: string) => {
    const newErrors = { ...errors };

    switch (field) {
      case 'name':
        if (!value.trim()) {
          newErrors.name = value.trim() ? '' : 'Por favor, insira o nome!';
        } else if (value.length < 5) {
          newErrors.name = 'O nome deve ter pelo menos 5 caracteres!';
        } else {
          newErrors.name = '';
        }
        break;

      case 'username':
        if (!value.trim()) {
          newErrors.username = value.trim()
            ? ''
            : 'Por favor, insira o usuário!';
        } else if (value.length < 5) {
          newErrors.username = 'O usuário deve ter pelo menos 5 caracteres!';
        } else {
          newErrors.username = '';
        }
        break;

      case 'password':
        if (!value.trim()) {
          newErrors.password = 'Por favor, insira a senha!';
        } else if (value.length < 6) {
          newErrors.password = 'A senha deve ter pelo menos 6 caracteres!';
        } else {
          newErrors.password = '';
        }
        break;

      case 'confirmPassword':
        newErrors.confirmPassword =
          value === password ? '' : 'As senhas não coincidem!';
        break;

      default:
        break;
    }

    setErrors(newErrors);
  };

  const handleSubmit = async () => {
    const hasErrors = Object.values(errors).some((error) => error !== '');
    if (hasErrors) return;

    const data: UserRegister = {
      name,
      username,
      password
    };

    try {
      const response = await userRegister(data);
      if (response?.status === 201) {
        successNotification('Usuário cadastrado com sucesso');
        navigate('/');
      }
    } catch (error) {
      console.error('Erro ao cadastrar:', error);
    }
  };

  return (
    <S.Container>
      <S.Card>
        <S.TopContainer>
          <div className="arrow">
            <ArrowUUpLeft
              size={22}
              color="#B1488A"
              onClick={() => navigate('/')}
            />
          </div>
        </S.TopContainer>
        <S.AngelsImage>
          <img src={Logo} alt="Logo Branca angels" />
        </S.AngelsImage>
        <S.Title>Cadastro de Profissional</S.Title>
        <Form layout="vertical" onFinish={handleSubmit}>
          <Form.Item>
            <Input
              placeHolder="Insira o nome completo do funcionário"
              label="Nome"
              inputFunction={(e) => {
                setName(e.target.value);
                validateField('name', e.target.value);
              }}
              value={name}
            />
            {errors.name && <S.ErrorMessage>{errors.name}</S.ErrorMessage>}
          </Form.Item>

          <Form.Item>
            <Input
              placeHolder="Insira o usuário"
              label="Usuário*"
              inputFunction={(e) => {
                setUsername(e.target.value);
                validateField('username', e.target.value);
              }}
              value={username}
            />
            {errors.username && (
              <S.ErrorMessage>{errors.username}</S.ErrorMessage>
            )}
          </Form.Item>

          <Form.Item>
            <Input
              placeHolder="Crie uma senha"
              type="password"
              label="Senha*"
              inputFunction={(e) => {
                setPassword(e.target.value);
                validateField('password', e.target.value);
              }}
              value={password}
            />
            {errors.password && (
              <S.ErrorMessage>{errors.password}</S.ErrorMessage>
            )}
          </Form.Item>

          <Form.Item>
            <Input
              placeHolder="Repita a senha criada"
              type="password"
              label="Confirme a senha*"
              inputFunction={(e) => {
                setConfirmPassword(e.target.value);
                validateField('confirmPassword', e.target.value);
              }}
              value={confirmPassword}
            />
            {errors.confirmPassword && (
              <S.ErrorMessage>{errors.confirmPassword}</S.ErrorMessage>
            )}
          </Form.Item>

          <S.buttonDiv>
            <S.StyledButton htmlType="button" onClick={handleSubmit}>
              Cadastrar
            </S.StyledButton>
          </S.buttonDiv>
        </Form>
      </S.Card>
    </S.Container>
  );
};
