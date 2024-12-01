import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ZodError } from 'zod';
import { postAcompanhamento } from '../../../services/PregnancyFollowUpService';
import { GetPregnancyById } from '../../../services/PregnancyServices';
import { message, RadioChangeEvent } from 'antd';
import { ErrorInterface } from '../../../types/interfaces/ErrorType';
import { PregnancyFollowUpSchema } from '../../../types/schemas/FollowUpRegisterSchema';
import { FollowUpInterface } from '../../../types/interfaces/PregnancyFollowUpType';
import Cookies from 'js-cookie';
import { successNotification } from '../../../components/Notification';

export function usePregnancyFollowUpHandlers(gestacaoId: number) {
  const navigate = useNavigate();

  const [gestanteId, setGestanteId] = useState<number>();
  const [weight, setWeight] = useState<string>('');
  const [weeks, setWeeks] = useState<string>('');
  const [pressureS, setPressureS] = useState<string>('');
  const [pressureD, setPressureD] = useState<string>('');
  const [height, setHeight] = useState<string>('');
  const [heartBeat, setHeartBeat] = useState<string>('');
  const [radio, setRadio] = useState<string>('');
  const [type, setType] = useState<string>('');
  const [date, setDate] = useState<string | string[]>();
  const authToken = Cookies.get('token');

  const [weightError, setWeightError] = useState<ErrorInterface>({
    errorShow: false,
    errorType: ''
  });
  const [weeksError, setWeeksError] = useState<ErrorInterface>({
    errorShow: false,
    errorType: ''
  });
  const [dateError, setDateError] = useState<ErrorInterface>({
    errorShow: false,
    errorType: ''
  });

  useEffect(() => {
    const fetchPregnancy = async () => {
      const data = await GetPregnancyById(gestacaoId, authToken || '');
      setGestanteId(data?.data.gestanteId);
    };
    fetchPregnancy();
  }, [gestacaoId]);

  const handleBackArrow = () => navigate(-1);

  const handleChangeDate = (date: unknown, dateString: string | string[]) => {
    try {
      PregnancyFollowUpSchema.shape.dataAcompanhamento.parse(dateString);
      setDateError({ errorType: '', errorShow: false });
    } catch (error) {
      setDateError({ errorType: 'error', errorShow: true });
    }
    setDate(dateString);
  };

  const handleChangeWeight = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      PregnancyFollowUpSchema.shape.pesoAtual.parse(parseInt(value));
      setWeightError({ errorType: '', errorShow: false });
    } catch (error) {
      setWeightError({ errorType: 'error', errorShow: true });
    }
    setWeight(value);
  };

  const handleChangeWeeks = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      PregnancyFollowUpSchema.shape.idadeGestacional.parse(parseInt(value));
      setWeeksError({ errorType: '', errorShow: false });
    } catch (error) {
      setWeeksError({ errorType: 'error', errorShow: true });
    }
    setWeeks(value);
  };

  const handleChangePressureS = (value: string | null) => {
    if (typeof value === 'number') {
      setPressureS(String(value));
    }
  };

  const handleChangePressureD = (value: string | null) => {
    if (typeof value === 'number') {
      setPressureD(String(value));
    }
  };

  const handleChangeHeartBeat = (e: { target: { value: string } }) => {
    const { value } = e.target;
    setHeartBeat(value);
  };

  const handleChangeHeight = (e: { target: { value: string } }) => {
    const { value } = e.target;
    setHeight(value);
  };

  const handleChangeType = (value: unknown) => {
    if (typeof value === 'string') {
      setType(value);
    }
  };

  const radioOnChange = (e: RadioChangeEvent) => {
    setRadio(e.target.value);
  };

  const handlePregnancyFollowUp = async () => {
    let pressure = `${pressureS}/${pressureD}`;
    const data: FollowUpInterface = {
      gestacaoId,
      pesoAtual: parseInt(weight),
      idadeGestacional: parseInt(weeks),
      pressaoArterial: pressure,
      batimentosCardiacosFeto: parseInt(heartBeat),
      alturaUterina: parseInt(height),
      tipo: type,
      dataAcompanhamento: date || '',
      realizadoPor: radio,
      riscoIA: false
    };

    try {
      PregnancyFollowUpSchema.parse(data);
      const response = await postAcompanhamento(
        gestacaoId,
        data,
        authToken || ''
      );
      if (response?.status == 201) {
        successNotification('Acompanhamento cadastrado!');
        navigate(`/pregnancies/${gestanteId}`);
      }
    } catch (error) {
      if (error instanceof ZodError) {
        message.error(error.issues[0].message);
      }
    }
  };

  return {
    weight,
    weeks,
    pressureS,
    pressureD,
    height,
    heartBeat,
    radio,
    type,
    date,
    weightError,
    weeksError,
    dateError,
    handleChangeDate,
    handleChangeWeight,
    handleChangeWeeks,
    handleChangePressureS,
    handleChangePressureD,
    handleChangeHeartBeat,
    handleChangeHeight,
    handleChangeType,
    radioOnChange,
    handlePregnancyFollowUp,
    handleBackArrow
  };
}
