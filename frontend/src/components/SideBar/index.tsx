import Cookies from 'js-cookie';
import React, { useState } from 'react';
import { CpfModal } from './styles';
import { UserCircle } from '@phosphor-icons/react';

import { SidebarContainer, SidebarItem, TextItem } from './styles';
import { SignOut } from '@phosphor-icons/react';

import Logo from '../../assets/angelsLogo.svg';
import { useNavigate } from 'react-router-dom';
import { GetPregnantByCpf } from '../../services/PregnantServices';
import { InputMask } from '../InputMask';
import { ErrorInterface } from '../../types/interfaces/ErrorType';
import { isValidCpf } from '../../utils/cpfValidator';
import { successNotification, warningNotification } from '../Notification';

const SideBar: React.FC = () => {
  const navigate = useNavigate();

  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  const [cpf, setCpf] = useState<string>('');
  const [errorCpf, setErrorCpf] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const handleChangeCpf = (e: { target: { value: string } }) => {
    const { value } = e.target;

    if (isValidCpf(value)) {
      setErrorCpf({ errorType: '', errorShow: false });
    } else {
      setErrorCpf({ errorType: 'error', errorShow: true });
    }
    setCpf(value);
  };

  const getPregnantByCpf = async (cpf: string) => {
    const response = await GetPregnantByCpf(cpf);
    const id = response?.data.id;
    if (response?.status == 200) {
      navigate(`/pregnancyRegister/${id}`);
    } else {
      navigate('/pregnantRegister');
    }
  };

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    if (errorCpf.errorShow == false) {
      getPregnantByCpf(cpf);
    } else {
      warningNotification('Digite um CPF válido!');
    }
  };

  const handleCancel = () => {
    setErrorCpf({ errorType: '', errorShow: false });
    setIsModalOpen(false);
  };

  const signOut = () => {
    Cookies.remove('token');
    warningNotification('Usuário deslogado!');
    navigate('/');
  };

  // const professionalRegister = () => {
  //   navigate('/professionalRegister');
  // };

  return (
    <SidebarContainer>
      <SidebarItem>
        <img src={Logo} alt="Logo Angels" width={80} height={80} />
      </SidebarItem>

      <SidebarItem onClick={showModal}>
        <UserCircle size={40} color="#B1488A" />
        <TextItem>Nova Gestação</TextItem>
      </SidebarItem>

      {/* <SidebarItem onClick={professionalRegister}>
        <IdentificationBadge size={40} color="#B1488A" />
        <TextItem>Cadastro Profissional</TextItem>
      </SidebarItem> */}

      <SidebarItem onClick={signOut}>
        <SignOut size={40} color="#B1488A" />
        <TextItem>Sair</TextItem>
      </SidebarItem>

      <CpfModal
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
        okText="Verificar"
      >
        <label>Verifique se a gestante já está cadastrada no sistema</label>
        <InputMask
          mask={'999.999.999-99'}
          label="Cpf"
          placeHolder="Digite o cpf da gestante"
          type="text"
          inputFunction={handleChangeCpf}
          value={cpf}
          errorShow={errorCpf?.errorShow}
          status={errorCpf?.errorType}
          infoText="O cpf precisa ser válido"
          color="#b1488a"
        />
      </CpfModal>
    </SidebarContainer>
  );
};

export default SideBar;
