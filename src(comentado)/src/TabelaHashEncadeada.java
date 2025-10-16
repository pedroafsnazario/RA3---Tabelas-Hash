import java.util.function.BiFunction;
import java.util.function.Function;

//Implementação de tabela hash com tratamento de colisões por encadeamento

class TabelaHashEncadeada {
    private int tamanho; //Tamanho da tabela (quantidade de posições do array principal)
    private NoHashEncadeado[] tabela; //Array principal que armazena as listas encadeadas
    BiFunction<Integer, Integer, Long> funcao_hash; //Função hash recebida como parametro
    long colisoes = 0;//Contador de colisões

    //Construtor da Tabela Hash Encadeada
    //tamanho_inicial: tamanho inicial da tabela hash
    //funcao_hash: função hash a ser utilizada para calcular as posições do array
    public TabelaHashEncadeada(int tamanho_inicial, BiFunction<Integer, Integer, Long> funcao_hash) {
        this.tamanho = tamanho_inicial;
        this.tabela = new NoHashEncadeado[tamanho_inicial];
        this.funcao_hash = funcao_hash;
    }


    //Função de Busca da Tabela, recebe a chave de parametro e retorna o registro associado a chave (ou null caso não exista)
    Registro buscar(int chave) {
        //Calcula a posição do array utilizando a função hash
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        //Se a posição do array estiver vazia, retorna null
        if (this.tabela[posicao_array] == null) return null;

        //Percorre a lista encadeada na posição do array, buscando o nó com a chave desejada
        NoHashEncadeado atual = this.tabela[posicao_array];
        while (atual != null) {
            if (atual.chave == chave) return atual.valor; //Se encontrar, retorna o valor associado a chave
            atual = atual.proximo;
        }

        return null; //Se não encontrar, retorna null
    }


    //Função de Inserção da Tabela, recebe a chave e o registro a ser inserido
    
    void inserir(int chave, Registro valor) {
        //Calcula a posição do array utilizando a função hash
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        //Se a posição do array estiver vazia, insere o novo nó diretamente, Caso contrário, insere o novo nó no início da lista encadeada (tratamento de colisão por encadeamento)
        if (this.tabela[posicao_array] == null) this.tabela[posicao_array] = new NoHashEncadeado(chave, valor);
        else {
            colisoes++; //Incrementa o contador de colisões
            NoHashEncadeado novo = new NoHashEncadeado(chave, valor); //Cria o novo nó
            novo.proximo = this.tabela[posicao_array]; //Aponta o próximo do novo nó para o início da lista atual
            this.tabela[posicao_array] = novo;  //Atualiza o início da lista para o novo nó
        }
    }



    //Função para calcular o Gap (espaços vazios entre elementos preenchidos)
    int[] gap() {
        //Variáveis para armazenar o maior, menor e média dos gaps
        int maior_gap = 0;
        int menor_gap = Integer.MAX_VALUE; //Inicia com o maior valor possível para garantir que qualquer gap encontrado seja menor
        int media_gap = 0;
        int qtd_gaps = 0;

        int valor_atual = 1; //Contador do tamanho do gap atual
        boolean iniciou = false; //Flag para indicar se já iniciou a contagem de um gap

        //Percorre a tabela para encontrar os gaps
        for (int i = 0; i < this.tamanho; i++) {
            if (!iniciou && this.tabela[i] == null) {
                // Se encontrar um espaço vazio e ainda não iniciou a contagem, inicia a contagem do gap
                iniciou = true;
                valor_atual = 1;
            } else if(iniciou && this.tabela[i] != null) {
                // Se encontrar um elemento preenchido e já estava contando um gap, finaliza a contagem do gap
                iniciou = false;
                if (valor_atual < menor_gap) menor_gap = valor_atual;
                if(valor_atual > maior_gap) maior_gap = valor_atual;
                media_gap += valor_atual;
                qtd_gaps++;
            } else if(iniciou && this.tabela[i] == null) valor_atual++; // Se continuar encontrando espaços vazios, incrementa o tamanho do gap
        }

        media_gap /= qtd_gaps == 0 ? 1 : qtd_gaps; //Calcula a média dos gaps, evitando divisão por zero

        return new int[]{menor_gap == Integer.MAX_VALUE ? 0 : menor_gap, maior_gap, media_gap}; //Retorna um array com o menor, maior e média dos gaps
    }


    //Função para encontrar os três maiores tamanhos de listas encadeadas na tabela
    int[] maiores_listas() {
        //Array para armazenar os três maiores tamanhos de listas
        int[] tamanho_listas = new int[]{0,0,0};

        //Percorre a tabela para encontrar os tamanhos das listas encadeadas
        for (int i = 0; i < this.tamanho; i++) {
            NoHashEncadeado atual = this.tabela[i];
            int tamanho = 0;

            //Percorre a lista encadeada na posição atual do array, contando quantos nós há na lista encadeada dessa posição
            while (atual != null) {
                tamanho++;
                atual = atual.proximo;
            }

            //Verifica se esse tamanho é um dos 3 maiores já vistos (por meio dos 3 if´s)
            if(tamanho > tamanho_listas[0]) {
                tamanho_listas[0] = tamanho;
                continue;
            }
            if(tamanho > tamanho_listas[1]){
                tamanho_listas[1] = tamanho;
                continue;
            }
            if(tamanho > tamanho_listas[2]) {
                tamanho_listas[2] = tamanho;
                continue;
            }
        }
        return tamanho_listas; //Retorna o array com os três maiores tamanhos de listas
    }
}

//Classe para representar o registro armazenado na tabela hash
//Representando um Nó (elemento) da lista encadeada usada na tabela hash.
class NoHashEncadeado {
    int chave; //Chave associada ao registro
    Registro valor; //Registro (valor) armazenado
    NoHashEncadeado proximo; //Referência para o próximo nó na lista encadeada

    //Construtor do nó, inicializa a chave e o valor
    public NoHashEncadeado(int chave, Registro valor) {
        this.chave = chave;
        this.valor = valor;
    }
}