import * as S from './styles';
import logo from '../../assets/angelsLogo.svg';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Button } from '../Button';
import { LoginModal } from '../LoginModal';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';

interface HeaderProps {}

export const Header: React.FC<HeaderProps> = () => {
  const [activeLink, setActiveLink] = useState('HOME');
  const navigate = useNavigate();
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const authToken = Cookies.get('token');

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
    setOpen(false);
  };

  const handleClick = (link: string) => {
    setActiveLink(link);
  };

  const systemAccess = () => {
    if (authToken) {
      navigate('/dashboard');
    } else {
      showModal();
    }
  };

  useEffect(() => {
    setActiveLink('HOME');
  }, []);

  const scrollToElement = (id: string) => {
    const element = document.getElementById(id);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <S.Container id="home">
      <div>
        <S.Navigation>
          <S.Nav>
            <S.Logo src={logo} alt="Angels logo" />
            <S.Ul>
              <S.Li>
                <Link
                  to="#home"
                  onClick={() => {
                    handleClick('HOME');
                    scrollToElement('home');
                  }}
                  className={activeLink === 'HOME' ? 'active' : ''}
                >
                  HOME
                </Link>
              </S.Li>
              <S.Li>
                <Link
                  to="#about"
                  onClick={() => {
                    handleClick('SOBRE');
                    scrollToElement('about');
                  }}
                  className={activeLink === 'SOBRE' ? 'active' : ''}
                >
                  SOBRE
                </Link>
              </S.Li>
              <S.Li>
                <Link
                  to="#data"
                  onClick={() => {
                    handleClick('INFORMAÇÕES');
                    scrollToElement('data');
                  }}
                  className={activeLink === 'INFORMAÇÕES' ? 'active' : ''}
                >
                  INFORMAÇÕES
                </Link>
              </S.Li>
              {/* <S.Li>
                <Link
                  to="#team"
                  onClick={() => handleClick('EQUIPE')}
                  className={activeLink === 'EQUIPE' ? 'active' : ''}
                >
                  EQUIPE
                </Link>
              </S.Li> */}
              <S.Li>
                <Link
                  to="#partners"
                  onClick={() => {
                    handleClick('PARCEIROS');
                    scrollToElement('partners');
                  }}
                  className={activeLink === 'PARCEIROS' ? 'active' : ''}
                >
                  PARCEIROS
                </Link>
              </S.Li>
              <S.Li>
                <Link
                  to="#footer"
                  onClick={() => {
                    handleClick('LINKS');
                    scrollToElement('footer');
                  }}
                  className={activeLink === 'LINKS' ? 'active' : ''}
                >
                  LINKS
                </Link>
              </S.Li>
            </S.Ul>
            <Button
              label="ACESSE O SISTEMA"
              buttonFunction={systemAccess}
              size="large"
            />
          </S.Nav>
        </S.Navigation>
        <LoginModal
          open={open}
          handleCancel={handleCancel}
          handleOk={handleOk}
          confirmLoading={confirmLoading}
        />
      </div>
    </S.Container>
  );
};
