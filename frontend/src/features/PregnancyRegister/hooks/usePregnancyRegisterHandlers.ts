import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ZodError } from 'zod';
import {
  errorNotification,
  successNotification
} from '../../../components/Notification';
import {
  GetPregnantEvolutionData,
  GetPregnantInfo
} from '../../../services/PregnantServices';
import { postIA } from '../../../services/IAService';
import { RadioChangeEvent } from 'antd';
import {
  EvolutionDataInterface,
  PregnantInfoInterface
} from '../../../types/interfaces/PregnantType';
import { ErrorInterface } from '../../../types/interfaces/ErrorType';
import { PregnancyRegisterSchema } from '../../../types/schemas/PregnancyRegisterSchema';
import { IAInterface } from '../../../types/interfaces/IAPregnancyType';
import { PregnancyRegisterInterface } from '../../../types/interfaces/PregnanciesType';
import { postGestacao } from '../../../services/PregnancyServices';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

export function usePregnancyRegisterHandlers(gestanteId: number) {
  const navigate = useNavigate();

  const [period, setPeriod] = useState<string | string[]>();
  const [beginning, setBeginning] = useState<string | string>();
  const [weight, setWeight] = useState<string>('');
  const [situation, setSituation] = useState<string>('');
  const [risc, setRisc] = useState<string>('');
  const [blood, setBlood] = useState<string>('');
  const [rh, setRh] = useState<string>('');
  const [hepB, setHepB] = useState<boolean>();
  const [drugs, setDrugs] = useState<string>('');
  const [smoke, setSmoke] = useState<boolean>();
  const [alcohol, setAlcohol] = useState<boolean>();
  const [planned, setPlanned] = useState<boolean>();
  const [alcoholFrequency, setAlcoholFrequency] = useState<string>('0');
  const [numberCigarettes, setNumberCigarettes] = useState<string>('0');
  const [gestantInfo, setGestantInfo] = useState<PregnantInfoInterface>();
  const [evolutionData, setEvolutionData] = useState<EvolutionDataInterface>();

  const authToken = Cookies.get('token');
  const decodedToken = jwtDecode(authToken || '');

  const [errorPeriod, setErrorPeriod] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorBeginning, setErrorBeginning] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorWeight, setErrorWeight] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  useEffect(() => {
    const loadPregnancyData = async () => {
      const evolutionResponse = await GetPregnantEvolutionData(
        gestanteId,
        authToken || ''
      );
      if (evolutionResponse?.status === 200) {
        console.log(evolutionResponse.data);
        setEvolutionData(evolutionResponse.data[0]);
      }

      const infoResponse = await GetPregnantInfo(gestanteId, authToken || '');
      if (infoResponse?.status === 200) {
        setGestantInfo(infoResponse.data);
      }
    };

    loadPregnancyData();
  }, [gestanteId]);

  const handleChangeDatePeriod = (
    date: unknown,
    dateString: string | string[]
  ) => {
    try {
      PregnancyRegisterSchema.shape.dataUltimaMenstruacao.parse(dateString);
      setErrorPeriod({ errorType: '', errorShow: false });
    } catch {
      setErrorPeriod({ errorType: 'error', errorShow: true });
    }
    setPeriod(dateString);
  };

  const handleChangeDateBeginning = (date: unknown, dateString: string) => {
    try {
      PregnancyRegisterSchema.shape.dataInicioGestacao.parse(dateString);
      setErrorBeginning({ errorType: '', errorShow: false });
    } catch {
      setErrorBeginning({ errorType: 'error', errorShow: true });
    }
    setBeginning(dateString);
  };

  const handleChangeWeight = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      PregnancyRegisterSchema.shape.pesoAntesGestacao.parse(parseInt(value));
      setErrorWeight({ errorType: '', errorShow: false });
    } catch {
      setErrorWeight({ errorType: 'error', errorShow: true });
    }
    setWeight(value);
  };

  const handleBackArrow = () => navigate('/dashboard');

  const handleChangeSituation = (value: unknown) => {
    if (typeof value === 'string') setSituation(value);
  };

  const handleChangePlanned = (e: RadioChangeEvent) => {
    setPlanned(e.target.value);
  };

  const handleChangeBlood = (value: unknown) => {
    if (typeof value === 'string') setBlood(value);
  };

  const handleChangeRh = (e: RadioChangeEvent) => {
    setRh(e.target.value);
  };

  const handleChangeHepB = (e: RadioChangeEvent) => {
    setHepB(e.target.value);
  };

  const handleChangeRisc = (value: unknown) => {
    if (typeof value === 'string') setRisc(value);
  };

  const handleChangeDrugs = (value: unknown) => {
    if (typeof value === 'string') setDrugs(value);
  };

  const handleChangeSmoke = (e: RadioChangeEvent) => {
    setSmoke(e.target.value);
  };

  const handleChangeAlcohol = (e: RadioChangeEvent) => {
    setAlcohol(e.target.value);
  };

  const handleAlcoholFreq = (value: unknown) => {
    if (typeof value === 'string') setAlcoholFrequency(value);
  };

  const handleCigarettes = (e: { target: { value: string } }) => {
    setNumberCigarettes(e.target.value);
  };

  const calcAge = (birthDate: string): number =>
    new Date().getFullYear() - new Date(birthDate).getFullYear();

  const calcMonths = (lastPregnancyDate: string): number => {
    let months =
      (new Date().getFullYear() - new Date(lastPregnancyDate).getFullYear()) *
      12;
    months += new Date().getMonth() - new Date(lastPregnancyDate).getMonth();
    return new Date().getDate() < new Date(lastPregnancyDate).getDate()
      ? months - 1
      : months;
  };

  const formatDate = (): string => {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0'); // Adiciona 1 ao mês, pois começa do zero
    const day = String(now.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  };

  const formatDateOnly = (dateString: string): string => {
    return dateString.split('T')[0];
  };

  const dataIA: IAInterface = {
    previous_weight: weight,
    gestational_risk: risc,
    schooling: evolutionData?.escolaridade?.toString(),
    has_hypertension: evolutionData?.hipertensao ? '1' : '0',
    has_diabetes: evolutionData?.diabetes ? '1' : '0',
    has_pelvic_surgery: evolutionData?.cirurgiaPelvica ? '1' : '0',
    has_urinary_infection: evolutionData?.infeccaoUrinaria ? '1' : '0',
    has_congenital_malformation: evolutionData?.maFormacaoCongenita ? '1' : '0',
    has_family_twinship: evolutionData?.familiarGemeos ? '1' : '0',
    amount_gestation: evolutionData?.quantidadeGestacao?.toString() || '0',
    amount_abortion: evolutionData?.quantidadeAbortos?.toString() || '0',
    amount_deliveries: evolutionData?.quantidadePartos?.toString() || '0',
    amount_cesarean: evolutionData?.quantidadePartosCesarios?.toString() || '0',
    mothers_birth_date:
      formatDateOnly(gestantInfo?.dataNascimento || '2024-01-01') ||
      '2024-01-01',
    date_first_prenatal: formatDate(),
    date_last_delivery:
      formatDateOnly(evolutionData?.dataUltimaGestacao || '2024-01-01') ||
      '2024-01-01',
    date_start_pregnancy: beginning || '2024-01-01'
  };

  const data: PregnancyRegisterInterface = {
    gestante_id: gestanteId,
    username: decodedToken ? decodedToken.sub : '',
    dataUltimaMenstruacao: period,
    dataInicioGestacao: beginning,
    pesoAntesGestacao: parseInt(weight),
    situacaoGestacional: situation,
    riscoGestacional: parseInt(risc),
    riscoIA: false,
    grupoSanguineo: blood,
    fatorRh: rh,
    vacinaHepatiteB: hepB,
    usoDrogas: drugs,
    fuma: smoke,
    gravidezPlanejada: planned,
    consumoAlcool: alcohol,
    frequenciaUsoAlcool: alcoholFrequency,
    quantidadeCigarrosDia: parseInt(numberCigarettes)
  };

  const handleCheckIA = async () => {
    const response = await postIA(dataIA);
    if (response?.status === 200) {
      data.riscoIA = response.data.risk;
      successNotification('Risco calculado');
    }
  };

  const handlePregnancyRegister = async () => {
    try {
      PregnancyRegisterSchema.parse(data);
      await handleCheckIA();
      await postGestacao(data, authToken || '');
      navigate(`/pregnancies/${gestanteId}`);
    } catch (error) {
      if (error instanceof ZodError) {
        errorNotification(error.issues[0].message);
      }
    }
  };

  return {
    period,
    beginning,
    weight,
    situation,
    risc,
    blood,
    rh,
    hepB,
    drugs,
    smoke,
    alcohol,
    planned,
    alcoholFrequency,
    numberCigarettes,
    gestantInfo,
    evolutionData,
    errorPeriod,
    errorBeginning,
    errorWeight,
    handleChangeDatePeriod,
    handleChangeDateBeginning,
    handleChangeWeight,
    handleChangeSituation,
    handleChangePlanned,
    handleChangeBlood,
    handleChangeRh,
    handleChangeHepB,
    handleChangeRisc,
    handleChangeDrugs,
    handleChangeSmoke,
    handleChangeAlcohol,
    handleAlcoholFreq,
    handleCigarettes,
    handleBackArrow,
    handleCheckIA,
    handlePregnancyRegister,
    calcAge,
    calcMonths
  };
}
