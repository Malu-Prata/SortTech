# SortTech - Sorteador de Estudos em Tecnologia

[![Java](https://img.shields.io/badge/Java-11%2B-orange.svg)](https://www.oracle.com/java/)
[![JavaScript](https://img.shields.io/badge/JavaScript-ES6%2B-yellow.svg)](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Typography: Forum](https://img.shields.io/badge/Typography-Forum-6C0820.svg)](https://fonts.google.com/specimen/Forum)

Aplicação interativa projetada para dinamizar e estruturar a rotina de estudos em tecnologia, auxiliando estudantes e profissionais a vencerem a paralisia por escolha através de sorteios direcionados, tópicos essenciais e um temporizador de foco dedicado.

Desenvolvido por **Maria Luiza Prata**:
- LinkedIn: [linkedin.com/in/maria-luiza-prata-a342812b3](https://www.linkedin.com/in/maria-luiza-prata-a342812b3)
- GitHub: [github.com/Malu-Prata](https://github.com/Malu-Prata)

---

## Proposta e Metodologia

O **SortTech** foi idealizado para resolver um desafio comum a desenvolvedores: a grande quantidade de assuntos a estudar e a indecisão na hora de sentar para praticar.

A ferramenta reúne **10 grandes áreas da computação** e **90 temas práticos de estudo**. O fluxo foi pensado para ir da escolha à prática em poucos segundos:
1. **Seleção Direcionada ou Livre**: Escolha uma ou mais áreas clicando nos botões em pílula, ou deixe desmarcado para sortear entre todo o ecossistema de tecnologia.
2. **Sorteio com Efeitos Suaves**: Ao clicar em Sortear, um algoritmo anti-repetição escolhe um tema, acompanhado de animação de embaralhamento (*shuffle*), efeitos sonoros gerados por síntese de áudio (Web Audio API) e partículas nas cores da paleta.
3. **Foco Visual sem Distrações**: O tema sorteado ganha destaque no centro da tela de forma fluida, sem caixas pesadas ou placeholders vazios.
4. **Tópicos-Chave Retráteis**: Detalhes do que estudar organizados em tópicos limpos, abertos sob demanda por uma seta discreta.
5. **Pesquisa Imediata**: Atalhos integrados para pesquisar o tema no **Google** e no **YouTube** em uma nova aba.
6. **Ciclo de Estudo Pomodoro (30 minutos)**: O botão **Iniciar** direciona para o temporizador exclusivo da aplicação, permitindo iniciar o ciclo de foco imediatamente.

---

## As 10 Áreas da Computação Disponíveis (90 Temas)

1. **Desenvolvimento Web e Mobile** (10 temas): APIs RESTful, GraphQL, Componentização, JWT, Web Vitals, PWAs, TypeScript, WebSockets, SSR e Design Responsivo.
2. **Inteligência Artificial e Dados** (10 temas): Redes Neurais, Transformers, LLMs, Machine Learning, RAG, Análise Exploratória, CNNs, Métricas de Avaliação, Embeddings e Ética em IA.
3. **Cibersegurança e Redes** (10 temas): OWASP Top 10, Criptografia Simétrica/Assimétrica, Modelo OSI, Zero Trust, Wireshark, Prevenção a Phishing, DDoS, Autenticação MFA, Pentest e Forense Digital.
4. **Engenharia de Software e Algoritmos** (10 temas): Padrões GoF, SOLID, Código Limpo, Estruturas de Dados, Notação Big-O, TDD, Arquitetura Limpa, Concorrência, Microsserviços e Refatoração.
5. **DevOps e Cloud Computing** (10 temas): Docker, Kubernetes, CI/CD com GitHub Actions, Terraform, Observabilidade, Estratégias de Deploy, AWS/GCP, Linux para Servidores, GitOps e Segurança em Pipelines.
6. **Desenvolvimento de Jogos** (8 temas): Game Loops, Motores de Jogos, Shaders, Física de Jogos, IA para Jogos, Áudio Interativo, Otimização de Performance e Multiplayer.
7. **Engenharia de Dados** (8 temas): Pipelines ETL/ELT, Data Lakes, Apache Kafka, Modelagem Dimensional, Orquestração com Airflow, Processamento com Spark, Qualidade de Dados e Governança.
8. **Sistemas de Baixo Nível** (8 temas): Gerenciamento de Memória, Ponteiros em C/C++, Compiladores, Sistemas Operacionais, Assembly, Drivers e Kernel, Concorrência em Baixo Nível e Arquitetura de Processadores.
9. **Qualidade e Testes de Software** (8 temas): Pirâmide de Testes, Cypress e Playwright, Testes de Carga com k6, BDD, Testes de Regressão, Mocks, Testes de Acessibilidade e Automação de APIs.
10. **Design de Interface e Acessibilidade** (8 temas): Diretrizes WCAG, Design Systems, Heurísticas de Nielsen, Microinterações, Arquitetura de Informação, Tipografia Digital, Pesquisa com Usuários e Prototipação.

---

## Identidade Visual e Experiência

- **Paleta de Cores Oficial**:
  - Burgundy: `#6C0820`
  - Cherry Blossom Pink: `#F2AEBC`
  - Misty Rose: `#F2DCDB`
  - Silver Lake Blue: `#5A86CB`
  - Lapis Lazuli: `#3D5D91`
- **Fundo com Iluminação Radial Suave**: Círculo de gradiente centralizado e contínuo, sem cortes de resolução.
- **Tipografia Forum**: Fonte com elegância clássica inspirada nas proporções romanas, desenvolvida por Denis Masharov.
- **Microinterações Refinadas**: Botões arredondados, transições suaves, confetes personalizados e áudio sintético nativo sem bibliotecas externas pesadas.

---

## Arquitetura e Tecnologias

A aplicação adota uma arquitetura híbrida, podendo ser executada tanto com o backend em Java quanto de forma 100% cliente (estática):

### Frontend (Web)
- **HTML5 Semântico**: Estrutura acessível com modal explicativo, seletores e exibição direta.
- **CSS3 Moderno**: Variáveis CSS customizadas, gradientes radiais, animações fluidas e responsividade completa para computadores e dispositivos móveis.
- **JavaScript Moderno (ES6+)**:
  - Gerenciamento de estado das áreas selecionadas.
  - Algoritmo de sorteio com persistência no `localStorage`.
  - Página dedicada de **Timer Pomodoro** de 30 minutos com feedback sonoro ao concluir.
  - Integração com a Web Audio API para efeitos sonoros via sintetizador por oscilador.
  - Carregamento assíncrono flexível via `temas.json` ou `temas.js` (fallback offline).

### Backend (Java)
- **Servidor HTTP Embutido**: Implementado exclusivamente com `com.sun.net.httpserver`, sem dependência de bibliotecas externas ou frameworks pesados.
- **API REST**:
  - `GET /api/areas`: Retorna a lista de áreas e temas disponíveis.
  - `GET /api/sortear?area={id}`: Executa o sorteio no servidor com controle de histórico e retorno em JSON.
- **Servidor de Arquivos Estáticos**: Entrega diretamente os arquivos da pasta `web/` com resolução correta de tipos MIME e suporte a CORS.

---

## Estrutura do Repositório

```text
SortTech/
├── .gitignore                   # Regras de exclusao de compilados e arquivos temporarios
├── LICENSE                      # Licenca MIT
├── README.md                    # Documentacao do projeto
├── run.sh                       # Script de execucao para Linux e macOS
├── run.bat                      # Script de execucao para Windows
├── src/                         # Codigo-fonte do backend Java
│   └── com/estudotempo/
│       ├── Main.java            # Inicializacao do servidor e abertura automatica no navegador
│       ├── model/
│       │   ├── AreaComputacao.java   # Catalogo de areas e serializacao JSON nativa
│       │   └── TemaEstudo.java       # Modelo de dados de cada tema
│       ├── service/
│       │   └── SorteadorService.java # Logica do sorteador com filtro anti-repeticao
│       └── server/
│           └── ServidorWeb.java      # Servidor HTTP nativo e manipuladores da API
└── web/                         # Frontend Web da aplicacao
    ├── index.html               # Pagina principal do SortTech
    ├── timer.html               # Pagina dedicada do Timer de Estudos (Pomodoro 30 min)
    ├── style.css                # Folha de estilos com a paleta oficial e fonte Forum
    ├── app.js                   # Logica de interacao, sorteio, audio e confetes
    ├── temas.js                 # Base de dados local com as 10 areas e 90 temas
    └── temas.json               # Catalogo estruturado em formato JSON
```

---

## Como Executar

### Opção 1: Execução Direta no Navegador (Offline / GitHub Pages)

Por contar com carregamento independente em JavaScript, basta abrir o arquivo [`web/index.html`](web/index.html) em qualquer navegador web moderno com dois cliques.

Também pode ser publicado diretamente em serviços de hospedagem estática, como **GitHub Pages**, **Vercel** ou **Netlify**, apontando para a pasta `web/`.

---

### Opção 2: Execução com o Servidor Java

Certifique-se de possuir o **JDK 11 ou superior** instalado.

- **No Linux ou macOS**:
  ```bash
  chmod +x run.sh
  ./run.sh
  ```

- **No Windows**:
  Dê dois cliques no arquivo `run.bat` ou execute no prompt de comando:
  ```cmd
  run.bat
  ```

O servidor será iniciado na porta **8080** e abrirá automaticamente a interface web em `http://localhost:8080`.

---

## Licença

Este projeto é disponibilizado sob a licença [MIT](LICENSE).
