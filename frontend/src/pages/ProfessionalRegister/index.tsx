import * as S from './styles';
import React from 'react';
import { Form } from 'antd';
import { Input } from '../../components/Input';

import Logo from '../../assets/angelsLogo.svg';
import { ArrowUUpLeft } from '@phosphor-icons/react';
import { useNavigate } from 'react-router-dom';

export const ProfessionalRegistration: React.FC = () => {
    const onFinish = (values: any) => {
        console.log('Form values: ', values);
    };
    const navigate = useNavigate();

    const dashboardScreen = () => {
        navigate('/dashboard');
    };

    return (
        <S.Container>
            <S.Card>
                <S.TopContainer>
                    <div className="arrow">
                        <ArrowUUpLeft size={22} color="#B1488A" onClick={dashboardScreen} />
                    </div>
                </S.TopContainer>
                <S.AngelsImage>
                    <img src={Logo} alt="Logo Branca angels" />
                </S.AngelsImage>
                <S.Title>Cadastro de Profissional</S.Title>
                <Form layout="vertical" onFinish={onFinish}>
                    <Form.Item
                        name="nome"
                        rules={[{ required: true, message: 'Por favor, insira o nome!' }]}
                    >
                        <Input
                            placeHolder="Insira o nome completo do funcionário"
                            label="Nome*"
                        />
                    </Form.Item>
                    <Form.Item
                        name="CPF"
                        rules={[{ required: true, message: 'Por favor, insira o CPF!' }]}
                    >
                        <Input placeHolder="Insira o CPF do funcionário" label="CPF*" />
                    </Form.Item>
                    <Form.Item
                        name="contato"
                        rules={[
                            { required: true, message: 'Por favor, insira o contato!' }
                        ]}
                    >
                        <Input
                            placeHolder="Insira o contato do funcionário"
                            label="Contato*"
                        />
                    </Form.Item>
                    <Form.Item
                        name="hospital"
                        rules={[
                            {
                                required: true,
                                message: 'Por favor, insira o hospital/clínica!'
                            }
                        ]}
                    >
                        <Input
                            placeHolder="Insira a clínica ou hospital que o funcionário trabalha"
                            label="Hospital/Clínica*"
                        />
                    </Form.Item>
                    <Form.Item
                        name="email"
                        rules={[
                            { required: true, message: 'Por favor, insira o e-mail!' },
                            { type: 'email', message: 'Por favor, insira um e-mail válido!' }
                        ]}
                    >
                        <Input
                            placeHolder="Insira o e-mail que deseja cadastrar o funcionário"
                            label="E-mail*"
                        />
                    </Form.Item>
                    <Form.Item
                        name="senha"
                        rules={[{ required: true, message: 'Por favor, insira a senha!' }]}
                    >
                        <Input
                            placeHolder="Crie uma senha"
                            type="password"
                            label="Senha*"
                        />
                    </Form.Item>
                    <Form.Item
                        name="confirmeSenha"
                        dependencies={['senha']}
                        rules={[
                            { required: true, message: 'Por favor, confirme a senha!' },
                            ({ getFieldValue }) => ({
                                validator(_, value) {
                                    if (!value || getFieldValue('senha') === value) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('As senhas não coincidem!'));
                                }
                            })
                        ]}
                    >
                        <Input
                            placeHolder="Repita a senha criada"
                            type="password"
                            label="Confirme a senha*"
                        />
                    </Form.Item>
                    <S.buttonDiv>
                        <S.StyledButton htmlType="submit">Cadastrar</S.StyledButton>
                    </S.buttonDiv>
                </Form>
            </S.Card>
        </S.Container>
    );
};
