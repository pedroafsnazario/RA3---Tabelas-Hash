import java.util.function.BiFunction;
import java.util.function.Function;

public class TabelaHashBTree {
    private int tamanho;
    BTree[] tabela;
    BiFunction<Integer, Integer, Long> funcao_hash;
    long colisoes = 0;
    public TabelaHashBTree(int tamanho_inicial, BiFunction<Integer, Integer, Long> funcao_hash) {
        this.tamanho = tamanho_inicial;
        this.tabela = new BTree[tamanho_inicial];
        this.funcao_hash = funcao_hash;
    }

    Registro buscar(int chave) {
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        if (this.tabela[posicao_array] == null) return null;

        BTree arvore = this.tabela[posicao_array];
        BTNode resultado = arvore.search(chave);
        if(resultado != null) return resultado.value;

        return null;
    }

    void inserir(int chave, Registro valor) {
        int posicao_array = (int)(this.funcao_hash.apply(chave, this.tamanho) % this.tamanho);

        if (this.tabela[posicao_array] == null) this.tabela[posicao_array] = new BTree(chave, valor);
        else colisoes += this.tabela[posicao_array].insert(chave, valor);
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

    int[] alturas() {
        int maior_altura = 0;
        int menor_altura = Integer.MAX_VALUE;
        int media_altura = 0;
        int qtd = 0;

        for (int i = 0; i < this.tamanho; i++) {
            if(this.tabela[i] != null) {
                if(this.tabela[i].altura > maior_altura) maior_altura = this.tabela[i].altura;
                if(this.tabela[i].altura < menor_altura) menor_altura = this.tabela[i].altura;
                media_altura += this.tabela[i].altura;
                qtd++;
            }
        }

        media_altura /= qtd == 0 ? 1 : qtd;

        return new int[]{menor_altura == Integer.MAX_VALUE ? 0 : menor_altura, maior_altura, media_altura};
    }
}
