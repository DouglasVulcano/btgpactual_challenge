# Diagramas de Arquitetura

Este diretório contém os diagramas de arquitetura do projeto BTG Pactual Challenge.

## 📊 Arquitetura do Sistema

### `codeviz-diagram-2025-07-20T16-15-41.drawio`

Diagrama principal da arquitetura hexagonal do sistema, mostrando:

- **Camadas da Arquitetura**: Domain, Application, Infrastructure
- **Fluxo de Dados**: RabbitMQ → Application → Domain → MongoDB
- **Ports & Adapters**: Interfaces e suas implementações
- **Componentes**: Controllers, Services, Repositories, Listeners

### 🔍 Como Visualizar

#### Opção 1: Draw.io Desktop/Web
1. Acesse [app.diagrams.net](https://app.diagrams.net/)
2. Abra o arquivo `codeviz-diagram-2025-07-20T16-15-41.drawio`
3. Visualize e edite conforme necessário

#### Opção 2: Visualizador Online (GitHub)
- [Visualizar Diagrama Online](https://viewer.diagrams.net/?url=https://raw.githubusercontent.com/DouglasVulcano/btgpactual_challenge/main/diagrams/codeviz-diagram-2025-07-20T16-15-41.drawio)

#### Opção 3: VS Code Extension
1. Instale a extensão "Draw.io Integration"
2. Abra o arquivo `.drawio` diretamente no VS Code

### 📝 Exportar para Outros Formatos

Para usar em documentação ou apresentações:

```bash
# Exportar para PNG (requer Draw.io desktop)
# File → Export as → PNG

# Exportar para SVG (requer Draw.io desktop)  
# File → Export as → SVG

# Exportar para PDF (requer Draw.io desktop)
# File → Export as → PDF
```

### 🔄 Atualizações

- **Data**: 2025-07-20T16:15:41
- **Versão**: 1.0
- **Autor**: Douglas Vulcano
- **Status**: ✅ Atualizado com arquitetura atual

---

> 💡 **Dica**: Mantenha este diagrama atualizado sempre que houver mudanças significativas na arquitetura do sistema.
