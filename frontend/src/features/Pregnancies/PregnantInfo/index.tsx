import { PencilSimple, TrashSimple } from '@phosphor-icons/react';
import * as S from './styles';
import { InfoContainer } from '../../../components/InfoContainer';
import moment from 'moment';
import { Input } from '../../../components/Input';
import { DateSelect } from '../../../components/DateSelect';
import { Select } from '../../../components/Select';
import { useNavigate } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import {
  PregantResponseInterface,
  PregnantInfoInterface
} from '../../../types/interfaces/PregnantType';
import {
  DeletePregnant,
  GetPregnantInfo,
  PutPregnant
} from '../../../services/PregnantServices';
import { successNotification } from '../../../components/Notification';
import { genderList, raceList } from '../../PregnantRegister/SelectOptions';
import { ErrorInterface } from '../../../types/interfaces/ErrorType';
import { pregnantSchemaPartOne } from '../../../types/schemas/PregnantRegisterSchema';
import Cookies from 'js-cookie';

interface PregnantInfoProps {
  id: number;
}

export const PregnantInfo: React.FC<PregnantInfoProps> = ({ id }) => {
  const navigate = useNavigate();
  const [pregnantInfo, setPregnantInfo] = useState<PregantResponseInterface>();
  const [isUptadeModalOpen, setIsUptadeModalOpen] = useState(false);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
  const [name, setName] = useState<string>();
  const [birthDate, setBirthDate] = useState<string | undefined>();
  const [race, setRace] = useState<string>('');
  const [gender, setGender] = useState<string>('');
  const [cpf, setCpf] = useState<string>('');
  const [reload, setReload] = useState<number>(0);
  const authToken = Cookies.get('token');

  const [errorName, setErrorName] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorBirthDate, setErrorBirthDate] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorCpf, setErrorCpf] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const handleChangeName = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartOne.shape.nome.parse(value);
      setErrorName({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorName({ errorType: 'error', errorShow: true });
    }
    setName(value);
  };

  const handleChangeBirthDate = (
    date: unknown,
    dateString: string | string[]
  ) => {
    let dateStr: string | undefined;

    if (Array.isArray(dateString)) {
      dateStr = dateString.length > 0 ? dateString[0] : undefined;
    } else {
      dateStr = dateString;
    }

    try {
      pregnantSchemaPartOne.shape.dataNascimento.parse(dateStr);
      setErrorBirthDate({ errorType: '', errorShow: false });
      if (!dateStr) {
        setErrorBirthDate({ errorType: 'error', errorShow: true });
      }
    } catch (error) {
      setErrorBirthDate({ errorType: 'error', errorShow: true });
    }
    setBirthDate(dateStr);
  };

  const handleChangeRace = (value: unknown) => {
    if (typeof value === 'string') {
      setRace(value);
    }
  };

  const handleChangeGender = (value: unknown) => {
    if (typeof value === 'string') {
      setGender(value);
    }
  };

  const handleChangeCpf = (e: { target: { value: string } }) => {
    const { value } = e.target;
    const inputValue = value.replace(/\D/g, '');
    try {
      pregnantSchemaPartOne.shape.cpf.parse(inputValue);
      setErrorCpf({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorCpf({ errorType: 'error', errorShow: true });
    }
    setCpf(inputValue);
  };

  const reloadPag = () => {
    setReload((prev) => prev + 1);
  };

  const handleUptadeOk = () => {
    putPregnant();
  };

  const showDeleteModal = () => {
    setIsDeleteModalOpen(true);
  };

  const handleDeleteOk = () => {
    deletePregnant();
  };

  const handleCancel = () => {
    setIsUptadeModalOpen(false);
    setIsDeleteModalOpen(false);
  };

  useEffect(() => {
    const getPregnantInfo = async () => {
      const response = await GetPregnantInfo(id, authToken || '');
      if (response?.status == 200) {
        setPregnantInfo(response.data);
        setName(response.data.nome);
        setBirthDate(response.data.dataNascimento);
        setCpf(response.data.cpf);
        setGender(response.data.sexo);
      }
    };

    getPregnantInfo();
  }, [reload]);

  const dashBoardScreen = () => {
    navigate('/dashboard');
  };

  const pregnantRegisterScreen = () => {
    navigate(`/pregnantRegister/${id}`);
  };

  const updateData: PregnantInfoInterface = {
    nome: name,
    dataNascimento: birthDate,
    cpf: cpf,
    raca: race,
    sexo: gender
  };

  const putPregnant = async () => {
    const response = await PutPregnant(id, updateData, authToken || '');
    if (response?.status == 200) {
      successNotification('Gestante atualizada com sucesso!');
      reloadPag();
      setIsUptadeModalOpen(false);
    }
  };

  const deletePregnant = async () => {
    const response = await DeletePregnant(id, authToken || '');
    if (response?.status == 200) {
      successNotification('Gestante deletada com sucesso!');
      dashBoardScreen();
    }
  };
  return (
    <S.Container>
      <S.ContentContainer>
        <S.ContentContainer>
          <S.LineContainer>
            <InfoContainer
              name="Nascimento"
              value={moment(pregnantInfo?.dataNascimento).format('DD/MM/YYYY')}
            />
            <S.InfoEmptyContainer />
            <S.InfoEmptyContainer />
            <S.InfoEmptyContainer />
            <InfoContainer
              name="Gênero"
              value={
                pregnantInfo?.sexo === 'MASCULINO'
                  ? 'Masculino'
                  : pregnantInfo?.sexo === 'FEMININO'
                  ? 'Feminino'
                  : pregnantInfo?.sexo === 'NAO_BINARIO'
                  ? 'Não-Binário'
                  : pregnantInfo?.sexo === 'AGENERO'
                  ? 'Agênero'
                  : pregnantInfo?.sexo === 'OUTRO'
                  ? 'Outro'
                  : 'Indefinido'
              }
            />
            <S.InfoEmptyContainer />
          </S.LineContainer>
          <S.LineContainer>
            <InfoContainer name="Município" value={pregnantInfo?.municipio} />
            <InfoContainer
              name="Risco"
              value={pregnantInfo?.emRisco == true ? 'Sim' : 'Não'}
            />
            <InfoContainer
              name="Hipertesão"
              value={pregnantInfo?.hipertensao == true ? 'Sim' : 'Não'}
            />
            <InfoContainer
              name="Diabete"
              value={pregnantInfo?.diabetes == true ? 'Sim' : 'Não'}
            />
            <InfoContainer
              name="Má formação congênita"
              value={pregnantInfo?.maFormacaoCongenita == true ? 'Sim' : 'Não'}
            />
            <S.InfoEmptyContainer />
          </S.LineContainer>
          <S.LineContainer>
            <InfoContainer
              name="Abortos"
              value={pregnantInfo?.quantidadeAbortos?.toString()}
            />
            <InfoContainer
              name="Filhos Vivos"
              value={pregnantInfo?.quantidadeFilhosVivos?.toString()}
            />
            <InfoContainer
              name="Gemelares"
              value={pregnantInfo?.quantidadeGemelares?.toString()}
            />
            <InfoContainer
              name="Gestações"
              value={pregnantInfo?.quantidadeGestacao?.toString()}
            />
            <InfoContainer
              name="Nascidos vivos"
              value={pregnantInfo?.quantidadeNascidosVivos?.toString()}
            />
            <InfoContainer
              name="Nascidos mortos"
              value={pregnantInfo?.quantidadeNascidosMortos?.toString()}
            />
          </S.LineContainer>
          <S.TitleContainer>
            <section>
              <PencilSimple
                size={28}
                weight="bold"
                color="#7c3a66"
                onClick={pregnantRegisterScreen}
              />
              <TrashSimple
                size={28}
                weight="bold"
                color="#7c3a66"
                onClick={showDeleteModal}
              />
            </section>
          </S.TitleContainer>
        </S.ContentContainer>
        <S.InfoModal
          open={isUptadeModalOpen}
          onOk={handleUptadeOk}
          onCancel={handleCancel}
          title="Atualizar dados da gestante"
        >
          <Input
            label={'Nome:'}
            placeHolder="Digite o nome..."
            type="text"
            inputFunction={handleChangeName}
            value={name}
            errorShow={errorName?.errorShow}
            status={errorName?.errorType}
            infoText="O nome precisa ter entre 2 e 80 letras"
            color="#b1488a"
          />
          <DateSelect
            label="Nascimento:"
            placeHolder="Selecione uma data"
            inputFunction={handleChangeBirthDate}
            status={errorBirthDate.errorType}
          />
          <Input
            label={'Cpf:'}
            placeHolder="xxx.xxx.xxx-xx"
            type="text"
            inputFunction={handleChangeCpf}
            value={cpf}
            errorShow={errorCpf?.errorShow}
            status={errorCpf?.errorType}
            infoText="O cpf precisa ser válido"
            color="#b1488a"
          />
          <Select
            label="Raça:"
            defaut="Selecione uma opcão"
            list={raceList}
            selectFunc={handleChangeRace}
            value={race}
          />
          <Select
            label="Sexo:"
            defaut="Selecione uma opcão"
            list={genderList}
            selectFunc={handleChangeGender}
            value={gender}
          />
        </S.InfoModal>
        <S.InfoModal
          open={isDeleteModalOpen}
          onOk={handleDeleteOk}
          onCancel={handleCancel}
          title="Deseja deletar a gestante?"
          okText="Deletar"
        ></S.InfoModal>
      </S.ContentContainer>
    </S.Container>
  );
};
