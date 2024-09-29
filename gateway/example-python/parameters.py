from pydantic import BaseModel


class ParametersDTO(BaseModel):
    previous_weight: int
    gestational_risk: int
    schooling: int
    has_hypertension: bool
    has_diabetes: bool
    has_pelvic_sugery: bool
    has_urinary_infection: bool
    has_congenital_malformation: bool
    has_family_twinship: bool
    amount_gestation: int
    amount_abortion: int
    amount_deliveries: int
    amount_cesarean: int
    target: int
    age: int
    fist_prenatal: int
    time_between_pregnancies: int
