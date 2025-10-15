import java.util.Random;
import java.util.function.BiFunction;

public class HashImplementation {
    public static void main(String[] args) {
        System.out.println("Memória base: " + memory() + " bytes");

        int N_100K = 100_000;
        Registro[] valores_100K = FuncoesHash.generate_values(N_100K);

        int N_1M = 1_000_000;
        Registro[] valores_1M = FuncoesHash.generate_values(N_1M);

        int N_10M = 10_000_000;
        Registro[] valores_10M = FuncoesHash.generate_values(N_10M);

        System.out.println("Memória de ocupação fixa: " + memory() + " bytes");
        System.out.println("\n\n");

        System.out.println("Hash encadeada:");
        hash_encadeada(N_100K, valores_100K, N_1M, valores_1M, N_10M, valores_10M);

        System.out.println("\n\nHash BTree:");
        hash_btree(N_100K, valores_100K, N_1M, valores_1M, N_10M, valores_10M);

        System.out.println("\n\nHash dupla:");
        hash_dupla(N_100K, valores_100K, N_1M, valores_1M, N_10M, valores_10M);

        System.out.println("\n\nHash linear:");
        hash_linear(N_100K, valores_100K, N_1M, valores_1M, N_10M, valores_10M);
}





    static int primeiro_tamanho = 10_007;
    static int segundo_tamanho = 100_103;
    static int terceiro_tamanho = 1_001_041;

    static int primo_100K = 100_003;
    static int primo_1M = 1_000_003;
    static int primo_10M = 10_000_019;
    static void hash_linear(int N_100K, Registro[] valores_100K, int N_1M, Registro[] valores_1M, int N_10M, Registro[] valores_10M) {
        // 100K VALORES
        testar_hash_linear("HASH LINEAR - 100K VALORES - FUNÇÃO 1", valores_100K, N_100K, primo_100K, FuncoesHash::funcao1);
        testar_hash_linear("HASH LINEAR - 100K VALORES - FUNÇÃO 2", valores_100K, N_100K, primo_100K, FuncoesHash::funcao2);
        testar_hash_linear("HASH LINEAR - 100K VALORES - FUNÇÃO 3", valores_100K, N_100K, primo_100K, FuncoesHash::funcao3);

        // 1M VALORES
        testar_hash_linear("HASH LINEAR - 1M VALORES - FUNÇÃO 1", valores_1M, N_1M, primo_1M, FuncoesHash::funcao1);
        testar_hash_linear("HASH LINEAR - 1M VALORES - FUNÇÃO 2", valores_1M, N_1M, primo_1M, FuncoesHash::funcao2);
        testar_hash_linear("HASH LINEAR - 1M VALORES - FUNÇÃO 3", valores_1M, N_1M, primo_1M, FuncoesHash::funcao3);

        // 1M VALORES
        testar_hash_linear("HASH LINEAR - 10M VALORES - FUNÇÃO 1", valores_10M, N_10M, primo_10M, FuncoesHash::funcao1);
        testar_hash_linear("HASH LINEAR - 10M VALORES - FUNÇÃO 2", valores_10M, N_10M, primo_10M, FuncoesHash::funcao2);
        testar_hash_linear("HASH LINEAR - 10M VALORES - FUNÇÃO 3", valores_10M, N_10M, primo_10M, FuncoesHash::funcao3);
    }
    static void testar_hash_linear(String label, Registro[] valores, int tamanho, int tamanho_tabela, BiFunction<Integer, Integer, Long> funcao_hash) {
        System.out.println(label);

        iniciar("Tempo de inserção");
        TabelaHashLinear tabela = new TabelaHashLinear(tamanho_tabela, funcao_hash);
        for (int i = 0; i < tamanho; i++) {
            tabela.inserir(valores[i].codigo, valores[i]);
        }
        finalizar();

        System.out.println("Memória utilizada: " + memory() + " bytes");

        iniciar("Tempo de pesquisa");
        for (int i = 0; i < tamanho; i++) {
            Registro v = tabela.buscar(valores[i].codigo);
        }
        finalizar();

        int[] gaps = tabela.gap();
        System.out.println("Menor Gap: " + gaps[0]);
        System.out.println("Maior Gap: " + gaps[1]);
        System.out.println("Gap Média: " + gaps[2]);

        System.out.println("Total de Colisões: " + tabela.colisoes);

        System.out.println();
    }







    static void hash_dupla(int N_100K, Registro[] valores_100K, int N_1M, Registro[] valores_1M, int N_10M, Registro[] valores_10M) {
        // 100K VALORES
        testar_hash_dupla("HASH DUPLA - 100K VALORES - FUNÇÃO 1 & FUNÇÃO SONDA 1", valores_100K, N_100K, primo_100K, FuncoesHash::funcao1, FuncoesHash::funcao1_sonda100K);
        testar_hash_dupla("HASH DUPLA - 100K VALORES - FUNÇÃO 1 & FUNÇÃO SONDA 2", valores_100K, N_100K, primo_100K, FuncoesHash::funcao1, FuncoesHash::funcao2_sonda100K);

        testar_hash_dupla("HASH DUPLA - 100K VALORES - FUNÇÃO 2 & FUNÇÃO SONDA 1", valores_100K, N_100K, primo_100K, FuncoesHash::funcao2, FuncoesHash::funcao1_sonda100K);
        testar_hash_dupla("HASH DUPLA - 100K VALORES - FUNÇÃO 2 & FUNÇÃO SONDA 2", valores_100K, N_100K, primo_100K, FuncoesHash::funcao2, FuncoesHash::funcao2_sonda100K);

        testar_hash_dupla("HASH DUPLA - 100K VALORES - FUNÇÃO 3 & FUNÇÃO SONDA 1", valores_100K, N_100K, primo_100K, FuncoesHash::funcao3, FuncoesHash::funcao1_sonda100K);
        testar_hash_dupla("HASH DUPLA - 100K VALORES - FUNÇÃO 3 & FUNÇÃO SONDA 2", valores_100K, N_100K, primo_100K, FuncoesHash::funcao3, FuncoesHash::funcao2_sonda100K);

        // 1M VALORES
        testar_hash_dupla("HASH DUPLA - 1M VALORES - FUNÇÃO 1 & FUNÇÃO SONDA 1", valores_1M, N_1M, primo_1M, FuncoesHash::funcao1, FuncoesHash::funcao1_sonda1M);
        testar_hash_dupla("HASH DUPLA - 1M VALORES - FUNÇÃO 1 & FUNÇÃO SONDA 2", valores_1M, N_1M, primo_1M, FuncoesHash::funcao1, FuncoesHash::funcao2_sonda1M);

        testar_hash_dupla("HASH DUPLA - 1M VALORES - FUNÇÃO 2 & FUNÇÃO SONDA 1", valores_1M, N_1M, primo_1M, FuncoesHash::funcao2, FuncoesHash::funcao1_sonda1M);
        testar_hash_dupla("HASH DUPLA - 1M VALORES - FUNÇÃO 2 & FUNÇÃO SONDA 2", valores_1M, N_1M, primo_1M, FuncoesHash::funcao2, FuncoesHash::funcao2_sonda1M);

        testar_hash_dupla("HASH DUPLA - 1M VALORES - FUNÇÃO 3 & FUNÇÃO SONDA 1", valores_1M, N_1M, primo_1M, FuncoesHash::funcao3, FuncoesHash::funcao1_sonda1M);
        testar_hash_dupla("HASH DUPLA - 1M VALORES - FUNÇÃO 3 & FUNÇÃO SONDA 2", valores_1M, N_1M, primo_1M, FuncoesHash::funcao3, FuncoesHash::funcao2_sonda1M);

        // 1M VALORES
        testar_hash_dupla("HASH DUPLA - 10M VALORES - FUNÇÃO 1 & FUNÇÃO SONDA 1", valores_10M, N_10M, primo_10M, FuncoesHash::funcao1, FuncoesHash::funcao1_sonda10M);
        testar_hash_dupla("HASH DUPLA - 10M VALORES - FUNÇÃO 1 & FUNÇÃO SONDA 2", valores_10M, N_10M, primo_10M, FuncoesHash::funcao1, FuncoesHash::funcao2_sonda10M);

        testar_hash_dupla("HASH DUPLA - 10M VALORES - FUNÇÃO 2 & FUNÇÃO SONDA 1", valores_10M, N_10M, primo_10M, FuncoesHash::funcao2, FuncoesHash::funcao1_sonda10M);
        testar_hash_dupla("HASH DUPLA - 10M VALORES - FUNÇÃO 2 & FUNÇÃO SONDA 2", valores_10M, N_10M, primo_10M, FuncoesHash::funcao2, FuncoesHash::funcao2_sonda10M);

        testar_hash_dupla("HASH DUPLA - 10M VALORES - FUNÇÃO 3 & FUNÇÃO SONDA 1", valores_10M, N_10M, primo_10M, FuncoesHash::funcao3, FuncoesHash::funcao1_sonda10M);
        testar_hash_dupla("HASH DUPLA - 10M VALORES - FUNÇÃO 3 & FUNÇÃO SONDA 2", valores_10M, N_10M, primo_10M, FuncoesHash::funcao3, FuncoesHash::funcao2_sonda10M);
    }
    static void testar_hash_dupla(String label, Registro[] valores, int tamanho, int tamanho_tabela, BiFunction<Integer, Integer, Long> funcao_hash, BiFunction<Integer, Integer, Long> funcao_sonda) {
        System.out.println(label);

        iniciar("Tempo de inserção");
        TabelaHashDupla tabela = new TabelaHashDupla(tamanho_tabela, funcao_hash, funcao_sonda);
        for (int i = 0; i < tamanho; i++) {
            tabela.inserir(valores[i].codigo, valores[i]);
        }
        finalizar();

        System.out.println("Memória utilizada: " + memory() + " bytes");

        iniciar("Tempo de pesquisa");
        for (int i = 0; i < tamanho; i++) {
            Registro v = tabela.buscar(valores[i].codigo);
        }
        finalizar();

        int[] gaps = tabela.gap();
        System.out.println("Menor Gap: " + gaps[0]);
        System.out.println("Maior Gap: " + gaps[1]);
        System.out.println("Gap Média: " + gaps[2]);

        System.out.println("Total de Colisões: " + tabela.colisoes);

        System.out.println();
    }








    static void hash_encadeada(int N_100K, Registro[] valores_100K, int N_1M, Registro[] valores_1M, int N_10M, Registro[] valores_10M) {
        // 100K VALORES
        testar_hash_encadeada("HASH ENCADEADA 10K - 100K VALORES - FUNÇÃO 1", valores_100K, N_100K, primeiro_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 10K - 100K VALORES - FUNÇÃO 2", valores_100K, N_100K, primeiro_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 10K - 100K VALORES - FUNÇÃO 3", valores_100K, N_100K, primeiro_tamanho, FuncoesHash::funcao3);

        testar_hash_encadeada("HASH ENCADEADA 100K - 100K VALORES - FUNÇÃO 1", valores_100K, N_100K, segundo_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 100K - 100K VALORES - FUNÇÃO 2", valores_100K, N_100K, segundo_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 100K - 100K VALORES - FUNÇÃO 3", valores_100K, N_100K, segundo_tamanho, FuncoesHash::funcao3);

        testar_hash_encadeada("HASH ENCADEADA 1M - 100K VALORES - FUNÇÃO 1", valores_100K, N_100K, terceiro_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 1M - 100K VALORES - FUNÇÃO 2", valores_100K, N_100K, terceiro_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 1M - 100K VALORES - FUNÇÃO 3", valores_100K, N_100K, terceiro_tamanho, FuncoesHash::funcao3);

        // 1M VALORES
        testar_hash_encadeada("HASH ENCADEADA 10K - 1M VALORES - FUNÇÃO 1", valores_1M, N_1M, primeiro_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 10K - 1M VALORES - FUNÇÃO 2", valores_1M, N_1M, primeiro_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 10K - 1M VALORES - FUNÇÃO 3", valores_1M, N_1M, primeiro_tamanho, FuncoesHash::funcao3);

        testar_hash_encadeada("HASH ENCADEADA 100K - 1M VALORES - FUNÇÃO 1", valores_1M, N_1M, segundo_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 100K - 1M VALORES - FUNÇÃO 2", valores_1M, N_1M, segundo_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 100K - 1M VALORES - FUNÇÃO 3", valores_1M, N_1M, segundo_tamanho, FuncoesHash::funcao3);

        testar_hash_encadeada("HASH ENCADEADA 1M - 1M VALORES - FUNÇÃO 1", valores_1M, N_1M, terceiro_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 1M - 1M VALORES - FUNÇÃO 2", valores_1M, N_1M, terceiro_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 1M - 1M VALORES - FUNÇÃO 3", valores_1M, N_1M, terceiro_tamanho, FuncoesHash::funcao3);


        // 10M VALORES
        testar_hash_encadeada("HASH ENCADEADA 10K - 10M VALORES - FUNÇÃO 1", valores_10M, N_10M, primeiro_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 10K - 10M VALORES - FUNÇÃO 2", valores_10M, N_10M, primeiro_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 10K - 10M VALORES - FUNÇÃO 3", valores_10M, N_10M, primeiro_tamanho, FuncoesHash::funcao3);

        testar_hash_encadeada("HASH ENCADEADA 100K - 10M VALORES - FUNÇÃO 1", valores_10M, N_10M, segundo_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 100K - 10M VALORES - FUNÇÃO 2", valores_10M, N_10M, segundo_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 100K - 10M VALORES - FUNÇÃO 3", valores_10M, N_10M, segundo_tamanho, FuncoesHash::funcao3);

        testar_hash_encadeada("HASH ENCADEADA 1M - 10M VALORES - FUNÇÃO 1", valores_10M, N_10M, terceiro_tamanho, FuncoesHash::funcao1);
        testar_hash_encadeada("HASH ENCADEADA 1M - 10M VALORES - FUNÇÃO 2", valores_10M, N_10M, terceiro_tamanho, FuncoesHash::funcao2);
        testar_hash_encadeada("HASH ENCADEADA 1M - 10M VALORES - FUNÇÃO 3", valores_10M, N_10M, terceiro_tamanho, FuncoesHash::funcao3);
    }
    static void testar_hash_encadeada(String label, Registro[] valores, int tamanho, int tamanho_tabela, BiFunction<Integer, Integer, Long> funcao_hash) {
        System.out.println(label);

        iniciar("Tempo de inserção");
        TabelaHashEncadeada tabela = new TabelaHashEncadeada(tamanho_tabela, funcao_hash);
        for (int i = 0; i < tamanho; i++) {
            tabela.inserir(valores[i].codigo, valores[i]);
        }
        finalizar();

        System.out.println("Memória utilizada: " + memory() + " bytes");

        iniciar("Tempo de pesquisa");
        for (int i = 0; i < tamanho; i++) {
            Registro v = tabela.buscar(valores[i].codigo);
        }
        finalizar();

        int[] gaps = tabela.gap();
        System.out.println("Menor Gap: " + gaps[0]);
        System.out.println("Maior Gap: " + gaps[1]);
        System.out.println("Gap Média: " + gaps[2]);

        int[] tamanhos = tabela.maiores_listas();
        System.out.println("Maiores tamanhos de listas: " + tamanhos[0] + " /  " + tamanhos[1] + " / " + tamanhos[2]);

        System.out.println("Total de Colisões: " + tabela.colisoes);

        System.out.println();
    }







    static void hash_btree(int N_100K, Registro[] valores_100K, int N_1M, Registro[] valores_1M, int N_10M, Registro[] valores_10M) {
        // 100K VALORES
        testar_hash_btree("BTREE HASH 10K - 100K VALORES - FUNÇÃO 1", valores_100K, N_100K, primeiro_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 10K - 100K VALORES - FUNÇÃO 2", valores_100K, N_100K, primeiro_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 10K - 100K VALORES - FUNÇÃO 3", valores_100K, N_100K, primeiro_tamanho, FuncoesHash::funcao3);

        testar_hash_btree("BTREE HASH 100K - 100K VALORES - FUNÇÃO 1", valores_100K, N_100K, segundo_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 100K - 100K VALORES - FUNÇÃO 2", valores_100K, N_100K, segundo_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 100K - 100K VALORES - FUNÇÃO 3", valores_100K, N_100K, segundo_tamanho, FuncoesHash::funcao3);

        testar_hash_btree("BTREE HASH 1M - 100K VALORES - FUNÇÃO 1", valores_100K, N_100K, terceiro_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 1M - 100K VALORES - FUNÇÃO 2", valores_100K, N_100K, terceiro_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 1M - 100K VALORES - FUNÇÃO 3", valores_100K, N_100K, terceiro_tamanho, FuncoesHash::funcao3);

        // 1M VALORES
        testar_hash_btree("BTREE HASH 10K - 1M VALORES - FUNÇÃO 1", valores_1M, N_1M, primeiro_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 10K - 1M VALORES - FUNÇÃO 2", valores_1M, N_1M, primeiro_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 10K - 1M VALORES - FUNÇÃO 3", valores_1M, N_1M, primeiro_tamanho, FuncoesHash::funcao3);

        testar_hash_btree("BTREE HASH 100K - 1M VALORES - FUNÇÃO 1", valores_1M, N_1M, segundo_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 100K - 1M VALORES - FUNÇÃO 2", valores_1M, N_1M, segundo_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 100K - 1M VALORES - FUNÇÃO 3", valores_1M, N_1M, segundo_tamanho, FuncoesHash::funcao3);

        testar_hash_btree("BTREE HASH 1M - 1M VALORES - FUNÇÃO 1", valores_1M, N_1M, terceiro_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 1M - 1M VALORES - FUNÇÃO 2", valores_1M, N_1M, terceiro_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 1M - 1M VALORES - FUNÇÃO 3", valores_1M, N_1M, terceiro_tamanho, FuncoesHash::funcao3);


        // 10M VALORES
        testar_hash_btree("BTREE HASH 10K - 10M VALORES - FUNÇÃO 1", valores_10M, N_10M, primeiro_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 10K - 10M VALORES - FUNÇÃO 2", valores_10M, N_10M, primeiro_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 10K - 10M VALORES - FUNÇÃO 3", valores_10M, N_10M, primeiro_tamanho, FuncoesHash::funcao3);

        testar_hash_btree("BTREE HASH 100K - 10M VALORES - FUNÇÃO 1", valores_10M, N_10M, segundo_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 100K - 10M VALORES - FUNÇÃO 2", valores_10M, N_10M, segundo_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 100K - 10M VALORES - FUNÇÃO 3", valores_10M, N_10M, segundo_tamanho, FuncoesHash::funcao3);

        testar_hash_btree("BTREE HASH 1M - 10M VALORES - FUNÇÃO 1", valores_10M, N_10M, terceiro_tamanho, FuncoesHash::funcao1);
        testar_hash_btree("BTREE HASH 1M - 10M VALORES - FUNÇÃO 2", valores_10M, N_10M, terceiro_tamanho, FuncoesHash::funcao2);
        testar_hash_btree("BTREE HASH 1M - 10M VALORES - FUNÇÃO 3", valores_10M, N_10M, terceiro_tamanho, FuncoesHash::funcao3);
    }
    static void testar_hash_btree(String label, Registro[] valores, int tamanho, int tamanho_tabela, BiFunction<Integer, Integer, Long> funcao_hash) {
        System.out.println(label);

        iniciar("Tempo de inserção");
        TabelaHashBTree tabela = new TabelaHashBTree(tamanho_tabela, funcao_hash);
        for (int i = 0; i < tamanho; i++) {
            tabela.inserir(valores[i].codigo, valores[i]);
        }
        finalizar();

        System.out.println("Memória utilizada: " + memory() + " bytes");

        iniciar("Tempo de pesquisa");
        for (int i = 0; i < tamanho; i++) {
            Registro v = tabela.buscar(valores[i].codigo);
        }
        finalizar();

        int[] gaps = tabela.gap();
        System.out.println("Menor Gap: " + gaps[0]);
        System.out.println("Maior Gap: " + gaps[1]);
        System.out.println("Gap Média: " + gaps[2]);

        int[] alturas = tabela.alturas();
        System.out.println("Menor Altura: " + alturas[0]);
        System.out.println("Maior Altura: " + alturas[1]);
        System.out.println("Altura Média: " + alturas[2]);

        System.out.println("Total de Colisões: " + tabela.colisoes);

        System.out.println();
    }






    static long tempoInicial;
    static String label;
    static void iniciar(String l) {
        tempoInicial = System.currentTimeMillis();
        label = l;
    }
    static void finalizar() {
        long tempoTotal = System.currentTimeMillis() - tempoInicial;
        System.out.println(label + " - Tempo de execução: " + tempoTotal + "ms");
    }

    static long memory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        return totalMemory - freeMemory;
    }
}

class FuncoesHash {
    static int min = 100000000;
    static int max = 999999999;

    static Registro[] generate_values(int n) {
        Random gerador = new Random(123);

        Registro[] values = new Registro[n];

        for (int i = 0; i < n; i++) {
            values[i] = new Registro((int)(gerador.nextFloat() * (max - min) + min));
        }

        return values;
    }

    static double potencia(double base, int expoente) {
        double resultado = 1.0;

        for (int i = 0; i < Math.abs(expoente); i++) {
            resultado *= base;
        }

        if (expoente < 0) {
            resultado = 1.0 / resultado;
        }

        return resultado;
    }

    static double raiz(double n) {
        double x = n;
        double y = 1;
        double precision = 1e-10; // how accurate the result should be

        while (Math.abs(x - y) > precision) {
            x = (x + y) / 2;
            y = n / x;
        }
        return x;
    }

    static long abs(long x) {
        return (x < 0) ? -x : x;
    }
    static long floor(double x) {
        long inteiro = (long) x;
        if (x < 0 && x != inteiro) inteiro -= 1;
        return inteiro;
    }

    static long funcao1(int chave, int M) {
        return chave;
    }

    static long funcao2(int chave, int M) {
        double A = 0.6180339887;
        double hash = chave * A;
        return (long)((hash - FuncoesHash.floor(hash)) * M);
    }

    static long funcao3(int chave, int M) {
        long soma = 0;
        long temp = chave;

        // soma dos dígitos
        while (temp > 0) {
            soma += temp % 10;
            temp /= 10;
        }

        // mistura bits
        long hash = (chave ^ (chave >>> 16)) + soma;
        return FuncoesHash.abs(hash);
    }

    static long funcao1_sonda100K(int chave, int M){
        return 99991 - (chave % 99991);
    }
    static long funcao1_sonda1M(int chave, int M){
        return 999983 - (chave % 999983);
    }
    static long funcao1_sonda10M(int chave, int M){
        return 9999991 - (chave % 9999991);
    }

    // MurmurHash3 fmix32 (finalizer) para misturar bem os bits
    private static long fmix32(int x) {
        x ^= x >>> 16;
        x *= 0x85ebca6b;
        x ^= x >>> 13;
        x *= 0xc2b2ae35;
        x ^= x >>> 16;
        return x;
    }
    static long funcao2_sonda100K(int chave, int M){
        // Mistura bem os bits
        long z = FuncoesHash.fmix32(chave);
        // garante passo no intervalo [1, M-1]
        long step = 1 + FuncoesHash.floor(FuncoesHash.abs(z) % (100_003 - 1));
        return step;
    }
    static long funcao2_sonda1M(int chave, int M){
        // Mistura bem os bits
        long z = FuncoesHash.fmix32(chave);
        // garante passo no intervalo [1, M-1]
        long step = 1 + FuncoesHash.floor(FuncoesHash.abs(z) % (1_000_003 - 1));
        return step;
    }
    static long funcao2_sonda10M(int chave, int M){
        // Mistura bem os bits
        long z = FuncoesHash.fmix32(chave);
        // garante passo no intervalo [1, M-1]
        long step = 1 + FuncoesHash.floor(FuncoesHash.abs(z) % (10_000_019 - 1));
        return step;
    }
}
