import java.util.Scanner;
import java.util.Arrays;

public class MatrizInversa {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Ingresa el tamaño de la matriz cuadrada (n x n): ");
            int n = scanner.nextInt();

            double[][] matriz = new double[n][n];

            System.out.println("\nIngresa los elementos de la matriz:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print("Elemento [" + i + "][" + j + "]: ");
                    matriz[i][j] = scanner.nextDouble();
                }
            }

            System.out.println("\nMatriz ingresada:");
            imprimirMatriz(matriz);

            double det = determinante(matriz);
            System.out.println("\nDeterminante: " + det);

            if (det == 0) {
                System.out.println("\nLa matriz NO tiene inversa.");
                return;
            }

            double[][] inversa = matrizInversa(matriz);

            System.out.println("\nMatriz inversa:");
            imprimirMatriz(inversa);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void imprimirMatriz(double[][] m) {
        for (double[] fila : m) {
            for (double val : fila) {
                System.out.printf("%10.6f ", val);
            }
            System.out.println();
        }
    }

    public static double determinante(double[][] matriz) {
        int n = matriz.length;

        if (n == 1) return matriz[0][0];
        if (n == 2) return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];

        double det = 0;

        for (int col = 0; col < n; col++) {
            det += Math.pow(-1, col) * matriz[0][col] *
                    determinante(submatriz(matriz, 0, col));
        }
        return det;
    }

    public static double[][] submatriz(double[][] matriz, int fila, int columna) {
        int n = matriz.length;
        double[][] sub = new double[n - 1][n - 1];

        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == fila) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == columna) continue;
                sub[r][c] = matriz[i][j];
                c++;
            }
            r++;
        }

        return sub;
    }

    public static double[][] matrizInversa(double[][] matriz) {
        int n = matriz.length;
        double det = determinante(matriz);

        double[][] adjunta = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjunta[j][i] = Math.pow(-1, i + j) * determinante(submatriz(matriz, i, j));
            }
        }

        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa[i][j] = adjunta[i][j] / det;
            }
        }

        return inversa;
    }
}