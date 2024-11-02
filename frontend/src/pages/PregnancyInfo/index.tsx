import {
  ArrowCircleDown,
  ArrowCircleUp,
  ArrowUUpLeft,
  PlusCircle
} from '@phosphor-icons/react';
import * as S from './styles.ts';
import { PregnantPersonalInfo } from '../../features/Pregnancies/PregnantPersonalInfo/index.tsx';
import { FollowUpCard } from '../../components/FollowUpCard/index.tsx';
import { useParams } from 'react-router-dom';
import { usePregnancyInfoHandlers } from '../../features/PregnancyInfo/hooks/usePregnancyInfoHandlers.ts';
import {
  frequenciaUsoAlcoolLabels,
  riscoGestacionalLabels,
  situacaoGestacionalLabels
} from '../../features/PregnancyInfo/Labels/index.ts';
import { PregnancyDisplayInfos } from '../../components/PregnancyDisplayInfos/index.tsx';

export default function PregnancyInfo() {
  const params = useParams();

  const pregnantId = Number(params?.pregnantId);
  const pregnancyId = Number(params?.pregnancyId);

  const {
    followUpInfo,
    pregnancyInfo,
    name,
    cpf,
    toggleInfo,
    toggleShowInfos,
    handleBackArrow,
    handleNewPregnancy
  } = usePregnancyInfoHandlers(Number(pregnantId), Number(pregnancyId));

  return (
    <S.Container>
      <S.Header>
        <section>
          <ArrowUUpLeft
            size={22}
            color="#B1488A"
            onClick={handleBackArrow}
            cursor={'Pointer'}
            weight="bold"
          />
          <S.PregnanciesText>
            GESTAÇÕES {'>'} Gestação {pregnancyId}
          </S.PregnanciesText>
        </section>
        <S.NewPregnancyButton onClick={handleNewPregnancy}>
          <PlusCircle size={18} weight="bold" />
          NOVA GESTAÇÃO
        </S.NewPregnancyButton>
      </S.Header>
      <S.PregnantInfoContainer>
        <PregnantPersonalInfo name="Nome" value={name} />
        <PregnantPersonalInfo name="CPF" value={cpf} />
      </S.PregnantInfoContainer>
      {toggleInfo ? (
        <ArrowCircleUp
          size={25}
          cursor={'Pointer'}
          color="#B1488A"
          weight="bold"
          onClick={toggleShowInfos}
        />
      ) : (
        <ArrowCircleDown
          size={25}
          cursor={'Pointer'}
          color="#B1488A"
          weight="bold"
          onClick={toggleShowInfos}
        />
      )}
      {toggleInfo ? (
        <PregnancyDisplayInfos
          consumoAlcool={pregnancyInfo?.consumoAlcool ? 'Sim' : 'Não'}
          dataInicioGestacao={
            pregnancyInfo?.dataInicioGestacao
              ? new Date(pregnancyInfo?.dataInicioGestacao).toLocaleDateString()
              : 'Sem dados'
          }
          dataUltimaMenstruacao={
            pregnancyInfo?.dataUltimaMenstruacao
              ? new Date(
                  pregnancyInfo?.dataUltimaMenstruacao
                ).toLocaleDateString()
              : 'Sem dados'
          }
          fatorRh={
            pregnancyInfo?.fatorRh == 'POSITIVO' ? 'Positivo' : 'Negativo'
          }
          frequenciaUsoAlcool={
            frequenciaUsoAlcoolLabels[
              pregnancyInfo?.frequenciaUsoAlcool ?? ''
            ] || 'Sem consumo'
          }
          fuma={pregnancyInfo?.fuma ? 'Sim' : 'Não'}
          gravidezPlanejada={pregnancyInfo?.gravidezPlanejada ? 'Sim' : 'Não'}
          grupoSanguineo={pregnancyInfo?.grupoSanguineo || ''}
          pesoAntesGestacao={pregnancyInfo?.pesoAntesGestacao || 0}
          quantidadeCigarrosDia={pregnancyInfo?.quantidadeCigarrosDia || 0}
          riscoGestacional={
            riscoGestacionalLabels[pregnancyInfo?.riscoGestacional ?? -1] ||
            'Não informado'
          }
          situacaoGestacional={
            situacaoGestacionalLabels[
              pregnancyInfo?.situacaoGestacional ?? ''
            ] || 'Sem dados'
          }
          usoDrogas={
            frequenciaUsoAlcoolLabels[
              pregnancyInfo?.frequenciaUsoAlcool ?? ''
            ] || 'Sem consumo'
          }
          vacinaHepatiteB={pregnancyInfo?.vacinaHepatiteB ? 'Sim' : 'Não'}
        />
      ) : (
        <></>
      )}

      <S.PregnanciesText>Acompanhamentos: </S.PregnanciesText>
      {followUpInfo.map((item, index) => (
        <FollowUpCard
          key={index}
          bloodPressure={item.pressaoArterial}
          date={new Date(item.dataAcompanhamento).toLocaleDateString()}
          gestationalAge={item.idadeGestacional}
          heartRate={item.batimentosCardiacosFeto}
          madeBy={item.realizadoPor}
          type={item.tipo}
          uterineHeight={item.alturaUterina}
          weight={item.pesoAtual}
        />
      ))}
    </S.Container>
  );
}
