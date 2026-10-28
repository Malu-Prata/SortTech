package com.estudotempo.model;

import java.util.Arrays;
import java.util.List;

public enum AreaComputacao {

    DESENVOLVIMENTO_WEB(
        "web",
        "Desenvolvimento Web e Mobile",
        "Frontend, Backend, APIs, Bancos de Dados e Frameworks modernos",
        Arrays.asList(
            new TemaEstudo(
                "web-1",
                "APIs RESTful e GraphQL",
                "Entenda as diferenças entre arquitetura REST e GraphQL, métodos HTTP, códigos de status e boas práticas de modelagem de endpoints.",
                "Intermediário",
                new String[]{"Verbos HTTP", "JSON", "Query e Mutation", "Autenticação", "Cache"}
            ),
            new TemaEstudo(
                "web-2",
                "Componentização e Gerenciamento de Estado",
                "Domine o fluxo de dados unidirecional, ciclo de vida de componentes e gerenciamento de estado global (ex: React, Vue ou Angular).",
                "Intermediário",
                new String[]{"Hooks", "Props", "Context API / Redux", "Re-renderização"}
            ),
            new TemaEstudo(
                "web-3",
                "Autenticação e Autorização Web",
                "Aprenda como proteger rotas e APIs utilizando JWT (JSON Web Tokens), OAuth 2.0, Cookies seguros e sessões distribuídas.",
                "Intermediário",
                new String[]{"JWT", "OAuth 2.0", "CORS", "HttpOnly Cookies", "RBAC"}
            ),
            new TemaEstudo(
                "web-4",
                "Otimização de Performance e Core Web Vitals",
                "Descubra como acelerar o carregamento de aplicações web, reduzindo LCP, FID e CLS, além de técnicas de Lazy Loading e Code Splitting.",
                "Avançado",
                new String[]{"Core Web Vitals", "Lazy Loading", "Minificação", "CDN", "Lighthouse"}
            ),
            new TemaEstudo(
                "web-5",
                "Banco de Dados para Aplicações Web",
                "Compare bancos de dados relacionais (PostgreSQL/MySQL) com bancos NoSQL (MongoDB/Redis) e saiba quando usar ORMs (Hibernate/Prisma).",
                "Iniciante",
                new String[]{"SQL", "NoSQL", "Modelagem ER", "Índices", "Migrações"}
            ),
            new TemaEstudo(
                "web-6",
                "Progressive Web Apps (PWA) e Service Workers",
                "Torne suas aplicações web instaláveis em dispositivos móveis e capazes de funcionar offline com Service Workers e caching.",
                "Avançado",
                new String[]{"Manifest.json", "Service Workers", "Cache Storage", "Push Notifications"}
            ),
            new TemaEstudo(
                "web-7",
                "TypeScript no Desenvolvimento Moderno",
                "Compreenda tipagem estática no ecossistema JavaScript, tipos genéricos, interfaces, type narrowing e configuração do tsconfig.",
                "Iniciante",
                new String[]{"Generics", "Interfaces vs Types", "Strict Mode", "Transpilação"}
            )
        )
    ),

    INTELIGENCIA_ARTIFICIAL(
        "ia",
        "Inteligência Artificial e Dados",
        "Machine Learning, Deep Learning, LLMs, NLP e Ciência de Dados",
        Arrays.asList(
            new TemaEstudo(
                "ia-1",
                "Redes Neurais Artificiais e Deep Learning",
                "Estude a arquitetura de perceptrons multicamadas, funções de ativação, forward propagation e o algoritmo de backpropagation.",
                "Avançado",
                new String[]{"Gradiente Descendente", "ReLU / Sigmoid", "Camadas Ocultas", "Loss Functions"}
            ),
            new TemaEstudo(
                "ia-2",
                "Processamento de Linguagem Natural e LLMs",
                "Entenda como funcionam os modelos de linguagem modernos baseados em Transformers, mecanismos de atenção e geração de texto.",
                "Avançado",
                new String[]{"Mecanismo de Atenção", "Tokenização", "Transformers", "Fine-Tuning", "Embeddings"}
            ),
            new TemaEstudo(
                "ia-3",
                "Aprendizado Supervisionado vs Não Supervisionado",
                "Aprenda a diferença entre problemas de regressão, classificação e agrupamento (clustering) utilizando algoritmos clássicos.",
                "Iniciante",
                new String[]{"Regressão Linear", "K-Means", "Random Forest", "Overfitting vs Underfitting"}
            ),
            new TemaEstudo(
                "ia-4",
                "Engenharia de Prompts e RAG (Retrieval-Augmented)",
                "Explore como integrar modelos de IA generativa com bases de conhecimento proprietárias usando busca vetorial e bancos de vetores.",
                "Intermediário",
                new String[]{"Vector DBs", "Cosine Similarity", "System Prompts", "Chunking"}
            ),
            new TemaEstudo(
                "ia-5",
                "Análise Exploratória de Dados (EDA)",
                "Aprenda a inspecionar distribuições, correlações, tratar valores nulos e visualizar insights em conjuntos de dados reais.",
                "Iniciante",
                new String[]{"Pandas / NumPy", "Histogramas", "Boxplots", "Outliers", "Limpeza de Dados"}
            ),
            new TemaEstudo(
                "ia-6",
                "Visão Computacional e Redes Convolucionais (CNN)",
                "Compreenda como filtros e camadas convolucionais extraem características visuais para reconhecimento de imagens e detecção de objetos.",
                "Avançado",
                new String[]{"Convoluções", "Pooling", "YOLO / OpenCV", "Data Augmentation"}
            ),
            new TemaEstudo(
                "ia-7",
                "Métricas de Avaliação de Modelos",
                "Vá além da simples acurácia: estude precisão, revocação (recall), F1-Score, matriz de confusão e curvas ROC-AUC.",
                "Intermediário",
                new String[]{"Matriz de Confusão", "F1-Score", "Recall", "ROC-AUC", "Cross-Validation"}
            )
        )
    ),

    CIBERSEGURANCA(
        "seguranca",
        "Cibersegurança e Redes",
        "Defesa, Pentest, Criptografia, OWASP, Protocolos e Identidade",
        Arrays.asList(
            new TemaEstudo(
                "sec-1",
                "OWASP Top 10 e Vulnerabilidades Web",
                "Investigue as falhas mais críticas em aplicações web modernas: SQL Injection, XSS, CSRF, Broken Access Control e como mitigá-las.",
                "Intermediário",
                new String[]{"SQLi", "XSS", "Sanitização de Inputs", "CSRF Tokens", "WAF"}
            ),
            new TemaEstudo(
                "sec-2",
                "Criptografia Simétrica vs Assimétrica",
                "Entenda a matemática e o funcionamento de chaves privadas/públicas, algoritmos como AES e RSA, além de funções hash como SHA-256.",
                "Iniciante",
                new String[]{"AES", "RSA", "Hashing", "Salting", "Assinatura Digital"}
            ),
            new TemaEstudo(
                "sec-3",
                "Fundamentos do Modelo OSI e Pilha TCP/IP",
                "Analise as camadas de rede, o handshake de 3 vias do TCP, funcionamento de UDP, DNS, DHCP e roteamento de pacotes.",
                "Iniciante",
                new String[]{"TCP 3-way Handshake", "UDP", "Sub-redes / CIDR", "Portas e Sockets"}
            ),
            new TemaEstudo(
                "sec-4",
                "Arquitetura Zero Trust e Gestão de Identidade (IAM)",
                "Estude o paradigma moderno de segurança: nunca confie, sempre verifique, MFA (Multi-Factor Authentication) e menor privilégio.",
                "Intermediário",
                new String[]{"MFA", "Princípio do Menor Privilégio", "Segmentação de Rede", "SSO"}
            ),
            new TemaEstudo(
                "sec-5",
                "Análise de Pacotes e Tráfego com Wireshark",
                "Aprenda a capturar e inspecionar tráfego de rede para identificar anomalias, pacotes maliciosos ou vazamentos de credenciais.",
                "Intermediário",
                new String[]{"Packet Sniffing", "Filtros de Exibição", "PCAP", "Análise de Handshakes"}
            ),
            new TemaEstudo(
                "sec-6",
                "Engenharia Social e Proteção Contra Phishing",
                "Entenda as táticas psicológicas usadas por invasores, simulações de phishing e políticas de segurança comportamental corporativa.",
                "Iniciante",
                new String[]{"Spear Phishing", "Spoofing", "DMARC/SPF/DKIM", "Conscientização"}
            ),
            new TemaEstudo(
                "sec-7",
                "Segurança de APIs e Prevenção de Ataques DDoS",
                "Aprenda estratégias de Rate Limiting, Throttling, API Gateways, inspeção de tokens e proteção contra sobrecarga maliciosa.",
                "Avançado",
                new String[]{"Rate Limiting", "API Gateway", "DDoS Mitigation", "Cloudflare / CDN"}
            )
        )
    ),

    ENGENHARIA_SOFTWARE(
        "engenharia",
        "Engenharia de Software e Algoritmos",
        "Padrões de Projeto, Clean Code, SOLID, Estruturas de Dados e Arquitetura",
        Arrays.asList(
            new TemaEstudo(
                "eng-1",
                "Padrões de Projeto (Design Patterns - GoF)",
                "Aprenda a aplicar padrões criacionais, estruturais e comportamentais como Factory, Singleton, Strategy, Observer e Decorator.",
                "Intermediário",
                new String[]{"Strategy", "Observer", "Factory Method", "Adapter", "Singleton"}
            ),
            new TemaEstudo(
                "eng-2",
                "Princípios SOLID e Código Limpo (Clean Code)",
                "Domine os 5 princípios SOLID e técnicas de refatoração para produzir código legível, testável e de fácil manutenção.",
                "Iniciante",
                new String[]{"Single Responsibility", "Open/Closed", "Liskov Substitution", "Interface Segregation", "Dependency Inversion"}
            ),
            new TemaEstudo(
                "eng-3",
                "Estruturas de Dados Essenciais",
                "Estude o funcionamento em memória de Tabelas Hash (HashMaps), Árvores Binárias de Busca, Filas de Prioridade e Grafos.",
                "Intermediário",
                new String[]{"Hash Collisions", "Árvore AVL", "Grafos / Matriz de Adjacência", "Heap"}
            ),
            new TemaEstudo(
                "eng-4",
                "Complexidade de Tempo e Espaço (Notação Big-O)",
                "Aprenda a mensurar a eficiência de algoritmos calculando O(1), O(log n), O(n), O(n log n) e evitando O(n²) em larga escala.",
                "Iniciante",
                new String[]{"Big-O", "Melhor / Pior Caso", "Consumo de Memória", "Análise Assintótica"}
            ),
            new TemaEstudo(
                "eng-5",
                "Testes Automatizados e Metodologia TDD",
                "Entenda a pirâmide de testes: testes unitários com mocks, testes de integração e a prática de Test-Driven Development (Red-Green-Refactor).",
                "Intermediário",
                new String[]{"JUnit / Assertions", "Mocks / Stubs", "Pirâmide de Testes", "Cobertura de Código"}
            ),
            new TemaEstudo(
                "eng-6",
                "Arquitetura Limpa e Domain-Driven Design (DDD)",
                "Separe regras de negócio de frameworks e bancos de dados usando portas e adaptadores (Hexagonal Architecture) e entidades ricas.",
                "Avançado",
                new String[]{"Clean Architecture", "Entidades vs DTOs", "Inversão de Controle", "Value Objects"}
            ),
            new TemaEstudo(
                "eng-7",
                "Concorrência, Multithreading e Paralelismo",
                "Explore execução concorrente, condições de corrida (Race Conditions), deadlocks, primitivas de sincronização e thread pools.",
                "Avançado",
                new String[]{"Threads", "Locks / Mutex", "Deadlocks", "Volatile", "ThreadPoolExecutor"}
            )
        )
    ),

    DEVOPS_CLOUD(
        "devops",
        "DevOps e Cloud Computing",
        "Containers, Kubernetes, CI/CD, Terraform, Nuvem e Observabilidade",
        Arrays.asList(
            new TemaEstudo(
                "ops-1",
                "Conteinerização com Docker",
                "Aprenda a empacotar aplicações em imagens leves, escrever Dockerfiles eficientes com Multi-Stage Build e orquestrar com Docker Compose.",
                "Iniciante",
                new String[]{"Dockerfile", "Docker Compose", "Volumes", "Networks", "Multi-stage"}
            ),
            new TemaEstudo(
                "ops-2",
                "Orquestração com Kubernetes (K8s)",
                "Descubra como gerenciar contêineres em escala: Pods, Deployments, Services, Ingress, Namespaces e escalabilidade horizontal (HPA).",
                "Avançado",
                new String[]{"Pods", "Deployments", "Services e Ingress", "ConfigMaps", "HPA"}
            ),
            new TemaEstudo(
                "ops-3",
                "Pipelines de CI/CD Automatizadas",
                "Construa fluxos contínuos de integração e entrega (ex: GitHub Actions) com linting, testes automatizados e deploy contínuo.",
                "Intermediário",
                new String[]{"GitHub Actions", "Build Automation", "Artifacts", "Secrets Management"}
            ),
            new TemaEstudo(
                "ops-4",
                "Infraestrutura como Código (IaC) com Terraform",
                "Gerencie e versione recursos de nuvem de maneira declarativa utilizando sintaxe HCL, planos de execução e arquivos de estado.",
                "Intermediário",
                new String[]{"HCL", "Terraform Plan/Apply", "State File", "Módulos"}
            ),
            new TemaEstudo(
                "ops-5",
                "Observabilidade: Métricas, Logs e Rastreamento",
                "Implemente os três pilares da observabilidade com ferramentas como Prometheus, Grafana, ELK Stack e OpenTelemetry.",
                "Intermediário",
                new String[]{"Prometheus", "Grafana", "Structured Logging", "Tracing Distribuído"}
            ),
            new TemaEstudo(
                "ops-6",
                "Estratégias de Deploy Modernas",
                "Conheça formas de lançar novas versões sem indisponibilidade: Rolling Updates, Blue-Green Deployment e Canary Releases.",
                "Intermediário",
                new String[]{"Blue-Green", "Canary", "Feature Flags", "Zero-downtime Rollback"}
            ),
            new TemaEstudo(
                "ops-7",
                "Fundamentos de Arquitetura em Nuvem (AWS/GCP)",
                "Compreenda serviços essenciais: Máquinas Virtuais (EC2), Armazenamento de Objetos (S3), VPCs e Serverless (Lambdas).",
                "Iniciante",
                new String[]{"Compute Instances", "Object Storage", "Serverless", "VPC e Subnets"}
            )
        )
    );

    private final String id;
    private final String nomeExibicao;
    private final String descricao;
    private final List<TemaEstudo> temas;

    AreaComputacao(String id, String nomeExibicao, String descricao, List<TemaEstudo> temas) {
        this.id = id;
        this.nomeExibicao = nomeExibicao;
        this.descricao = descricao;
        this.temas = temas;
    }

    public String getId() {
        return id;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<TemaEstudo> getTemas() {
        return temas;
    }

    public static AreaComputacao porId(String id) {
        for (AreaComputacao a : values()) {
            if (a.id.equalsIgnoreCase(id) || a.name().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    public String toJson(boolean incluirTemas) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"").append(id).append("\",");
        sb.append("\"nome\":\"").append(nomeExibicao).append("\",");
        sb.append("\"descricao\":\"").append(descricao).append("\",");
        sb.append("\"totalTemas\":").append(temas.size());
        if (incluirTemas) {
            sb.append(",\"temas\":[");
            for (int i = 0; i < temas.size(); i++) {
                sb.append(temas.get(i).toJson());
                if (i < temas.size() - 1) sb.append(",");
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }
}
