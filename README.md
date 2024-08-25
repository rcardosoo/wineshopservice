# wineshop-service
Este projeto é uma aplicação para gerenciar informações relacionadas a uma loja de vinhos.

### Requisitos
É necessário que o **Java 17** está instalado em sua máquina.

### Execução do Projeto
Para rodar o projeto, você pode usar o Gradle Wrapper fornecido. Abra um terminal e execute o seguinte comando:

```bash
./gradlew bootRun
```
Este comando irá iniciar a aplicação.

### Swagger
Para facilitar a utilização e teste dos endpoints, a aplicação expõe uma interface Swagger. Após iniciar a aplicação, você pode acessar a interface Swagger no seguinte URL:

http://localhost:8080/swagger-ui/index.html#/

### Observações
* O serviço utiliza o banco de dados em memória H2
* Cada vez que o serviço for iniciado os dados do banco de dados em memória irão ser deletados, as api's fornecidas como fonte de dados serão consumidas e os dados preenchidos novamente na base para testes.
