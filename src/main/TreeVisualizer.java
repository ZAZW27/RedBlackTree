package main;

import java.util.*;

public class TreeVisualizer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK_OR_GREY = "\u001B[70m";

    public void printCompact(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        System.out.println("\n=== Red-Black Tree ===");
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String s = String.valueOf(n.getKey());

                    // Apply color based on node's redness
                    if (n.isRed()) {
                        s = ANSI_RED + s + ANSI_RESET;
                    } else {
                        s = ANSI_BLACK_OR_GREY + s + ANSI_RESET;
                    }

                    // We need to calculate widest based on the actual key length, not the colored string length
                    if (String.valueOf(n.getKey()).length() > widest) widest = String.valueOf(n.getKey()).length();

                    line.add(s);

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null && !line.get(j - 1).trim().isEmpty()) { // Check for actual content
                            c = (line.get(j) != null && !line.get(j).trim().isEmpty()) ? '┴' : '┘'; // Check for actual content
                        } else {
                            if (line.get(j) != null && !line.get(j).trim().isEmpty()) c = '└'; // Check for actual content
                        }
                    }
                    System.out.print(c);

                    if (line.get(j) == null || line.get(j).trim().isEmpty()) { // Check for actual content
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";

                int actualKeyLength = 0;
                if (line.get(j) != null && !line.get(j).trim().isEmpty()) {
                    String rawKey = line.get(j).replaceAll("\\u001B\\[[;\\d]*m", ""); // Remove ANSI codes
                    actualKeyLength = rawKey.length();
                }

                int gap1 = (int) Math.ceil(perpiece / 2f - actualKeyLength / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - actualKeyLength / 2f);

                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
        System.out.println();
    }
}