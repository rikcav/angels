import * as S from './styles.ts';
import Logo from '../../assets/angelsLogo.svg';
import { Select } from '../../components/Select/index.tsx';
import { Input } from '../../components/Input/index.tsx';
import { RadioSelect } from '../../components/RadioSelect/index.tsx';
import { DateSelect } from '../../components/DateSelect/index.tsx';
import { useParams } from 'react-router-dom';
import { ArrowUUpLeft } from '@phosphor-icons/react';
import { usePregnancyRegisterHandlers } from '../../features/PregnancyRegister/hooks/usePregnancyRegisterHandlers.ts';
import {
  alcoholFreq,
  bloodType,
  drugsUse,
  gestRisk,
  gestSitu
} from '../../features/PregnancyRegister/SelectOptions/index.ts';

export default function PregnancyRegister() {
  const params = useParams();
  const gestanteId = Number(params.id);

  const {
    weight,
    rh,
    hepB,
    smoke,
    alcohol,
    planned,
    numberCigarettes,
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
    handlePregnancyRegister
  } = usePregnancyRegisterHandlers(gestanteId);

  return (
    <S.Container>
      <S.Content>
        <S.NavContainer>
          <ArrowUUpLeft size={22} color="#B1488A" onClick={handleBackArrow} />
          <S.LogoContainer>
            <img src={Logo} alt="Angels Logo"></img>
          </S.LogoContainer>
        </S.NavContainer>
        <S.FormContainer>
          <S.FirstRow>
            <DateSelect
              label="Data da última menstruação"
              inputFunction={handleChangeDatePeriod}
              status={errorPeriod.errorType}
            ></DateSelect>
            <DateSelect
              label="Data de início da gestação"
              inputFunction={handleChangeDateBeginning}
              status={errorBeginning.errorType}
            ></DateSelect>
            <Input
              label="Peso anterior a gestação"
              value={weight}
              inputFunction={handleChangeWeight}
              rightAdd="Kg"
              type="number"
              errorShow={errorWeight.errorShow}
              status={errorWeight.errorType}
            ></Input>
          </S.FirstRow>
          <S.FirstRow>
            <Select
              label="Situação gestacional"
              defaut="Selecione a situação gestacional"
              list={gestSitu}
              selectFunc={handleChangeSituation}
            ></Select>
            <Select
              label="Risco gestacional"
              list={gestRisk}
              defaut="Selecione o risco"
              selectFunc={handleChangeRisc}
            ></Select>
            <Select
              label="Grupo sanguíneo"
              list={bloodType}
              defaut="Grupo sanguíneo"
              selectFunc={handleChangeBlood}
            ></Select>
            <RadioSelect
              label="Fator RH"
              firstOption="+"
              secondOption="-"
              firstValue={'POSITIVO'}
              secondValue={'NEGATIVO'}
              value={rh}
              radioFunction={handleChangeRh}
            ></RadioSelect>
          </S.FirstRow>
          <S.FirstRow>
            <Select
              list={drugsUse}
              label="Frequência do uso de drogas"
              defaut="Selecione a frequencia de uso"
              selectFunc={handleChangeDrugs}
            ></Select>
            <RadioSelect
              label="Gravidez planejada"
              firstOption="sim"
              secondOption="não"
              firstValue={true}
              secondValue={false}
              value={planned}
              radioFunction={handleChangePlanned}
            ></RadioSelect>
            <RadioSelect
              label="Vacina hepatite b"
              firstOption="sim"
              secondOption="não"
              firstValue={true}
              secondValue={false}
              value={hepB}
              radioFunction={handleChangeHepB}
            ></RadioSelect>
            <RadioSelect
              label="Fumante"
              firstOption="sim"
              secondOption="não"
              firstValue={true}
              secondValue={false}
              radioFunction={handleChangeSmoke}
              value={smoke}
            ></RadioSelect>
            <RadioSelect
              label="Consumo de álcool"
              firstOption="sim"
              secondOption="não"
              firstValue={true}
              secondValue={false}
              radioFunction={handleChangeAlcohol}
              value={alcohol}
            ></RadioSelect>
          </S.FirstRow>
          <S.FirstRow>
            {smoke && (
              <Input
                label="Quantidade de cigarro por dia"
                type="number"
                optional={true}
                value={numberCigarettes}
                inputFunction={handleCigarettes}
              ></Input>
            )}
            {alcohol && (
              <Select
                list={alcoholFreq}
                label="Frequência do uso de álcool"
                defaut="Selecione a frequencia de uso"
                optional={true}
                selectFunc={handleAlcoholFreq}
              ></Select>
            )}
          </S.FirstRow>
          <S.btn onClick={handlePregnancyRegister}>Cadastrar</S.btn>
        </S.FormContainer>
      </S.Content>
    </S.Container>
  );
}
