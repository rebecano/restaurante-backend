# Sistema de Restaurante

Projeto 1 do curso de TI: um sistema de pedidos de restaurante em Java, executado pelo console.

**Autora:** Rebeca
**Vídeo de demonstração:** https://youtu.be/PHJt2RpIaeg

## O que o sistema faz

- Registra pedidos com o nome do cliente e quantos itens ele quiser.
- Cardápio com três lanches: X-Burger (R$ 16,00), X-Salada (R$ 18,00) e X-Bacon (R$ 22,00).
- Personalização: o cliente escolhe quais ingredientes retirar (queijo, alface, tomate, cebola). Retirar um ingrediente não muda o preço.
- Adicionais pagos: cheddar (+ R$ 4,00) e bacon extra (+ R$ 6,00), em qualquer quantidade e ordem.
- Lista os pedidos do dia com os itens e o total de cada um.
- Modifica um pedido existente: acrescentar item, remover item (escolhido por número) ou cancelar o pedido inteiro.
- Fecha o restaurante mostrando o total vendido e quantas combinações diferentes de lanche foram pedidas, com a lista delas.
- Entradas inválidas (letras no lugar de números, opção que não existe, nome em branco, resposta diferente de s/n) avisam e repetem a pergunta, sem encerrar o programa.

## Como executar

Requisitos: JDK instalado na versão indicada no `pom.xml` (`maven.compiler.source`) e Maven.

```bash
mvn compile
mvn exec:java -Dexec.mainClass="br.com.restaurante.app.Main"
```

Também dá para abrir o projeto no IntelliJ e executar a classe `Main`.

Para rodar os testes:

```bash
mvn test
```

## Organização dos pacotes

```
br.com.restaurante
├── app        Main, MenuConsole e RestauranteFacade
├── cardapio   ItemCardapio, Lanche, LancheFactory e os decorators
└── pedido     Pedido e GerenciadorPedidos
```

Cada pacote agrupa classes que mudam pelo mesmo motivo. `app` cuida da interação e do fluxo, `cardapio` do que é vendido e `pedido` de como as vendas são guardadas.

### Divisão de responsabilidades

| Classe | Responsabilidade |
|---|---|
| `Main` | Controla o fluxo principal. Não imprime nada, não lê nada e não faz cálculos. |
| `MenuConsole` | Toda leitura do teclado, toda mensagem na tela e toda validação de entrada. |
| `RestauranteFacade` | Ponto de entrada para as operações do sistema. Não conversa com o usuário. |
| `GerenciadorPedidos` | Guarda todos os pedidos, calcula o total do dia e as combinações. |
| `Pedido` | Guarda o cliente e a lista de itens de um pedido. |

## Padrões de projeto

- **Decorator** (`ComCheddar`, `ComBaconExtra`): cada adicional implementa `ItemCardapio`, guarda dentro de si outro `ItemCardapio` e acrescenta sua descrição e seu preço. Assim, um lanche pode receber qualquer combinação de adicionais sem precisar de uma classe para cada combinação. Exemplo: `X-Bacon + cheddar + bacon extra`, R$ 32,00.
- **Factory** (`LancheFactory`): cria os lanches do cardápio, sempre uma instância nova a cada chamada. Isso é necessário para que a personalização de um pedido nunca afete outro.
- **Facade** (`RestauranteFacade`): esconde do `Main` como as operações são feitas por trás (criar lanche, aplicar a personalização, embrulhar os adicionais e adicionar ao pedido).

## Testes

Os testes estão em `src/test/java`:

- `LancheTest`: verifica que um preço negativo é recusado.
- `ComCheddarTest`: verifica que um X-Bacon com cheddar custa R$ 26,00.

## Decisões e limitações

- **Ordem dos adicionais:** `X-Bacon + cheddar + bacon extra` e `X-Bacon + bacon extra + cheddar` têm o mesmo preço, mas descrições diferentes, então o `HashSet` do fechamento conta como duas combinações. 
- **Pedido sem itens:** se o último item de um pedido for removido, o pedido continua existindo com total R$ 0,00. 
- **Valores monetários:** usam `double`, como pede o enunciado. Em um sistema financeiro real, o ideal seria outro tipo.
