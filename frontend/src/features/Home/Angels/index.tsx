import * as S from "./styles";
import { Button } from "../../../features/Home/Button";
import WhiteLogo from '../../../assets/angelsWhiteLogo.svg';
import { useState } from "react";
import { LoginModal } from "../../../components/LoginModal";
// import { useNavigate } from 'react-router-dom';

export const Angels = () => {
    // const navigate = useNavigate();
    const [activeLink, setActiveLink] = useState('HOME');
    const [open, setOpen] = useState(false);
    const [confirmLoading, setConfirmLoading] = useState(false);

    const showModal = () => {
        setOpen(true);
    };

    const handleOk = () => {
        setConfirmLoading(true);
        setTimeout(() => {
        setOpen(false);
        setConfirmLoading(false);
        }, 2000);
    };

    const handleCancel = () => {
        console.log('Clicked cancel button');
        setOpen(false);
    }; 
    
    // const dashboardScreen = () => {
    //     navigate('/dashboard');
    //   };

    return (
        <S.NoRightPadding>
            <S.AngelsWrapper id="home">
                <S.AngelsContent>
                    <S.AngelsContentWrapper>
                        <h1>An Intelligent Gestational Follow-up System</h1>
                        <p>"Se mudarmos o começo da história, mudamos a história toda." - Documentário "O Começo da Vida"</p>
                        <Button label="ACESSE O SISTEMA" buttonFunction={showModal} size="large" />
                    </S.AngelsContentWrapper>
                    <LoginModal
                    open={open}
                    handleCancel={handleCancel}
                    handleOk={handleOk}
                    confirmLoading={confirmLoading}
                    />
                </S.AngelsContent>
                <S.AngelsImage>
                    <img src={WhiteLogo} alt="Logo Branca angels" />
                </S.AngelsImage>
            </S.AngelsWrapper>
        </S.NoRightPadding>  
    )
}