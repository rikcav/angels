import { useContext } from 'react';
import { PregnancyInterface } from '../../types/interfaces/PregnanciesType';
import { DataContext } from './DataProvider';

interface DataContextType {
  pregnanciesList: Array<PregnancyInterface>;
  reloadPag: () => void;
}

export function useData(): DataContextType {
  const context = useContext(DataContext);
  if (!context) {
    throw new Error('useData must be used within an DataProvider');
  }
  return context;
}
