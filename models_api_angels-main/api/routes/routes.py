from flask import jsonify, request
from api import app
from api.models.prediction_model import predict_test
from api.models.data_validation import data_validation
from api.models.data_treatment import data_treatment
from datetime import datetime

#endpoint de teste
@app.route('/api/')
def index():
    return "Hello, World!", 200

#lista de parametros do modelo
parameters = ['previous_weight', 'gestational_risk', 'schooling', 'has_hypertension', 'has_diabetes', 'has_pelvic_surgery','has_urinary_infection', 'has_congenital_malformation', 
    'has_family_twinship', 'amount_gestation', 'amount_abortion', 'amount_deliveries','amount_cesarean', 'mothers_birth_date', 'date_start_pregnancy', 'date_first_prenatal', 'date_last_delivery']

#endpoint de predição
@app.route('/api/predict', methods=['POST'])
def predict():
    try:
        #recebe os dados do modelo
        data = request.json
        #colocar os parametros na ordem correta para o modelo de acordo com a lista presente em used_columns
        
        # Validar os dados
        data_is_invalid = data_validation(data) is not True
        if data_is_invalid:
            validation_error_number = data_validation(data)
            result = {'error_number':validation_error_number}
            return result, 422
        
        # Tratar os dados
        treated_data = data_treatment(data)
        # Transformar os dados em um DataFrame
        X_test = [treated_data]
        print(X_test)
        # Carregar o modelo
        prediction = predict_test(X_test)
        # Retornar a predição
        return jsonify(prediction), 200
    except Exception as e:
        return jsonify({'error': str(e)}), 500


    

#endpoint que lista os parâmetros do modelo para o getway
@app.route('/api/parameters', methods=['GET'])
def model_parameters():
    try:
        return parameters, 200
    except Exception as e:
        return jsonify({'error': str(e)}), 500