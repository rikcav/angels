export interface IAInterface {
  previous_weight?: number;
  gestational_risk?: number;
  schooling?: number;
  has_hypertension: boolean;
  has_diabetes: boolean;
  has_pelvic_sugery: boolean;
  has_urinary_infection: boolean;
  has_congenital_malformation: boolean;
  has_family_twinship: boolean;
  amount_gestation: number;
  amount_abortion: number;
  amount_deliveries: number;
  amount_cesarean: number;
  target: number;
  age: number;
  fist_prenatal: number;
  time_between_pregnancies: number;
}

export interface responseIA {
  risk?: boolean;
}
