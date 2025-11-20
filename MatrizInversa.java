import java.util.Scanner;

public class MatrizInversa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tamaño de la matriz: ");
        int n = scanner.nextInt();

        double[][] matriz = new double[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matriz[i][j] = scanner.nextDouble();

        System.out.println("\nMatriz:");
        imprimir(matriz);

        double det = determinante(matriz);
        System.out.println("\nDeterminante: " + det);

        double[][] inv = matrizInversa(matriz);
        System.out.println("\nInversa (incorrecta):");
        imprimir(inv);

        scanner.close();
    }

    public static void imprimir(double[][] m) {
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
                    determinante(sub(m, 0, col));
        }
        return det;
    }

    public static double[][] sub(double[][] m, int fila, int columna) {
        int n = m.length;
        double[][] r = new double[n - 1][n - 1];
        int rr = 0;

        for (int i = 0; i < n; i++) {
            if (i == fila) continue;
            int cc = 0;
            for (int j = 0; j < n; j++) {
                if (j == columna) continue;
                r[rr][cc++] = m[i][j];
            }
            rr++;
        }
        return r;
    }

    public static double[][] matrizInversa(double[][] m) {
        int n = m.length;
        double det = determinante(m);

        double[][] adj = new double[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                adj[j][i] = Math.pow(-1, i + j) * determinante(sub(m, i, j));

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                adj[i][j] = (int)(adj[i][j] / det);

        return adj;
    }
}