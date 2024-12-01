import { ReactNode, createContext, useEffect, useState } from 'react';
import { PregnancyInterface } from '../../types/interfaces/PregnanciesType';
import { GetPregnancies } from '../../services/PregnancyServices';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

interface DataContextType {
  pregnanciesList: Array<PregnancyInterface>;
  reloadPag: () => void;
}

interface DataProviderProps {
  children: ReactNode;
}

export const DataContext = createContext<DataContextType | undefined>(
  undefined
);

export function DataProvider({ children }: DataProviderProps) {
  const [pregnanciesList, setPregnantList] = useState<
    Array<PregnancyInterface>
  >([]);
  const [reload, setReload] = useState<number>(0);
  const authToken = Cookies.get('token');

  const decodedToken = jwtDecode(authToken || '');

  useEffect(() => {
    const getAllPregnancies = async () => {
      const response = await GetPregnancies(
        decodedToken.sub || '',
        authToken || ''
      );
      if (response?.status == 200) {
        setPregnantList(response.data);
      }
    };

    if (authToken) {
      getAllPregnancies();
    }
  }, [reload]);

  function reloadPag() {
    setReload((prev) => prev + 1);
  }

  return (
    <DataContext.Provider value={{ pregnanciesList, reloadPag }}>
      {children}
    </DataContext.Provider>
  );
}
