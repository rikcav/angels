from fastapi import FastAPI
from py_eureka_client.eureka_client import EurekaClient

from parameters import ParametersDTO

app = FastAPI()

eureka_server = "http://localhost:8761/eureka"
app_name = "model-test-service"
instance_host = "127.0.0.1"
instance_port = 8000

client = EurekaClient(eureka_server=eureka_server, app_name=app_name, instance_host=instance_host,
                      instance_port=instance_port, status_page_url="/docs")


@app.on_event("startup")
async def startup_event():
    await client.start()


@app.get("/api/parameters")
async def parameters():
    return [
        "previous_weight", "gestational_risk", "schooling", "has_hypertension", "has_diabetes",
        "has_pelvic_sugery", "has_urinary_infection", "has_congenital_malformation",
        "has_family_twinship", "amount_gestation", "amount_abortion", "amount_deliveries",
        "amount_cesarean", "target", "age", "fist_prenatal", "time_between_pregnancies"
    ]


@app.post("/api/predict")
async def predict(parametersDTO: ParametersDTO):
    return {
        "prediction": 1,
        "probability": parametersDTO
    }


@app.on_event("shutdown")
async def shutdown_event():
    await client.stop()
