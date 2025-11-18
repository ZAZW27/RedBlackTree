package main;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        RedBlackTree rdt = new RedBlackTree();
        Orders orders = new Orders();
        TreeVisualizer viz = new TreeVisualizer();

        boolean running = true;
        showMenu();
        while (running) {
            System.out.print("==== Choose: ");
            int choice;
            try{
                choice = scanner.nextInt();
            } catch (Exception e){
                scanner.nextLine();
                choice = -1;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter number: ");
                    int num = scanner.nextInt();
                    rdt.add(num);
                    System.out.println(num + " added.");
                    break;

                case 2:
                    rdt = new RedBlackTree();
                    System.out.print("How many random numbers to generate? ");
                    int count = scanner.nextInt();
                    System.out.print("Adding: ");
                    for (int i = 0; i < count; i++) {
                        int val = random.nextInt(100) + 1;
                        System.out.print(val + " ");
                        rdt.add(val);
                    }
                    System.out.println("\nTree regenerated.");
                    break;

                case 3:
                    if (rdt.getRoot() == null) {
                        System.out.println("Tree is empty.");
                    } else {
                        System.out.println("\n--- Visual Representation ---");
                        viz.printCompact(rdt.getRoot());

                        System.out.println("\nPreorder Traversal:");
                        orders.preorder(rdt.getRoot());

                        System.out.println("\nInOrder Traversal:");
                        orders.inorder(rdt.getRoot());

                        System.out.println("\nPostorder Traversal:");
                        orders.postorder(rdt.getRoot());
                        System.out.println();
                    }
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please retry");
                    showMenu();
            }
        }
        scanner.close();
    }

    private static void showMenu(){
        System.out.println("\n=== Red Black Tree Menu ===");
        System.out.println("1. Tambah data");
        System.out.println("2. Reset dan generate random");
        System.out.println("3. Show Tree & Traversals");
        System.out.println("4. Exit");
    }
}