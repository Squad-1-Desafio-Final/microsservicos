from locust import HttpUser, task, between
import uuid

# Variável global para armazenar o último idUsuario
ultimo_id_usuario = None

class UsuarioApiUser(HttpUser):
    wait_time = between(1, 2)

    @task(1)
    def cadastrar_usuario(self):
        global ultimo_id_usuario
        url = "/usuario"
        payload = {
            "nome": "João Silva",
            "login": f"joao.silva{uuid.uuid4()}",
            "senha": "senha123",
            "cpf": "123.456.789-00",
            "telefone": "+5511999999999",
            "dtNasc": "1990-01-01",
            "role": "admin"
        }

        response = self.client.post(url, json=payload)
        print(f"POST {url} - Status: {response.status_code}")
        print(f"Resposta bruta: {response.text}")  # mostra o conteúdo que chegou

        if response.status_code in [200, 201]:
            try:
                data = response.json()  # Esperado: dicionário com dados do usuário

                if isinstance(data, dict) and "id" in data:
                    ultimo_id_usuario = data["id"]
                    print(f"Usuário cadastrado com sucesso! ID: {ultimo_id_usuario}")
                else:
                    print("Resposta não contém dados esperados do usuário:", data)

            except ValueError:
                print("Resposta não é JSON válido.")
        else:
            try:
                error_data = response.json()
                if isinstance(error_data, dict) and "error" in error_data:
                    print(f"Erro ao cadastrar usuário: {error_data['error']}")
                else:
                    print(f"Erro desconhecido: {error_data}")
            except ValueError:
                print(f"Erro ao cadastrar usuário: {response.text}")


class EnderecoApiUser(HttpUser):
    wait_time = between(1, 2)

    @task(2)
    def cadastrar_endereco(self):
        global ultimo_id_usuario
        url = "/endereco"

        if not ultimo_id_usuario:
            print("Nenhum usuário cadastrado ainda.")
            return

        payload = {
            "logradouro": "Rua das Flores",
            "numero": 123,
            "complemento": "Apto 101",
            "bairro": "Centro",
            "cidade": "São Paulo",
            "estado": "SP",
            "cep": "12345-678",
            "idUsuario": ultimo_id_usuario
        }
        response = self.client.post(url, json=payload)
        print(f"POST {url} - Status: {response.status_code}")
        print(f"Resposta: {response.text}")
