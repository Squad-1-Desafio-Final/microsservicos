from locust import HttpUser, task, between
import random
import string
import psycopg2

def pegar_ids_usuarios():
    conn = None
    ids = []
    try:
        conn = psycopg2.connect(
            host="localhost",
            database="acabou_mony",
            user="postgres",
            password="01082011Dudu@"
        )
        cursor = conn.cursor()
        cursor.execute("SELECT id FROM usuario")  # sua query para pegar os IDs
        linhas = cursor.fetchall()

        # linhas é uma lista de tuplas, cada tupla tem um elemento (id)
        ids = [linha[0] for linha in linhas]

    except Exception as e:
        print(f"Erro ao buscar IDs: {e}")
    finally:
        if cursor:
            cursor.close()
        if conn:
            conn.close()
    return ids

# Teste rápido para ver o resultado
if __name__ == "__main__":
    print(pegar_ids_usuarios())

# IDs de usuários válidos
IDS_USUARIOS_VALIDOS = pegar_ids_usuarios()
#     [
#     "c9478ba7-3ea5-4650-b6d6-e3e64dcd62de",
#     "3da7aafd-3893-4fa5-a373-b7bbd1d79376",
#     "3738d693-1bf4-40cc-b368-8ef49651fa26",
#     "bc19c94d-a921-467e-a7b0-28db4a2935f4",
#     "8e9912e0-9390-4714-b0e2-0b7dd141a409",
#     "b94f42d8-66ea-4ead-bb15-94ba9d8a0609",
#     "7853c385-5ed6-449c-ba5a-51824e3ec3ab",
#     "4716a2a5-015c-4d96-853a-ac7ade74234c",
#     "181be513-5e1f-4321-a81f-3b1139a45e46",
#     "54b0b3d8-4e40-4135-b15d-6b79490303f7",
#     "59411249-772d-4fad-9168-4c99a75116b9",
#     "ef60f832-5da6-43a7-9d38-b380166a653f",
#     "c8e26d82-870d-46af-94e8-53483372427e",
#     "8a441c4d-6c99-4690-9595-0ae1dfef6292",
#     "a68cd68d-96d8-4682-b0cb-996daf0845cc",
#     "b9969291-d9e2-4549-99a8-1fa803edd818",
#     "13232e14-3cbf-408b-8385-d729bf16c39d",
#     "67f2fda1-345f-4f3b-b456-dc20136e6da9",
#     "05b02eb3-fdbc-4fdd-a632-abfb3ba0ea52",
#     "cb6e1fde-88e5-472a-a4f1-d6441b12c51a",
#     "9b1b92c0-aeb0-4a1b-9764-f29a1a1f32d0",
#     "67372cb7-adf3-44b5-b678-18cd4f9bc2e3",
#     "6746e620-0eee-44bb-9a5c-7679c60cae6b",
#     "c3158fa8-2b87-4c6f-9d4a-df0e0814bd07",
#     "dca16a4b-67cd-4606-b290-dc6cc60e1d62",
#     "2984b763-2aa7-4fee-93cf-70bebd4a9458"
# ]

# IDs de contas válidas previamente cadastradas (para testes de GET por ID)
IDS_CONTAS_EXISTENTES = [
    "57096cff-05b6-4152-abb7-55eb22ba72fc"
]


def gerar_numero_conta():
    return int(''.join(random.choices(string.digits, k=6)))


class ContaUser(HttpUser):
    host = "http://localhost:8080/api"  # ou localhost:8080 conforme seu path

    wait_time = between(1, 2)

    def on_start(self):
        # Simulando pegar o token (substitua pelo seu real)
        self.token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImVkdWFyZG9LMUBnbWFpbC5jb20iLCJleHAiOjE3NDk0ODA3NTB9.dzaVaz_Hvry18nrODNLBWCTXwFKhCbizTuDxAMT5pLQ"  # seu JWT válido

        # Header comum para todas as requisições
        self.headers = {
            "Authorization": f"Bearer {self.token}",
            "Content-Type": "application/json"
        }

    @task
    def criar_conta(self):
        payload = {
            "dataVencimento": "2025-06-10",
            "limite": round(random.uniform(1000, 5000), 2),
            "agencia": random.randint(1000, 9999),
            "numero": gerar_numero_conta(),
            "banco": random.randint(1, 10),
            "idUsuario": random.choice(IDS_USUARIOS_VALIDOS)
        }
        with self.client.post("/conta", json=payload, headers=self.headers, catch_response=True) as response:
            if response.status_code == 201:
                response.success()
            else:
                response.failure(f"{response.status_code} - {response.text}")

    @task
    def listar_todas_contas(self):
        with self.client.get("/conta", headers=self.headers, catch_response=True) as response:
            if response.status_code == 200:
                response.success()
            else:
                response.failure(f"{response.status_code} - {response.text}")

    @task
    def buscar_uma_conta(self):
        conta_id = random.choice(IDS_CONTAS_EXISTENTES)
        with self.client.get(f"/conta/{conta_id}", headers=self.headers, catch_response=True) as response:
            if response.status_code == 200:
                response.success()
            else:
                response.failure(f"{response.status_code} - {response.text}")