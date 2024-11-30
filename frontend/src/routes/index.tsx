import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from '../pages/Home';
import { PregnantRegister } from '../pages/PregnantRegister';
import { PregnancyFollowUp } from '../pages/PregnancyFollowUp';
import PregnantInfo from '../pages/PregnantInfo';
import { Dashboard } from '../pages/DashBoard/Index';
import PregnancyRegister from '../pages/PregnancyRegister';
import Pregnancies from '../pages/Pregnancies';
import PregnancyInfo from '../pages/PregnancyInfo';
import { ProfessionalRegistration } from '../pages/ProfessionalRegister';
import { PrivateRoutes } from './PrivateRoutes';
import { PublicRoutes } from './PublicRoutes';

export const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/pregnantRegister/:id?"
          element={
            <PrivateRoutes>
              <PregnantRegister />
            </PrivateRoutes>
          }
        />
        <Route
          path="/pregnancyFollowUp/:gestacaoId"
          element={
            <PrivateRoutes>
              <PregnancyFollowUp />
            </PrivateRoutes>
          }
        />
        <Route
          path="/pregnancyRegister/:id"
          element={
            <PrivateRoutes>
              <PregnancyRegister />
            </PrivateRoutes>
          }
        />
        <Route
          path="/pregnantInfo"
          element={
            <PrivateRoutes>
              <PregnantInfo />
            </PrivateRoutes>
          }
        />
        <Route
          path="/dashboard"
          element={
            <PrivateRoutes>
              <Dashboard />
            </PrivateRoutes>
          }
        />
        <Route path="/pregnancies/:id" element={<Pregnancies />} />
        <Route
          path="/pregnancyInfo/:pregnancyId/:pregnantId"
          element={
            <PrivateRoutes>
              <PregnancyInfo />
            </PrivateRoutes>
          }
        />
        <Route
          path="/professionalRegister"
          element={
            <PublicRoutes>
              <ProfessionalRegistration />
            </PublicRoutes>
          }
        />
      </Routes>
    </BrowserRouter>
  );
};
