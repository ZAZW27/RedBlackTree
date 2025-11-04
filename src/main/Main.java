package main;

public class Main {
    public static void main(String[] args) {
        RedBlackTree rdt = new RedBlackTree();
        rdt.add('a');
        rdt.add('c');
        rdt.add('d');
        rdt.add('r');
        rdt.add('z');
        rdt.add('p');
        rdt.add('l');
        rdt.add('k');

        Orders orders = new Orders();

        System.out.println("Inorder Traversal:");
        orders.inorder(rdt.getRoot());

        System.out.println("\nPreorder Traversal:");
        orders.preorder(rdt.getRoot());

        System.out.println("\nPostorder Traversal:");
        orders.postorder(rdt.getRoot());
    }
}