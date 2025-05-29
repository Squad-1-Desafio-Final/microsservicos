from locust import HttpUser, task, between
import json

class ProdutoUser(HttpUser):
    wait_time = between(1, 3)

    @task
    def criar_produto(self):
        payload = {
            "nome": "Produto Teste",
            "preco": 99.99,
            "descricao": "Teste de carga",
            "disponivel": 1,
            "quantidade": 10
        }
        headers = {"Content-Type": "application/json"}
        self.client.post("/produto/criar", data=json.dumps(payload), headers=headers)