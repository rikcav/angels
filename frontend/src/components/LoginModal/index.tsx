import * as S from './styles';
import React, { useState } from 'react';
import { Modal } from 'antd';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Form } from 'antd';
import { userLogin } from '../../services/UserServices';
import { UserLogin } from '../../types/interfaces/UserType';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import { successNotification } from '../Notification';

interface LoginProps {
  open: boolean;
  handleCancel: () => void;
  handleOk: () => void;
  confirmLoading: boolean;
}

export const LoginModal: React.FC<LoginProps> = ({
  open,
  handleCancel,
  handleOk,
  confirmLoading
}) => {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const data: UserLogin = {
    username: username,
    password: password
  };

  const login = async () => {
    const response = await userLogin(data);
    if (response?.status === 200) {
      const token = response.data;
      Cookies.set('token', token, { expires: 7, secure: true });
      successNotification('Login realizado com sucesso');
      navigate('/dashboard');
    }
  };

  const handleRegister = async () => {
    navigate('/professionalRegister');
  };

  return (
    <Modal
      open={open}
      onOk={handleOk}
      confirmLoading={confirmLoading}
      onCancel={handleCancel}
      footer={null}
    >
      <S.container>
        <Form
          name="login"
          initialValues={{ remember: true }}
          style={{ maxWidth: 360 }}
        >
          <S.title>Login</S.title>
          <Form.Item
            name="usuario"
            rules={[
              { required: true, message: 'Por favor insira seu usuário!' }
            ]}
          >
            <S.input
              prefix={<UserOutlined color="#B1488A" />}
              placeholder="Usuário"
              onChange={handleUsernameChange}
            />
          </Form.Item>
          <Form.Item
            name="senha"
            rules={[{ required: true, message: 'Por favor insira sua senha!' }]}
          >
            <S.input
              prefix={<LockOutlined color="#B1488A" />}
              type="password"
              placeholder="Senha"
              onChange={handlePasswordChange}
            />
          </Form.Item>

          <Form.Item>
            <S.subButton block htmlType="submit" onClick={login}>
              Entrar
            </S.subButton>
          </Form.Item>
          <S.link onClick={handleRegister}>Cadastre-se</S.link>
        </Form>
      </S.container>
    </Modal>
  );
};
