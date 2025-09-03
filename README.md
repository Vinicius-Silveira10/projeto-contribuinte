# Sistema de Consulta de Contribuintes (Full-Stack)

Esta é uma aplicação web completa, desenvolvida com Spring Boot no backend e React no frontend. O sistema permite a consulta de informações de contribuintes a partir de uma API externa, registra todas as operações para fins de auditoria e garante a segurança dos dados e do acesso.

## Funcionalidades Principais 🚀

- **API RESTful:** Backend robusto construído com Spring Boot para servir os dados.
- **Consulta de Contribuintes:** Interface em React para buscar dados de contribuintes por CPF a partir de uma API externa.
- **Auditoria Completa:** Cada consulta realizada é salva em um banco de dados H2, registrando o CPF, o IP do solicitante e a data/hora.
- **Segurança com Spring Security:**
    - Painel de auditoria protegido por autenticação.
    - Configuração de CORS para comunicação segura com o frontend.
- **Criptografia de Dados:** CPFs são criptografados no banco de dados usando AES para proteger dados sensíveis.
- **Gerenciamento de Usuários:** Autenticação de administradores via banco de dados, com senhas armazenadas usando hash BCrypt.
- **Limpeza Automática de Dados:** Uma tarefa agendada (`@Scheduled`) remove periodicamente registros de contribuintes antigos, funcionando como um cache.
- **Estrutura Profissional:** Organizado em um monorepo com frontend e backend desacoplados.

## Tecnologias Utilizadas 🛠️

#### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Maven
- Banco de Dados H2

#### Frontend
- React
- JavaScript
- React Router
- Node.js / NPM

## Como Executar o Projeto ⚙️

Este projeto é um monorepo. O backend e o frontend devem ser executados em terminais separados.

#### Backend
1. Navegue até a pasta `backend`.
2. Abra o projeto em uma IDE como o IntelliJ IDEA.
3. Execute a classe principal `ApiRealApplication.java`.
4. O servidor estará rodando em `http://localhost:8081`.

#### Frontend
1. Navegue até a pasta `frontend`.
2. Execute `npm install` para instalar as dependências.
3. Execute `npm start` para iniciar o servidor de desenvolvimento.
4. Acesse `http://localhost:3000` (ou a porta indicada no terminal) no seu navegador.