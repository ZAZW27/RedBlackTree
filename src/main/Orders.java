package main;

public class Orders {
    private void preOrderHelper(Node node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrderHelper(node.getLeft());
            preOrderHelper(node.getRight());
        }
    }

    public void preorder(Node root) {
        preOrderHelper(root);
    }

    private void inOrderHelper(Node node) {
        if (node != null) {
            inOrderHelper(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrderHelper(node.getRight());
        }
    }

    public void inorder(Node root) {
        inOrderHelper(root);
    }

    private void postOrderHelper(Node node) {
        if (node != null) {
            postOrderHelper(node.getLeft());
            postOrderHelper(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    public void postorder(Node root) {
        postOrderHelper(root);
    }
}