# **Calorie Tracker App**

## **Introdução**

Aplicação para registro de alimentos, permitindo que usuários adicionem refeições num diário e acompanhem sua ingestão de calorias e macronutrientes ao longo do tempo. 
O projeto foi desenvolvido com Angular para o frontend e Spring Boot para o backend, e PostgreSQL como banco de dados. 
Base de dados de alimentos utilizada: [www.ars.usda.gov/database](https://www.ars.usda.gov/northeast-area/beltsville-md-bhnrc/beltsville-human-nutrition-research-center/methods-and-application-of-food-composition-laboratory/mafcl-site-pages/sr11-sr28/)

## **Features**

* Registro e login de usuários com autenticação JWT
* Encriptação de senhas com BCrypt
* API REST com endpoints para adicionar, editar e remover refeições e seus itens.
* Cálculo automático de calories e macronutrientes consumidos por refeição e por dia.
* Frontend Angular para consumo da API REST.

![Screenshot from 2024-10-28 19-10-15](https://github.com/user-attachments/assets/b919c978-341f-41e5-87a1-69e07d72a892)

## **Instalação**

Para instalar o backend, seguir os passos(é necessário JDK 21+ e Maven 3+):

1. Clonar repositório: **`git clone https://github.com/arthurbf2/CalorieTrackerApp.git`**
2. Navegar para o diretório do projeto: **`cd CalorieTrackerApp`**
3. Adicionar database 'calorie-tracker-app' no postgres, atualizar dados em `src/main/resources/application.properties`.
4. Transferir dados da base de dados baixada à db do projeto:

   4.1 **`cd scripts`**
   
   4.2 **`python3 -m venv venv`** e **`source venv/bin/activate`**
   
   4.3 **`pip install -r requirements.txt`**
   
   4.4 Atualizar credenciais da sua db em import_database.py e **`python import_database.py`**
   
5. Instalar dependências: **`mvn clean install`**
6. Rodar projeto: **`mvn spring-boot:run`**

Instalação do frontend(é necessário Angular 17):

1. **`cd src/frontend`**
2. **`npm install`**
3. **`ng serve`**

A aplicação ficará disponível em http://localhost:4200.


