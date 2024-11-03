import * as S from './styles';
import Logo from '../../assets/angelsLogo.svg';
import { Input } from '../../components/Input';
import { Select } from '../../components/Select';
import { DateSelect } from '../../components/DateSelect';
import { RadioSelect } from '../../components/RadioSelect';
import { Button } from '../../components/Button';
import { ArrowLeft, ArrowRight, ArrowUUpLeft } from '@phosphor-icons/react';
import { InputMask } from '../../features/PregnantRegister/InputMask';
import {
  educationLevelsList,
  genderList,
  housingTypesList,
  malnutritionLevelsList,
  maritalStatusList,
  raceList
} from '../../features/PregnantRegister/SelectOptions';
import moment from 'moment';
import { usePregnantRegisterHandlers } from '../../features/PregnantRegister/hooks/usePregnantRegisterHandlers';

export function PregnantRegister() {
  const {
    progress,
    progressBar,
    name,
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
    handleBackArrow
  } = usePregnantRegisterHandlers();

  return (
    <S.Container>
      <S.Contente>
        <S.TopContainer>
          <S.ProgressBar percent={progressBar} showInfo={false} />
          <div className="arrow">
            <ArrowUUpLeft size={22} color="#B1488A" onClick={handleBackArrow} />
            <img src={Logo} alt="angels logo" />
          </div>
        </S.TopContainer>
        {!progress && (
          <S.FormContainer>
            <S.InputRow>
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
                disabled={blockName}
              />
            </S.InputRow>
            <S.InputRow>
              <DateSelect
                label="Nascimento:"
                placeHolder="Selecione uma data"
                inputFunction={handleChangeBirthDate}
                status={errorBirthDate.errorType}
                value={
                  birthDate?.toString()
                    ? moment(birthDate.toString())
                    : undefined
                }
                disable={blockBirth}
              />
              <Select
                label="Raça:"
                defaut="Selecione uma opcão"
                list={raceList}
                selectFunc={handleChangeRace}
                value={race}
                disable={blockRace}
              />
              <Select
                label="Sexo:"
                defaut="Selecione uma opcão"
                list={genderList}
                selectFunc={handleChangeGender}
                value={gender}
                disable={blockGender}
              />
              <InputMask
                mask={'999.999.999-99'}
                label={'Cpf:'}
                placeHolder="xxx.xxx.xxx-xx"
                type="text"
                inputFunction={handleChangeCpf}
                value={cpf}
                errorShow={errorCpf?.errorShow}
                status={errorCpf?.errorType}
                infoText="O cpf precisa ser válido"
                color="#b1488a"
                disabled={blockCpf}
              />
            </S.InputRow>
            <S.InputRow>
              <RadioSelect
                label="Chefe de familia:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeHeadOfHousehold}
                value={headOfHousehold}
              />
              <RadioSelect
                label="Em risco:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeRisc}
                value={risc}
              />
              <Select
                label="Estado civil:"
                defaut="Selecione uma opcão"
                list={maritalStatusList}
                selectFunc={handleChangeMaritalStatus}
                value={maritalStatus}
              />
              <Select
                label="Escolaridade:"
                defaut="Selecione uma opcão"
                list={educationLevelsList}
                selectFunc={handleChangeEducationLevel}
                value={educationLevel}
              />
              <Input
                label={'Renda Familiar:'}
                placeHolder="Digite a renda..."
                type="text"
                inputFunction={handleChangeFamilyIncome}
                value={familyIncome}
                errorShow={errorFamilyIncome?.errorShow}
                status={errorFamilyIncome?.errorType}
                infoText="a renda precisa ser válida"
                color="#b1488a"
              />
            </S.InputRow>
            <S.InputRow>
              <Input
                label={'Minicipio:'}
                placeHolder="Digite municipio..."
                type="text"
                inputFunction={handleChangeCity}
                value={city}
                errorShow={errorCity?.errorShow}
                status={errorCity?.errorType}
                infoText="A cidade ter entre 2 e 30 letras"
                color="#b1488a"
              />
              <Select
                label="Moradia:"
                defaut="Selecione uma opcão"
                list={housingTypesList}
                selectFunc={handleChangeHousing}
                value={housing}
              />
              <RadioSelect
                label="Eletricidade na moradia:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeElectricity}
                value={electricity}
              />
              <RadioSelect
                label="Rede de esgoto:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeSewageNetwork}
                value={sewageNetwork}
              />
              <RadioSelect
                label="Água tratada:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeTreatedWater}
                value={treatedWater}
              />
            </S.InputRow>
            <S.InputRow>
              <RadioSelect
                label="Primeira gestação:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={1}
                secondValue={2}
                radioFunction={handleChangeFirstPregnant}
                value={firstPregnant}
                disable={blockFirstPregnancy}
              />
              <Select
                label="Nível de nutrição:"
                defaut="Selecione uma opcão"
                list={malnutritionLevelsList}
                selectFunc={handleChangeWellFed}
                value={wellFed}
              />

              <RadioSelect
                label="Amamentação:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeBreastfeeding}
                value={breastfeeding}
              />
              <InputMask
                label={'Contato:'}
                placeHolder="Digite o contatto..."
                type="text"
                inputFunction={handleChangeContact}
                value={contact}
                errorShow={errorContact?.errorShow}
                status={errorContact?.errorType}
                infoText="O contato precisa ser válido Brasil"
                color="#b1488a"
                mask={'(99) 9 9999-9999'}
              />
              <InputMask
                label={'Contato de emergencia:'}
                placeHolder="Digite o contato de emergencia..."
                type="text"
                inputFunction={handleChangeEmergencyContact}
                value={emergencyContact}
                errorShow={errorEmergencyContact?.errorShow}
                status={errorEmergencyContact?.errorType}
                infoText="O contato precisa ser válido no Brasil"
                color="#b1488a"
                mask={'(99) 9 9999-9999'}
              />
            </S.InputRow>
          </S.FormContainer>
        )}
        {progress && (
          <S.FormContainer>
            {firstPregnant == 2 ? (
              <>
                <S.InputRow>
                  <DateSelect
                    label="Última gestação:"
                    placeHolder="Selecione uma data"
                    inputFunction={handleChangeLastPregnancyDate}
                    status={errorLastPregnancyDate.errorType}
                  />
                  <Input
                    label={'Abortos:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeAbortions}
                    value={abortions}
                    status={errorAbortions.errorType}
                  />

                  <Input
                    label={'Filhos vivos:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeLiveChildren}
                    value={liveChildren}
                    status={errorLiveChildren.errorType}
                  />
                  <Input
                    label={'Gemelares:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeTwins}
                    value={twins}
                    status={errorTwins.errorType}
                  />
                  <Input
                    label={'Nascidos vivos:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeLiveBirths}
                    value={liveBirths}
                    status={errorLiveBirths.errorType}
                  />
                  <Input
                    label={'Nascidos mortos:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeStillbirths}
                    value={stillbirths}
                    status={errorStillbirths.errorType}
                  />
                </S.InputRow>
                <S.InputRow>
                  <Input
                    label={'rn peso entre 2500 e 4000:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeBirthWeight25004000}
                    value={birthWeight25004000}
                    status={errorBirthWeight25004000.errorType}
                  />
                  <Input
                    label={'rn peso menor 2500:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeBirthWeightlt2500}
                    value={birthWeightlt2500}
                    status={errorBirthWeightlt2500.errorType}
                  />
                  <Input
                    label={'rn peso maior 4000:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeBirthWeightgt4000}
                    value={birthWeightgt4000}
                    status={errorBirthWeightgt4000.errorType}
                  />
                </S.InputRow>
                <S.InputRow>
                  <Input
                    label={'Óbitos na primeira semana:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeDeathsFirstWeek}
                    value={deathsFirstWeek}
                    status={errorDeathsFirstWeek.errorType}
                  />
                  <Input
                    label={'Óbitos após primeira semana:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeDeathsAfterFirstWeek}
                    value={deathsAfterFirstWeek}
                    status={errorDeathsAfterFirstWeek.errorType}
                  />

                  <RadioSelect
                    label="Diabetes:"
                    firstOption="Sim"
                    secondOption="Nao"
                    firstValue={true}
                    secondValue={false}
                    radioFunction={handleChangeDiabetes}
                    value={diabetes}
                  />
                  <RadioSelect
                    label="Cirugia pélvica:"
                    firstOption="Sim"
                    secondOption="Nao"
                    firstValue={true}
                    secondValue={false}
                    radioFunction={handleChangePelvicSurgery}
                    value={pelvicSurgery}
                  />
                </S.InputRow>
                <S.InputRow>
                  <Input
                    label={'Partos:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeDeliveries}
                    value={deliveries}
                    status={errorDeliveries.errorType}
                  />
                  <Input
                    label={'Partos vaginais:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeVaginalDeliveries}
                    value={vaginalDeliveries}
                    status={errorVaginalDeliveries.errorType}
                  />
                  <Input
                    label={'Partos cesarios:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeCesareanDeliveries}
                    value={cesareanDeliveries}
                    status={errorCesareanDeliveries.errorType}
                  />
                </S.InputRow>
              </>
            ) : (
              <>
                <S.InputRow>
                  <Input
                    label={'Abortos:'}
                    placeHolder="0"
                    type="string"
                    inputFunction={handleChangeAbortions}
                    value={abortions}
                    status={errorAbortions.errorType}
                  />
                  <RadioSelect
                    label="Diabetes:"
                    firstOption="Sim"
                    secondOption="Nao"
                    firstValue={true}
                    secondValue={false}
                    radioFunction={handleChangeDiabetes}
                    value={diabetes}
                  />
                  <RadioSelect
                    label="Cirugia pélvica:"
                    firstOption="Sim"
                    secondOption="Nao"
                    firstValue={true}
                    secondValue={false}
                    radioFunction={handleChangePelvicSurgery}
                    value={pelvicSurgery}
                  />
                </S.InputRow>
              </>
            )}
            <S.InputRow>
              <RadioSelect
                label="Infecção urinária:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeUrinaryInfection}
                value={urinaryInfection}
              />
              <RadioSelect
                label="Má formação congênica:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeCongenitalMalformation}
                value={congenitalMalformation}
              />
              <RadioSelect
                label="Hipertensão:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeHypertension}
                value={hypertension}
              />
              <RadioSelect
                label="Familiar gêmeos:"
                firstOption="Sim"
                secondOption="Nao"
                firstValue={true}
                secondValue={false}
                radioFunction={handleChangeTwinFamilyHistory}
                value={twinFamilyHistory}
              />
            </S.InputRow>
          </S.FormContainer>
        )}
        <S.ButtonContainer $progress={progress}>
          {progress && (
            <Button
              label="Voltar"
              icon={<ArrowLeft size={18} weight="fill" />}
              shape="round"
              buttonFunction={handleSetProgress}
            />
          )}
          <Button
            label={progress ? 'Cadastrar' : 'Próximo'}
            icon={!progress && <ArrowRight size={18} weight="fill" />}
            shape="round"
            buttonFunction={progress ? Register : handleSetProgress}
          />
        </S.ButtonContainer>
      </S.Contente>
    </S.Container>
  );
}
