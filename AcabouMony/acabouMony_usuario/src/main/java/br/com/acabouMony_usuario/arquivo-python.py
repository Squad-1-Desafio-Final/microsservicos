from locust import HttpUser, task, between
import uuid

class UsuarioApiUser(HttpUser):
    wait_time = between(1, 2)

    @task(1)
    def cadastrar_usuario(self):
        url = "/usuario"
        payload = {
            "nome": "João Silva",
            "login": f"joao.silva{uuid.uuid4()}",
            "senha": "senha123",
            "cpf": "123.456.789-00",
            "telefone": "+5511999999999",
            "dtNasc": "1990-01-01",
            "role": "USER"
        }

        response = self.client.post(url, json=payload)
        print(f"POST {url} - Status: {response.status_code}")

class EnderecoApiUser(HttpUser):
    wait_time = between(1, 2)

    @task(2)
    def cadastrar_endereco(self):
        url = "/endereco"
        {
            "logradouro": "Rua das Flores",
            "numero": 123,
            "complemento": "Apto 101",
            "bairro": "Centro",
            "cidade": "São Paulo",
            "estado": "SP",
            "cep": "12345-678",
            "idUsuario": "b2a8e2f4-3d3b-4a5b-9d9e-1f3c4e5d6a7b"
        }
        response = self.client.post(url, json=payload)
        print(f"POST {url} - Status: {response.status_code}")

