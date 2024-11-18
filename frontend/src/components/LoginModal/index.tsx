import * as S from "./styles";
import React from 'react';
import { Modal } from 'antd';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Checkbox, Form, Flex } from 'antd';

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
  confirmLoading,
}) => {
    const onFinish = (values: any) => {
        console.log('Received values of form: ', values);
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
                onFinish={onFinish}
                
                >
                    <S.title>Login</S.title>
                    <Form.Item
                        name="email"
                        rules={[{ required: true, message: 'Por favor insira seu e-mail!' }]}
                    >
                        <S.input prefix={<UserOutlined color="#B1488A"/>} placeholder="E-mail" />
                    </Form.Item>
                    <Form.Item
                        name="senha"
                        rules={[{ required: true, message: 'Por favor insira sua senha!' }]}
                    >
                        <S.input prefix={<LockOutlined  color="#B1488A"/>}  type="password" placeholder="Senha" />
                    </Form.Item>
                    <Form.Item>
                        <Flex justify="space-between" align="center">
                        <Form.Item name="remember" valuePropName="checked" noStyle>
                            <Checkbox>Lembre-se de mim</Checkbox>
                        </Form.Item>
                        <S.link href="">Esqueci minha senha</S.link>
                        </Flex>
                    </Form.Item>

                    <Form.Item>
                        <S.subButton block htmlType="submit">
                        Entrar
                        </S.subButton>
                    </Form.Item>
                </Form>
            </S.container>
        </Modal>
    );
};
