package main;

public class RedBlackTree {
    private Node root;

    public RedBlackTree() {
        this.root = null;
    }

    private void rotateToLeft(Node current){
        Node temp = current.getRight();
        current.setRight(temp.getLeft());

        if(current.getParent() != null){
            temp.getLeft().setParent(current);
        }

        temp.setParent(current.getParent());
        if(current.getParent() == null){
            this.root = temp;
        } else if (current == current.getParent().getLeft()){
            current.getParent().setLeft(temp);
        } else{
            current.getParent().setRight(temp);
        }

        temp.setLeft(current);
        current.setParent(temp);
    }

    private void rotateToRight(Node current){
        Node temp = current.getLeft();
        current.setLeft(temp.getRight());

        if(temp.getRight()!=null){
            temp.getRight().setParent(current);
        }

        temp.setParent(current.getParent());

        if(current.getParent() == null){
            this.root = temp;
        }else if (current == current.getParent().getRight()){
            current.getParent().setRight(temp);
        }else {
            current.getParent().setLeft(temp);
        }

        temp.setRight(current);
        current.setParent(temp);
    }

    public void add(char key){
        Node node = new Node(key);
        node.setParent(null);
        node.setLeft(null);
        node.setRight(null);
        node.setRed(true);

        Node y = null;
        Node x = this.root;

        while(x != null){
            y = x;
            if(node.getKey() < x.getKey()){
                x = x.getLeft();
            }else {
                x = x.getRight();
            }

            node.setParent(y);
            if(y == null){
                root = node;
            } else if (node.getKey() < y.getKey()){
                y.setLeft(node);
            } else{
                y.setRight(node);
            }

            if(node.getParent() == null){
                node.setRed(false);
                return;
            }

            if(node.getParent().getParent() == null){
                return;
            }
        }
    }

    private void fixInsert(Node k){
        Node u;

        while(k.getParent().isRed()){
            if(k.getParent() == k.getParent().getParent().getRight()){
                u = k.getParent().getParent().getLeft();
                if (u.isRed()){
                    u.setRed(false);
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getLeft()){
                        k = k.getParent();
                        rotateToRight(k);
                    }
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    rotateToLeft(k.getParent().getParent());
                }
            } else{
                u = k.getParent().getParent().getRight();

                if(u.isRed()){
                    u.setRed(false);
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    k = k.getParent().getParent();
                } else {
                    if (k == k.getParent().getRight()){
                        k = k.getParent();
                        rotateToLeft(k);
                    }
                    k.getParent().setRed(false);
                    k.getParent().getParent().setRed(true);
                    rotateToRight(k.getParent().getParent());
                }
            }

            if (k == root){
                break;
            }
        }

        root.setRed(false);
    }
}
