from locust import HttpUser, task, between
import uuid

class MyUser(HttpUser):
    wait_time = between(1, 3)  # Tempo entre requisições

    # Define a URL base como localhost:8083
    host = "http://localhost:8083"

    @task
    def post_cadastro_transacao(self):
        # Exemplo de dados aleatórios (você pode usar UUIDs reais se necessário)
        payload = {
            "tipo": "CREDITO",  # ou "DEBITO", dependendo da enumeração
            "cartao": "2a778538-d8b4-45dc-b2ee-648c077830ad",
            "usuario": "7ef10a5c-7423-4660-83be-59eb69cb3124",
            "pedido": "1a400dea-a4f1-4249-ad2c-11d2488894b6"
        }

        headers = {
            "Content-Type": "application/json"
        }

        self.client.post("/transacao", json=payload, headers=headers)
