from locust import HttpUser, task, between
import uuid
import random

class UsuarioApiUser(HttpUser):
    wait_time = between(1, 2)

    # Guardar credenciais do último usuário criado
    ultimo_email = None
    ultima_senha = None

    @task(1)
    def cadastrar_usuario_e_endereco(self):
        url_usuario = "/usuario"

        random_str = str(uuid.uuid4())
        dominios = ["teste.com", "exemplo.com", "mail.com"]
        dominio = random.choice(dominios)
        email_aleatorio = f"joao.silva.{random_str}@{dominio}"

        cpf_aleatorio = ''.join(str(random.randint(0, 9)) for _ in range(11))

        dt_nasc = "1990-01-01"
        senha = "senha123"

        payload_usuario = {
            "nome": "João Silva",
            "login": email_aleatorio,
            "password": senha,
            "cpf": cpf_aleatorio,
            "telefone": "+5511999999999",
            "dtNasc": dt_nasc,
            "role": "admin"
        }

        response_usuario = self.client.post(url_usuario, json=payload_usuario)
        print(f"POST {url_usuario} - Status: {response_usuario.status_code}")
        print(f"Payload usuário: {payload_usuario}")
        print(f"Resposta usuário: {response_usuario.text}")

        if response_usuario.status_code in [200, 201]:
            try:
                data = response_usuario.json()
                if "id" in data:
                    self.ultimo_email = email_aleatorio
                    self.ultima_senha = senha
                    print(f"Usuário cadastrado com sucesso! ID: {data['id']}")
                else:
                    print("Resposta não contém ID do usuário.")
                    return
            except Exception as e:
                print(f"Erro ao parsear resposta JSON do usuário: {e}")
                return
        else:
            print("Falha ao cadastrar usuário, pulando criação de endereço.")
            return

        url_endereco = "/endereco"
        payload_endereco = {
            "logradouro": "Rua das Flores",
            "numero": 123,
            "complemento": "Apto 101",
            "bairro": "Centro",
            "cidade": "São Paulo",
            "estado": "SP",
            "cep": "12345-678",
            "idUsuario": data["id"]
        }

        response_endereco = self.client.post(url_endereco, json=payload_endereco)
        print(f"POST {url_endereco} - Status: {response_endereco.status_code}")
        print(f"Payload endereço: {payload_endereco}")
        print(f"Resposta endereço: {response_endereco.text}")

    @task(2)
    def login(self):
        url_login = "/usuario/login"
        if not self.ultimo_email or not self.ultima_senha:
            print("Nenhum usuário criado para login, pulando task de login.")
            return

        payload_login = {
            "login": self.ultimo_email,
            "password": self.ultima_senha
        }

        response_login = self.client.post(url_login, json=payload_login)
        print(f"POST {url_login} - Status: {response_login.status_code}")
        print(f"Payload login: {payload_login}")
        print(f"Resposta login: {response_login.text}")

        if response_login.status_code == 200:
            print("Login efetuado com sucesso.")
        elif response_login.status_code == 404:
            print("Login falhou: usuário não encontrado.")
        else:
            print(f"Resposta inesperada: {response_login.status_code}")
