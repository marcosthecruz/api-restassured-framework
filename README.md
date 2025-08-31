# API Automation Framework

Framework de automação de testes para API RESTful de gerenciamento de usuários.

## 📋 Funcionalidades Testadas

- ✅ Criação de usuários
- ✅ Leitura de usuários (individual e lista)
- ✅ Atualização de usuários
- ✅ Exclusão de usuários
- ✅ Validação de campos obrigatórios
- ✅ Autenticação JWT
- ✅ Rate limiting

## 🛠️ Tecnologias Utilizadas

- **Java 11**
- **RestAssured** - Cliente HTTP para testes de API
- **JUnit 5** - Framework de testes
- **Maven** - Gerenciamento de dependências
- **Allure Report** - Relatórios de testes
- **GitHub Actions** - CI/CD Pipeline

## 📦 Pré-requisitos

- Java JDK 11 ou superior
- Maven 3.6 ou superior
- Git

## 🚀 Como Executar os Testes

# Executar todos os testes

mvn test

# Executar testes específicos

mvn test -Dtest=UserAPITest

# Executar com relatório Allure

mvn test allure:report

# Abrir relatório Allure

mvn allure:serve

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/api-automation-framework.git
cd api-automation-framework
```
