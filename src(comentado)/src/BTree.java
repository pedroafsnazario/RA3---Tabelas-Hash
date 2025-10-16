//Implementação de uma árvore binária de busca
//Utilizada para a TabelaHashBtree

class BTree {
    // construtor vazio
    BTree(int key, Registro value) {
        this.insert(key, value);
    }
    int altura = 0; // altura da árvore
    int quantidade = 0; // quantidade de nós na árvore

    // raiz da árvore (iniciada como nula)
    private BTNode root = null;

    // insere um novo nó na árvore e retorna a nova altura da árvore
    public int insert(int key, Registro value) {
        BTNode newNode = new BTNode(key, value);
        int novaAltura = 0;
        if (root == null) {
            root = newNode;
            altura++;
        } else {
            BTNode current = root;
            while (true) {
                novaAltura++;
                if (current.key > key) {
                    if (current.left == null) {
                        current.left = newNode;
                        break;
                    } else current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = newNode;
                        break;
                    } else current = current.right;
                }
            }
            if(novaAltura > altura) altura = novaAltura;
        }
        quantidade++;
        return novaAltura;
    }

    // remove um nó da árvore e retorna o nó removido (ou nulo se não encontrado)
    public BTNode remove(int key) {
        BTSearchResult found = searchWithPrevious(key);
        if (found == null) return null;
        BTNode target = found.node;
        BTNode previous = found.previous;
        if (target.right == null && target.left == null) {
            if(previous != null) {
                if (previous.left == target) previous.left = null;
                else previous.right = null;
            } else this.root = null;
        } else if(target.right == null ^ target.left == null) {
            if(previous != null) {
                if (previous.left == target) previous.left = target.right != null ?
                        target.right : target.left;
                else previous.right = target.right != null ? target.right :
                        target.left;
            } else {
                this.root = target.right != null ? target.right : target.left;
            }
        } else {
            BTSearchResult mostRight = getMostRight(target.left, target);
            BTNode pred = mostRight.node;
            BTNode pPred = mostRight.previous;
            if (pPred != target) {
                pPred.right = pred.left;
                pred.left = target.left;
            }
            pred.right = target.right;
            if(previous != null) {
                if (previous.left == target) previous.left = pred;
                else previous.right = pred;
            } else {
                this.root = pred;
            }
        }
        return target;
    }

    // busca um nó na árvore pelo seu valor e retorna o nó encontrado (ou nulo se não encontrado)
    public BTNode search(int key) {
        BTSearchResult r = this.searchWithPrevious(key, this.root, null);
        return r == null ? null : r.node;
    }
    // busca um nó na árvore pelo seu valor e retorna o nó encontrado e seu pai (ou nulo se não encontrado) parametro usado é a chave
    public BTSearchResult searchWithPrevious(int key) {
        return this.searchWithPrevious(key, this.root, null);
    }
    // busca um nó na árvore pelo seu valor e retorna o nó encontrado e seu pai (ou nulo se não encontrado), parâmetros usados são a chave, o nó atual e o nó anterior
    public BTSearchResult searchWithPrevious(int key, BTNode current, BTNode previous) {
        if (current == null) return null;
        if (current.key == key) return new BTSearchResult(current, previous);
        if (current.key > key) return searchWithPrevious(key, current.left,
                current);
        else return searchWithPrevious(key, current.right, current);
    }

    // métodos auxiliares para encontrar o nó mais à direita ou mais à esquerda
    public BTSearchResult getMostRight(){
        return this.getMostRight(this.root, null);
    }
    public BTSearchResult getMostRight(BTNode current, BTNode parent) {
        if (current.right == null) return new BTSearchResult(current, parent);
        else return getMostRight(current.right, current);
    }
    public BTSearchResult getMostLeft(){
        return this.getMostLeft(this.root, null);
    }
    public BTSearchResult getMostLeft(BTNode current, BTNode parent) {
        if (current.left == null) return new BTSearchResult(current, parent);
        else return getMostLeft(current.left, current);
    }
}

// classe que representa um nó da árvore
class BTNode {
    Registro value;
    int key;
    BTNode left;
    BTNode right;

    // construtor do nó 
    BTNode(int key, Registro value) {
        this.key = key;
        this.value = value;
    }
}

// classe que representa o resultado de uma busca na árvore, contendo o nó encontrado e seu pai
class BTSearchResult {
    BTNode node;
    BTNode previous;
    // construtor do resultado da busca
    BTSearchResult(BTNode node, BTNode previous) {
        this.node = node;
        this.previous = previous;
    }
}
