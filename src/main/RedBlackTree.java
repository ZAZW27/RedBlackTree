package main;

public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root;

    public RedBlackTree() {
        this.root = null;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    private void rotateLeft(Node<T> node) {
        Node<T> rightChild = node.getRight();
        node.setRight(rightChild.getLeft());

        if (rightChild.getLeft() != null) {
            rightChild.getLeft().setParent(node);
        }

        rightChild.setParent(node.getParent());

        if (node.getParent() == null) {
            this.root = rightChild;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(rightChild);
        } else {
            node.getParent().setRight(rightChild);
        }

        rightChild.setLeft(node);
        node.setParent(rightChild);
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());

        if (leftChild.getRight() != null) {
            leftChild.getRight().setParent(node);
        }

        leftChild.setParent(node.getParent());

        if (node.getParent() == null) {
            this.root = leftChild;
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(leftChild);
        } else {
            node.getParent().setLeft(leftChild);
        }

        leftChild.setRight(node);
        node.setParent(leftChild);
    }

    public void add(T key) {
        Node<T> newNode = new Node<>(key);
        newNode.setRed(true);

        if (this.root == null) {
            this.root = newNode;
            newNode.setRed(false);
            return;
        }

        Node<T> parent = null;
        Node<T> current = this.root;

        while (current != null) {
            parent = current;
            if (newNode.getKey().compareTo(current.getKey()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        newNode.setParent(parent);
        if (newNode.getKey().compareTo(parent.getKey()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        fixInsert(newNode);
    }

    private void fixInsert(Node<T> node) {
        while (node.getParent() != null && node.getParent().isRed()) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                Node<T> uncle = node.getParent().getParent().getRight();

                if (uncle != null && uncle.isRed()) {
                    node.getParent().setRed(false);
                    uncle.setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    rotateRight(node.getParent().getParent());
                }
            } else {
                Node<T> uncle = node.getParent().getParent().getLeft();

                if (uncle != null && uncle.isRed()) {
                    node.getParent().setRed(false);
                    uncle.setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rotateRight(node);
                    }
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    rotateLeft(node.getParent().getParent());
                }
            }

            if (node == this.root) {
                break;
            }
        }
        this.root.setRed(false);
    }
}