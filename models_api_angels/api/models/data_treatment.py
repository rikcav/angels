from datetime import datetime

def calculate_age(mothers_birth_date, date_start_pregnancy):
    birth_date = datetime.fromisoformat(mothers_birth_date)
    date_start_pregnancy = datetime.fromisoformat(date_start_pregnancy)

    age = abs((int)((birth_date - date_start_pregnancy).days / 365))

    return age

def first_prenatal_weeks(pregnancy_start_date, first_prenatal_date):
    pregnancy_start_date = datetime.fromisoformat(pregnancy_start_date)
    first_prenatal_date = datetime.fromisoformat(first_prenatal_date)

    weeks_count = abs((int)((pregnancy_start_date - first_prenatal_date).days / 7))

    return weeks_count

def calculate_time_between_pregnancies(pregnancy_start_date, last_delivery_date):
    if last_delivery_date is None:
        return -1
    
    pregnancy_start_date = datetime.fromisoformat(pregnancy_start_date)
    last_delivery_date = datetime.fromisoformat(last_delivery_date)

    months_count = abs((int)((pregnancy_start_date - last_delivery_date).days / 30))

    if months_count > 12:
        months_count = 12
    
    return months_count

def data_treatment(data):
    treated_data = []
    age = calculate_age(data['mothers_birth_date'], data['date_start_pregnancy'])
    first_prenatal = first_prenatal_weeks(data['date_start_pregnancy'], data['date_first_prenatal'])
    time_between_pregnancies = calculate_time_between_pregnancies(data['date_start_pregnancy'], data['date_last_delivery'])

    treated_data.append(float(data['previous_weight']))
    treated_data.append(int(data['gestational_risk']))
    treated_data.append(int(data['schooling']))
    treated_data.append(int(data['has_hypertension']))
    treated_data.append(int(data['has_diabetes']))
    treated_data.append(int(data['has_pelvic_surgery']))
    treated_data.append(int(data['has_urinary_infection']))
    treated_data.append(int(data['has_congenital_malformation']))
    treated_data.append(int(data['has_family_twinship']))
    treated_data.append(int(data['amount_gestation']))
    treated_data.append(int(data['amount_abortion']))
    treated_data.append(int(data['amount_deliveries']))
    treated_data.append(int(data['amount_cesarean']))
    treated_data.append(age)
    treated_data.append(first_prenatal)
    treated_data.append(time_between_pregnancies)

    return treated_data
