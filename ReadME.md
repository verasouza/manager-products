# Desafio

### Objetivos

- [x] CRUD de produtos com API RESTful
- [x] Testes unitários e integração continua
- [x] Mensageria RabbitMQ
- [x] Integração com MySQL
- [ ] Rodar na AWS
- [x] Usar docker para subir a aplicação


**Iniciar aplicação**

``./build.sh``

**Como testar**

- CRUDs urls: http://localhost:8079/swagger-ui/index.html
- Continuos integration: commitar na branch develop
- Mensageria: cadastrar novo produto e verificar nos logs
  ``docker logs <container> | grep "MESSAGE_CONSUMER"``
- Testes unitários: localmente _**mvn clean install**_ ou no GitHub Actions branch develop



