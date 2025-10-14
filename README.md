# RA3-Tabelas-Hash #️⃣
### Trabalho de "Resolução de Problemas Estruturados em Computação"🎲

## Alunos 👥
- Eduardo Zenere
- Pedro Antonio

## Introdução
- trabalho sobre Hash Tables aonde se deve implementar, utilizar e testar 3 tipos de tabela hash e verificar por meio de análises (por graficos, medição de tempo e outros fatores) qual das 3 é no geral, a melhor.
- As variáveis para tamanho das Hash Tables que utilizamos foi de 10.000, 100.000 e 1.000.000
- utilizamos de 3 funções diferentes para as hash tables que foram:
- - a
  - b
  - c




## As Tabelas

### Tabela Hash Btree🌳
**Descrição:** Nesse tipo de tabela, cada espaço da hash guarda uma árvore binária. Então, se acontecer de vários valores caírem na mesma posição (as famosas colisões), eles são organizados dentro dessa árvore. Isso ajuda a deixar as buscas e inserções mais rápidas do que se fosse só uma lista simples.

**Funções & Estrutura:**

**3 Maiores Listas:**


### Tabela Hash Encadeada⛓
**Descrição:** Aqui, cada posição da tabela guarda uma lista encadeada. Quando dois valores caem no mesmo lugar, eles são colocados um “atrás do outro” nessa lista. É um jeito bem comum e fácil de lidar com colisões, e funciona muito bem na maioria dos casos.

**Funções & Estrutura:**

**3 Maiores Listas:**



### Tabela Hash Dupla🎎
**Descrição:** Na hash dupla, a ideia é usar duas funções hash diferentes. Se um valor tenta ocupar uma posição que já está cheia, a segunda função é usada pra achar outro lugar livre. Isso evita que vários elementos se acumulem em sequência e ajuda a espalhar melhor os dados pela tabela.

**Funções & Estrutura:**

**3 Maiores Listas:**


## Análise das Tabelas🔎

### Por Tempo⏳
- #️⃣ Btree
- #️⃣ Encadeada
- #️⃣ Dupla

  
### Por Colisões💥
- #️⃣ Btree
- #️⃣ Encadeada
- #️⃣ Dupla

### Por Gap's🕳
- #️⃣ Btree
- #️⃣ Encadeada
- #️⃣ Dupla

## Análise por Gráficos📊
- #️⃣ Btree
- #️⃣ Encadeada
- #️⃣ Dupla

## Resultado & Conclusão✅
- Feitas as análises gerais, podemos concluir que, no geral, dentre as 3 tabelas apresentadas, implementadas e testadas neste trabalho, a que ganha o título de "Melhor Tabela" vai para: Tabela ....
- Devido a
  
