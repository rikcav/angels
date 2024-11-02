import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { PregnancyRegisterSchema } from '../../../services/types/PregnancyRegisterType';
import { ZodError } from 'zod';
import {
  errorNotification,
  successNotification
} from '../../../components/Notification';
import { postGestacao } from '../../../services/PregnancyRegisterService';
import {
  GetPregnantEvolutionData,
  GetPregnantInfo
} from '../../../services/PregnantServices';
import { postIA } from '../../../services/IAService';
import { RadioChangeEvent } from 'antd';
import { PregnancyRegisterInterface } from '../../../services/PregnancyRegisterService/interface';
import {
  EvolutionDataInterface,
  PregnantInfoInterface
} from '../../../services/types/PregnantType';
import { IAInterface } from '../../../services/IAService/interface';

interface ErrorInterface {
  errorShow?: boolean;
  errorType?: '' | 'warning' | 'error' | undefined;
}

export function usePregnancyRegisterHandlers(gestanteId: number) {
  const navigate = useNavigate();

  const [period, setPeriod] = useState<string | string[]>();
  const [beginning, setBeginning] = useState<string | string[]>();
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
      const evolutionResponse = await GetPregnantEvolutionData(gestanteId);
      if (evolutionResponse?.status === 200) {
        setEvolutionData(evolutionResponse.data[0]);
      }

      const infoResponse = await GetPregnantInfo(gestanteId);
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

  const handleChangeDateBeginning = (
    date: unknown,
    dateString: string | string[]
  ) => {
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

  const dataIA: IAInterface = {
    previous_weight: parseInt(weight),
    gestational_risk: parseInt(situation),
    schooling: evolutionData?.escolaridade,
    has_hypertension: evolutionData?.hipertensao || false,
    has_diabetes: evolutionData?.diabetes || false,
    has_pelvic_sugery: evolutionData?.cirurgiaPelvica || false,
    has_urinary_infection: evolutionData?.infeccaoUrinaria || false,
    has_congenital_malformation: evolutionData?.maFormacaoCongenita || false,
    has_family_twinship: evolutionData?.familiarGemeos || false,
    amount_gestation: evolutionData?.quantidadeGestacao || 0,
    amount_abortion: evolutionData?.quantidadeAbortos || 0,
    amount_deliveries: evolutionData?.quantidadePartos || 0,
    amount_cesarean: evolutionData?.quantidadePartosCesarios || 0,
    target: 0,
    age: calcAge(gestantInfo?.dataNascimento || '2024-01-01'),
    fist_prenatal: 0,
    time_between_pregnancies: calcMonths(
      evolutionData?.dataUltimaGestacao || '2024-01-01'
    )
  };

  const data: PregnancyRegisterInterface = {
    gestante_id: gestanteId,
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
      successNotification('Deu certo IA');
    }
  };

  const handlePregnancyRegister = async () => {
    try {
      PregnancyRegisterSchema.parse(data);
      await postGestacao(data);
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
