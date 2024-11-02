import * as S from './styles';
import Logo from '../../assets/angelsLogo.svg';
import { Input } from '../../components/Input';
import { DateSelect } from '../../components/DateSelect';
import { Select } from '../../components/Select';
import { RadioSelect } from '../../components/RadioSelect';
import { InputNumber } from 'antd';
import { ArrowUUpLeft } from '@phosphor-icons/react';
import { useParams } from 'react-router-dom';
import { usePregnancyFollowUpHandlers } from '../../features/PregnancyFollowUp/hooks/usePregnancyFollowUpHandlers';

export function PregnancyFollowUp() {
  const params = useParams();
  const gestacaoId = Number(params.gestacaoId);
  const {
    weight,
    weeks,
    pressureS,
    pressureD,
    height,
    heartBeat,
    radio,
    selectList,
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
    handleBackArrow,
    weightError,
    weeksError,
    dateError
  } = usePregnancyFollowUpHandlers(gestacaoId);

  return (
    <S.Container>
      <S.Content>
        <S.NavContainer>
          <ArrowUUpLeft
            size={22}
            color="#B1488A"
            onClick={handleBackArrow}
            cursor={'Pointer'}
          />
          <S.LogoContainer>
            <img src={Logo} alt="Angels Logo"></img>
          </S.LogoContainer>
        </S.NavContainer>
        <S.FormContainer>
          <S.FirstRow>
            <DateSelect
              label="Data do acompanhamento"
              inputFunction={handleChangeDate}
              status={dateError.errorType}
            ></DateSelect>
            <Input
              type="number"
              label="Peso atual"
              rightAdd="Kg"
              value={weight}
              inputFunction={handleChangeWeight}
              status={weightError.errorType}
              errorShow={weightError.errorShow}
            ></Input>
            <Input
              type="number"
              label="Idade gestacional"
              infoText="Insira o número de semanas"
              value={weeks}
              inputFunction={handleChangeWeeks}
              status={weeksError.errorType}
              errorShow={weeksError.errorShow}
            ></Input>
          </S.FirstRow>
          <S.FirstRow>
            <Input
              type="number"
              label="Batimento cardíaco do feto"
              value={heartBeat}
              optional={true}
              rightAdd="bpm"
              inputFunction={handleChangeHeartBeat}
            ></Input>
            <S.PressureDiv>
              <label htmlFor="">Pressão Arterial*</label>
              <S.InputAreaP>
                <InputNumber
                  type="number"
                  placeholder="mmHg"
                  value={pressureS}
                  onChange={handleChangePressureS}
                ></InputNumber>
                <p>/</p>
                <InputNumber
                  type="number"
                  placeholder="mmHg"
                  value={pressureD}
                  onChange={handleChangePressureD}
                ></InputNumber>
              </S.InputAreaP>
            </S.PressureDiv>
            <Input
              type="number"
              label="Altura uterina"
              value={height}
              optional={true}
              rightAdd="cm"
              inputFunction={handleChangeHeight}
            ></Input>
          </S.FirstRow>
          <S.FirstRow>
            <Select
              label="Tipo"
              defaut="Tipo de gestação"
              list={selectList}
              selectFunc={handleChangeType}
            ></Select>
            <RadioSelect
              label="Atendido por"
              firstOption="Médico"
              firstValue={'MEDICO'}
              secondValue={'ENFERMEIRO'}
              secondOption="Enfermeiro"
              value={radio}
              radioFunction={radioOnChange}
            ></RadioSelect>
          </S.FirstRow>
          <S.btn onClick={handlePregnancyFollowUp}>Cadastrar</S.btn>
        </S.FormContainer>
      </S.Content>
    </S.Container>
  );
}
