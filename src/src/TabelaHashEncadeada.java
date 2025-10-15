import java.util.function.BiFunction;
import java.util.function.Function;

class TabelaHashEncadeada {
    private int tamanho;
    private NoHashEncadeado[] tabela;
    BiFunction<Integer, Integer, Long> funcao_hash;
    long colisoes = 0;
    public TabelaHashEncadeada(int tamanho_inicial, BiFunction<Integer, Integer, Long> funcao_hash) {
        this.tamanho = tamanho_inicial;
        this.tabela = new NoHashEncadeado[tamanho_inicial];
        this.funcao_hash = funcao_hash;
    }

    Registro buscar(int chave) {
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        if (this.tabela[posicao_array] == null) return null;

        NoHashEncadeado atual = this.tabela[posicao_array];
        while (atual != null) {
            if (atual.chave == chave) return atual.valor;
            atual = atual.proximo;
        }

        return null;
    }

    void inserir(int chave, Registro valor) {
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        if (this.tabela[posicao_array] == null) this.tabela[posicao_array] = new NoHashEncadeado(chave, valor);
        else {
            colisoes++;
            NoHashEncadeado novo = new NoHashEncadeado(chave, valor);
            novo.proximo = this.tabela[posicao_array];
            this.tabela[posicao_array] = novo;
        }
    }

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

    int[] maiores_listas() {
        int[] tamanho_listas = new int[]{0,0,0};
        for (int i = 0; i < this.tamanho; i++) {
            NoHashEncadeado atual = this.tabela[i];
            int tamanho = 0;
            while (atual != null) {
                tamanho++;
                atual = atual.proximo;
            }
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
        return tamanho_listas;
    }
}

class NoHashEncadeado {
    int chave;
    Registro valor;
    NoHashEncadeado proximo;
    public NoHashEncadeado(int chave, Registro valor) {
        this.chave = chave;
        this.valor = valor;
    }
}