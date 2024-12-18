import { useEffect, useState } from 'react';
import * as S from './styles';
import {
  DeletePregnant,
  GetPregnantInfo,
  PutPregnant
} from '../../services/PregnantServices';
import {
  ArrowCircleLeft,
  PencilSimple,
  TrashSimple
} from '@phosphor-icons/react';
import { useNavigate } from 'react-router-dom';
import { Input } from '../../components/Input';
import { DateSelect } from '../../components/DateSelect';
import { Select } from '../../components/Select';
import { PregnantInfoInterface } from '../../types/interfaces/PregnantType';
import {
  genderList,
  raceList
} from '../../features/PregnantRegister/SelectOptions';
import { successNotification } from '../../components/Notification';
import { PregnantDetails } from '../../components/PregnantDetails';
import { ErrorInterface } from '../../types/interfaces/ErrorType';
import Cookies from 'js-cookie';
import { pregnantSchemaPartOne } from '../../types/schemas/PregnantRegisterSchema';
interface PregantReposnseInterface {
  id?: number;
  nome?: string;
  dataNascimento?: string;
  cpf?: string;
  sexo?: string;
  municipio?: string;
  emRisco?: boolean;
  quantidadeAbortos?: number;
  quantidadeFilhosVivos?: number;
  quantidadeGemelares?: number;
  quantidadeGestacao?: number;
  quantidadeNascidosMortos?: number;
  quantidadeNascidosVivos?: number;
  hipertensao?: boolean;
  diabetes?: boolean;
  maFormacaoCongenita?: boolean;
}

export default function PregnantInfo() {
  const navigate = useNavigate();
  const [pregnantInfo, setPregnantInfo] = useState<PregantReposnseInterface>();
  const [isUptadeModalOpen, setIsUptadeModalOpen] = useState(false);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
  const [name, setName] = useState<string>();
  const [birthDate, setBirthDate] = useState<string>();
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

  const handleChangeBirthDate = (date: unknown, dateString: string) => {
    try {
      pregnantSchemaPartOne.shape.dataNascimento.parse(dateString);
      setErrorBirthDate({ errorType: '', errorShow: false });
      if (dateString == '') {
        setErrorBirthDate({ errorType: 'error', errorShow: true });
      }
    } catch (error) {
      console.log(error);
      setErrorBirthDate({ errorType: 'error', errorShow: true });
    }
    console.log(date);
    setBirthDate(dateString);
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

  const showUptadeModal = () => {
    setIsUptadeModalOpen(true);
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
      const response = await GetPregnantInfo(1, authToken || '');
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

  const updateData: PregnantInfoInterface = {
    nome: name,
    dataNascimento: birthDate,
    cpf: cpf,
    raca: race,
    sexo: gender
  };

  const putPregnant = async () => {
    const response = await PutPregnant(1, updateData, authToken || '');
    if (response?.status == 200) {
      successNotification('Gestante atualizada com sucesso!');
      reloadPag();
      setIsUptadeModalOpen(false);
    }
  };

  const deletePregnant = async () => {
    const response = await DeletePregnant(1, authToken || '');
    if (response?.status == 200) {
      successNotification('Gestante deletada com sucesso!');
      dashBoardScreen();
    }
  };

  return (
    <S.Container>
      <S.ContentContainer>
        <S.TitleContainer>
          <ArrowCircleLeft
            size={32}
            weight="bold"
            color="#7c3a66"
            onClick={dashBoardScreen}
          />
          <S.Title>Informações da gestante</S.Title>
          <section>
            <PencilSimple
              size={32}
              weight="bold"
              color="#7c3a66"
              onClick={showUptadeModal}
            />
            <TrashSimple
              size={32}
              weight="bold"
              color="#7c3a66"
              onClick={showDeleteModal}
            />
          </section>
        </S.TitleContainer>
        <PregnantDetails pregnantInfo={pregnantInfo} />
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
        {/* <S.ButtonContainer>
          {page == 1 ? (
            <S.ArrowButton onClick={() => setPage(0)}>
              <ArrowLeft size={28} weight="bold" />
            </S.ArrowButton>
          ) : (
            <S.ArrowButton onClick={() => setPage(1)}>
              <ArrowRight size={28} weight="bold" />
            </S.ArrowButton>
          )}
        </S.ButtonContainer> */}
      </S.ContentContainer>
    </S.Container>
  );
}
