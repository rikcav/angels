import React from 'react';
import { Navigate } from 'react-router-dom';
import Cookies from 'js-cookie';

interface PrivateRoutesProps {
  children: React.ReactNode;
}

export const PrivateRoutes: React.FC<PrivateRoutesProps> = ({ children }) => {
  const token = Cookies.get('token');

  if (!token) {
    return <Navigate to="/" replace />;
  }

  return <>{children}</>;
};
