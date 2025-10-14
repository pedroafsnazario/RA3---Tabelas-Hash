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
  - TBD
 
  
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

**3 Maiores Listas:**

----------------------------

### Tabela Hash Encadeada⛓
**Descrição:** Aqui, cada posição da tabela guarda uma lista encadeada. Quando dois valores caem no mesmo lugar, eles são colocados um “atrás do outro” nessa lista. É um jeito bem comum e fácil de lidar com colisões, e funciona muito bem na maioria dos casos.

**Funções & Estrutura:** A mais fácil de se implementar em si foi a Tabela Encadeada, devido a...

**3 Maiores Listas:**

----------------------------

### Tabela Hash Dupla🎎
**Descrição:** Na hash dupla, a ideia é usar duas funções hash diferentes. Se um valor tenta ocupar uma posição que já está cheia, a segunda função é usada pra achar outro lugar livre. Isso evita que vários elementos se acumulem em sequência e ajuda a espalhar melhor os dados pela tabela.

**Funções & Estrutura:**

**3 Maiores Listas:**


## Análise das Tabelas🔎

### Por Tempo⏳
Utilizamos de uma library que mede o tempo de execução de nosso código, e com isto, teve como resultado a medição da "velocidade" de execução para cada Tabela Hash e Função Hash
- #️⃣ Btree
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    
----------------------------

- #️⃣ Encadeada
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    
----------------------------

 
- #️⃣ Dupla
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento


  
### Por Colisões💥
Colisões são o número de vezes que levou ao script da Tabela Hash para encontrar um espaço nulo para a inserção de um valor novo
- #️⃣ Btree
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    
----------------------------

- #️⃣ Encadeada
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    
----------------------------

- #️⃣ Dupla
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento

### Por Gap's🕳
Quantidade de espaços vazios dentro de uma tabela Hash
- #️⃣ Btree
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento

----------------------------

- #️⃣ Encadeada
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    
----------------------------

- #️⃣ Dupla
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    

## Análise por Gráficos📊

- #️⃣ Btree
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    
----------------------------

- #️⃣ Encadeada
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento
    
----------------------------

- #️⃣ Dupla
  - Módulo Direto (Resto por divisão)
  - Multiplicativo (Método de Knuth)
  - Soma dos digitos e deslocamento

## Resultado & Conclusão✅
- Feitas as análises gerais, podemos concluir que, no geral, dentre as 3 tabelas apresentadas, implementadas e testadas neste trabalho, a que ganha o título de "Melhor Tabela" vai para: Tabela ....
- Devido a
  
