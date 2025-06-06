from locust import HttpUser, task, between
import json

import uuid

class TransacaoUser(HttpUser):
    wait_time = between(1, 3)  # Tempo entre requisições

    # Define a URL base como localhost:8080/api
    host = "http://localhost:8080/api"

    # Defina o token aqui
    bearer_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW9AZ21haWwuY29tIiwiZXhwIjoxNzQ5MjIzNDMyfQ.ZuOvck5Nq6Bn46292tmcbq7wKEtvbiR1oH4YiFgs-C4"

    def get_headers(self):
        return {
            "Content-Type": "application/json",
            "Authorization": f"Bearer {self.bearer_token}"
        }

    @task
    def post_cadastro_transacao(self):
        payload = {
            "tipo": "DEBITO",  # ou "DEBITO"
            "cartao": "403db729-8c78-489b-844b-57a817a52549",
            "destinatario": "7d66c04d-f5b9-406a-bcf7-db6b065ce7a0",
            "pedido": "87009c92-c596-4b9f-8d4a-e432392472d4"
        }

        headers = self.get_headers()
        self.client.post("/transacao", data=json.dumps(payload), headers=headers)

