import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { ZodError } from 'zod';
import {
  successNotification,
  warningNotification
} from '../../../components/Notification';
import {
  GetPregnantEvolutionData,
  PostPregnant,
  PostPregnantEvolutionData
} from '../../../services/PregnantServices';
import { RadioChangeEvent } from 'antd';
import { PregnantInterface } from '../../../types/interfaces/PregnantType';
import { ErrorInterface } from '../../../types/interfaces/ErrorType';
import {
  pregnantSchemaPartOne,
  pregnantSchemaPartTwo,
  pregnantSchemaPartTwoFirstPregnant
} from '../../../types/schemas/PregnantRegisterSchema';
import { isValidCpf } from '../../../utils/cpfValidator';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

export function usePregnantRegisterHandlers() {
  const navigate = useNavigate();
  const params = useParams();
  const authToken = Cookies.get('token');
  const decodedToken = jwtDecode(authToken || '');

  const [progress, setProgress] = useState<boolean>(false);
  const [progressBar, setProgressBar] = useState<number>(0);
  const [name, setName] = useState<string>('');
  const [birthDate, setBirthDate] = useState<string | string[]>();
  const [race, setRace] = useState<string>('');
  const [gender, setGender] = useState<string>('');
  const [cpf, setCpf] = useState<string>('');
  const [headOfHousehold, setHeadOfHousehold] = useState<boolean>();
  const [risc, setRisc] = useState<boolean>();
  const [maritalStatus, setMaritalStatus] = useState<string>('');
  const [educationLevel, setEducationLevel] = useState<string>('');
  const [familyIncome, setFamilyIncome] = useState<string>('');
  const [city, setCity] = useState<string>('');
  const [housing, setHousing] = useState<string>('');
  const [email, setEmail] = useState<string>('');
  const [electricity, setElectricity] = useState<boolean>();
  const [sewageNetwork, setSewageNetwork] = useState<boolean>();
  const [treatedWater, setTreatedWater] = useState<boolean>();
  const [firstPregnant, setFirstPregnant] = useState<number>();
  const [lastPregnancyDate, setLastPregnancyDate] = useState<
    string | string[]
  >();
  const [wellFed, setWellFed] = useState<string>('');
  const [breastfeeding, setBreastfeeding] = useState<boolean>();
  const [contact, setContact] = useState<string>('');
  const [emergencyContact, setEmergencyContact] = useState<string>('');

  const [abortions, setAbortions] = useState<string>('');
  const [liveChildren, setLiveChildren] = useState<string>('');
  const [twins, setTwins] = useState<string>('');
  const [liveBirths, setLiveBirths] = useState<string>('');
  const [stillbirths, setStillbirths] = useState<string>('');
  const [birthWeight25004000, setBirthWeight25004000] = useState<string>('');
  const [birthWeightlt2500, setBirthWeightlt2500] = useState<string>('');
  const [birthWeightgt4000, setBirthWeightgt4000] = useState<string>('');
  const [deathsFirstWeek, setDeathsFirstWeek] = useState<string>('');
  const [deathsAfterFirstWeek, setDeathsAfterFirstWeek] = useState<string>('');
  const [diabetes, setDiabetes] = useState<boolean>();
  const [pelvicSurgery, setPelvicSurgery] = useState<boolean>();
  const [deliveries, setDeliveries] = useState<string>('');
  const [vaginalDeliveries, setVaginalDeliveries] = useState<string>('');
  const [cesareanDeliveries, setCesareanDeliveries] = useState<string>('');
  const [urinaryInfection, setUrinaryInfection] = useState<boolean>();
  const [congenitalMalformation, setCongenitalMalformation] =
    useState<boolean>();
  const [hypertension, setHypertension] = useState<boolean>();
  const [twinFamilyHistory, setTwinFamilyHistory] = useState<boolean>();

  const [blockName, setBlockName] = useState<boolean>(false);
  const [blockBirth, setBlockBirth] = useState<boolean>(false);
  const [blockRace, setBlockRace] = useState<boolean>(false);
  const [blockGender, setBlockGender] = useState<boolean>(false);
  const [blockCpf, setBlockCpf] = useState<boolean>(false);
  const [blockFirstPregnancy, setBlockFirstPregnancy] =
    useState<boolean>(false);

  const [errorName, setErrorName] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorEmail, setErrorEmail] = useState<ErrorInterface>({
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

  const [errorFamilyIncome, setErrorFamilyIncome] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorCity, setErrorCity] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorLastPregnancyDate, setErrorLastPregnancyDate] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  const [errorContact, setErrorContact] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorEmergencyContact, setErrorEmergencyContact] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  // Segunda parte

  const [errorAbortions, setErrorAbortions] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorLiveChildren, setErrorLiveChildren] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorTwins, setErrorTwins] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorLiveBirths, setErrorLiveBirths] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorStillbirths, setErrorStillbirths] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorBirthWeight25004000, setErrorBirthWeight25004000] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  const [errorBirthWeightlt2500, setErrorBirthWeightlt2500] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  const [errorBirthWeightgt4000, setErrorBirthWeightgt4000] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  const [errorDeathsFirstWeek, setErrorDeathsFirstWeek] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  const [errorDeathsAfterFirstWeek, setErrorDeathsAfterFirstWeek] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  const [errorDeliveries, setErrorDeliveries] = useState<ErrorInterface>({
    errorType: '',
    errorShow: false
  });

  const [errorVaginalDeliveries, setErrorVaginalDeliveries] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  const [errorCesareanDeliveries, setErrorCesareanDeliveries] =
    useState<ErrorInterface>({
      errorType: '',
      errorShow: false
    });

  useEffect(() => {
    const getPregnantEvolutionData = async () => {
      if (params.id) {
        const response = await GetPregnantEvolutionData(
          parseInt(params.id),
          authToken || ''
        );
        if (response?.status === 200) {
          const data = response.data[response.data.length - 1];

          setBlockName(true);
          setBlockBirth(true);
          setBlockCpf(true);
          setBlockGender(true);
          setBlockRace(true);
          setBlockFirstPregnancy(true);

          setName(data.gestante.nome);
          setBirthDate(data.gestante.dataNascimento);
          setRace(data.gestante.raca.toString());
          setGender(data.gestante.sexo);
          setCpf(data.gestante.cpf);
          setHeadOfHousehold(data.chefeFamilia);
          setRisc(data.emRisco);
          setMaritalStatus(data.estadoCivil.toString());
          setEducationLevel(data.escolaridade.toString());
          setFamilyIncome(data.rendaFamiliar.toString());
          setCity(data.municipio);
          setHousing(data.tipoMoradia.toString());
          setElectricity(data.energiaEletricaDomicilio);
          setSewageNetwork(data.moradiaRedeEsgoto);
          setTreatedWater(data.tratamentoAgua);
          setBreastfeeding(data.amamentacao);
          setContact(data.contato);
          setEmergencyContact(data.contatoEmergencia);
          setWellFed(data.diagnosticoDesnutricao.toString());
          setFirstPregnant(2);

          // Segunda parte
          setAbortions(data.quantidadeAbortos.toString());
          setLiveChildren(data.quantidadeFilhosVivos.toString());
          setTwins(data.quantidadeGemelares.toString());
          setLiveBirths(data.quantidadeNascidosVivos.toString());
          setStillbirths(data.quantidadeNascidosMortos.toString());
          setBirthWeight25004000(data.quantidadeRnPeso2500_4000.toString());
          setBirthWeightlt2500(data.quantidadeRnPesoMenor2500.toString());
          setBirthWeightgt4000(data.quantidadeRnPesoMaior4000.toString());
          setDeathsFirstWeek(data.quantidadeObitosSemana1.toString());
          setDeathsAfterFirstWeek(data.quantidadeObitosAposSemana1.toString());
          setDiabetes(data.diabetes);
          setPelvicSurgery(data.cirurgiaPelvica);
          setDeliveries(data.quantidadePartos.toString());
          setVaginalDeliveries(data.quantidadePartosVaginais.toString());
          setCesareanDeliveries(data.quantidadePartosCesarios.toString());
          setUrinaryInfection(data.infeccaoUrinaria);
          setCongenitalMalformation(data.maFormacaoCongenita);
          setHypertension(data.hipertensao);
          setTwinFamilyHistory(data.familiarGemeos);
        }
      }
    };
    getPregnantEvolutionData();
  }, []);

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

  const handleChangeEmail = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartOne.shape.email.parse(value);
      setErrorEmail({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorEmail({ errorType: 'error', errorShow: true });
    }
    setEmail(value);
  };

  const handleChangeBirthDate = (
    date: unknown,
    dateString: string | string[]
  ) => {
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

    if (isValidCpf(value)) {
      setErrorCpf({ errorType: '', errorShow: false });
    } else {
      setErrorCpf({ errorType: 'error', errorShow: true });
    }
    setCpf(value);
  };

  const handleChangeHeadOfHousehold = (e: RadioChangeEvent) => {
    setHeadOfHousehold(e.target.value);
  };

  const handleChangeRisc = (e: RadioChangeEvent) => {
    setRisc(e.target.value);
  };

  const handleChangeMaritalStatus = (value: unknown) => {
    if (typeof value === 'string') {
      setMaritalStatus(value);
    }
  };

  const handleChangeEducationLevel = (value: unknown) => {
    if (typeof value === 'string') {
      setEducationLevel(value);
    }
  };

  const handleChangeFamilyIncome = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartOne.shape.rendaFamiliar.parse(value);
      setErrorFamilyIncome({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorFamilyIncome({ errorType: 'error', errorShow: true });
    }
    setFamilyIncome(value);
  };

  const handleChangeCity = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartOne.shape.municipio.parse(value);
      setErrorCity({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorCity({ errorType: 'error', errorShow: true });
    }
    setCity(value);
  };

  const handleChangeHousing = (value: unknown) => {
    if (typeof value === 'string') {
      setHousing(value);
    }
  };

  const handleChangeElectricity = (e: RadioChangeEvent) => {
    setElectricity(e.target.value);
  };

  const handleChangeSewageNetwork = (e: RadioChangeEvent) => {
    setSewageNetwork(e.target.value);
  };

  const handleChangeTreatedWater = (e: RadioChangeEvent) => {
    setTreatedWater(e.target.value);
  };

  const handleChangeFirstPregnant = (e: RadioChangeEvent) => {
    setFirstPregnant(e.target.value);
  };

  const handleChangeLastPregnancyDate = (
    date: unknown,
    dateString: string | string[]
  ) => {
    try {
      pregnantSchemaPartTwo.shape.dataUltimaGestacao.parse(dateString);
      setErrorLastPregnancyDate({ errorType: '', errorShow: false });
      if (dateString == '') {
        setErrorLastPregnancyDate({ errorType: 'error', errorShow: true });
      }
    } catch (error) {
      console.log(error);
      setErrorLastPregnancyDate({ errorType: 'error', errorShow: true });
    }
    console.log(date);
    setLastPregnancyDate(dateString);
  };

  const handleChangeWellFed = (value: unknown) => {
    if (typeof value === 'string') {
      setWellFed(value);
    }
  };

  const handleChangeBreastfeeding = (e: RadioChangeEvent) => {
    setBreastfeeding(e.target.value);
  };

  const handleChangeContact = (e: { target: { value: string } }) => {
    const { value } = e.target;
    const inputValue = value.replace(/\D/g, '');
    try {
      pregnantSchemaPartOne.shape.contato.parse(inputValue);
      setErrorContact({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorContact({ errorType: 'error', errorShow: true });
    }
    setContact(inputValue);
  };

  const handleChangeEmergencyContact = (e: { target: { value: string } }) => {
    const { value } = e.target;
    const inputValue = value.replace(/\D/g, '');
    try {
      pregnantSchemaPartOne.shape.contatoEmergencia.parse(inputValue);
      setErrorEmergencyContact({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorEmergencyContact({ errorType: 'error', errorShow: true });
    }
    setEmergencyContact(inputValue);
  };

  //segunda parte
  const handleChangeAbortions = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeAbortos.parse(value);
      setErrorAbortions({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorAbortions({ errorType: 'error', errorShow: true });
    }
    setAbortions(value);
  };

  const handleChangeLiveChildren = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeFilhosVivos.parse(value);
      setErrorLiveChildren({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorLiveChildren({ errorType: 'error', errorShow: true });
    }
    setLiveChildren(value);
  };

  const handleChangeTwins = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeGemelares.parse(value);
      setErrorTwins({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorTwins({ errorType: 'error', errorShow: true });
    }
    setTwins(value);
  };

  const handleChangeLiveBirths = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeNascidosVivos.parse(value);
      setErrorLiveBirths({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorLiveBirths({ errorType: 'error', errorShow: true });
    }
    setLiveBirths(value);
  };

  const handleChangeStillbirths = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeNascidosMortos.parse(value);
      setErrorStillbirths({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorStillbirths({ errorType: 'error', errorShow: true });
    }
    setStillbirths(value);
  };

  const handleChangeBirthWeight25004000 = (e: {
    target: { value: string };
  }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeRnPeso2500_4000.parse(value);
      setErrorBirthWeight25004000({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorBirthWeight25004000({ errorType: 'error', errorShow: true });
    }
    setBirthWeight25004000(value);
  };

  const handleChangeBirthWeightlt2500 = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeRnPesoMenor2500.parse(value);
      setErrorBirthWeightlt2500({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorBirthWeightlt2500({ errorType: 'error', errorShow: true });
    }
    setBirthWeightlt2500(value);
  };

  const handleChangeBirthWeightgt4000 = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeRnPesoMaior4000.parse(value);
      setErrorBirthWeightgt4000({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorBirthWeightgt4000({ errorType: 'error', errorShow: true });
    }
    setBirthWeightgt4000(value);
  };

  const handleChangeDeathsFirstWeek = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeObitosSemana1.parse(value);
      setErrorDeathsFirstWeek({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorDeathsFirstWeek({ errorType: 'error', errorShow: true });
    }
    setDeathsFirstWeek(value);
  };

  const handleChangeDeathsAfterFirstWeek = (e: {
    target: { value: string };
  }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadeObitosAposSemana1.parse(value);
      setErrorDeathsAfterFirstWeek({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorDeathsAfterFirstWeek({ errorType: 'error', errorShow: true });
    }
    setDeathsAfterFirstWeek(value);
  };

  const handleChangeDiabetes = (e: RadioChangeEvent) => {
    setDiabetes(e.target.value);
  };

  const handleChangePelvicSurgery = (e: RadioChangeEvent) => {
    setPelvicSurgery(e.target.value);
  };

  const handleChangeDeliveries = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadePartos.parse(value);
      setErrorDeliveries({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorDeliveries({ errorType: 'error', errorShow: true });
    }
    setDeliveries(value);
  };

  const handleChangeVaginalDeliveries = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadePartosVaginais.parse(value);
      setErrorVaginalDeliveries({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorVaginalDeliveries({ errorType: 'error', errorShow: true });
    }
    setVaginalDeliveries(value);
  };

  const handleChangeCesareanDeliveries = (e: { target: { value: string } }) => {
    const { value } = e.target;
    try {
      pregnantSchemaPartTwo.shape.quantidadePartosCesarios.parse(value);
      setErrorCesareanDeliveries({ errorType: '', errorShow: false });
    } catch (error) {
      setErrorCesareanDeliveries({ errorType: 'error', errorShow: true });
    }
    setCesareanDeliveries(value);
  };

  const handleChangeUrinaryInfection = (e: RadioChangeEvent) => {
    setUrinaryInfection(e.target.value);
  };

  const handleChangeCongenitalMalformation = (e: RadioChangeEvent) => {
    setCongenitalMalformation(e.target.value);
  };

  const handleChangeHypertension = (e: RadioChangeEvent) => {
    setHypertension(e.target.value);
  };

  const handleChangeTwinFamilyHistory = (e: RadioChangeEvent) => {
    setTwinFamilyHistory(e.target.value);
  };

  const pregnantFirstData = {
    nome: name,
    dataNascimento: birthDate,
    raca: race,
    sexo: gender,
    cpf: cpf,
    email: email,
    chefeFamilia: headOfHousehold,
    emRisco: risc,
    estadoCivil: maritalStatus,
    escolaridade: educationLevel,
    rendaFamiliar: familyIncome,
    municipio: city,
    tipoMoradia: housing,
    energiaEletricaDomicilio: electricity,
    moradiaRedeEsgoto: sewageNetwork,
    tratamentoAgua: treatedWater,
    amamentacao: breastfeeding,
    diagnosticoDesnutricao: wellFed,
    contato: contact,
    contatoEmergencia: emergencyContact
  };
  const pregnantSecondData = {
    // Segunda parte
    dataUltimaGestacao: lastPregnancyDate || '',
    quantidadeAbortos: abortions,
    quantidadeFilhosVivos: liveChildren,
    quantidadeGemelares: twins,
    quantidadeNascidosVivos: liveBirths,
    quantidadeNascidosMortos: stillbirths,
    quantidadeRnPeso2500_4000: birthWeight25004000,
    quantidadeRnPesoMenor2500: birthWeightlt2500,
    quantidadeRnPesoMaior4000: birthWeightgt4000,
    quantidadeObitosSemana1: deathsFirstWeek,
    quantidadeObitosAposSemana1: deathsAfterFirstWeek,
    diabetes: diabetes,
    cirurgiaPelvica: pelvicSurgery,
    quantidadePartos: deliveries,
    quantidadePartosVaginais: vaginalDeliveries,
    quantidadePartosCesarios: cesareanDeliveries,
    infeccaoUrinaria: urinaryInfection,
    maFormacaoCongenita: congenitalMalformation,
    hipertensao: hypertension,
    familiarGemeos: twinFamilyHistory,
    contato: contact,
    contatoEmergencia: emergencyContact
  };

  const pregnantData: PregnantInterface = {
    gestante: {
      nome: name,
      dataNascimento: birthDate?.toString(),
      raca: race,
      sexo: gender,
      cpf: cpf,
      email: email,
      username: decodedToken ? decodedToken.sub : ''
    },
    dadosEvolutivos: {
      gestanteId: 0,
      chefeFamilia: headOfHousehold,
      estadoCivil: maritalStatus,
      escolaridade: parseInt(educationLevel),
      rendaFamiliar: parseInt(familyIncome),
      emRisco: risc,
      municipio: city,
      tipoMoradia: housing,
      energiaEletricaDomicilio: electricity,
      moradiaRedeEsgoto: sewageNetwork,
      tratamentoAgua: treatedWater,
      amamentacao: breastfeeding,
      diagnosticoDesnutricao: wellFed,
      contato: contact,
      contatoEmergencia: emergencyContact,
      dataUltimaGestacao: lastPregnancyDate?.toString() || '',
      quantidadeAbortos: parseInt(abortions),
      quantidadeFilhosVivos: parseInt(liveChildren),
      quantidadeGemelares: parseInt(twins),
      quantidadeNascidosVivos: parseInt(liveBirths),
      quantidadeNascidosMortos: parseInt(stillbirths),
      quantidadeRnPeso2500_4000: parseInt(birthWeight25004000),
      quantidadeRnPesoMenor2500: parseInt(birthWeightlt2500),
      quantidadeRnPesoMaior4000: parseInt(birthWeightgt4000),
      quantidadeObitosSemana1: parseInt(deathsFirstWeek),
      quantidadeObitosAposSemana1: parseInt(deathsAfterFirstWeek),
      diabetes: diabetes,
      cirurgiaPelvica: pelvicSurgery,
      quantidadePartos: parseInt(deliveries),
      quantidadePartosVaginais: parseInt(vaginalDeliveries),
      quantidadePartosCesarios: parseInt(cesareanDeliveries),
      infeccaoUrinaria: urinaryInfection,
      maFormacaoCongenita: congenitalMalformation,
      hipertensao: hypertension,
      familiarGemeos: twinFamilyHistory
    }
  };

  const postPregnant = async () => {
    if (pregnantData.gestante) {
      const response = await PostPregnant(
        pregnantData.gestante,
        authToken || ''
      );
      if (response?.status == 201) {
        const gestanteId = response.data.id;
        if (pregnantData.dadosEvolutivos != undefined) {
          pregnantData.dadosEvolutivos.gestanteId = gestanteId;
        }

        successNotification('Gestante cadastrada com sucesso!');

        navigate(`/pregnancies/${response.data.id}`);
      }
    }
  };

  const postEvolutionData = async () => {
    if (params.id && pregnantData.dadosEvolutivos) {
      pregnantData.dadosEvolutivos.gestanteId = parseInt(params.id);
      const response = await PostPregnantEvolutionData(
        pregnantData.dadosEvolutivos,
        authToken || ''
      );
      if (response?.status == 200) {
        successNotification('Dados atualizados com sucesso!');
        navigate(`/pregnancies/${params.id}`);
      }
    } else if (pregnantData.dadosEvolutivos) {
      const response = await PostPregnantEvolutionData(
        pregnantData.dadosEvolutivos,
        authToken || ''
      );

      if (response?.status == 201) {
        successNotification('Dados cadastrados com sucesso!');
      }
    }
  };

  const handleSetProgress = () => {
    try {
      pregnantSchemaPartOne.parse(pregnantFirstData);
      setProgress(!progress);
      if (firstPregnant == 1) {
        setProgressBar(72);
      } else {
        setProgressBar(38.5);
      }
    } catch (error) {
      if (error instanceof ZodError) {
        warningNotification(error.errors[0].message);
      }
    }
  };

  const Register = async () => {
    try {
      if (firstPregnant == 2) {
        pregnantSchemaPartTwo.parse(pregnantSecondData);
        if (params.id) {
          postEvolutionData();
        } else {
          await postPregnant();
          await postEvolutionData();
        }
      } else {
        pregnantSchemaPartTwoFirstPregnant.parse(pregnantSecondData);
        postPregnant();
      }
      setProgressBar(100);
      // navigate('/dashboard');
    } catch (error) {
      if (error instanceof ZodError) {
        warningNotification(error.errors[0].message);
      }
    }
  };

  const handleBackArrow = () => {
    navigate('/dashboard');
  };

  return {
    progress,
    progressBar,
    name,
    email,
    birthDate,
    race,
    gender,
    cpf,
    headOfHousehold,
    risc,
    maritalStatus,
    educationLevel,
    familyIncome,
    city,
    housing,
    electricity,
    sewageNetwork,
    treatedWater,
    firstPregnant,
    wellFed,
    breastfeeding,
    contact,
    emergencyContact,
    abortions,
    liveChildren,
    twins,
    liveBirths,
    stillbirths,
    birthWeight25004000,
    birthWeightlt2500,
    birthWeightgt4000,
    deathsFirstWeek,
    deathsAfterFirstWeek,
    diabetes,
    pelvicSurgery,
    deliveries,
    vaginalDeliveries,
    cesareanDeliveries,
    urinaryInfection,
    congenitalMalformation,
    hypertension,
    twinFamilyHistory,
    errorName,
    errorBirthDate,
    errorCpf,
    errorFamilyIncome,
    errorCity,
    errorLastPregnancyDate,
    errorContact,
    errorEmergencyContact,
    errorAbortions,
    errorLiveChildren,
    errorTwins,
    errorLiveBirths,
    errorStillbirths,
    errorBirthWeight25004000,
    errorBirthWeightlt2500,
    errorBirthWeightgt4000,
    errorDeathsFirstWeek,
    errorDeathsAfterFirstWeek,
    errorDeliveries,
    errorVaginalDeliveries,
    errorCesareanDeliveries,
    errorEmail,
    blockName,
    blockBirth,
    blockRace,
    blockGender,
    blockCpf,
    blockFirstPregnancy,
    handleChangeName,
    handleChangeBirthDate,
    handleChangeRace,
    handleChangeGender,
    handleChangeCpf,
    handleChangeHeadOfHousehold,
    handleChangeRisc,
    handleChangeMaritalStatus,
    handleChangeEducationLevel,
    handleChangeFamilyIncome,
    handleChangeCity,
    handleChangeHousing,
    handleChangeElectricity,
    handleChangeSewageNetwork,
    handleChangeTreatedWater,
    handleChangeFirstPregnant,
    handleChangeLastPregnancyDate,
    handleChangeWellFed,
    handleChangeBreastfeeding,
    handleChangeContact,
    handleChangeEmergencyContact,
    handleChangeAbortions,
    handleChangeLiveChildren,
    handleChangeTwins,
    handleChangeLiveBirths,
    handleChangeStillbirths,
    handleChangeBirthWeight25004000,
    handleChangeBirthWeightlt2500,
    handleChangeBirthWeightgt4000,
    handleChangeDeathsFirstWeek,
    handleChangeDeathsAfterFirstWeek,
    handleChangeDiabetes,
    handleChangePelvicSurgery,
    handleChangeDeliveries,
    handleChangeVaginalDeliveries,
    handleChangeCesareanDeliveries,
    handleChangeUrinaryInfection,
    handleChangeCongenitalMalformation,
    handleChangeHypertension,
    handleChangeTwinFamilyHistory,
    handleSetProgress,
    Register,
    handleBackArrow,
    handleChangeEmail
  };
}
