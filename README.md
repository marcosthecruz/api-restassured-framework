🚀 API Automation Framework
Framework completo de automação de testes para API RESTful de gerenciamento de usuários, com integração CI/CD via GitHub Actions e relatórios Allure.

https://github.com/seu-usuario/api-automation-framework/actions/workflows/ci.yml/badge.svg
https://img.shields.io/badge/Java-11%252B-blue
https://img.shields.io/badge/Maven-3.6%252B-orange
https://img.shields.io/badge/RestAssured-5.3.0-green

📋 Funcionalidades Testadas
✅ CRUD Completo: Create, Read, Update, Delete de usuários
✅ Autenticação JWT: Fluxo completo de autenticação
✅ Validações: Campos obrigatórios e esquemas de resposta
✅ Rate Limiting: Testes de limitação de taxa (100 requests/minuto)
✅ Testes Positivos e Negativos: Validações de sucesso e erro

🛠️ Tecnologias Utilizadas
Java 11 - Linguagem de programação
RestAssured 5.3.0 - Cliente HTTP para testes de API
JUnit 5 - Framework de testes
Maven - Gerenciamento de dependências
Allure Report - Relatórios de testes interativos
GitHub Actions - CI/CD Pipeline automatizada
JSON & Jackson - Manipulação de dados

📦 Estrutura do Projeto
api-automation-framework/
├── .github/workflows/
│ └── ci.yml # Pipeline CI/CD
├── src/
│ ├── main/java/com/company/
│ │ ├── models/
│ │ │ └── User.java # Modelo de dados do usuário
│ │ ├── services/
│ │ │ └── UserService.java # Serviços da API
│ │ └── utils/
│ │ └── TestConfig.java # Configurações e autenticação
│ └── test/java/com/company/api/
│ ├── UserAPITest.java # Testes principais da API
│ └── PerformanceTest.java # Testes de performance
├── .vscode/
│ └── settings.json # Configurações do VS Code
├── src/test/resources/
│ └── config.properties # Configurações de ambiente
├── pom.xml # Dependências Maven
└── README.md # Documentação

⚙️ Pré-requisitos
Java JDK 11 ou superior
Maven 3.6 ou superior
Git
VS Code (recomendado) com extensões:
Prettier - Code formatter
Allure Report Preview

🚀 Como Executar os Testes

1. Clone o repositório
   git clone https://github.com/seu-usuario/api-automation-framework.git
   cd api-automation-framework

2. Execute os testes com relatório

# Executa testes e gera relatório Allure

mvn clean test allure:report

# Abre o relatório no navegador

mvn allure:serve

# Executar apenas testes específicos

mvn test -Dtest=UserAPITest

# Executar com logging detalhado

mvn test -Dtest=UserAPITest -X

# Gerar relatório apenas

mvn allure:report
📊 Relatórios Allure
Visualização Local

# Gera e abre o relatório

mvn allure:serve

# Ou abra manualmente após gerar:

open target/site/allure-maven-plugin/index.html
Estrutura do Relatório
Dashboard: Visão geral dos testes

Categories: Agrupamento por tipo de teste
Suites: Organização por classes de teste
Graphs: Métricas e estatísticas
Timeline: Linha do tempo de execução

🔧 Configuração
Arquivo config.properties

# API Configuration

base.url=https://serverest.dev
auth.endpoint=/login
users.endpoint=/usuarios

# Test Data

admin.email=fulano@qa.com
admin.password=teste
Variáveis de Ambiente (CI)

Configure estas secrets no GitHub:
ADMIN_EMAIL: Email do usuário administrador
ADMIN_PASSWORD: Senha do usuário administrador

🏗️ Pipeline CI/CD
GitHub Actions Workflow

O projeto inclui pipeline automatizada que:
✅ Executa testes em push/pull requests
✅ Gera relatórios Allure
✅ Disponibiliza artefatos para download
✅ Valida qualidade do código

Artefatos Gerados
test-results: Relatórios XML do Maven Surefire
allure-report: Relatório HTML completo do Allure

🧪 Cobertura de Testes
Endpoints Testados
GET /usuarios - Listar todos os usuários
POST /usuarios - Criar novo usuário
GET /usuarios/{id} - Buscar usuário específico
PUT /usuarios/{id} - Atualizar usuário
DELETE /usuarios/{id} - Excluir usuário

Casos de Teste Implementados
@Epic("User Management API")
@Feature("CRUD Operations for Users")
public class UserAPITest {
// 7 testes cobrindo:
// - Criação de usuário (sucesso e erro)
// - Leitura de usuários (individual e lista)
// - Atualização de informações
// - Exclusão de usuários
// - Validações de campos obrigatórios
}

🐛 Solução de Problemas Comuns
Erro de Autenticação

# Verifique as credenciais no config.properties

# Ou configure as secrets no GitHub Actions

Relatório Allure não gerado

# Use allure:report em vez de allure:serve para CI

mvn allure:report

Erro no GitHub Actions

# Certifique-se de usar actions/upload-artifact@v4

uses: actions/upload-artifact@v4

📝 Padrões de Commit
feat: Nova funcionalidade de teste
fix: Correção de bug nos testes
docs: Atualização de documentação
chore: Configurações e dependências

🔄 Fluxo de Desenvolvimento
Desenvolvimento Local
mvn test allure:serve
Commit e Push
git add .
git commit -m "feat: add new test cases"
git push origin main

Verificação CI
Acesse GitHub → Actions
Verifique status da pipeline
Download dos artefatos
Análise de Resultados
Analise relatórios Allure
Verifique cobertura de testes
Ajuste testes se necessário

📈 Próximas Melhorias
Testes de carga e performance
Integração com Slack notifications
Dashboard de métricas de qualidade
Testes em múltiplos ambientes
Paralelização de testes

🤝 Contribuindo
Fork o projeto
Crie uma branch para sua feature
Commit suas mudanças
Push para a branch
Abra um Pull Request

📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para detalhes.

🆘 Suporte
Se encontrar problemas:
Verifique a aba Issues do GitHub
Consulte os logs do GitHub Actions
Execute localmente com mvn test -X
