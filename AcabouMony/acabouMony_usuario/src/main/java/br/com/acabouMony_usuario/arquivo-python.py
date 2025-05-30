from locust import HttpUser, task, between
import uuid

# Variável global para armazenar o último idUsuario e o login
ultimo_id_usuario = None
ultimo_login = None  # vamos precisar do login para o teste de login


class UsuarioApiUser(HttpUser):
    wait_time = between(1, 2)

    @task(1)
    def cadastrar_usuario(self):
        global ultimo_id_usuario, ultimo_login

        url = "/usuario"
        login_gerado = f"joao.silva{uuid.uuid4()}"
        payload = {
            "nome": "João Silva",
            "login": login_gerado,
            "senha": "senha123",
            "cpf": "123.456.789-00",
            "telefone": "+5511999999999",
            "dtNasc": "1990-01-01",
            "role": "admin"
        }

        response = self.client.post(url, json=payload)
        print(f"POST {url} - Status: {response.status_code}")
        print(f"Resposta bruta: {response.text}")

        if response.status_code in [200, 201]:
            try:
                data = response.json()
                if isinstance(data, dict) and "id" in data:
                    ultimo_id_usuario = data["id"]
                    ultimo_login = login_gerado
                    print(f"Usuário cadastrado! ID: {ultimo_id_usuario} | Login: {ultimo_login}")
                else:
                    print("Resposta inesperada ao cadastrar usuário:", data)
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

class LoginApiUser(HttpUser):
    wait_time = between(1, 2)

    @task(3)
    def login(self):
        global ultimo_login

        url = "/login"

        if not ultimo_login:
            print("Nenhum usuário com login válido cadastrado ainda.")
            return

        payload = {
            "login": ultimo_login,
            "password": "senha123"  # mesma senha usada no cadastro
        }

        response = self.client.post(url, json=payload)
        print(f"POST {url} - Status: {response.status_code}")

        if response.status_code == 200:
            print(f"Login bem-sucedido! Token: {response.text}")
        elif response.status_code == 404:
            print("Usuário não encontrado.")
        elif response.status_code == 401:
            print("Credenciais inválidas.")
        else:
            print(f"Erro ao fazer login: {response.status_code} - {response.text}")
