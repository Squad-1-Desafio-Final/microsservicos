from locust import HttpUser, task, between
import random
import string

# IDs de usuários válidos
IDS_USUARIOS_VALIDOS = [
    "2303a082-3d33-4199-8237-8b1ac57ea1dc",
    "0e536a04-55d8-48d8-8e1c-93374c6e8897",
    "e25a5728-82ac-4d9f-84dc-4ddb05196a85",
    "ec4067e4-c08e-4b93-a6df-f4dee8b2a4de",
    "92934a6d-b33c-44f8-8ede-83fa9883c98c",
    "37dba395-3c41-46a2-a657-a10edb0773b2"
]

# IDs de contas válidas previamente cadastradas (para testes de GET por ID)
IDS_CONTAS_EXISTENTES = [
    "b4094d40-28d2-4ae9-98d2-5327daca3fcc",
    "e3a97044-4ff6-4af2-b7f1-4a536a49ff78",
    "f793638a-972d-4b6a-a136-49e7325647d2",
    "d4eb9443-1bc9-461c-ac44-c7a9fac31515",
    "b8a7f153-f662-4bad-a27c-02f3b329c0e0",
    "d940008c-a456-46c2-bddc-e4baa84c22e0"
]

def gerar_numero_conta():
    return int(''.join(random.choices(string.digits, k=6)))

class ContaUser(HttpUser):
    host = "http://localhost:8080"  # ou a URL
    wait_time = between(1, 2)

    @task
    def criar_conta(self):
        payload = {
            "dataVencimento": "2025-06-10",
            "limite": round(random.uniform(1000, 5000), 2),
            "agencia": random.randint(1000, 9999),
            "numero": gerar_numero_conta(),
            "banco": random.randint(1, 10),
            "idUsuario": random.choice(IDS_USUARIOS_VALIDOS)  # agora dinâmico
        }

        with self.client.post("/conta", json=payload, catch_response=True) as response:
            if response.status_code == 201:
                response.success()
            elif response.status_code == 409:
                response.failure("409 - Conta já existe")
            elif response.status_code == 404:
                response.failure("404 - Usuário não encontrado")
            else:
                response.failure(f"{response.status_code} - {response.text}")

    @task
    def listar_todas_contas(self):
        with self.client.get("/conta", catch_response=True) as response:
            if response.status_code == 200:
                response.success()
            elif response.status_code == 204:
                response.failure("204 - Nenhuma conta encontrada")
            else:
                response.failure(f"{response.status_code} - {response.text}")

    @task
    def buscar_uma_conta(self):
        conta_id = random.choice(IDS_CONTAS_EXISTENTES)

        with self.client.get(f"/conta/{conta_id}", catch_response=True) as response:
            if response.status_code == 200:
                response.success()
            elif response.status_code == 404:
                response.failure("404 - Conta não encontrada")
            else:
                response.failure(f"{response.status_code} - {response.text}")
