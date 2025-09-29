# 📌 Projeto: Task Manager (Gerenciador de Tarefas)

## 📖 Descrição
Este é um projeto simples em **Spring Boot** para treinar conceitos básicos de criação de APIs REST.  
O sistema gerencia tarefas (to-do list), permitindo **criar, listar, atualizar e excluir** tarefas.

---

## 🔹 Requisitos
1. Criar uma API REST usando **Spring Boot**.
2. Cada tarefa deve ter:
    - **ID** (gerado automaticamente)
    - **Título** (String, obrigatório)
    - **Descrição** (String, opcional)
    - **Status** (Pendente, Em andamento, Concluída)
    - **Data de criação** (gerada automaticamente)
3. Endpoints obrigatórios:
    - `POST /tasks` → Criar uma nova tarefa
    - `GET /tasks` → Listar todas as tarefas
    - `GET /tasks/{id}` → Buscar tarefa por ID
    - `PUT /tasks/{id}` → Atualizar tarefa (título, descrição ou status)
    - `DELETE /tasks/{id}` → Excluir tarefa
4. Banco de dados: usar **H2 Database** (em memória, fácil para aprendizado).
5. Testar os endpoints com **Postman** ou **Insomnia**.

---

## 🔹 Diferenciais (opcionais)
- Implementar paginação em `GET /tasks`.
- Adicionar validações (exemplo: não permitir título vazio).
- Criar documentação com **Swagger**.
- Criar um front-end simples em React ou Angular.

---

## 🚀 Como executar o projeto
1. Clonar o repositório ou criar um projeto Spring Boot vazio.
2. Adicionar as dependências no `pom.xml`:
    - Spring Web
    - Spring Data JPA
    - H2 Database
3. Rodar a aplicação:
   ```bash
   mvn spring-boot:run
