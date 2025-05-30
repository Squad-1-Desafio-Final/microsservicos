from locust import HttpUser, task, between
import json
import uuid

class ProdutoUser(HttpUser):
    wait_time = between(1, 3)

    # Define a URL base como localhost:8081
    host = "http://localhost:8081"

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

    @task
    def criar_pedido(self):
        # Gerar UUIDs fictícios para simular a requisição
        usuario_id = "7ef10a5c-7423-4660-83be-59eb69cb3124"
        produto_ids = ["478c61e5-d3ef-4be7-a9fd-b659632ed33f"]  # Lista com 3 UUIDs

        payload = {
            "usuario": usuario_id,
            "produtos": produto_ids
        }
        headers = {"Content-Type": "application/json"}
        self.client.post("/pedido/criar", data=json.dumps(payload), headers=headers)
