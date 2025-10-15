import java.util.function.BiFunction;
import java.util.function.Function;

class TabelaHashDupla {
    private int tamanho;
    private NoHashSimples[] tabela;
    private BiFunction<Integer, Integer, Long> funcao_hash;
    private BiFunction<Integer, Integer, Long> funcao_sonda;
    int elementos = 0;
    long colisoes = 0;
    public TabelaHashDupla(int tamanho_inicial, BiFunction<Integer, Integer, Long> funcao_hash, BiFunction<Integer, Integer, Long> funcao_sonda) {
        this.tamanho = tamanho_inicial;
        this.tabela = new NoHashSimples[tamanho_inicial];
        this.funcao_hash = funcao_hash;
        this.funcao_sonda = funcao_sonda;
    }

    int pegar_chave(int i, int chave) {
        int pos = (int)((this.funcao_hash.apply(chave, this.tamanho) + i * (this.funcao_sonda.apply(chave, this.tamanho) + 1)) % this.tamanho);
        return pos < 0 ? -pos : pos;
    }

    Registro buscar(int chave) {
        int i = 0;
        int posicao_array = pegar_chave(i, chave);

        while (this.tabela[posicao_array] != null) {
            if(!this.tabela[posicao_array].removido && this.tabela[posicao_array].chave == chave) return this.tabela[posicao_array].valor;

            posicao_array = this.pegar_chave(++i, chave);
            if(i >= this.tamanho) break;
        }

        return null;
    }

    boolean inserir(int chave, Registro valor) {
        if (this.elementos == this.tamanho) return false;

        int i = 0;
        int posicao_array = this.pegar_chave(i, chave);

        if (this.tabela[posicao_array] == null) this.tabela[posicao_array] = new NoHashSimples(chave, valor);
        else {
            int primeiro_deletado = -1;
            while (this.tabela[posicao_array] != null) {
                colisoes++;
                if(this.tabela[posicao_array].removido) {
                    if (primeiro_deletado == -1) primeiro_deletado = posicao_array;
                } else if(this.tabela[posicao_array].chave == chave) {
                    this.tabela[posicao_array].valor = valor;
                    return true;
                }
                posicao_array = this.pegar_chave(++i, chave);
                if(i >= this.tamanho) {
                    if(primeiro_deletado == -1) return false;
                    break;
                }
            }
            this.tabela[primeiro_deletado != -1 ? primeiro_deletado : posicao_array] = new NoHashSimples(chave, valor);
        }

        this.elementos++;
        return true;
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
}