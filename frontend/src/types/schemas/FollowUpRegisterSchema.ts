import { z } from 'zod';

export const PregnancyFollowUpSchema = z.object({
  dataAcompanhamento: z.coerce
    .date({ required_error: 'Informe a data' })
    .min(new Date('1900-01-01'), { message: 'Escolha uma data válida' })
    .max(new Date(), { message: 'Escolha uma data válida' }),
  pesoAtual: z
    .number({
      required_error: 'Informe o peso',
      invalid_type_error: 'Informe um peso válido'
    })
    .min(0, 'Informe o peso'),
  idadeGestacional: z
    .number({
      required_error: 'Informe a idade gestacional',
      invalid_type_error: 'Informe uma idade válida'
    })
    .min(1, 'Informe a idade gestacional'),
  pressaoArterial: z
    .string({
      required_error: 'Informe a pressão arterial',
      invalid_type_error: 'Informe uma pressão válida'
    })
    .min(2, 'Informe a pressão arterial'),
  alturaUterina: z.number().optional(),
  batimentosCardiacosFeto: z.number().optional(),
  tipo: z.enum(['PRENATAL_ROTINA', 'OCORRENCIA', 'VOLTA'], {
    required_error: 'Selecione um tipo'
  }),
  realizadoPor: z.string({ required_error: 'Selecione uma opção' })
});
