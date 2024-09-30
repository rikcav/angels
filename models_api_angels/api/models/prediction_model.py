import pickle

def predict_test(X_test):
    with open('api/models/modelo_xgboost.pickle', 'rb') as f:
        data = pickle.load(f)

    modelo = data['modelo']

    y_pred = modelo.predict(X_test)
    y_proba = modelo.predict_proba(X_test)
    result = {
            'prediction': int(y_pred[0]), #predição da classe alvo
            'probability': {
                '0': float(y_proba[0][0]), #predição da probabilidade de ser 0
                '1': float(y_proba[0][1]) #predição da probabilidade de ser 1 #TALVEZ É PORCENTAGEM
            }
        }
    return result