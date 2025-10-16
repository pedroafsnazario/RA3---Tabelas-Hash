import java.util.function.BiFunction;
import java.util.function.Function;

//Implementação de tabela hash com tratamento de colisões por sondagem dupla
//

class TabelaHashDupla {
    private int tamanho; //Quantidade total de posições na tabela
    private NoHashSimples[] tabela; //Array principal que armazena os elementos
    private BiFunction<Integer, Integer, Long> funcao_hash; //Função hash primária recebida como parametro
    private BiFunction<Integer, Integer, Long> funcao_sonda; //Função hash secundária de sondagem, também recebida como parametro
    int elementos = 0; //Quantidade de elementos atualmente na tabela
    long colisoes = 0; //Contador de colisões

    //Construtor da Tabela Hash Dupla
    public TabelaHashDupla(int tamanho_inicial, BiFunction<Integer, Integer, Long> funcao_hash, BiFunction<Integer, Integer, Long> funcao_sonda) {
        this.tamanho = tamanho_inicial; //tamanho_inicial: tamanho inicial da tabela hash
        this.tabela = new NoHashSimples[tamanho_inicial]; //Array principal que armazena os elementos
        this.funcao_hash = funcao_hash; //funcao_hash: função hash a ser utilizada para calcular as posições do array
        this.funcao_sonda = funcao_sonda; //funcao_sonda: função hash a ser utilizada para calcular os incrementos de sondagem e deslocamento
    }

    //Função para calcular a posição do array com base na chave e no número de tentativas de sondagem
    //+1 é adicionado para evitar incremento zero na sondagem, levando a uma sondagem infinita
    // i é o número de tentativas de sondagem (0 para a primeira tentativa, 1 para a segunda, etc.)
    //chave é a chave do elemento a ser inserido ou buscado
    //retorna a posição calculada no array
    int pegar_chave(int i, int chave) {
        int pos = (int)((this.funcao_hash.apply(chave, this.tamanho) + i * (this.funcao_sonda.apply(chave, this.tamanho) + 1)) % this.tamanho);
        return pos < 0 ? -pos : pos; //garante que a posição não seja negativa
    }

    //Função de Busca da Tabela, recebe a chave de parametro e retorna o registro associado a chave (ou null caso não exista)
    Registro buscar(int chave) {
        
        int i = 0; //contador de tentativas de sondagem
        int posicao_array = pegar_chave(i, chave); //calcula a posição inicial no array

        //Continua procurando (sondando) enquanto houver elementos na tabela
        while (this.tabela[posicao_array] != null) {
            //Se encontrar a chave e o elemento não estiver marcado como removido, retorna o valor associado
            if(!this.tabela[posicao_array].removido && this.tabela[posicao_array].chave == chave) return this.tabela[posicao_array].valor;
            //Caso não encontrado, incrementa o contador de sondagem e calcula a próxima posição
            posicao_array = this.pegar_chave(++i, chave);

            //Para evitar loop infinito, se todas as posições foram sondadas, quebra o ciclo
            if(i >= this.tamanho) break;
        }

        return null; //Se não encontrar a chave, retorna null
    }


    //Função de Inserção na Tabela, recebe a chave e o valor (registro) de parametro
    //Retorna true se a inserção foi bem sucedida, false caso a tabela esteja cheia
    boolean inserir(int chave, Registro valor) {
        //Se a tabela estiver cheia, não insere e retorna false
        if (this.elementos == this.tamanho) return false;

        int i = 0; //contador de tentativas de sondagem
        int posicao_array = this.pegar_chave(i, chave); //calcula a posição inicial no array

        //Se a posição calculada estiver vazia, insere o novo elemento diretamente
        if (this.tabela[posicao_array] == null) this.tabela[posicao_array] = new NoHashSimples(chave, valor);

        //Se a posição já estiver ocupada, inicia a sondagem para encontrar a próxima posição disponível
        else {
            int primeiro_deletado = -1; //Armazena posição de possível espaço reutilizável (marcado como removido)

            // Percorre até encontrar posição livre
            while (this.tabela[posicao_array] != null) {
                colisoes++; //Incrementa contador de colisões quando cair em uma posição ocupada

                //Se encontrar um espaço marcado como removido, armazena sua posição para possível reutilização
                if(this.tabela[posicao_array].removido) {
                    if (primeiro_deletado == -1) primeiro_deletado = posicao_array;
                } else if(this.tabela[posicao_array].chave == chave) {
                    //Se encontrar a chave já existente, atualiza o valor associado e retorna true
                    this.tabela[posicao_array].valor = valor;
                    return true;
                }
                //Caso não encontrado, incrementa o contador de sondagem e calcula a próxima posição
                posicao_array = this.pegar_chave(++i, chave);

                //Para evitar loop infinito, se todas as posições foram sondadas, quebra o ciclo
                if(i >= this.tamanho) {
                    if(primeiro_deletado == -1) return false; //Tabela cheia, não há onde inserir
                    break;
                }
            }
            //Insere o novo elemento na primeira posição marcada como removida, se existir, ou na próxima posição livre encontrada
            this.tabela[primeiro_deletado != -1 ? primeiro_deletado : posicao_array] = new NoHashSimples(chave, valor);
        }
        //Incrementa o contador de elementos e retorna true indicando sucesso na inserção
        this.elementos++;
        return true;
    }

    //Função para calcular estatísticas de gaps (sequências de posições vazias) na tabela
    int[] gap() {
        //Calcula o maior, menor e média de gaps (sequências de posições vazias) na tabela
        int maior_gap = 0;
        int menor_gap = Integer.MAX_VALUE; //inicializa com o maior valor possível para garantir que qualquer gap encontrado seja menor
        int media_gap = 0;
        int qtd_gaps = 0;

        int valor_atual = 1; //contador do tamanho do gap atual
        boolean iniciou = false; //flag para indicar se está dentro de um gap

        //Percorre toda a tabela para identificar gaps
        for (int i = 0; i < this.tamanho; i++) {
            
            if (!iniciou && this.tabela[i] == null) {
                //Se encontrar o início de um gap, inicia a contagem
                iniciou = true;
                valor_atual = 1;
            } else if(iniciou && this.tabela[i] != null) {
                //Se encontrar o fim de um gap, atualiza as estatísticas e reseta a contagem
                iniciou = false;
                if (valor_atual < menor_gap) menor_gap = valor_atual;
                if(valor_atual > maior_gap) maior_gap = valor_atual;
                media_gap += valor_atual;
                qtd_gaps++;
            } else if(iniciou && this.tabela[i] == null) valor_atual++; //Se continuar dentro de um gap, incrementa o tamanho atual do gap
        }

        media_gap /= qtd_gaps == 0 ? 1 : qtd_gaps; //Calcula a média, evitando divisão por zero

        return new int[]{menor_gap == Integer.MAX_VALUE ? 0 : menor_gap, maior_gap, media_gap}; //Retorna um array com menor, maior e média de gaps
    }
}