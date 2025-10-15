class BTree {
    BTree(int key, Registro value) {
        this.insert(key, value);
    }
    int altura = 0;
    int quantidade = 0;

    private BTNode root = null;
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
    public BTNode search(int key) {
        BTSearchResult r = this.searchWithPrevious(key, this.root, null);
        return r == null ? null : r.node;
    }
    public BTSearchResult searchWithPrevious(int key) {
        return this.searchWithPrevious(key, this.root, null);
    }
    public BTSearchResult searchWithPrevious(int key, BTNode current, BTNode previous) {
        if (current == null) return null;
        if (current.key == key) return new BTSearchResult(current, previous);
        if (current.key > key) return searchWithPrevious(key, current.left,
                current);
        else return searchWithPrevious(key, current.right, current);
    }
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
class BTNode {
    Registro value;
    int key;
    BTNode left;
    BTNode right;
    BTNode(int key, Registro value) {
        this.key = key;
        this.value = value;
    }
}
class BTSearchResult {
    BTNode node;
    BTNode previous;
    BTSearchResult(BTNode node, BTNode previous) {
        this.node = node;
        this.previous = previous;
    }
}
