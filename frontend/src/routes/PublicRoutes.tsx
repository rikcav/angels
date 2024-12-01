import React from 'react';
import { Navigate } from 'react-router-dom';
import Cookies from 'js-cookie';

interface PublicRoutesProps {
  children: React.ReactNode;
}

export const PublicRoutes: React.FC<PublicRoutesProps> = ({ children }) => {
  const token = Cookies.get('token');

  if (token) {
    return <Navigate to="/dashboard" replace />;
  }

  return <>{children}</>;
};
