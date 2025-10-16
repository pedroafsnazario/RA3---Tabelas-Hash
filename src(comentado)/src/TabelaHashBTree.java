import java.util.function.BiFunction;
import java.util.function.Function;

// Implementação de uma tabela hash que utiliza árvores B para tratar colisões
// Cada posição da tabela hash contém uma árvore B que armazena os registros com a mesma chave hash
public class TabelaHashBTree {
    private int tamanho; // Tamanho da tabela hash
    BTree[] tabela; // Array de árvores B
    BiFunction<Integer, Integer, Long> funcao_hash; // Função hash que recebe a chave e o tamanho da tabela e retorna a posição na tabela
    long colisoes = 0; // Contador de colisões


    // Construtor da tabela hash que recebe o tamanho inicial e a função hash
    public TabelaHashBTree(int tamanho_inicial, BiFunction<Integer, Integer, Long> funcao_hash) {
        this.tamanho = tamanho_inicial; // Define o tamanho inicial da tabela hash
        this.tabela = new BTree[tamanho_inicial]; // Inicializa o array de árvores B com o tamanho inicial
        this.funcao_hash = funcao_hash; // Define a função hash
    }

    // Função para buscar um registro na tabela hash Btree, recebe uma chave e retorna o registro correspondente ou null se não encontrado
    Registro buscar(int chave) {
        // Calcula a posição na tabela hash usando a função hash
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        // Se a posição na tabela hash estiver vazia, retorna null
        if (this.tabela[posicao_array] == null) return null;

        // Busca o registro na árvore B correspondente à posição na tabela hash
        BTree arvore = this.tabela[posicao_array];
        BTNode resultado = arvore.search(chave); // Busca o nó na árvore B
        if(resultado != null) return resultado.value; // Retorna o registro se encontrado

        return null; // Retorna null se o registro não for encontrado
    }

    // Função para inserir um registro na tabela hash Btree, recebe uma chave e o registro a ser inserido
    void inserir(int chave, Registro valor) {
        // Calcula a posição na tabela hash usando a função hash
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        // Se a posição na tabela hash estiver vazia, cria uma nova árvore B e insere o registro
        if (this.tabela[posicao_array] == null) this.tabela[posicao_array] = new BTree(chave, valor);
        // Se a posição na tabela hash já estiver ocupada, insere o registro na árvore B correspondente e incrementa o contador de colisões
        else colisoes += this.tabela[posicao_array].insert(chave, valor);
    }


    //Função para calcular estatísticas sobre os gaps (espaços vazios) na tabela hash
    int[] gap() {
        int maior_gap = 0;
        int menor_gap = Integer.MAX_VALUE; // Inicializa com o maior valor possível, para garantir que qualquer gap encontrado será menor
        int media_gap = 0;
        int qtd_gaps = 0;

        int valor_atual = 1; // Contador do tamanho do gap atual
        boolean iniciou = false; // Flag para indicar se estamos dentro de um gap

        // Percorre toda a tabela hash para encontrar gaps
        for (int i = 0; i < this.tamanho; i++) {
        
            if (!iniciou && this.tabela[i] == null) {
                //Se encontrar gaps, indica que um gap começou e inicia o contador
                iniciou = true;
                valor_atual = 1;
            } else if(iniciou && this.tabela[i] != null) {
                //Se encontrar o fim de um gap, atualiza as estatísticas e reseta o contador
                iniciou = false;
                if (valor_atual < menor_gap) menor_gap = valor_atual;
                if(valor_atual > maior_gap) maior_gap = valor_atual;
                media_gap += valor_atual;
                qtd_gaps++;
            } else if(iniciou && this.tabela[i] == null) valor_atual++; //Se continuar dentro de um gap, incrementa o contador
        }

        media_gap /= qtd_gaps == 0 ? 1 : qtd_gaps; // Evita divisão por zero

        return new int[]{menor_gap == Integer.MAX_VALUE ? 0 : menor_gap, maior_gap, media_gap}; // Retorna as estatísticas dos gaps: menor, maior e média
    }

    //Função para calcular estatísticas sobre as alturas das árvores B na tabela hash
    int[] alturas() {
        // Inicializa as variáveis para armazenar as estatísticas
        int maior_altura = 0;
        int menor_altura = Integer.MAX_VALUE; // Inicializa com o maior valor possível, para garantir que qualquer altura encontrada será menor
        int media_altura = 0;
        int qtd = 0;

        // Percorre toda a tabela hash para calcular as alturas das árvores B
        for (int i = 0; i < this.tamanho; i++) {
            // Se a posição na tabela hash não estiver vazia, atualiza as estatísticas
            if(this.tabela[i] != null) {
                if(this.tabela[i].altura > maior_altura) maior_altura = this.tabela[i].altura; // Atualiza a maior altura
                if(this.tabela[i].altura < menor_altura) menor_altura = this.tabela[i].altura; // Atualiza a menor altura
                media_altura += this.tabela[i].altura; // Acumula a altura para calcular a média depois
                qtd++; // Incrementa o contador de árvores B
            }
        }

        media_altura /= qtd == 0 ? 1 : qtd; // Evita divisão por zero

        return new int[]{menor_altura == Integer.MAX_VALUE ? 0 : menor_altura, maior_altura, media_altura}; // Retorna as estatísticas das alturas: menor, maior e média
    }
}
