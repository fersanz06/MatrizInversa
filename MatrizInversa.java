import java.util.Scanner;

public class MatrizInversa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tamaño de la matriz: ");
        int n = scanner.nextInt();

        double[][] matriz = new double[n][n];

        System.out.println("Ingresa los valores:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("\nMatriz:");
        imprimirMatriz(matriz);

        double det = determinante(matriz);
        System.out.println("\nDeterminante: " + det);

        double[][] adj = adjunta(matriz);
        System.out.println("\nAdjunta:");
        imprimirMatriz(adj);

        scanner.close();
    }

    public static void imprimirMatriz(double[][] m) {
        for (double[] f : m) {
            for (double v : f) System.out.print(v + " ");
            System.out.println();
        }
    }

    public static double determinante(double[][] m) {
        int n = m.length;

        if (n == 1) return m[0][0];
        if (n == 2) return m[0][0] * m[1][1] - m[0][1] * m[1][0];

        double det = 0;
        for (int col = 0; col < n; col++) {
            det += Math.pow(-1, col) * m[0][col] *
                    determinante(submatriz(m, 0, col));
        }
        return det;
    }

    public static double[][] submatriz(double[][] m, int fila, int columna) {
        int n = m.length;
        double[][] sub = new double[n - 1][n - 1];
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (i == fila) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == columna) continue;
                sub[r][c] = m[i][j];
                c++;
            }
            r++;
        }
        return sub;
    }

    public static double[][] adjunta(double[][] m) {
        int n = m.length;
        double[][] adj = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adj[i][j] = Math.pow(-1, i + j) *
                        determinante(submatriz(m, i, j));
            }
        }
        return adj;
    }
}