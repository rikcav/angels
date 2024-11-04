export interface IAInterface {
  previous_weight?: String;
  gestational_risk?: String;
  schooling?: String;
  has_hypertension: String;
  has_diabetes: String;
  has_pelvic_surgery: String;
  has_urinary_infection: String;
  has_congenital_malformation: String;
  has_family_twinship: String;
  amount_gestation: String;
  amount_abortion: String;
  amount_deliveries: String;
  amount_cesarean: String;
  mothers_birth_date: String;
  date_start_pregnancy: String;
  date_first_prenatal: String;
  date_last_delivery: String;
}

export interface responseIA {
  risk?: String;
}
