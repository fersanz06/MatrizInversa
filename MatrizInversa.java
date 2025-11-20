import java.util.Scanner;

public class MatrizInversa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tamaño de la matriz cuadrada: ");
        int n = scanner.nextInt();

        double[][] matriz = new double[n][n];

        System.out.println("Ingresa los valores:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Matriz:");
        imprimir(matriz);

        double det = determinante(matriz);
        System.out.println("Determinante calculado: " + det);

        double[][] inv = matrizInversa(matriz);

        scanner.close();
    }

    public static void imprimir(double[][] m) {
        for (double[] fila : m) {
            for (double v : fila) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    public static double determinante(double[][] m) {
        if (m.length == 2) {
            return m[0][0] * m[1][1] - m[0][1] * m[1][0];
        }
        return 0;
    }

    public static double[][] matrizInversa(double[][] m) {
        return new double[m.length][m.length];
    }
}