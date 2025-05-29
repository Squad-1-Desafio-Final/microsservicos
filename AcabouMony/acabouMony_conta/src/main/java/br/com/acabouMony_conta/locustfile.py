from locust import HttpUser, task, between
import random
import string

def gerar_numero_conta():
    return ''.join(random.choices(string.digits, k=6))

def gerar_id_usuario():
    return random.randint(1, 1000000)

class ContaUser(HttpUser):
    wait_time = between(1, 3)  # tempo entre tarefas

    @task
    def criar_conta(self):
        numero_conta = gerar_numero_conta()
        id_usuario = gerar_id_usuario()

        payload = {
            "numero": numero_conta,
            "idUsuario": id_usuario
        }

        with self.client.post("/conta", json=payload, catch_response=True) as response:
            if response.status_code == 201:
                response.success()
            elif response.status_code == 409:
                response.failure("Conta já existente (409)")
            else:
                response.failure(f"Erro inesperado: {response.status_code}")
