# RA3 Tabelas Hash #️⃣
### Trabalho de "Resolução de Problemas Estruturados em Computação"🎲

## Alunos 👥
- Eduardo Zenere
- Pedro Antonio

## Introdução 📖
- trabalho sobre Hash Tables aonde se deve implementar, utilizar e testar 3 tipos de tabela hash e verificar por meio de análises (por graficos, medição de tempo e outros fatores) qual das 3 é no geral, a melhor.
- Este tópico é para trazer informações base para contextualização do projeto e das tables.

- Os tamanhos que utilizamos para os arrays são:
- Para **BTREE e Encadeamento**:
  - 10K = 10.007 posições
  - 100K = 100.103 posições
  - 1M = 1.001.041 posições

- Para **Hash Linear e Dupla**:
  - 100K valores = Tabela de 100_003 posições
  - 1M valores = Tabela de 1_000_003 posições
  - 10M valores = Tabela de  10_000_019 posições

- A quantidade de itens para serem inseridos são exatos:
  - 100K = 100.000 itens
  - 1M = 1.000.000 itens
  - 10M = 10.000.000 itens
 
(Os arrays tem essas quantidades de posições estranhas pois eles precisam ter uma quantidade de posições igual à um número primo, se não as funções de hashing não rodam direito, principalmente as de sondagem no hash duplo)

- **Funções Hashing**⚙
  - Divisão (Resto por divisão)
  - Método de Knuth (Multiplicação)
  - Multiplicação + Divisão
- **Foi feita também 2 Funções Hashing adicionais para a Tabela Hash Dupla devida a sua estrutura/função de sondagem que são:**
  - Subtração com primo fixo
  - Murmurhash3
 
  
- **Sobre a memória**🧠
  - **Memória Base:** A memória que o java ocupa, sem nada rodando ainda.
  - **Memória de Ocupação Fixa:** É a memória base + a quantidade de memória que os valores de teste ocupam.
  - **Memória Utilizada:** A memória de ocupação fixa + a memória utilizada pela arvore hash no momento. Ou seja, pra pegar a memória que a estrutura de tabela hash está ocupando, é preciso fazer **Memória utilizada - Memória de ocupação fixa**

- **Quantidade de testes feitos**
  - Fizemos no total 3 vezes os testes das Hash Tables para realizarmos análises mais precisas.
 
    
## As Tabelas

### Tabela Hash Btree🌳
**Descrição:** Nesse tipo de tabela, cada espaço da hash guarda uma árvore binária. Então, se acontecer de vários valores caírem na mesma posição (as famosas colisões), eles são organizados dentro dessa árvore. Isso ajuda a deixar as buscas e inserções mais rápidas do que se fosse só uma lista simples.

**Funções & Estrutura:** O Código foi relativamente mais fácil de implementar pelo fator de já termos a estrutura de BTree (que criamos de por meio de suas aulas e trabalhos passados anteriormente), a estrutura dessa tabela...


----------------------------

### Tabela Hash Encadeada⛓
**Descrição:** Aqui, cada posição da tabela guarda uma lista encadeada. Quando dois valores caem no mesmo lugar, eles são colocados um “atrás do outro” nessa lista. É um jeito bem comum e fácil de lidar com colisões, e funciona muito bem na maioria dos casos.

**Funções & Estrutura:** A mais fácil de se implementar em si foi a Tabela Encadeada, devido a...

----------------------------

### Tabela Hash Dupla🎎
**Descrição:** Na hash dupla, a ideia é usar duas funções hash diferentes. Se um valor tenta ocupar uma posição que já está cheia, a segunda função é usada pra achar outro lugar livre. Isso evita que vários elementos se acumulem em sequência e ajuda a espalhar melhor os dados pela tabela.

**Funções & Estrutura:**


## Análise das Tabelas🔎
- Está junto do código 3 arquivos .ini aonde estão os diferentes resultados que obtivemos com o teste.
- Como fizemos diversas iterações com as tabelas (para tamanhos e funções diferentes), os resultados também foram diversos, apesar de que, dentro dos 3 testes, percebemos alguns dados que permaneceram o mesmo.



## Resultado & Conclusão✅
- Feitas as análises gerais, podemos concluir que, no geral, dentre as 3 tabelas apresentadas, implementadas e testadas neste trabalho, a que ganha o título de "Melhor Tabela" vai para: Tabela ....
- Devido a
  
