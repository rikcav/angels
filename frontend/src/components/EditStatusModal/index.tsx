import React from 'react';
import { Modal, Select, Button } from 'antd';
import * as S from "./styles";

const { Option } = Select;

interface EditStatusModalProps {
  open: boolean;
  currentStatus: string;
  onClose: () => void;
  onSave: (newStatus: string) => void;
}

const EditStatusModal: React.FC<EditStatusModalProps> = ({
  open,
  currentStatus,
  onClose,
  onSave,
}) => {
  const [newStatus, setNewStatus] = React.useState(currentStatus);

  const handleSave = () => {
    onSave(newStatus); 
    onClose(); 
  };

  return (
    <Modal
      title={<S.title>Editar Status da Gestação</S.title>}
      open={open}
      onCancel={onClose}
      footer={[
        <Button key="cancel" onClick={onClose}>
          Cancelar
        </Button>,
        <S.subButton
          key="save"
          type="primary"
          onClick={handleSave}
        >
          Salvar
        </S.subButton>,
      ]}
    >
      <S.Container>
        <Select
          value={newStatus}
          onChange={(value) => setNewStatus(value)}
          style={{ width: '100%' }}
        >
          <Option value="EM_ANDAMENTO">Em andamento</Option>
          <Option value="FINALIZADA_COM_SUCESSO">Finalizada com sucesso</Option>
          <Option value="FINALIZADA_DESFECHO_NEGATIVO">
            Finalizada com desfecho negativo
          </Option>
        </Select>
      </S.Container>
    </Modal>
  );
};

export default EditStatusModal;
