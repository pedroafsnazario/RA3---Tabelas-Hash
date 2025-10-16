import java.util.function.BiFunction;
import java.util.function.Function;

//Implementação de uma tabela hash com tratamento de colisões por endereçamento aberto e sondagem linear

class TabelaHashLinear {
    private int tamanho; // tamanho da tabela
    private NoHashSimples[] tabela; // array que representa a tabela hash
    private BiFunction<Integer, Integer, Long> funcao_hash; // função hash passada como parâmetro
    int elementos = 0; // quantidade de elementos na tabela
    long colisoes = 0; // quantidade de colisões ocorridas

    // construtor da tabela hash, recebe o tamanho inicial e a função hash
    public TabelaHashLinear(int tamanho_inicial, BiFunction<Integer, Integer, Long> funcao_hash) {
        this.tamanho = tamanho_inicial;
        this.tabela = new NoHashSimples[tamanho_inicial];
        this.funcao_hash = funcao_hash;
    }

    //Função de busca um elemento na tabela hash, retorna o registro ou nulo se não encontrado
    Registro buscar(int chave) {
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);
        int posicao_inicio = posicao_array;

        while (this.tabela[posicao_array] != null) {
            if(!this.tabela[posicao_array].removido && this.tabela[posicao_array].chave == chave) return this.tabela[posicao_array].valor;

            posicao_array++;
            if(posicao_array == this.tamanho) posicao_array = 0;
            if(posicao_inicio == posicao_array) break;
        }

        return null;
    }


    //Função de inserção de um elemento na tabela hash, retorna falso se não for possível inserir (tabela cheia)
    boolean inserir(int chave, Registro valor) {
        if (this.elementos == this.tamanho) return false;

        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        if (this.tabela[posicao_array] == null) this.tabela[posicao_array] = new NoHashSimples(chave, valor);
        else {
            int primeiro_deletado = -1;
            int posicao_inicio = posicao_array;
            while (this.tabela[posicao_array] != null) {
                colisoes++;
                if(this.tabela[posicao_array].removido) {
                    if (primeiro_deletado == -1) primeiro_deletado = posicao_array;
                } else if(this.tabela[posicao_array].chave == chave) {
                    this.tabela[posicao_array].valor = valor;
                    return true;
                }
                posicao_array++;
                if(posicao_array == this.tamanho) posicao_array = 0;
                if(posicao_inicio == posicao_array) {
                    if(primeiro_deletado == -1) return false;
                    break;
                };
            }
            this.tabela[primeiro_deletado != -1 ? primeiro_deletado : posicao_array] = new NoHashSimples(chave, valor);
        }

        this.elementos++;
        return true;
    }

    //Função para calcular gaps na tabela hash, retorna um array com o menor, maior e média dos gaps
    int[] gap() {
        int maior_gap = 0;
        int menor_gap = Integer.MAX_VALUE;
        int media_gap = 0;
        int qtd_gaps = 0;

        int valor_atual = 1;
        boolean iniciou = false;
        for (int i = 0; i < this.tamanho; i++) {
            if (!iniciou && this.tabela[i] == null) {
                iniciou = true;
                valor_atual = 1;
            } else if(iniciou && this.tabela[i] != null) {
                iniciou = false;
                if (valor_atual < menor_gap) menor_gap = valor_atual;
                if(valor_atual > maior_gap) maior_gap = valor_atual;
                media_gap += valor_atual;
                qtd_gaps++;
            } else if(iniciou && this.tabela[i] == null) valor_atual++;
        }

        media_gap /= qtd_gaps == 0 ? 1 : qtd_gaps;

        return new int[]{menor_gap == Integer.MAX_VALUE ? 0 : menor_gap, maior_gap, media_gap};
    }
}

// classe que representa um nó na tabela hash
class NoHashSimples {
    int chave; // chave do nó
    Registro valor; // valor do nó
    boolean removido; // flag para indicar se o nó foi removido
    // construtor do nó
    NoHashSimples(int chave, Registro valor) {
        this.chave = chave;
        this.valor = valor;
        this.removido = false;
    }
}